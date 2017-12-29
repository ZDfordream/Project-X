package com.amxc.project.movie.model.http;

import com.amxc.project.Utils.OkhttpUtils;
import com.amxc.project.home.model.entity.BaseEntity;

import io.reactivex.Observable;

/**
 * Created by zhudong on 17-11-7.
 */

public class MovieHttpApi {

    public static Observable<String> getMovieData(String url) {
        return OkhttpUtils.request(url);
    }

}
