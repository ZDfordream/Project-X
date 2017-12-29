package com.amxc.project.home.model.entity;

import com.amxc.library.view.BaseView;
import com.amxc.project.Utils.ToastUtil;
import com.amxc.project.app.AppConstants;
import com.lzy.okgo.exception.HttpException;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;

import io.reactivex.functions.Consumer;


/**
 * Created by amxc on 17-8-9.
 */

public class BaseThrowableConsumer implements Consumer<Throwable> {
    private BaseView baseView;
    public BaseThrowableConsumer(BaseView baseView){
        this.baseView = baseView;
    }

    @Override
    public void accept(Throwable t) throws Exception {
        if (t instanceof HttpException) {
            ToastUtil.showToast("服务器异常");
            t.printStackTrace();
        } else if (t instanceof NoRouteToHostException) {
            ToastUtil.showToast("服务器状态异常");
        } else if (t instanceof SocketTimeoutException) {
            ToastUtil.showToast("网络连接超时");
        } else if (t instanceof ConnectException){
            ToastUtil.showToast("网络连接错误");
        }else {
            t.printStackTrace();
        }
        if(baseView!=null){
            baseView.setState(AppConstants.STATE_ERROR);
        }
    }
}
