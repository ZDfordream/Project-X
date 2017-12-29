package com.amxc.project.home.presenter;

import android.app.Activity;
import android.content.Context;

import com.amxc.project.app.AppConstants;
import com.amxc.project.app.ApplicationModule;
import com.amxc.project.home.contract.HomeContract;
import com.amxc.project.home.presenter.component.DaggerPresenterComponent;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by HuangWei on 2017/11/5.
 */

public class HomePresenter implements HomeContract.Presenter {
    private Context context;
    private final HomeContract.View homeView;
    private CompositeDisposable subscriptions;

    @Inject
    HomePresenter(Context context, HomeContract.View view) {

        this.context = context;
        this.homeView = view;
        this.subscriptions = new CompositeDisposable();
        homeView.setPresenter(this);

        DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .build().inject(this);
    }


    @Override
    public void subscribe() {
        homeView.setState(AppConstants.STATE_SUCCESS);
    }

    @Override
    public void unSubscribe() {
        if(subscriptions!=null){
            subscriptions.clear();
        }
    }
}
