package com.amxc.project.mine.presenter.component;


import com.amxc.project.app.ApplicationModule;
import com.amxc.project.mine.presenter.MinePresenter;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class})
public interface PresenterComponent {

    void inject(MinePresenter presenter);

}
 