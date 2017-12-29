package com.amxc.project.home.model.http;


import com.amxc.project.app.App;

/**
 * Created by cjiang on 17-8-9.
 */

public class ApiConstants {

    public static final String BASE_URL = App.getInstance().getNativeHost();

    /**
     * 注册接口获取验证码
     */
    public static final String COMMITCODE = BASE_URL + "api/v1/auth/logincommitcode";

}