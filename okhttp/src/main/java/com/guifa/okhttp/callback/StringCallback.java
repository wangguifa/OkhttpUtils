package com.guifa.okhttp.callback;

import okhttp3.Response;

/**
 * 返回值为 UTF-8 的字符串 callback
 */
public abstract class StringCallback extends BaseCallback<String> {

    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        if (response.body() != null) {
            return response.body().string();
        } else {
            return "";
        }
    }
}