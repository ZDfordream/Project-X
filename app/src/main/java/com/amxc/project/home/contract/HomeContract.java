package com.amxc.project.home.contract;

import android.app.Activity;

import com.amxc.library.presenter.BasePresenter;
import com.amxc.library.view.BaseView;

import java.util.List;

/**
 * Created by HuangWei on 2017/11/5.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter>{
        /**
         * 刷新界面
         * @param data
         */
        void refreshView(String data);
    }


    interface Presenter extends BasePresenter{

    }
}
