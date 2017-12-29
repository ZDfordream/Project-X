package com.amxc.project.movie.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.amxc.library.util.RxSchedulerUtils;
import com.amxc.project.Utils.JsonUtils;
import com.amxc.project.Utils.NewsJsonUtils;
import com.amxc.project.Utils.ToastUtil;
import com.amxc.project.app.Apis;
import com.amxc.project.app.AppConstants;
import com.amxc.project.app.ApplicationModule;
import com.amxc.project.app.NotApiThrowableConsumer;
import com.amxc.project.home.model.entity.NewsDetialEntity;
import com.amxc.project.home.model.http.HomeHttpApi;
import com.amxc.project.movie.contract.MovieContract;
import com.amxc.project.movie.model.entity.MovieEntity;
import com.amxc.project.movie.model.http.MovieHttpApi;
import com.amxc.project.movie.presenter.component.DaggerPresenterComponent;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by HuangWei on 2017/11/5.
 */

public class MoviePresenter implements MovieContract.Presenter{

    private Context context;
    private MovieContract.View view;
    private CompositeDisposable subscriptions;
    private int pageIndex = 0;

    @Inject
    MoviePresenter(Context context, MovieContract.View view) {
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
        fetchData(pageIndex);
    }

    public void fetchData(int pageIndex) {
        MovieHttpApi.getMovieData(Apis.HOST + Apis.Video + Apis.VIDEO_HOT_ID + "/n/" + pageIndex + "-10.html")
                .compose(RxSchedulerUtils.normalSchedulersTransformer())
                .doOnSubscribe(disposable1 -> subscriptions.add(disposable1))
                .doFinally(() -> view.stopFetchData())
                .subscribe(response -> {
                    view.setState(AppConstants.STATE_SUCCESS);
                    MovieEntity entity = JsonUtils.deserialize(response, MovieEntity.class);
                    view.refreshView(entity.getV9LG4B3A0());
                }, new NotApiThrowableConsumer());
    }

    @Override
    public void unSubscribe() {
        if(subscriptions!=null){
            subscriptions.clear();
        }
    }

    @Override
    public void refresh() {
        pageIndex = 0;
        fetchData(pageIndex);
    }

    @Override
    public void loadmore() {
        pageIndex++;
        fetchData(pageIndex);
    }
}
