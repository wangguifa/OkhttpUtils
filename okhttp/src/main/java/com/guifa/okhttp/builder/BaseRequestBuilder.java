package com.guifa.okhttp.builder;

import com.guifa.okhttp.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseRequestBuilder<T extends BaseRequestBuilder<T>> {

    protected String url;
    protected Object tag;
    protected Map<String, Object> headers;
    protected Map<String, Object> params;
    protected int id;

    public T id(int id) {
        this.id = id;
        return (T) this;
    }

    public T url(String url) {
        this.url = url;
        return (T) this;
    }

    public T tag(Object tag) {
        this.tag = tag;
        return (T) this;
    }

    public T headers(Map<String, Object> headers) {
        this.headers = headers;
        return (T) this;
    }

    public T addHeader(String key, String val) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return (T) this;
    }

    public abstract RequestCall build();
}