package com.amxc.project.home.activity.module;

import com.amxc.project.home.contract.ImagePagerContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhudong.
 */

@Module
public class ImagePagerModule {
    private ImagePagerContract.View view;

    /**
     * 构建ImagePagerModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ImagePagerModule(ImagePagerContract.View view) {
        this.view = view;
    }

    @Provides
    ImagePagerContract.View provideImagePagerContractView() {
        return this.view;
    }
}