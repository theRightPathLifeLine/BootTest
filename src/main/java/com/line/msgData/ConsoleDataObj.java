package com.line.msgData;

import java.util.Date;

import com.line.msgData.mete.MsgType;
import com.line.utils.CalendarUtils;

/**
 * Created by admin on 2018/4/16.
 */
public class ConsoleDataObj extends AbsMsgObj{

    private String data;

    private String color;

    public ConsoleDataObj() {
    }
    public ConsoleDataObj(String data, String color, MsgType msgType) {
        this.data = getTime() +" "+data;
        this.color = color;
        this.msgType = msgType;
    }

    public String getData() {
        return data;
    }

    /**
     * 控制台打印需要时间
     * @param data
     */
    public void setData(String data) {
        this.data = getTime() +" "+ data;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public String getTime(){
        Date date = new Date();
        return CalendarUtils.formatDateTime(date);
    }
}
