package com.line.thirdparty.mastercontrol.worker;

import com.line.thirdparty.mastercontrol.xmlbean.CmdParams;
import com.line.thirdparty.mastercontrol.xmlbean.Command;

public class CameraMoveWork extends BaseWorker{

	private int cameraID;
	
	private int stepOne;
	
	private int stepTwo;
	
	public CameraMoveWork(int cameraID, int stepOne, int stepTwo) {
		super();
		this.cameraID = cameraID;
		this.stepOne = stepOne;
		this.stepTwo = stepTwo;
	}

	@Override
	public int doWork() {
		int cmdID = getCmdID();
		Command command = new Command();
		command.setCmdID(cmdID);
		command.setCmdName("CAMERA_MOVE");
		CmdParams cmdParams = new CmdParams();
		cmdParams.setCameraID(cameraID);
		cmdParams.setStepOne(stepOne);
		cmdParams.setStepTwo(stepTwo);
		command.setCmdParams(cmdParams);
		param.setCommand(command);
		session.write(obj2IoBuffer(param));
		return cmdID;
	}

}
