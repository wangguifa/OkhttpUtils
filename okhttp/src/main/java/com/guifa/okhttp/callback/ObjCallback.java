package com.guifa.okhttp.callback;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 返回值为 对象 的 callback（会将返回的额json返回为对应的实体类）
 * 支持返回String、Array、Bean、List<T>、BaseResponse<T>等等
 */
public abstract class ObjCallback<T> extends BaseCallback<T> {

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        String result = parseJson(body.string());
        if (result == null) {
            return null;
        }
        if (getClass().getGenericSuperclass() == ObjCallback.class) {
            // 默认返回String
            return (T) result;
        }
        if (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0] == String.class) {
            // String类型直接返回
            return (T) result;
        }
        Type type = $Gson$Types.canonicalize(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        return new Gson().fromJson(result, type);
    }

    /**
     * 子线程，可执行耗时操作
     */
    protected String parseJson(String string) {
        // 可以重写此方法做些你想做的事
        return string;
    }

    @Override
    public void onResponse(T response, int id) {
        if (response != null) {
            onSuccess(response, id);
        }
    }

    public abstract void onSuccess(T data, int id);
}