package com.line.msgData;


import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 * Created by admin on 2018/4/26.
 */
public abstract class AbsMsgObj  implements Serializable {

    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}
