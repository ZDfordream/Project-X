package com.amxc.project.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.amxc.library.activity.BaseMaterialActivity;
import com.amxc.project.R;
import com.amxc.project.Utils.LogUtils;
import com.amxc.project.Utils.SettingUtil;
import com.amxc.project.adapter.ImageAdapter;
import com.amxc.project.app.App;
import com.amxc.project.home.activity.component.DaggerImagePagerComponent;
import com.amxc.project.home.activity.module.ImagePagerModule;
import com.amxc.project.home.contract.ImagePagerContract;
import com.amxc.project.home.presenter.ImagePagerPresenter;
import com.amxc.project.widget.PullBackLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 变量名需要改成小写
 */

/**
 * Created by zhudong.
 */

public class ImagePagerActivity extends BaseMaterialActivity implements ImagePagerContract.View, PullBackLayout.Callback {

    @Inject
    ImagePagerPresenter presenter;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.guideGroup)
    LinearLayout guideGroup;
    @BindView(R.id.pullback)
    PullBackLayout pullback;

    private List<View> guideViewList = new ArrayList<>();
    private ColorDrawable backgroudColor;
    public static final String INTENT_IMGURLS = "imgurls";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerImagePagerComponent
                .builder()
                .applicationComponent(App.getInstance().getApplicationComponent())
                .imagePagerModule(new ImagePagerModule(this)) //请将ImagePagerModule()第一个首字母改为小写
                .build()
                .inject(this);
        initView();
    }

    private void initView() {
        backgroudColor = new ColorDrawable(Color.BLACK);
        pullback.setCallback(this);
        ArrayList<String> imgUrls = getIntent().getStringArrayListExtra(INTENT_IMGURLS);
        ImageAdapter adapter = new ImageAdapter(this, imgUrls);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < guideViewList.size(); i++) {
                    guideViewList.get(i).setSelected(i == position ? true : false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        addGuideView(guideGroup, 0, imgUrls);
    }

    private void addGuideView(LinearLayout guideGroup, int startPos, ArrayList<String> imgUrls) {
        if (imgUrls != null && imgUrls.size() > 0) {
            guideViewList.clear();
            for (int i = 0; i < imgUrls.size(); i++) {
                View view = new View(this);
                view.setBackgroundResource(R.drawable.selector_guide_bg);
                view.setSelected(i == startPos ? true : false);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(SettingUtil.dip2px(ImagePagerActivity.this, 6),
                        SettingUtil.dip2px(ImagePagerActivity.this, 6));
                layoutParams.setMargins(10, 0, 0, 0);
                guideGroup.addView(view, layoutParams);
                guideViewList.add(view);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_imagepager;
    }

    @Override
    public void setPresenter(ImagePagerContract.Presenter presenter) {
    }

    @Override
    public void setState(int state) {
    }

    @Override
    public void refreshView(String msg) {
    }

    public static void LaunchActivity(Activity mContext, ArrayList<String> urls) {
        Intent intent = new Intent(mContext, ImagePagerActivity.class);
        intent.putStringArrayListExtra(INTENT_IMGURLS, urls);
        mContext.startActivity(intent);
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

    @Override
    public void onPullStart() {
        guideGroup.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPull(float progress) {
        progress = Math.min(1f, progress * 3f);
        backgroudColor.setAlpha((int) (0xff/*255*/ * (1f - progress)));
        pullback.setBackgroundColor(backgroudColor.getColor());

    }

    @Override
    public void onPullCancel() {
        guideGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPullComplete() {
        supportFinishAfterTransition();
    }
}