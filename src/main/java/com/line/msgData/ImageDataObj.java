package com.line.msgData;

import com.line.msgData.mete.MsgType;

public class ImageDataObj extends AbsMsgObj{
	
	private String data;
	
	public ImageDataObj(String data){
		this.data = data;
		this.msgType = MsgType.IMG_MSG;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	

}
