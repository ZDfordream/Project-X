package com.amxc.project.find.contract;

import com.amxc.library.presenter.BasePresenter;
import com.amxc.library.view.BaseView;
import com.amxc.project.find.model.entity.WXItemBean;

import java.util.List;

/**
 * Created by HuangWei on 2017/11/5.
 */

public interface FindContract {
    interface View extends BaseView<Presenter> {
        /**
         * 刷新界面
         * @param list
         */
        void refreshView(List<WXItemBean> list);
        /**
         * 加载错误之后，停止加载动画
         */
        void stopFetchData();
    }


    interface Presenter extends BasePresenter {
        /**
         * 下拉刷新
         */
        void refresh();

        /**
         * 加载更多
         */
        void loadmore();
    }
}
