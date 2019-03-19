package com.line.thirdparty.mastercontrol.worker;

import java.util.ArrayList;
import java.util.List;

import com.line.thirdparty.mastercontrol.xmlbean.CmdParams;
import com.line.thirdparty.mastercontrol.xmlbean.Command;
import com.line.thirdparty.mastercontrol.xmlbean.Light;

public class LightControlWork extends BaseWorker {
	
	private int lightID;
	
	private int on;

	public LightControlWork(int lightID, int on) {
		super();
		this.lightID = lightID;
		this.on = on;
	}

	@Override
	public int doWork() {
		int cmdID = getCmdID();
		Command command = new Command();
		command.setCmdID(cmdID);
		command.setCmdName("LIGHT_CONTROL");
		CmdParams cmdParams = new CmdParams();
		cmdParams.setLightID(lightID);
		List<Light> lights = new ArrayList<Light>();
		for (int i = 1; i < 5; i++) {
			Light light = new Light();
			light.setPosition(i+"");
			light.setOn(on+"");
			lights.add(light);
		}
		cmdParams.setLights(lights);
		command.setCmdParams(cmdParams);
		param.setCommand(command);
		session.write(obj2IoBuffer(param));
		return cmdID;
	}

}
