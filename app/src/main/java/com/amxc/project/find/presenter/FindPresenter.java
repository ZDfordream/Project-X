package com.amxc.project.find.presenter;

import android.content.Context;
import android.util.Log;

import com.amxc.library.util.RxSchedulerUtils;
import com.amxc.project.Utils.ToastUtil;
import com.amxc.project.app.AppConstants;
import com.amxc.project.app.ApplicationModule;
import com.amxc.project.app.NotApiThrowableConsumer;
import com.amxc.project.find.contract.FindContract;
import com.amxc.project.find.model.entity.WXItemBean;
import com.amxc.project.find.model.http.RetrofitHelper;
import com.amxc.project.find.presenter.component.DaggerPresenterComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;


/**
 * Created by zhudong on 2017/11/5.
 */

public class FindPresenter implements FindContract.Presenter{

    private Context context;
    private FindContract.View view;
    private CompositeDisposable subscriptions;
    private int pageIndex = 1;
    private List<WXItemBean> jcodes = new ArrayList<>();

    @Inject
    FindPresenter(Context context, FindContract.View view) {

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
        RetrofitHelper.getInstance(context)
                .getServer()
                .getWXHot(AppConstants.KEY_API, 20, pageIndex)
                .compose(RxSchedulerUtils.normalSchedulersTransformer())
                .doOnSubscribe(disposable1 -> subscriptions.add(disposable1))
                .doFinally(() -> view.stopFetchData())
                .doOnError(throwable -> view.setState(3))
                .subscribe(response -> {
                    view.setState(AppConstants.STATE_SUCCESS);
                    List<WXItemBean> newslist = response.getNewslist();
                    view.refreshView(newslist);
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
