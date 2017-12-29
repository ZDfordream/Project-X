package com.amxc.project.Utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amxc.project.R;
import com.amxc.project.app.App;


/**
 * Created by llf on 2017/2/27.
 * Toast工具
 */

public class ToastUtil {
    private static boolean isShow = true;//默认显示
    private static Toast mToast = null;//全局唯一的Toast
    private static Toast mErrorToast = null;//全局唯一的Toast

    /*private控制不应该被实例化*/
    private ToastUtil() {
        throw new UnsupportedOperationException("不能被实例化");
    }

    /**
     * 全局控制是否显示Toast
     * @param isShowToast
     */
    public static void controlShow(boolean isShowToast){
        isShow = isShowToast;
    }

    /**
     * 取消Toast显示
     */
    public void cancelToast() {
        if(isShow && mToast != null){
            mToast.cancel();
        }
        if(isShow && mErrorToast != null){
            mErrorToast.cancel();
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showToast(CharSequence message) {
        if (isShow){
            if (mToast == null) {
                mToast = new Toast(App.getInstance().getApplicationContext());
            }
            mToast.setDuration(Toast.LENGTH_SHORT);
            View view = View.inflate(App.getInstance().getApplicationContext(), R.layout.layouttoast, null);
            TextView tv_toast = (TextView) view.findViewById(R.id.tv_toast);
            tv_toast.setText(message);
            mToast.setView(view);
            mToast.setGravity(Gravity.CENTER,0,0);
            mToast.show();
        }
    }

    /**
     * 显示错误提示Toast
     * @param errorContent
     * @return
     */
    public static void showError(String errorContent) {
        if (isShow) {
            View errorView = LayoutInflater.from(App.getInstance().getApplicationContext()).inflate(R.layout.app_error_tip, null);
            TextView tvContent = (TextView) errorView.findViewById(R.id.content);
            tvContent.setText(errorContent);
            if (mErrorToast == null) {
                mErrorToast = new Toast(App.getInstance().getApplicationContext());
            }
            mErrorToast.setView(errorView);
            mErrorToast.setGravity(Gravity.CENTER, 0, 0);
            mErrorToast.setDuration(Toast.LENGTH_SHORT);
            mErrorToast.show();
        }
    }
}

