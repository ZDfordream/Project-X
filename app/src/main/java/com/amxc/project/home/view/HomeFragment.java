package com.amxc.project.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.amxc.library.entity.BasePostJsonBean;
import com.amxc.library.fragment.BaseFragment;
import com.amxc.library.util.RxSchedulerUtils;
import com.amxc.project.R;
import com.amxc.project.Utils.NewsJsonUtils;
import com.amxc.project.Utils.OkhttpUtils;
import com.amxc.project.adapter.BaseFragmentAdapter;
import com.amxc.project.app.NotApiThrowableConsumer;
import com.amxc.project.home.contract.HomeContract;
import com.amxc.project.home.model.entity.NewsDetialEntity;
import com.amxc.project.home.model.http.HomeHttpApi;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * Created by zhudong on 2017/11/5.
 */

public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View {

    public static final int ONE = 0;
    public static final int TWO = 1;
    public static final int THREE = 2;
    public static final int FOUR = 3;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private String[] titles = {"头条", "NBA", "汽车", "笑话"};
    private Fragment[] mFragments = new Fragment[4];
    private BaseFragmentAdapter mAdapter;

    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void refreshView(String data) {
    }

    @Override
    protected void loadData() {
        presenter.subscribe();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mFragments[0] = NewsClassfiFragment.getInstance(ONE);
        mFragments[1] = NewsClassfiFragment.getInstance(TWO);
        mFragments[2] = NewsClassfiFragment.getInstance(THREE);
        mFragments[3] = NewsClassfiFragment.getInstance(FOUR);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        mAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mFragments, titles);
        viewPager.setAdapter(mAdapter);
        tabs.setupWithViewPager(viewPager);
    }


    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setState(int state) {
        mLoadingPage.state = state;
        mLoadingPage.showPage();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
