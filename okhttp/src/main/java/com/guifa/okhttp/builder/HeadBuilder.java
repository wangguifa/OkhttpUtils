package com.guifa.okhttp.builder;

import com.guifa.okhttp.OkHttpUtils;
import com.guifa.okhttp.request.OtherRequest;
import com.guifa.okhttp.request.RequestCall;

/**
 * 构建 HEAD 请求 参数
 */
public class HeadBuilder extends GetBuilder {

    @Override
    public RequestCall build() {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers, id).build();
    }
}