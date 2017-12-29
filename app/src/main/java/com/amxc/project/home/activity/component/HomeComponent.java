package com.amxc.project.home.activity.component;

import com.amxc.project.app.ApplicationComponent;
import com.amxc.project.find.activity.module.FindModule;
import com.amxc.project.home.activity.MainActivity;
import com.amxc.project.Utils.ActivityScoped;
import com.amxc.project.home.activity.module.HomeModule;
import com.amxc.project.movie.activity.module.MovieModule;
import com.amxc.project.mine.activity.module.MineModule;

import dagger.Component;

/**
 * Created by HuangWei on 2017/11/5.
 */
@ActivityScoped
@Component(modules = {HomeModule.class, MovieModule.class, FindModule.class, MineModule.class}, dependencies = ApplicationComponent.class)
public interface HomeComponent {
    void inject(MainActivity mainActivity);
}
