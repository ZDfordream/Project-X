package com.amxc.project.home.contract;

import com.amxc.library.presenter.BasePresenter;
import com.amxc.library.view.BaseView;

/**
 * Created by zhudong on 17-11-17.
 */

public interface NewsDetailContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView<Presenter> {
        /**
         * 刷新数据
         * @param msg
         */
        void refreshView(String msg);

        /**
         * 从activity中得到数据字符串
         * @return
         */
        String getDataString();
    }

    //Presenter实现逻辑操作
    interface Presenter extends BasePresenter {
    }
}