package com.guifa.okhttp.builder;

import com.guifa.okhttp.request.OtherRequest;
import com.guifa.okhttp.request.RequestCall;

import okhttp3.RequestBody;

/**
 * DELETE、PUT、PATCH等其他方法
 */
public class OtherBuilder extends BaseRequestBuilder<OtherBuilder> {

    private RequestBody requestBody;
    private String method;
    private String content;

    public OtherBuilder(String method) {
        this.method = method;
    }

    @Override
    public RequestCall build() {
        return new OtherRequest(requestBody, content, method, url, tag, params, headers, id).build();
    }

    public OtherBuilder requestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public OtherBuilder requestBody(String content) {
        this.content = content;
        return this;
    }
}