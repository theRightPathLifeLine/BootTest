package com.line.msgData;


import java.io.Serializable;

import com.line.msgData.mete.MsgType;

import net.sf.json.JSONObject;

/**
 * Created by admin on 2018/4/26.
 */
public abstract class AbsMsgObj  implements Serializable {

	protected MsgType msgType;
	
	
	
    public MsgType getMsgType() {
		return msgType;
	}



	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}



	public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}
