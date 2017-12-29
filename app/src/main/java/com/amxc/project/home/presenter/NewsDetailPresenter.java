package com.amxc.project.home.presenter;

import android.app.Application;

import javax.inject.Inject;

import android.content.Context;
import android.os.Handler;

import com.amxc.library.util.RxSchedulerUtils;
import com.amxc.project.Utils.ActivityScoped;
import com.amxc.project.Utils.LogUtils;
import com.amxc.project.Utils.NewsJsonUtils;
import com.amxc.project.Utils.ToastUtil;
import com.amxc.project.app.Apis;
import com.amxc.project.app.AppConstants;
import com.amxc.project.app.ApplicationModule;
import com.amxc.project.app.NotApiThrowableConsumer;
import com.amxc.project.home.contract.NewsDetailContract;
import com.amxc.project.home.model.entity.NewsDetialEntity;
import com.amxc.project.home.model.http.ApiUtils;
import com.amxc.project.home.model.http.HomeHttpApi;
import com.amxc.project.home.presenter.component.DaggerPresenterComponent;
import com.amxc.project.home.view.NewsClassfiFragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by zhudong on 17-11-17.
 */

@ActivityScoped
public class NewsDetailPresenter implements NewsDetailContract.Presenter {
    private Context context;
    private final NewsDetailContract.View view;
    private CompositeDisposable subscriptions;

    @Inject
    NewsDetailPresenter(Context context, NewsDetailContract.View view) {
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
        String detailUrl = getDetailUrl(view.getDataString());
        HomeHttpApi.getNewsData(detailUrl)
                .compose(RxSchedulerUtils.normalSchedulersTransformer())
                .doOnSubscribe(disposable1 -> subscriptions.add(disposable1))
                .subscribe(response -> {
                    NewsDetialEntity newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, view.getDataString());
                    view.refreshView(newsDetailBean.getBody());
                }, new NotApiThrowableConsumer());
    }

    @Override
    public void unSubscribe() {
        if (subscriptions != null) {
            subscriptions.clear();
        }
    }

    private String getDetailUrl(String docId) {
        StringBuffer sb = new StringBuffer(Apis.NEW_DETAIL);
        sb.append(docId).append(Apis.END_DETAIL_URL);
        return sb.toString();
    }

}