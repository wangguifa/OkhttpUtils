package com.guifa.okhttp.callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class BaseCallback<T> {

    /**
     * UI Thread
     */
    public void onBefore(Request request, int id) {
    }

    /**
     * UI Thread
     */
    public void onAfter(int id) {
    }

    /**
     * UI Thread
     */
    public void inProgress(float progress, long total, int id) {
    }

    /**
     * if you parse response code in parseNetworkResponse, you should make this method return true.
     */
    public boolean validateResponse(Response response, int id) {
        return response.isSuccessful();
    }

    /**
     * Thread Pool Thread
     */
    public abstract T parseNetworkResponse(Response response, int id) throws Exception;

    /**
     * UI Thread
     */
    public abstract void onResponse(T response, int id);

    /**
     * UI Thread
     */
    public abstract void onError(Call call, Exception e, int id);

    public static BaseCallback<String> CALLBACK_BASE_DEFAULT = new BaseCallback<String>() {

        @Override
        public String parseNetworkResponse(Response response, int id) throws Exception {
            ResponseBody body = response.body();
            if (body != null) {
                return body.string();
            } else {
                return "";
            }
        }

        @Override
        public void onError(Call call, Exception e, int id) {
        }

        @Override
        public void onResponse(String response, int id) {
        }
    };
}