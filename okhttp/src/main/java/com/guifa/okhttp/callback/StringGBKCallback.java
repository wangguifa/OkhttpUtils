package com.guifa.okhttp.callback;

import okhttp3.Response;

/**
 * 返回值为 GBK 的字符串 callback
 */
public abstract class StringGBKCallback extends BaseCallback<String> {

    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        if (response.body() != null) {
            return new String(response.body().string().getBytes(), "GBK");
        } else {
            return "";
        }
    }
}