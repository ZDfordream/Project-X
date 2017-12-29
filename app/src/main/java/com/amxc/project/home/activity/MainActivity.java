package com.amxc.project.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.amxc.project.R;
import com.amxc.project.adapter.BaseFragmentAdapter;
import com.amxc.project.app.App;
import com.amxc.project.find.activity.module.FindModule;
import com.amxc.project.find.presenter.FindPresenter;
import com.amxc.project.find.view.FindFragment;
import com.amxc.project.home.activity.component.DaggerHomeComponent;
import com.amxc.project.home.activity.module.HomeModule;
import com.amxc.project.home.presenter.HomePresenter;
import com.amxc.project.home.view.HomeFragment;
import com.amxc.project.mine.activity.module.MineModule;
import com.amxc.project.mine.presenter.MinePresenter;
import com.amxc.project.mine.view.MineFragment;
import com.amxc.project.movie.activity.module.MovieModule;
import com.amxc.project.movie.presenter.MoviePresenter;
import com.amxc.project.movie.view.MovieFragment;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Inject
    HomePresenter homePresenter;
    @Inject
    MoviePresenter moviePresenter;
    @Inject
    FindPresenter findPresenter;
    @Inject
    MinePresenter minePresenter;

    @BindView(R.id.group)
    RadioGroup mGroup;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private HomeFragment homeFragment;
    private MovieFragment movieFragment;
    private FindFragment findFragment;
    private MineFragment mineFragment;
    private Fragment[] fragments = new Fragment[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTheme(R.style.AppTheme);
        homeFragment = HomeFragment.getInstance();
        movieFragment = MovieFragment.getInstance();
        findFragment = FindFragment.getInstance();
        mineFragment = MineFragment.getInstance();

        DaggerHomeComponent.builder()
                .applicationComponent(App.getInstance().getApplicationComponent())
                .homeModule(new HomeModule(homeFragment))
                .movieModule(new MovieModule(movieFragment))
                .findModule(new FindModule(findFragment))
                .mineModule(new MineModule(mineFragment))
                .build()
                .inject(this);
        initView();
        initLisenter();
    }

    private void initView() {
        fragments[0] = homeFragment;
        fragments[1] = movieFragment;
        fragments[2] = findFragment;
        fragments[3] = mineFragment;
        BaseFragmentAdapter mAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
    }

    private void initLisenter() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mGroup.check(R.id.rb_home);
                        break;
                    case 1:
                        mGroup.check(R.id.rb_movie);
                        break;
                    case 2:
                        mGroup.check(R.id.rb_find);
                        break;
                    case 3:
                        mGroup.check(R.id.rb_mine);
                        break;
                }

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_home:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.rb_movie:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.rb_find:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.rb_mine:
                    viewPager.setCurrentItem(3);
                    break;
            }
        });
    }
}
