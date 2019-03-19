package com.line.utils;

/**
 * Created by A17003284 on 2018/5/22.
 */
public class NodeIndexUtil {

    /**
     * 获取节点在页面中的位置
     * @param chestNo 柜子好
     * @param boxNo   箱子
     * @param position 节点在箱子的位置
     * @return
     */
    public int getIndex(int chestNo, int boxNo, int position){
        int index = -1 ;
        switch (chestNo){
            case 1:
            case 2:
            case 3:
                //每个箱子4个节点
                index = (chestNo -1) * 8 + ( boxNo - 1) * 4 + position + 2;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                //每个箱子24个节点
                index = 24 + (chestNo - 4)*48 + (boxNo -1) * 24 + position + 2;
                break;
            case 8:
            case 9:
                index = 24 + 192 + (chestNo - 8) * 24 +(boxNo -1) * 12 + position + 2;
                break;
            case 10:
                //四表节点
                break;
            case 11:
            case 12:
                //主节点 从0开始
                index = (chestNo - 11)+ boxNo - 1 + position - 1;
        }
        return index;
    }

}
