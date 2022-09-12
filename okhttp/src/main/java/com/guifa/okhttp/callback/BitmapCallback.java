package com.guifa.okhttp.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class BitmapCallback extends BaseCallback<Bitmap> {

    @Override
    public Bitmap parseNetworkResponse(Response response, int id) throws Exception {
        ResponseBody body = response.body();
        if (body != null) {
            return BitmapFactory.decodeStream(body.byteStream());
        } else {
            return null;
        }
    }
}