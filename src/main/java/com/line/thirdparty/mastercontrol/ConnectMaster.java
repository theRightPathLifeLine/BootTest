package com.line.thirdparty.mastercontrol;

import java.io.ByteArrayInputStream;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.line.config.GlobalConfig;
import com.line.eventbus.EventBusFactory;
import com.line.post.ReadyPost;
import com.line.thirdparty.mastercontrol.worker.AckWork;
import com.line.thirdparty.mastercontrol.worker.BaseWorker;
import com.line.thirdparty.mastercontrol.worker.ConnectWork;
import com.line.thirdparty.mastercontrol.worker.ExecuteResult;
import com.line.thirdparty.mastercontrol.xmlbean.Command;
import com.line.thirdparty.mastercontrol.xmlbean.Param;

/**
 * Created by admin on 2018/12/19.
 */
@Service
public class ConnectMaster extends IoHandlerAdapter {

	private static Logger log = LoggerFactory.getLogger(ConnectMaster.class);

	private IoConnector connector;
	private IoSession session;

	private Param param;
	
	private String ip = "127.0.0.1";

	private int port = 8899;
	
	private Map<String, BaseWorker> workerMap = Collections.synchronizedMap(new LinkedHashMap<>());

	 public void addWorker(String packetSeq, BaseWorker worker) {
	        workerMap.put(packetSeq, worker);
	    }
	
	    public void removeWorker(String packetSeq) {
	        workerMap.remove(packetSeq);
	    }

	    public BaseWorker getWorker(String packetSeq) {
	        return workerMap.get(packetSeq);
	    }
	
	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
	}

	public IoConnector getConnector() {
		return connector;
	}

	public void setConnector(IoConnector connector) {
		this.connector = connector;
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ConnectMaster() {
		try {
			GlobalConfig config = GlobalConfig.getInstance();
			ip = config.getProperty("ip");
			port = Integer.parseInt(config.getProperty("port"));
			log.info("连接主控系统 --" + ip + ":" + port);
			connector = new NioSocketConnector();
			connector.setHandler(this);
			ConnectFuture connFuture = connector.connect(new InetSocketAddress(ip,
					port));
			connFuture.awaitUninterruptibly();
			session = connFuture.getSession();
			ConnectWork connectWork = new ConnectWork();
		    excuteConcentWorker(connectWork, 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) throws Exception {
		ConnectMaster client = new ConnectMaster();
		// client.connectMaster();
	}

	@Override
	public void sessionCreated(IoSession iosession) throws Exception {
		log.info("客户端会话创建");
		super.sessionCreated(iosession);
	}

	@Override
	public void sessionOpened(IoSession iosession) throws Exception {
		log.info("客户端会话打开");
		super.sessionOpened(iosession);
		
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		IoBuffer bbuf = (IoBuffer) message;
		byte[] byten = new byte[bbuf.limit()];
		bbuf.get(byten, bbuf.position(), bbuf.limit());
		String xml = new String(byten);
		log.info("发送消息:\n" + xml);
		super.messageSent(session, message);
	}

	@Override
	public void messageReceived(IoSession iosession, Object message)
			throws Exception {
		IoBuffer bbuf = (IoBuffer) message;
		byte[] byten = new byte[bbuf.limit()];
		bbuf.get(byten, bbuf.position(), bbuf.limit());
		String xml = new String(byten);
		log.info("接收主控消息：\n" + xml);
		param = string2Obj(xml);
		if(param != null){
			Command command = param.getCommand();
			if(command != null){
				if(command.getCmdName().equals("COMMAND_FINISHED")){
					AckWork ackWork = new AckWork(command.getCmdID(),command.getCmdName());
					excuteConcentWorker(ackWork,1);
				}else if(command.getCmdName().equals("READY")){
					AckWork ackWork = new AckWork(command.getCmdID(),command.getCmdName());
					excuteConcentWorker(ackWork,1);
					//TODO
					EventBusFactory.getInstance().getEventBus().post(new ReadyPost());
				}else{
					
				}
			}
		}
	}

	/**
     * 执行测试命令
     * @param worker
     * @param timeout
     * @return
     */
    public synchronized ExecuteResult excuteConcentWorker(BaseWorker worker, int timeout) {
        ExecuteResult result = new ExecuteResult();
        if (session != null) {
            worker.setSession(session);
            String packetSeq = null;
            try {
                int seq = worker.doWork();
                packetSeq = seq + "";
                addWorker(packetSeq, worker);
                worker.waitResponse(timeout);
                if (!worker.isFinish()) {
                  result.setResult(false);
                } else {
                    result = worker.getExecuteResult();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.setResult(false);
            } finally {
                if (packetSeq != null) {
                    removeWorker(packetSeq);
                }
            }
        } else {
            result.setResult(false);
        }
        return result;
    }
    
    public Param string2Obj(String xml) {
		try {
			JAXBContext context = JAXBContext.newInstance(Param.class); // 获取上下文对象
			Marshaller marshaller = context.createMarshaller(); // 根据上下文获取marshaller对象
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); // 设置编码字符集
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化XML输出，有分行和缩进
			Unmarshaller unmarshaller = context.createUnmarshaller();
	        ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes());
	        Param department = (Param)unmarshaller.unmarshal(in);
	        return department;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
