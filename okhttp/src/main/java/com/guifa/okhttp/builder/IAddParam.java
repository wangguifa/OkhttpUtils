package com.guifa.okhttp.builder;

import java.util.Map;

public interface IAddParam<T extends BaseRequestBuilder<T>> {

    T addParam(String key, Object val);

    T addParams(Map<String, Object> params);
}