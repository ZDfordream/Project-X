package com.amxc.project.Utils;


import com.amxc.library.entity.BaseHttpHeader;
import com.amxc.library.entity.BasePostJsonBean;


import com.amxc.library.entity.JsonConvert;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx2.adapter.ObservableBody;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;


/**
 * Created by zhudong on 2017/6/21.
 */

public class OkhttpUtils {

    /**
     * @param url
     * @return
     */
    public static Observable<String> request(String url) {
        return OkGo.<String>post(url)
                .converter(new StringConvert())
                .adapt(new ObservableBody<String>());
    }

    /**
     * 默认的api版本1
     *
     * @param url
     * @param para
     * @return
     */
    public static <P> Observable<String> request(String url, P para) {
        return request(url, 1, para, null);
    }



    /**
     * @param url
     * @param version api的版本
     * @param para    post上传的附加数据
     * @return
     */
    public static <P> Observable<String> request(String url, Integer version, P para, String mdkey) {
        BasePostJsonBean<P> jsonBean = new BasePostJsonBean<>();
        jsonBean.setHead(new BasePostJsonBean.HeadBean(22, mdkey));
        jsonBean.setPara(para);
        return OkGo.<String>post(url)
                .upJson(new Gson().toJson(jsonBean))
                .converter(new StringConvert())
                .adapt(new ObservableBody<String>());
    }


    /**
     * 请求单个数据接口
     *
     * @param url   　接口访问地址
     * @param clazz 接口返回的data数据的class对象
     * @param para  传入的请求参数的对象
     * @param <T>   接口返回的对象
     * @param <P>   请求传入的对象
     * @return
     */
    public static <T, P> Observable<T> request(String url, @NotNull Class<T> clazz, @NotNull P para) {
        return OkGo.<T>post(url)
                .headers(BaseHttpHeader.getInstance().getHttpHeaders())
                .upJson(new Gson().toJson(para))//也可以使用.upJson(JSON.toJSONString(para))
                .converter(new JsonConvert<T>(clazz))
                .adapt(new ObservableBody<T>());
    }


}