package com.guifa.okhttp.request;

import com.guifa.okhttp.callback.BaseCallback;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class BaseRequest {

    protected String url;
    protected Object tag;
    protected Map<String, Object> params;
    protected Map<String, Object> headers;
    protected int id;

    protected Request.Builder builder = new Request.Builder();

    protected BaseRequest(String url, Object tag, Map<String, Object> params, Map<String, Object> headers, int id) {
        this.url = url;
        this.tag = tag;
        this.params = params;
        this.headers = headers;
        this.id = id;
        if (url == null) {
            throw new IllegalArgumentException("url can not be null.");
        }
        initBuilder();
    }

    /**
     * 初始化一些基本参数 url , tag , headers
     */
    private void initBuilder() {
        builder.url(url).tag(tag);
        appendHeaders();
    }

    /**
     * 添加Headers
     */
    protected void appendHeaders() {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) {
            return;
        }
        for (String key : headers.keySet()) {
            headerBuilder.add(key, String.valueOf(headers.get(key)));
        }
        builder.headers(headerBuilder.build());
    }

    protected abstract RequestBody buildRequestBody();

    protected abstract Request buildRequest(RequestBody requestBody);

    public Request generateRequest(BaseCallback baseCallback) {
        RequestBody requestBody = buildRequestBody();
        RequestBody wrappedRequestBody = wrapRequestBody(requestBody, baseCallback);
        return buildRequest(wrappedRequestBody);
    }

    protected RequestBody wrapRequestBody(RequestBody requestBody, final BaseCallback baseCallback) {
        return requestBody;
    }

    public RequestCall build() {
        return new RequestCall(this);
    }

    public int getId() {
        return id;
    }

    public Object getTag() {
        return tag;
    }
}