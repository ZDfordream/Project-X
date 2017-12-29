package com.amxc.project.movie.activity.module;

import com.amxc.project.movie.contract.VideoDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhudong.
 */

@Module
public class VideoDetailModule {
    private VideoDetailContract.View view;

    /**
     * 构建VideoDetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public VideoDetailModule(VideoDetailContract.View view) {
        this.view = view;
    }

    @Provides
    VideoDetailContract.View provideVideoDetailContractView() {
        return this.view;
    }
}