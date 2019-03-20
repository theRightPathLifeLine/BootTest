package com.line.msgData;

import com.line.msgData.mete.MsgType;

public class ResultDataObj extends AbsMsgObj{
	
	private String data;
	
	public ResultDataObj(String data){
		this.data = data;
		this.msgType = MsgType.RESULT_MSG;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	

}
