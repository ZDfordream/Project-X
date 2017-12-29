package com.amxc.project.movie.activity.component;

import com.amxc.project.Utils.ActivityScoped;
import com.amxc.project.app.ApplicationComponent;
import com.amxc.project.movie.activity.VideoDetailActivity;
import com.amxc.project.movie.activity.module.VideoDetailModule;

import dagger.Component;

/**
 * Created by zhudong.
 */

@ActivityScoped
@Component(modules = VideoDetailModule.class, dependencies = ApplicationComponent.class)
public interface VideoDetailComponent {
    void inject(VideoDetailActivity activity);
}