package com.amxc.project.home.activity.component;

import com.amxc.project.Utils.ActivityScoped;
import com.amxc.project.app.ApplicationComponent;
import com.amxc.project.home.activity.ImagePagerActivity;
import com.amxc.project.home.activity.module.ImagePagerModule;

import dagger.Component;

/**
 * Created by zhudong.
 */

@ActivityScoped
@Component(modules = ImagePagerModule.class, dependencies = ApplicationComponent.class)
public interface ImagePagerComponent {
    void inject(ImagePagerActivity activity);
}