package com.line.thirdparty.mastercontrol.worker;

import com.line.thirdparty.mastercontrol.xmlbean.Command;

public class FinishedWork extends BaseWorker {

	@Override
	public int doWork() {
		int cmdID = getCmdID();
		Command command = new Command();
		command.setCmdID(cmdID);
		command.setCmdName("FINISHED");
		param.setCommand(command);
		session.write(obj2IoBuffer(param));
		return cmdID;
	}

}
