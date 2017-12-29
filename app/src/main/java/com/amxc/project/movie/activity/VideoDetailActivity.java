package com.amxc.project.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amxc.library.activity.BaseMaterialActivity;
import com.amxc.project.R;
import com.amxc.project.adapter.MovieAdapter;
import com.amxc.project.app.App;
import com.amxc.project.movie.activity.component.DaggerVideoDetailComponent;
import com.amxc.project.movie.activity.module.VideoDetailModule;
import com.amxc.project.movie.contract.VideoDetailContract;
import com.amxc.project.movie.model.entity.MovieEntity;
import com.amxc.project.movie.presenter.VideoDetailPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import amxc.videomodel.VideoPlayer;
import butterknife.BindView;

import static com.amxc.project.Utils.ToastUtil.showToast;

/**
 * 变量名需要改成小写
 */

/**
 * Created by zhudong.
 */

public class VideoDetailActivity extends BaseMaterialActivity implements VideoDetailContract.View {

    @Inject
    VideoDetailPresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewPager)
    VideoPlayer mViewPager;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private int pageIndex;
    private String url;
    private String title;
    private MovieAdapter mMovieAdapter;
    private List<MovieEntity.V9LG4B3A0Bean> datalist = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerVideoDetailComponent
                .builder()
                .applicationComponent(App.getInstance().getApplicationComponent())
                .videoDetailModule(new VideoDetailModule(this)) //请将VideoDetailModule()第一个首字母改为小写
                .build()
                .inject(this);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        pageIndex = intent.getIntExtra("pageIndex", 0);
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        mViewPager.playVideo(url, title);
        mToolbar.setContentInsetStartWithNavigation(0);
        mToolbar.inflateMenu(R.menu.menu_video_detail);
        mToolbar.setNavigationOnClickListener(v -> finish());
        mToolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.search:
                    showToast("搜索");
                    break;
                case R.id.love:
                    showToast("喜欢");
                    break;
                case R.id.share:
                    showToast("分享");
                    break;
                case R.id.report:
                    showToast("举报成功");
                    break;
                default:
                    break;
            }
            return false;
        });

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMovieAdapter = new MovieAdapter(VideoDetailActivity.this,null);
        mRecyclerView.setAdapter(mMovieAdapter);
        mMovieAdapter.setOnItemClickListener((adapter, view, position) -> {
            mViewPager.playVideo(datalist.get(position).getMp4_url(), datalist.get(position).getTitle());
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_detail;
    }

    @Override
    public void setPresenter(VideoDetailContract.Presenter presenter) {

    }

    @Override
    public void setState(int state) {
    }

    @Override
    public void refreshView(List<MovieEntity.V9LG4B3A0Bean> list) {
        datalist.clear();
        datalist.addAll(list);
        mMovieAdapter.setNewData(list);
    }

    @Override
    public void onStart() {
        super.onStart();
        assert presenter != null;
        presenter.subscribe();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unSubscribe();
    }

    public static void launch(Context context, String url, String title, int pageIndex) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("pageIndex", pageIndex);
        context.startActivity(intent);
    }
}