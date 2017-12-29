package com.amxc.library.entity;

import com.lzy.okgo.model.HttpHeaders;

/**
 * Created by zhudong on 17-12-12.
 */

public class BaseHttpHeader {
    private HttpHeaders httpHeaders;

    private BaseHttpHeader() {
        httpHeaders = new HttpHeaders();
    }

    private static class BaseHttpHeaderHolder {
        private static final BaseHttpHeader INSTANCE = new BaseHttpHeader();
    }

    public static final BaseHttpHeader getInstance() {
        return BaseHttpHeaderHolder.INSTANCE;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public void putHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders.put(httpHeaders);
    }
}
