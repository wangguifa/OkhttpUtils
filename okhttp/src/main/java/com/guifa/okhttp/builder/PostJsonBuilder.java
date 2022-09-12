package com.guifa.okhttp.builder;

import com.google.gson.Gson;
import com.guifa.okhttp.request.PostStringRequest;
import com.guifa.okhttp.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * 构建 Post Json 请求 参数
 */
public class PostJsonBuilder extends BaseRequestBuilder<PostJsonBuilder> {

    private final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    public PostJsonBuilder addParams(Map<String, Object> params) {
        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        this.params.putAll(params);
        return this;
    }

    public PostJsonBuilder addParam(String key, Object val) {
        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        this.params.put(key, val);
        return this;
    }

    @Override
    public RequestCall build() {
        String content = new Gson().toJson(this.params);
        return new PostStringRequest(url, tag, params, headers, content, mediaType, id).build();
    }
}