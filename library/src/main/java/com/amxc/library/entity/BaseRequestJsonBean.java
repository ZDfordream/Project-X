package com.amxc.library.entity;

/**
 * Created by zhudong on 17-8-9.
 */

public class BaseRequestJsonBean<T> {

    /**
     * head : {"code":"10000","msg":"请求成功"}
     * data : {"record":"1"}
     */

    private HeadBean head;
    private T data;

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class HeadBean {
        /**
         * code : 10000
         * msg : 请求成功
         */

        private String code;
        private String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
