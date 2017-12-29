package com.amxc.project.mine.contract;

import android.support.v4.app.Fragment;

import com.amxc.library.presenter.BasePresenter;
import com.amxc.library.view.BaseView;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangWei on 2017/11/5.
 */

public interface MineContract {
    interface View extends BaseView<Presenter> {

    }


    interface Presenter extends BasePresenter {
        /**
         * 获取tablayout数据
         * @return
         */
        ArrayList<CustomTabEntity> getTabEntities(String[] mNames);

        /**
         * 获取fragment
         * @return
         */
        Fragment[] getFragments();
    }
}
