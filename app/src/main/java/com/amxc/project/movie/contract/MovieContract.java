package com.amxc.project.movie.contract;

import android.app.Activity;

import com.amxc.library.presenter.BasePresenter;
import com.amxc.library.view.BaseView;
import com.amxc.project.movie.model.entity.MovieEntity;

import java.util.List;

/**
 * Created by HuangWei on 2017/11/5.
 */

public interface MovieContract {
    interface View extends BaseView<Presenter> {
        /**
         * 刷新界面
         * @param list
         */
        void refreshView(List<MovieEntity.V9LG4B3A0Bean> list);

        /**
         * 加载错误之后，停止加载动画
         */
        void stopFetchData();
    }


    interface Presenter extends BasePresenter {
        void refresh();
        void loadmore();
    }
}
