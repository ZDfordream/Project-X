package com.amxc.project.home.presenter;

import android.app.Application;

import javax.inject.Inject;

import android.content.Context;
import android.os.Handler;

import com.amxc.project.Utils.ActivityScoped;
import com.amxc.project.app.AppConstants;
import com.amxc.project.app.ApplicationModule;
import com.amxc.project.home.contract.ImagePagerContract;
import com.amxc.project.home.presenter.component.DaggerPresenterComponent;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by zhudong.
 */

@ActivityScoped
public class ImagePagerPresenter implements ImagePagerContract.Presenter {
    private Context context;
    private final ImagePagerContract.View view;
    private CompositeDisposable subscriptions;

    @Inject
    ImagePagerPresenter(Context context, ImagePagerContract.View view) {
        this.context = context;
        this.view = view;
        this.subscriptions = new CompositeDisposable();
        view.setPresenter(this);
        DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .build().inject(this);
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unSubscribe() {
        if (subscriptions != null) {
            subscriptions.clear();
        }
    }
}