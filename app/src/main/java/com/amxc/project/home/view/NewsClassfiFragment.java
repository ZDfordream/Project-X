package com.amxc.project.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amxc.library.util.RxSchedulerUtils;
import com.amxc.project.R;
import com.amxc.project.Utils.NewsJsonUtils;
import com.amxc.project.Utils.ToastUtil;
import com.amxc.project.adapter.MultipleItemQuickAdapter;
import com.amxc.project.app.AppConstants;
import com.amxc.project.app.NotApiThrowableConsumer;
import com.amxc.project.home.activity.ImagePagerActivity;
import com.amxc.project.home.activity.NewsDetailActivity;
import com.amxc.project.home.model.entity.NewsEntity;
import com.amxc.project.home.model.http.ApiUtils;
import com.amxc.project.home.model.http.HomeHttpApi;
import com.amxc.project.widget.EasyLoadMoreView;
import com.amxc.project.widget.WrapContentLinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by zhudong on 17-12-15.
 */

public class NewsClassfiFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    Unbinder unbinder;
    private int type = HomeFragment.ONE;
    private int pageIndex = 0;
    private CompositeDisposable subscriptions = new CompositeDisposable();
    private MultipleItemQuickAdapter multipleItemAdapter;
    private boolean isRefresh = false;//判断是否下拉刷新的标记
    private ArrayList<String> images = new ArrayList<>();

    /**
     * 获取NewsClassfiFragment的实例
     * @param type
     * @return
     */
    public static NewsClassfiFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        NewsClassfiFragment fragment = new NewsClassfiFragment();
        bundle.putInt(AppConstants.NEWSCLASSFI_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_classfi, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        multipleItemAdapter = new MultipleItemQuickAdapter(getActivity(), null);
        WrapContentLinearLayoutManager manager = new WrapContentLinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        multipleItemAdapter.bindToRecyclerView(recyclerView);
        multipleItemAdapter.disableLoadMoreIfNotFullPage();
        type = getArguments().getInt(AppConstants.NEWSCLASSFI_TYPE);
        HomeHttpApi.getNewsData(ApiUtils.getUrl(type, pageIndex))
                .compose(RxSchedulerUtils.normalSchedulersTransformer())
                .doOnSubscribe(disposable1 -> subscriptions.add(disposable1))
                .subscribe(s -> NewsClassfiFragment.this.dealData(s), new NotApiThrowableConsumer());
        refreshLayout.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshLayout.setOnRefreshListener(this);
        multipleItemAdapter.setLoadMoreView(new EasyLoadMoreView());
        multipleItemAdapter.setOnLoadMoreListener(this,recyclerView);
        initLisenters();
    }

    private void initLisenters() {
        multipleItemAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<NewsEntity> newsEntities = adapter.getData();
            if(newsEntities.get(position).getImgextra() == null){
                NewsEntity newsEntity = newsEntities.get(position);
                NewsDetailActivity.LaunchActivity(getActivity(),newsEntity);
            }else{
                images.clear();
                for (int i = 0; i < newsEntities.get(position).getImgextra().size(); i++) {
                    images.add(newsEntities.get(position).getImgextra().get(i).getImgsrc());
                }
                ImagePagerActivity.LaunchActivity(getActivity(),images);
            }

        });
    }

    /**
     * 处理请求返回的接口数据，展示数据
     * @param s
     */
    private void dealData(String s) {
        List<NewsEntity> newsEntities = NewsJsonUtils.readJsonDataBeans(s, ApiUtils.getID(type));
        for(int i=0;i<newsEntities.size();i++){
            if(newsEntities.get(i).getImgextra()== null){
                newsEntities.get(i).setType(NewsEntity.SINGLE_IMAGE);
            }else{
                newsEntities.get(i).setType(NewsEntity.MULTI_IMAGES);
            }
        }
        if(isRefresh){
            isRefresh=false;
            refreshLayout.setRefreshing(false);//停止刷新
            multipleItemAdapter.setNewData(newsEntities);
        }else{
            multipleItemAdapter.loadMoreComplete();//停止加载更多
            multipleItemAdapter.addData(newsEntities);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscriptions != null) {
            subscriptions.clear();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        pageIndex = 0;
        refreshLayout.setRefreshing(true);
        HomeHttpApi.getNewsData(ApiUtils.getUrl(type, pageIndex))
                .compose(RxSchedulerUtils.normalSchedulersTransformer())
                .doOnSubscribe(disposable1 -> subscriptions.add(disposable1))
                .subscribe(s -> NewsClassfiFragment.this.dealData(s), new NotApiThrowableConsumer());

    }

    @Override
    public void onLoadMoreRequested() {
        pageIndex++;
        HomeHttpApi.getNewsData(ApiUtils.getUrl(type, pageIndex))
                .compose(RxSchedulerUtils.normalSchedulersTransformer())
                .doOnSubscribe(disposable1 -> subscriptions.add(disposable1))
                .subscribe(s -> NewsClassfiFragment.this.dealData(s), new NotApiThrowableConsumer());
    }
}
