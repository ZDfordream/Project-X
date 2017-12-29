package com.amxc.project.home.activity.module;

import com.amxc.project.home.contract.NewsDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhudong on 17-11-17.
 */

@Module
public class NewsDetailModule {
    private NewsDetailContract.View view;

    /**
     * 构建NewsDetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public NewsDetailModule(NewsDetailContract.View view) {
        this.view = view;
    }

    @Provides
    NewsDetailContract.View provideNewsDetailContractView() {
        return this.view;
    }
}