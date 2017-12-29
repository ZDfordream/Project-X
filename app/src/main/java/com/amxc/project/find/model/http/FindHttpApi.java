package com.amxc.project.find.model.http;

import com.amxc.project.Utils.OkhttpUtils;

import io.reactivex.Observable;

/**
 * Created by zhudong on 17-11-7.
 */

public class FindHttpApi {

    public static Observable<String> getFindData(String url) {
        return OkhttpUtils.request(url);
    }

}
