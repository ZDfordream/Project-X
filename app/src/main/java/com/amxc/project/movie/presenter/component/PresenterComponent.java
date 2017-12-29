package com.amxc.project.movie.presenter.component;

import com.amxc.project.app.ApplicationModule;
import com.amxc.project.movie.presenter.MoviePresenter;
import com.amxc.project.movie.presenter.VideoDetailPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HuangWei on 2017/11/5.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface PresenterComponent {
    void inject(MoviePresenter presenter);
    void inject(VideoDetailPresenter presenter);
}
