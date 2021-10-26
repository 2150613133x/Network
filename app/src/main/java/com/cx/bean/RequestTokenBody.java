package com.cx.bean;

/**
 * Created by ly-chenxiao on 25/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author: chenxiao
 */
public class RequestTokenBody {
    private String grant_type;

    public RequestTokenBody(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
