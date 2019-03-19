package com.line.thirdparty.mastercontrol.worker;

import com.line.thirdparty.mastercontrol.xmlbean.CmdParams;
import com.line.thirdparty.mastercontrol.xmlbean.Command;

public class TrialResultWork extends BaseWorker{

	private String trialData;

	public TrialResultWork(String trialData) {
		this.trialData = trialData;
	}


	@Override
	public int doWork() {
		int cmdID = getCmdID();
		Command command = new Command();
		command.setCmdID(cmdID);
		command.setCmdName("CAMERA_MOVE");
		CmdParams cmdParams = new CmdParams();
		cmdParams.setTrialData(trialData);
		command.setCmdParams(cmdParams);
		param.setCommand(command);
		session.write(obj2IoBuffer(param));
		return cmdID;
	}

}
