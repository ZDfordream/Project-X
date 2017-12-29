package com.amxc.project.app;

import com.amxc.project.Utils.ToastUtil;
import com.lzy.okgo.exception.HttpException;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;


/**
 * Created by admin on 2017/12/27.
 */

public class NotApiThrowableConsumer implements Consumer<Throwable> {
    @Override
    public void accept(Throwable t) throws Exception {
        if (t instanceof HttpException) {
            ToastUtil.showToast("服务器异常");
            t.printStackTrace();
        } else if (t instanceof NoRouteToHostException) {
            ToastUtil.showToast("服务器状态异常");
        } else if (t instanceof SocketTimeoutException) {
            ToastUtil.showToast("网络连接超时");
        } else if (t instanceof ConnectException) {
            ToastUtil.showToast("网络连接错误");
        } else if(t instanceof UnknownHostException){
            ToastUtil.showToast("服务器域名无法解析");
        }else {
            t.printStackTrace();
        }
    }
}
