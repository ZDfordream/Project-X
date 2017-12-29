package com.amxc.project.home.activity.module;

import com.amxc.project.home.contract.HomeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HuangWei on 2017/11/5.
 */
@Module
public class HomeModule {
    private HomeContract.View view;
    public HomeModule(HomeContract.View view) {

        this.view = view;
    }

    @Provides
    HomeContract.View provideHomePageContractView() {
        return view;
    }
}
