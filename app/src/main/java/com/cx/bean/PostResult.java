package com.cx.bean;

/**
 * Created by ly-chenxiao on 18/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author: chenxiao
 */
public class PostResult {

    private Boolean success;
    private Integer code;
    private String message;
    private Object data;

    @Override
    public String toString() {
        return "PostResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
