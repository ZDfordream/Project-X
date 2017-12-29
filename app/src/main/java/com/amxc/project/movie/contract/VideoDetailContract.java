package com.amxc.project.movie.contract;

import com.amxc.library.presenter.BasePresenter;
import com.amxc.library.view.BaseView;
import com.amxc.project.movie.model.entity.MovieEntity;

import java.util.List;

/**
 * Created by zhudong.
 */

public interface VideoDetailContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView<Presenter> {
        void refreshView(List<MovieEntity.V9LG4B3A0Bean> list);
    }

    //Presenter实现逻辑操作
    interface Presenter extends BasePresenter {

    }
}