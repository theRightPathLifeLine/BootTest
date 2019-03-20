package com.line.process;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.eventbus.Subscribe;
import com.line.dao.CardResultRepository;
import com.line.entity.CardResult;
import com.line.eventbus.EventBusFactory;
import com.line.msgData.ImageDataObj;
import com.line.msgData.ResultDataObj;
import com.line.post.ReadyPost;
import com.line.thirdparty.hik.CameraHik;
import com.line.thirdparty.imagelogic.Calculations;
import com.line.thirdparty.imagelogic.ImageRecognitionServer;
import com.line.thirdparty.imagelogic.Offset;
import com.line.thirdparty.mastercontrol.ConnectMaster;
import com.line.thirdparty.mastercontrol.worker.CameraMoveWork;
import com.line.thirdparty.mastercontrol.worker.FinishedWork;
import com.line.thirdparty.mastercontrol.worker.PointCorrectWork;
import com.line.websocket.WebSocketServer;

@Service
public class BaseProcess {

	@Autowired
	private ConnectMaster connectMaster;

	@Autowired
	private CameraHik cameraHik;

	@Autowired
	private ImageRecognitionServer imageRecognitionServer;
	
	@Autowired
    private CardResultRepository cardResultRepository;

	public BaseProcess() {
		EventBusFactory.getInstance().getEventBus().register(this);
	}

	@Subscribe
	public void ready(ReadyPost readyPost) {
		System.out.println("ready");
		startTest();
	}
	
	private void startTest(){
		//1.拍照  推送界面
		String img = cameraHik.takePhoto();
		ImageDataObj dataObj = new ImageDataObj("http://127.0.0.1:7001/img/" + img);
		WebSocketServer.sendMsg(dataObj.toString());
		//2.调算法 校验图片  生成照片 推送前台
		// TODO
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultDataObj obj = new ResultDataObj("");
		WebSocketServer.sendMsg(obj.toString());
		//3. 校验结果处理
		// TODO
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String name = sdf.format(date) + ".jpg";
		CardResult result = new CardResult();
		result.setImageName(name);
		result.setDate(date);
		Random r = new Random();
		result.setPoint(r.nextInt(3) + 30);
		int pass = r.nextInt(2);
		result.setPass(pass);
		if(pass != 1){
			result.setMiss(r.nextInt(4));
			result.setMore(r.nextInt(4));
			result.setError(r.nextInt(4));
			result.setContrary(r.nextInt(4));
		}
		cardResultRepository.save(result);
		dataObj = new ImageDataObj("http://127.0.0.1:7001/img/result3.jpg");
		WebSocketServer.sendMsg(dataObj.toString());
		
		
	}
	
	public static void main(String[] args) {
		Random r = new Random();
		for (int i = 0; i < 60; i++) {
			System.out.println(r.nextInt(2));
		}
	}

	private void testStart() {
		// 1 拍照 定位
		String url = "E://" + cameraHik.takePhoto();
		Offset offset = imageRecognitionServer.getoffset(url);
		// 2 判定位置偏差 校正
		PointCorrectWork correctWork = new PointCorrectWork(
				offset.getPoint().x, offset.getPoint().y, offset.getAngle());
		connectMaster.excuteConcentWorker(correctWork, 4);
		// 3 按照路径拍照 6*6
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				CameraMoveWork cameraMoveWork = new CameraMoveWork(1, i, j);
				cameraHik.takePhoto();
			}
		}
		//TODO 4 调用算法
		Calculations.calculation();
		//5 处理结果
		
		//6放行
		FinishedWork finishedWork = new FinishedWork();
		connectMaster.excuteConcentWorker(finishedWork, 3);

	}
}
