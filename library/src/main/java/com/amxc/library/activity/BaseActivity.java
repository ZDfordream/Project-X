package com.amxc.library.activity;


import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amxc.library.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhudong
 */
public class BaseActivity extends AppCompatActivity {
    /*
     * 解决Vector兼容性问题
     *
     */
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    // 管理运行的所有的activity
    public final static List<AppCompatActivity> mActivities = new LinkedList<AppCompatActivity>();
    protected Integer fragmentContentId = R.id.fl_content;
    private RelativeLayout rl_title;
    private ImageView img_title_left;
    private TextView tv_title_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        img_title_left = (ImageView) findViewById(R.id.img_title_left);
        tv_title_title = (TextView) findViewById(R.id.tv_title_title);
        getSupportActionBar().hide();
        synchronized (mActivities) {
            mActivities.add(this);
        }
    }

    protected void hideTittleLayout(boolean flag) {
        if(flag){
            rl_title.setVisibility(View.GONE);
        }else{
            rl_title.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivities) {
            mActivities.remove(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void killAll() {
        // 复制了一份mActivities 集合Å
        List<AppCompatActivity> copy;
        synchronized (mActivities) {
            copy = new LinkedList<>(mActivities);
        }
        for (AppCompatActivity activity : copy) {
            activity.finish();
        }
        // 杀死当前的进程
        Process.killProcess(Process.myPid());
    }

}
