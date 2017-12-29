package com.amxc.project.home.model.http;

import com.amxc.library.entity.BasePostJsonBean;
import com.amxc.project.Utils.OkhttpUtils;
import com.amxc.project.home.model.entity.AuthStatusEntity;
import com.amxc.project.home.model.entity.BaseEntity;

import io.reactivex.Observable;

/**
 * Created by zhudong on 17-11-7.
 */

public class HomeHttpApi {

    static String url = "https://api.douban.com/v2/movie/top250";
    public static Observable<String> getMovieTop250() {
        BaseEntity baseBean = new BaseEntity();
        baseBean.setStart(1);
        baseBean.setCount(10);
        return OkhttpUtils.request(url, baseBean);
    }

    public static Observable<String> getNewsData(String url) {
        return OkhttpUtils.request(url);
    }


    public static Observable<AuthStatusEntity> commitcode(String phone) {
        String url = ApiConstants.COMMITCODE;
        AuthStatusEntity authStatusEntity = new AuthStatusEntity();
        authStatusEntity.setPhone(phone);
        return OkhttpUtils.request(url, AuthStatusEntity.class, authStatusEntity);
    }

}
