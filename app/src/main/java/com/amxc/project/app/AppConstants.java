package com.amxc.project.app;

/**
 * Created by zhudong.
 */

public class AppConstants {
    /**
     * LoadingPage加载状态值
     * */
    public static final int STATE_LOADING = 1;// 加载中的界面
    public static final int STATE_ERROR = 2;// 错误界面
    public static final int STATE_EMPTY = 3;// 空界面
    public static final int STATE_SUCCESS = 4;// 加载成功的界面

    public static final String NEWSCLASSFI_TYPE = "type";

    //需要APIKEY请去 http://www.tianapi.com/#wxnew 申请,复用会减少访问可用次数。还有很多别的接口大家可以研究。
    public static final String KEY_API = "e6d6ec3ba2f9d7a3051a6c09f0524738";
}
