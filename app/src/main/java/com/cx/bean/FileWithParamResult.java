package com.cx.bean;

/**
 * Created by ly-chenxiao on 18/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author: chenxiao
 */
public class FileWithParamResult {
    private Boolean success;
    private Integer code;
    private String message;
    private GetResult.DataBean data;


    public static class DataBean {
        private String descriptions;
        private String isFree;
    }

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

    public GetResult.DataBean getData() {
        return data;
    }

    public void setData(GetResult.DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
