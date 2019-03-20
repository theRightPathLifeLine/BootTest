package com.line.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.line.config.GlobalConfig;
import com.line.dao.CardResultRepository;
import com.line.dto.CardDateStatisticsDto;
import com.line.dto.CardPointStatisticsDto;
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
import com.line.utils.ExportExcel;

@RestController
public class BaseController {
	
	@Autowired
	private ConnectMaster connectMaster;
	
	@Autowired
	private CameraHik cameraHik;
	
	@Autowired
	private CardResultRepository cardResultRepository;
	
	@RequestMapping("/takePhono")
    public String takePhoto() {
//		EventBusFactory.getInstance().getEventBus().post(new ReadyPost());
        return "http://127.0.0.1:7001/img/" + cameraHik.takePhoto();
    }
	
	@RequestMapping("/startTest")
    public String startTest() {
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
    
    /**
     * 获取所有的测试记录
     *
     * @return
     */
    @RequestMapping(value = "/exportData")
    @ResponseBody
    public String exportData(@RequestBody JSONObject projectModel,HttpServletRequest request){
        String fileName = "测试数据.xls";
        String contextPath = request.getContextPath();
        String realPath = request.getSession().
                getServletContext().getRealPath("/");

        String filePath = realPath + fileName;
      
        List<Object[]> list = cardResultRepository.findbyDay();
        List<CardDateStatisticsDto> dtos = new ArrayList<CardDateStatisticsDto>();
    	for (Object[] objects : list) {
			CardDateStatisticsDto dto = new CardDateStatisticsDto((String)objects[0], ((BigInteger)objects[1]).intValue(), ((BigDecimal)objects[2]).intValue());
			dtos.add(dto);
		}
    	List<Object[]> list1 = cardResultRepository.findByPoint();
    	List<CardPointStatisticsDto> dtos2 = new ArrayList<CardPointStatisticsDto>();
    	for (Object[] objects : list1) {
			CardPointStatisticsDto dto = new CardPointStatisticsDto((int)objects[0], 
					 ((BigInteger)objects[1]).intValue(), ((BigDecimal)objects[2]).intValue(), 
					 ((BigDecimal)objects[3]).intValue(), ((BigDecimal)objects[4]).intValue(),
					 ((BigDecimal)objects[5]).intValue(), ((BigDecimal)objects[6]).intValue());
			dtos2.add(dto);
		}
    	ExportExcel excel = new ExportExcel("");
    	excel.exportConsisterLogReport(dtos, fileName, dtos2);

        String basePath = request.getScheme()+"://"+request.getServerName()+":"+
                request.getServerPort()+contextPath+"/img/" + fileName;
        return basePath;
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