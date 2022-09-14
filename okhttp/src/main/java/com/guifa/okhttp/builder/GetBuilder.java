package com.guifa.okhttp.builder;

import android.net.Uri;

import com.guifa.okhttp.request.GetRequest;
import com.guifa.okhttp.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 构建 Get 请求 参数
 */
public class GetBuilder extends BaseRequestBuilder<GetBuilder> implements IAddParam<GetBuilder> {

    @Override
    public RequestCall build() {
        if (params != null) {
            url = appendParams(url, params);
        }
        return new GetRequest(url, tag, params, headers, id).build();
    }

    protected String appendParams(String url, Map<String, Object> params) {
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            builder.appendQueryParameter(key, String.valueOf(params.get(key)));
        }
        return builder.build().toString();
    }

    @Override
    public GetBuilder addParam(String key, Object val) {
        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public GetBuilder addParams(Map<String, Object> params) {
        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        this.params.putAll(params);
        return this;
    }
}
