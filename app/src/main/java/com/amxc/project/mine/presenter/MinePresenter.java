package com.amxc.project.mine.presenter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;

import com.amxc.project.app.ApplicationModule;
import com.amxc.project.Utils.ActivityScoped;
import com.amxc.project.mine.contract.MineContract;
import com.amxc.project.mine.model.entity.TabEntity;
import com.amxc.project.mine.presenter.component.DaggerPresenterComponent;
import com.amxc.project.mine.view.ItemFragment1;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by amxc on 17-8-23.
 */
@ActivityScoped
public class MinePresenter implements MineContract.Presenter {
    private Context context;
    private MineContract.View view;
    private CompositeDisposable subscriptions;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Inject
    MinePresenter(Context context, MineContract.View view) {

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
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setState(4);
            }
        },400);
    }

    @Override
    public void unSubscribe() {
        if(subscriptions!=null){
            subscriptions.clear();
        }
    }

    /**
     * 假数据
     *
     * @return
     */
    public ArrayList<CustomTabEntity> getTabEntities(String[] mNames) {
        for (String str : mNames) {
            mTabEntities.add(new TabEntity(String.valueOf(new Random().nextInt(200)), str));
        }
        return mTabEntities;
    }

    @Override
    public Fragment[] getFragments() {
        Fragment[] fragments = new Fragment[4];
        fragments[0] = new ItemFragment1();
        fragments[1] = new ItemFragment1();
        fragments[2] = new ItemFragment1();
        fragments[3] = new ItemFragment1();
        return fragments;
    }
}
