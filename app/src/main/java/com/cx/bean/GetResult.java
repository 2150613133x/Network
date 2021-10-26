package com.cx.bean;

/**
 * Created by ly-chenxiao on 18/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author: chenxiao
 */

public class GetResult {

    private Boolean success;
    private Integer code;
    private String message;
    private DataBean data;


    public static class DataBean {
        private String page;
        private String keyword;
        private String order;
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
