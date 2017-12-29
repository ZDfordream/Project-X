package com.amxc.project.find.view;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.amxc.library.fragment.BaseFragment;
import com.amxc.project.R;
import com.amxc.project.adapter.FindQuickAdapter;
import com.amxc.project.find.contract.FindContract;
import com.amxc.project.find.model.entity.WXItemBean;
import com.amxc.project.home.contract.HomeContract;
import com.amxc.project.widget.EasyLoadMoreView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhudong.
 */

public class FindFragment extends BaseFragment<FindContract.Presenter> implements FindContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.floatBtn)
    FloatingActionButton floatBtn;

    private FindQuickAdapter mFindAdapter;

    public static FindFragment getInstance() {
        FindFragment fragment = new FindFragment();
        return fragment;
    }

    @Override
    protected void loadData() {
        presenter.subscribe();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mRefreshLayout.setOnRefreshListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFindAdapter = new FindQuickAdapter(getContext(), null);
        mRecyclerView.setAdapter(mFindAdapter);
        mFindAdapter.setLoadMoreView(new EasyLoadMoreView());
        mFindAdapter.setOnLoadMoreListener(this, mRecyclerView);
    }

    @Override
    public void refreshView(List<WXItemBean> list) {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);//停止刷新
            mFindAdapter.setNewData(list);
        } else {
            mFindAdapter.loadMoreComplete();//停止加载更多
            mFindAdapter.addData(list);
        }
    }

    @Override
    public void stopFetchData() {
        if(mRefreshLayout!=null&&mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);//停止刷新
        }
        if(mFindAdapter!=null){
            mFindAdapter.loadMoreComplete();//停止加载更多
        }
    }

    @Override
    public void setPresenter(FindContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void setState(int state) {
        mLoadingPage.state = state;
        mLoadingPage.showPage();
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.setRefreshing(true);//停止刷新
        presenter.refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.loadmore();
    }
}
