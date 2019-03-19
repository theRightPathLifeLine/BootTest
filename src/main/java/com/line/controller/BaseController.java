package com.line.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.line.config.GlobalConfig;
import com.line.entity.Device;
import com.line.eventbus.EventBusFactory;
import com.line.post.ReadyPost;
import com.line.thirdparty.hik.CameraHik;
import com.line.thirdparty.mastercontrol.ConnectMaster;
import com.line.thirdparty.mastercontrol.worker.CameraMoveWork;
import com.line.thirdparty.mastercontrol.worker.FinishedWork;
import com.line.thirdparty.mastercontrol.worker.LightControlWork;
import com.line.thirdparty.mastercontrol.worker.PointCorrectWork;
import com.line.utils.DetectionLogUtils;

@RestController
public class BaseController {
	
	@Autowired
	private ConnectMaster connectMaster;
	
	@Autowired
	private CameraHik cameraHik;
	
	@RequestMapping("/takePhono")
    public String takePhoto() {
		EventBusFactory.getInstance().getEventBus().post(new ReadyPost());
        return "http://127.0.0.1:7001/img/" + cameraHik.takePhoto();
    }
	
    @RequestMapping("/pointCorrect")
    public String pointCorrect() {
    	PointCorrectWork pointCorrectWork = new PointCorrectWork(3, 3.4, 1);
    	connectMaster.excuteConcentWorker(pointCorrectWork, 3);
        return "";
    }
    
    @RequestMapping("/cameraMove")
    public String cameraMove(){
    	CameraMoveWork cameraMoveWork = new CameraMoveWork(1, 1, 1);
    	connectMaster.excuteConcentWorker(cameraMoveWork, 3);
    	return "";
    }
    
    @RequestMapping("/finished")
    public String finished(){
    	FinishedWork cameraMoveWork = new FinishedWork();
    	connectMaster.excuteConcentWorker(cameraMoveWork, 3);
    	return "";
    }
    
    @RequestMapping("/lightOpen")
    public String lightOpen(){
    	LightControlWork cameraMoveWork = new LightControlWork(1, 1);
    	connectMaster.excuteConcentWorker(cameraMoveWork, 3);
    	return "";
    }
    
    @RequestMapping("/lightClose")
    public String lightClose(){
    	LightControlWork cameraMoveWork = new LightControlWork(1, 0);
    	connectMaster.excuteConcentWorker(cameraMoveWork, 3);
    	return "";
    }
    
    @RequestMapping("/getData")
    public List<Device> getData(){
    	List<Device> devices = new ArrayList<Device>();
    	devices = DetectionLogUtils.getFileData();
    	return devices;
    }
    
    @RequestMapping("/editImg")
    public void editImg(){
    	String filepath = GlobalConfig.getInstance().getProperty("path");
		String command = "cmd /c start " + filepath;
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
}