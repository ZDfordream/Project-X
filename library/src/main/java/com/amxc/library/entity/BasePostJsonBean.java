package com.amxc.library.entity;



/**
 * Created by zhudong on 2017/6/21.
 * <p>
 * head:头信息,用于校验,参数固定为 version、partner、key、mdkey 四项,version 为 api 版本号,mdkey 为短信
 * 校验登陆成功后返回的唯一校验值,部分接口 mdkey 可为空,partner 为设备标识 android/ios,key 为预先约定好的
 * 常量。
 * <p>
 * para:请求体,客户端请求所需要传入的参数,依据不同接口而定
 */

public class BasePostJsonBean<T> {

    /**
     * head : {"version":"1.0","partner":"xxxxxxxxxxxxxxxxxxxxxxx","key":"xxxxxxxxx","mdkey":"xxxxxxxxxxx"}
     * para : {"key ":"value ","key":"value"}
     */

    private HeadBean head;
    private T para;

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public T getPara() {
        return para;
    }

    public void setPara(T para) {
        this.para = para;
    }

    public static class HeadBean {
        /**
         * version : 1.0
         * partner : xxxxxxxxxxxxxxxxxxxxxxx
         * key : xxxxxxxxx
         * mdkey : xxxxxxxxxxx
         */

        private Integer version;
        private String partner;
        private String key;
        private String mdkey;

        public HeadBean(Integer version) {
            this.version = version;
            this.partner = "android";
        }

        public HeadBean(Integer version, String mdkey) {
            this(version);
            this.mdkey = mdkey;
        }

        public HeadBean(Integer version, String key, String mdkey) {
            this(version, mdkey);
            this.key = key;
        }

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }

        public String getPartner() {
            return partner;
        }

        public void setPartner(String partner) {
            this.partner = partner;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getMdkey() {
            return mdkey;
        }

        public void setMdkey(String mdkey) {
            this.mdkey = mdkey;
        }
    }

}
