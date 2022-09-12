package com.guifa.okhttp.callback;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.guifa.okhttp.OkHttpUtils;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 对于返回json有统一 BaseResponse 包裹的 返回 callback
 * 会在解析中判断code值，成功返回onSuccess，失败返回onFail，请求失败则返回onError
 * 支持返回String、Array、Bean、List<T>等等
 * <p>
 * 若 BaseResponse 与 服务端返回的有所不同，可参考此类重新定义Callback
 */
public abstract class CommonCallback<T> extends BaseCallback<T> {

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        ResponseBody body = response.body();
        if (body != null) {
            String json = body.string();
            JSONObject obj = new JSONObject(json);
            int code = obj.getInt("code");
            if (code == 200) {
                String tData = obj.getString("data");
                Type type = $Gson$Types.canonicalize(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
                Object o = new Gson().fromJson(tData, type);
                return (T) o;
            } else {
                String tData = obj.getString("msg");
                // 发送到UI线程
                OkHttpUtils.getInstance().getDelivery().execute(new Runnable() {
                    @Override
                    public void run() {
                        onFail(code, tData);
                    }
                });
            }
        }
        return null;
    }

    @Override
    public void onResponse(T response, int id) {
        // 这里成功的返回已交给onSuccess处理
        if (response != null) {
            onSuccess(response, id);
        }
    }

    public abstract void onSuccess(T data, int id);

    public abstract void onFail(int errCode, String errMsg);
}