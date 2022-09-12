package com.guifa.okhttp.request;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 构建 Get 请求
 */
public class GetRequest extends BaseRequest {

    public GetRequest(String url, Object tag, Map<String, Object> params, Map<String, Object> headers, int id) {
        super(url, tag, params, headers, id);
    }

    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }

    @Override
    protected Request buildRequest(RequestBody requestBody) {
        return builder.get().build();
    }
}