package com.amxc.project.home.activity.component;

import com.amxc.project.Utils.ActivityScoped;
import com.amxc.project.app.ApplicationComponent;
import com.amxc.project.home.activity.NewsDetailActivity;
import com.amxc.project.home.activity.module.NewsDetailModule;

import dagger.Component;

/**
 * Created by zhudong on 17-11-17.
 */

@ActivityScoped
@Component(modules = NewsDetailModule.class, dependencies = ApplicationComponent.class)
public interface NewsDetailComponent {
    void inject(NewsDetailActivity activity);
}