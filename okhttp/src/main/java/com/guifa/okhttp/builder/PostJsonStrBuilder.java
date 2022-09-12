package com.guifa.okhttp.builder;

import com.guifa.okhttp.request.PostStringRequest;
import com.guifa.okhttp.request.RequestCall;

import okhttp3.MediaType;

/**
 * 构建 Post Json Str 请求 参数
 */
public class PostJsonStrBuilder extends BaseRequestBuilder<PostJsonStrBuilder> {

    private final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    private String content;

    public PostJsonStrBuilder addJsonContent(String jsonStr) {
        this.content = jsonStr;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostStringRequest(url, tag, params, headers, content, mediaType, id).build();
    }
}