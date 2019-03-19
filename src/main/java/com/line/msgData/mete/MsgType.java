package com.line.msgData.mete;

/**
 * Created by admin on 2018/4/16.
 */
public enum MsgType {

    CONSOLE_MSG("控制台消息"),
    BROADCAST_MSG("广播消息"),
    NETSEN_MSG("节点离线消息"),
    PTP_MSG("连线消息"),
    COLOR_MSG("节点颜色修改消息"),
    PORT_MSG("端口消息"),
    LINKMANAGER_MSG("连接管理消息"),
    NODEMANAGER_MSG("节点管理消息"),
    FOURMETERREAD_MSG("四表抄读消息"),
    NETWORKREAD_MSG("组网抄表消息"),
    ANIMOTESTA_MSG("组网动画状态信息"),
    NODESTATUS_MSG("节点实时状态"),
    ROUT_MSG("七级中继消息"),
    TWOROUT_MSG("二层组合消息");


    private String name;

    // get set 方法
    public String getName() {
        return name;
    }
    private MsgType(String name) {
        this.name = name;
    }


}
