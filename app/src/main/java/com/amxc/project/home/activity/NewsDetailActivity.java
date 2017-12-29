package com.amxc.project.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.amxc.library.activity.BaseMaterialActivity;
import com.amxc.project.R;
import com.amxc.project.Utils.GlideUtils;
import com.amxc.project.Utils.LogUtils;
import com.amxc.project.app.App;
import com.amxc.project.home.activity.component.DaggerNewsDetailComponent;
import com.amxc.project.home.activity.module.NewsDetailModule;
import com.amxc.project.home.contract.NewsDetailContract;
import com.amxc.project.home.model.entity.NewsEntity;
import com.amxc.project.home.presenter.NewsDetailPresenter;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 变量名需要改成小写
 */

/**
 * Created by zhudong on 17-11-17.
 */

public class NewsDetailActivity extends BaseMaterialActivity implements NewsDetailContract.View {

    @Inject
    NewsDetailPresenter presenter;//请将NewsDetailPresenter第一个首字母改为小写
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.htNewsContent)
    HtmlTextView htNewsContent;
    public NewsEntity mEntity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEntity = (NewsEntity) getIntent().getSerializableExtra("news");
        DaggerNewsDetailComponent
                .builder()
                .applicationComponent(App.getInstance().getApplicationComponent())
                .newsDetailModule(new NewsDetailModule(this))
                .build()
                .inject(this);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_details;
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        collapsingToolbar.setTitle(mEntity.getTitle());
        GlideUtils.displayImage(NewsDetailActivity.this,mEntity.getImgsrc(),ivImage);
    }

    public static void LaunchActivity(Activity mContext, NewsEntity newsEntity) {
        Intent intent = new Intent(mContext, NewsDetailActivity.class);
        intent.putExtra("news", newsEntity);
        mContext.startActivity(intent);
    }

    @Override
    public void setPresenter(NewsDetailContract.Presenter presenter) {
    }

    @Override
    public void setState(int state) {
    }

    @Override
    public void refreshView(String msg) {
        LogUtils.e(msg+"");
        try {
            htNewsContent.setHtmlFromString(msg, new HtmlTextView.LocalImageGetter());
        } catch (Exception e) {
        }
    }

    @Override
    public String getDataString() {
        return mEntity.getDocid();
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
}