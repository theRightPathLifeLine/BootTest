package com.line.thirdparty.mastercontrol.worker;

import com.line.thirdparty.mastercontrol.xmlbean.CmdParams;
import com.line.thirdparty.mastercontrol.xmlbean.Command;

public class PointCorrectWork extends BaseWorker{

	private double x;
	
	private double y;
	
	private double angle;

	public PointCorrectWork(double x, double y, double angle) {
		super();
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	@Override
	public int doWork() {
		int cmdID = getCmdID();
		Command command = new Command();
		command.setCmdID(cmdID);
		command.setCmdName("CAMERA_MOVE");
		CmdParams cmdParams = new CmdParams();
		cmdParams.setX(x);
		cmdParams.setY(y);
		cmdParams.setAngle(angle);
		command.setCmdParams(cmdParams);
		param.setCommand(command);
		session.write(obj2IoBuffer(param));
		return cmdID;
	}

}
