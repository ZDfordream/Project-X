package com.amxc.project.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amxc.library.fragment.BaseFragment;
import com.amxc.project.R;
import com.amxc.project.Utils.GlideUtils;
import com.amxc.project.Utils.ToastUtil;
import com.amxc.project.adapter.BaseFragmentAdapter;
import com.amxc.project.mine.contract.MineContract;
import com.amxc.project.widget.CircleImageView;
import com.amxc.project.widget.NoScrollViewPager;
import com.amxc.project.widget.RoundProgressBar;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zhudong on 2017/11/5.
 */

public class MineFragment extends BaseFragment<MineContract.Presenter> implements MineContract.View {
    @BindView(R.id.uc_zoomiv)
    ImageView mZoomIv;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.title_center_layout)
    LinearLayout titleCenterLayout;
    @BindView(R.id.uc_progressbar)
    RoundProgressBar ucProgressbar;
    @BindView(R.id.uc_setting_iv)
    ImageView mSettingIv;
    @BindView(R.id.uc_msg_iv)
    ImageView mMsgIv;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.uc_viewpager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.uc_avater)
    CircleImageView mAvater;
    @BindView(R.id.uc_tablayout)
    CommonTabLayout mTablayout;
    @BindView(R.id.title_uc_avater)
    CircleImageView titleUcAvater;

    private String[] mNames = new String[]{"Weather", "Moon", "Like", "Fans"};
    private Fragment[] fragments;
    private int lastState = 1;
    private ArrayList<ImageItem> images = null;

    public static MineFragment getInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected void loadData() {
        presenter.subscribe();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        fragments = presenter.getFragments();
        BaseFragmentAdapter myFragmentPagerAdapter = new BaseFragmentAdapter(getChildFragmentManager(), fragments, mNames);
        mTablayout.setTabData(presenter.getTabEntities(mNames));
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mTablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTablayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            float percent = Float.valueOf(Math.abs(verticalOffset)) / Float.valueOf(appBarLayout.getTotalScrollRange());
            if (titleCenterLayout != null && mAvater != null && mSettingIv != null && mMsgIv != null) {
                titleCenterLayout.setAlpha(percent);
                if (percent == 0) {
                    MineFragment.this.groupChange(1f, 1);
                } else if (percent == 1) {
                    if (mAvater.getVisibility() != View.GONE) {
                        mAvater.setVisibility(View.GONE);
                    }
                    MineFragment.this.groupChange(1f, 2);
                } else {
                    if (mAvater.getVisibility() != View.VISIBLE) {
                        mAvater.setVisibility(View.VISIBLE);
                    }
                    MineFragment.this.groupChange(percent, 0);
                }

            }
        });

        mAvater.setOnClickListener(v -> {
            selectImage();

        });
        titleUcAvater.setOnClickListener(v -> {
            selectImage();
        });
    }

    private void selectImage() {
        Intent intent = new Intent(getContext(), ImageGridActivity.class);
        //intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                GlideUtils.displayImage(getContext(), images.get(0).path, mAvater);
                GlideUtils.displayImage(getContext(), images.get(0).path, titleUcAvater);
            } else {
                ToastUtil.showToast("没有数据");
            }
        }
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setState(int state) {
        mLoadingPage.state = state;
        mLoadingPage.showPage();
    }

    /**
     * @param alpha
     * @param state 0-正在变化 1展开 2 关闭
     */
    public void groupChange(float alpha, int state) {
        lastState = state;

        mSettingIv.setAlpha(alpha);
        mMsgIv.setAlpha(alpha);

        switch (state) {
            case 1://完全展开 显示白色
                mMsgIv.setImageResource(R.mipmap.icon_msg);
                mSettingIv.setImageResource(R.mipmap.icon_setting);
                mViewPager.setNoScroll(false);
                break;
            case 2://完全关闭 显示黑色
                mMsgIv.setImageResource(R.mipmap.icon_msg_black);
                mSettingIv.setImageResource(R.mipmap.icon_setting_black);
                mViewPager.setNoScroll(false);
                break;
            case 0://介于两种临界值之间 显示黑色
                if (lastState != 0) {
                    mMsgIv.setImageResource(R.mipmap.icon_msg_black);
                    mSettingIv.setImageResource(R.mipmap.icon_setting_black);
                }
                mViewPager.setNoScroll(true);
                break;
        }
    }

    @OnClick({R.id.func_music, R.id.func_orange, R.id.func_swim, R.id.func_soccer, R.id.func_storm, R.id.func_book, R.id.func_basketball, R.id.func_lighting})
    public void onViewClicked(View view) {
        TextView textView = (TextView) view;
        ToastUtil.showToast("跳转到" + textView.getText() + "界面");
    }

}
