package com.amxc.project.home.presenter.component;


import com.amxc.project.app.ApplicationModule;
import com.amxc.project.home.presenter.HomePresenter;
import com.amxc.project.home.presenter.ImagePagerPresenter;
import com.amxc.project.home.presenter.NewsDetailPresenter;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class})
public interface PresenterComponent {
    void inject(HomePresenter presenter);

    void inject(NewsDetailPresenter presenter);

    void inject(ImagePagerPresenter presenter);

}
 