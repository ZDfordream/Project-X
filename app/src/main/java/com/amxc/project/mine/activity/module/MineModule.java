package com.amxc.project.mine.activity.module;

import com.amxc.project.mine.contract.MineContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HuangWei on 2017/11/5.
 * 这里的MineModule不一定在相应的component下有component,应为作为fragment一起注入到mainactivity
 */
@Module
public class MineModule {
    private MineContract.View view;
    public MineModule(MineContract.View view) {

        this.view = view;
    }

    @Provides
    MineContract.View provideMinePageContractView() {
        return view;
    }
}
