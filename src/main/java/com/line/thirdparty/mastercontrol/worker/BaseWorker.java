	package com.line.thirdparty.mastercontrol.worker;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.line.thirdparty.mastercontrol.xmlbean.Command;
import com.line.thirdparty.mastercontrol.xmlbean.Param;

public abstract class BaseWorker {
	
	protected IoSession session;
	
	protected ExecuteResult executeResult;
	
	protected static int cmdId = 0;
	
	protected Param param = new Param(12,21);
	
	private boolean finish;
	
	public abstract int doWork();
	
	public void doAck(Param param){
		executeResult = new ExecuteResult();
		Command data = param.getCommand();
		executeResult.setResult(data.getResultFlag() == 1);
		executeResult.setInfo(data.getErrorInfo());
	}
	
	public boolean isFinish() {
        return finish;
    }
	
	public synchronized static int getCmdID(){
		cmdId++;
		if(cmdId > 65535){
			cmdId = 1;
		}
		return cmdId;
	}

    public synchronized void waitResponse(int timeout) {
        try {
            wait(timeout*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void notifyFinish(boolean finish) {
        this.finish = finish;
        notify();
    }

	public ExecuteResult getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(ExecuteResult executeResult) {
		this.executeResult = executeResult;
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}
	

	public IoBuffer obj2IoBuffer(Param param) {
		try {
			JAXBContext context = JAXBContext.newInstance(Param.class); // 获取上下文对象
			Marshaller marshaller = context.createMarshaller(); // 根据上下文获取marshaller对象

			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); // 设置编码字符集
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化XML输出，有分行和缩进
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			marshaller.marshal(param, baos);
			IoBuffer buffer = IoBuffer.allocate(baos.size());
			buffer.put(baos.toByteArray());
			buffer.flip();
			return buffer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	
}
