package com.amxc.project.movie.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.amxc.library.fragment.BaseFragment;
import com.amxc.project.R;
import com.amxc.project.adapter.MovieAdapter;
import com.amxc.project.mine.contract.MineContract;
import com.amxc.project.movie.activity.VideoDetailActivity;
import com.amxc.project.movie.contract.MovieContract;
import com.amxc.project.movie.model.entity.MovieEntity;
import com.amxc.project.widget.EasyLoadMoreView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhudong.
 */

public class MovieFragment extends BaseFragment<MovieContract.Presenter> implements MovieContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;

    private MovieAdapter mMovieAdapter;

    public static MovieFragment getInstance() {
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }

    @Override
    protected void loadData() {
        presenter.subscribe();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initView() {
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mRefreshLayout.setOnRefreshListener(this);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMovieAdapter = new MovieAdapter(getContext(),null);
        mMovieAdapter.bindToRecyclerView(mRecyclerView);
        mMovieAdapter.disableLoadMoreIfNotFullPage();
        mMovieAdapter.setLoadMoreView(new EasyLoadMoreView());
        mMovieAdapter.setOnLoadMoreListener(this,mRecyclerView);
        mMovieAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<MovieEntity.V9LG4B3A0Bean>  data = adapter.getData();
            if(data.size()>0){
                VideoDetailActivity.launch(getActivity(), data.get(position).getMp4_url(), data.get(position).getTitle(), 0);
            }
        });
    }

    @Override
    public void refreshView(List<MovieEntity.V9LG4B3A0Bean> list) {
        if(mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);//停止刷新
            mMovieAdapter.setNewData(list);
        }else{
            mRecyclerView.setEnabled(true);
            mMovieAdapter.loadMoreComplete();//停止加载更多
            mMovieAdapter.addData(list);
        }
    }

    @Override
    public void stopFetchData() {
        if(mRefreshLayout!=null&&mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);//停止刷新
        }
        if(mMovieAdapter!=null){
            mMovieAdapter.loadMoreComplete();//停止加载更多
        }
    }

    @Override
    public void setPresenter(MovieContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void setState(int state) {
        mLoadingPage.state = state;
        mLoadingPage.showPage();
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.setRefreshing(true);
        presenter.refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.setEnabled(false);
        presenter.loadmore();
    }
}
