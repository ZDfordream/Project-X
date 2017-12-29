package com.amxc.project.movie.presenter;

import android.app.Application;

import javax.inject.Inject;

import android.content.Context;
import android.os.Handler;

import com.amxc.library.util.RxSchedulerUtils;
import com.amxc.project.Utils.ActivityScoped;
import com.amxc.project.Utils.JsonUtils;
import com.amxc.project.Utils.ToastUtil;
import com.amxc.project.app.Apis;
import com.amxc.project.app.AppConstants;
import com.amxc.project.app.ApplicationModule;
import com.amxc.project.app.NotApiThrowableConsumer;
import com.amxc.project.movie.contract.VideoDetailContract;
import com.amxc.project.movie.model.entity.MovieEntity;
import com.amxc.project.movie.model.http.MovieHttpApi;
import com.amxc.project.movie.presenter.component.DaggerPresenterComponent;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhudong.
 */

@ActivityScoped
public class VideoDetailPresenter implements VideoDetailContract.Presenter {
    private Context context;
    private final VideoDetailContract.View view;
    private CompositeDisposable subscriptions;

    @Inject
    VideoDetailPresenter(Context context, VideoDetailContract.View view) {
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
        MovieHttpApi.getMovieData(Apis.HOST + Apis.Video + Apis.VIDEO_HOT_ID + "/n/" + 0 + "-10.html")
                .compose(RxSchedulerUtils.normalSchedulersTransformer())
                .doOnSubscribe(disposable1 -> subscriptions.add(disposable1))
                .subscribe(response -> {
                    view.setState(AppConstants.STATE_SUCCESS);
                    MovieEntity entity = JsonUtils.deserialize(response, MovieEntity.class);
                    view.refreshView(entity.getV9LG4B3A0());
                }, new NotApiThrowableConsumer());
    }

    @Override
    public void unSubscribe() {
        if (subscriptions != null) {
            subscriptions.clear();
        }
    }
}