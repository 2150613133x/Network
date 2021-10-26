package com.cx.bean;

/**
 * Created by ly-chenxiao on 25/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author: chenxiao
 */

public class TokenResponse {

    private String statusCode;
    private String statusMessage;
    private DataBean data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String access_token;
        private String refresh_token;
        private Integer expires_in;
        private Object project_id;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public Integer getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(Integer expires_in) {
            this.expires_in = expires_in;
        }

        public Object getProject_id() {
            return project_id;
        }

        public void setProject_id(Object project_id) {
            this.project_id = project_id;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "access_token='" + access_token + '\'' +
                    ", refresh_token='" + refresh_token + '\'' +
                    ", expires_in=" + expires_in +
                    ", project_id=" + project_id +
                    '}';
        }


    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "statusCode='" + statusCode + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
