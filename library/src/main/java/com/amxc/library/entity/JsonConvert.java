package com.amxc.library.entity;


import com.google.gson.Gson;
import com.lzy.okgo.convert.Converter;
import com.lzy.okgo.exception.HttpException;

import okhttp3.Response;

/**
 * Created by zhudong on 2017/11/21.
 */

public class JsonConvert<T> implements Converter<T> {
    private Class<T> clazz;

    public JsonConvert(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        return parseClass(response, clazz);
    }

    private T parseClass(Response response, Class<T> clazz) throws Exception {
        if (response.code() == 200) {
            String s = response.body().string();
            try{
                if (clazz == String.class) {
                    return (T) response.body().string();
                }else{
                    T t = new Gson().fromJson(s, clazz);
                    return  t;
                }
            }catch (Exception e){
                throw new HttpException("---解析出错---");
            }
        } else {
            throw new HttpException(" ");
        }
    }
}
