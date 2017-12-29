package com.amxc.project.find.activity.module;

import com.amxc.project.find.contract.FindContract;
import com.amxc.project.movie.contract.MovieContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HuangWei on 2017/11/5.
 * 这里的LoanModule不一定在相应的component下有component,应为作为fragment一起注入到mainactivity
 */
@Module
public class FindModule {
    private FindContract.View view;
    public FindModule(FindContract.View view) {

        this.view = view;
    }

    @Provides
    FindContract.View provideFindPageContractView() {
        return view;
    }
}
