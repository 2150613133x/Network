package com.cx.bean;

import java.util.List;

/**
 * Created by ly-chenxiao on 18/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author: chenxiao
 */
public class JsonResult {

    private Boolean success;
    private Integer code;
    private String message;
    private List<DataBean> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String title;
        private Integer viewCount;
        private Integer commentCount;
        private String publishTime;
        private String userName;
        private String cover;
    }
}
