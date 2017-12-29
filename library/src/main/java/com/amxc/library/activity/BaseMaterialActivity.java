package com.amxc.library.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Window;


import com.amxc.library.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * @author zhudong
 */
public abstract class BaseMaterialActivity extends AppCompatActivity {
    /*
     * 解决Vector兼容性问题
     *
     */
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    // 管理运行的所有的activity
    public final static List<AppCompatActivity> mActivities = new LinkedList<AppCompatActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        synchronized (mActivities) {
            mActivities.add(this);
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
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    protected abstract int getLayoutId();

}
