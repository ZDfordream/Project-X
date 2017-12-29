package com.amxc.project.find.presenter.component;

import com.amxc.project.app.ApplicationModule;
import com.amxc.project.find.presenter.FindPresenter;
import com.amxc.project.movie.presenter.MoviePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HuangWei on 2017/11/5.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface PresenterComponent {
    void inject(FindPresenter presenter);
}
