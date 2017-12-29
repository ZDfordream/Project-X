package com.amxc.project.home.contract;

import com.amxc.library.presenter.BasePresenter;
import com.amxc.library.view.BaseView;

/**
 * Created by zhudong.
 */

public interface ImagePagerContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView<Presenter> {
        void refreshView(String msg);
    }

    //Presenter实现逻辑操作
    interface Presenter extends BasePresenter {

    }
}