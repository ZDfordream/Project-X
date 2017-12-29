package com.amxc.project.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    App getApplication();

    Context getContext();
}
