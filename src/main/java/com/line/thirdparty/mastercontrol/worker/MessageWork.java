package com.line.thirdparty.mastercontrol.worker;

import com.line.thirdparty.mastercontrol.xmlbean.CmdParams;
import com.line.thirdparty.mastercontrol.xmlbean.Command;

public class MessageWork extends BaseWorker{

	private String message;

	public MessageWork(String message) {
		this.message = message;
	}


	@Override
	public int doWork() {
		int cmdID = getCmdID();
		Command command = new Command();
		command.setCmdID(cmdID);
		command.setCmdName("CAMERA_MOVE");
		CmdParams cmdParams = new CmdParams();
		cmdParams.setMessage(message);
		command.setCmdParams(cmdParams);
		param.setCommand(command);
		session.write(obj2IoBuffer(param));
		return cmdID;
	}

}
