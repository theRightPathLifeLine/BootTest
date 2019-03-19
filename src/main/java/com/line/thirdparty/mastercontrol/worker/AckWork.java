package com.line.thirdparty.mastercontrol.worker;

import com.line.thirdparty.mastercontrol.xmlbean.Command;


public class AckWork extends BaseWorker{
	
	private int cmdID;
	
	private String cmdName;
	
	public AckWork(int cmdID,String cmdName) {
		this.cmdID = cmdID;
		this.cmdName = cmdName;
	}
	
	@Override
	public int doWork() {
		Command command = new Command();
		command.setCmdID(cmdID);
		command.setCmdName(cmdName);
		command.setErrorInfo("");
		command.setResultFlag(1);
		param.setCommand(command);
		session.write(obj2IoBuffer(param));
		return cmdID;
	}

}
