package com.guifa.okhttp.interceptor;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * 添加公参拦截器
 */
public class AddParamsInterceptor implements Interceptor {

    private static final String TAG = AddParamsInterceptor.class.getSimpleName();

    private Map<String, Object> header;
    private Map<String, Object> body;

    public AddParamsInterceptor(Map<String, Object> header, Map<String, Object> body) {
        this.header = header;
        this.body = body;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response response;
        Request originRequest = chain.request();
        Request.Builder newRequest = originRequest.newBuilder();
        RequestBody body = originRequest.body();
        // 添加请求头参数，调用addHeader(key,value)方法
        if (this.header != null) {
            for (Map.Entry<String, Object> entry : getCommonHeader().entrySet()) {
                newRequest.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        if (body == null) {
            Log.d(TAG, "body is null");
        } else if (body instanceof FormBody) {
            Log.d(TAG, "body is FormBody");
            // 添加表单参数 form-body
            FormBody oldBody = (FormBody) body;
            FormBody.Builder newFormBodyBuilder = new FormBody.Builder();
            for (int i = 0; i < oldBody.size(); i++) {
                newFormBodyBuilder.add(oldBody.name(i), oldBody.value(i));
            }
            // 添加公共参数
            for (Map.Entry<String, Object> entry : getCommonBody().entrySet()) {
                newFormBodyBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
            FormBody newFormBody = newFormBodyBuilder.build();
            newRequest.post(newFormBody);
        } else if (body instanceof MultipartBody) {
            Log.d(TAG, "body is MultipartBody");
        } else {
            Log.d(TAG, "body is Other");
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
                if (charset != null) {
                    // 读取原请求参数内容
                    String requestParams = buffer.readString(charset);
                    try {
                        // 重新拼凑请求体
                        JSONObject jsonObject = new JSONObject(requestParams);
                        for (Map.Entry<String, Object> entry : getCommonBody().entrySet()) {
                            jsonObject.put(entry.getKey(), entry.getValue());
                        }
                        RequestBody newBody = RequestBody.create(body.contentType(), jsonObject.toString());
                        newRequest.post(newBody);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        response = chain.proceed(newRequest.build());
        return response;
    }

    /**
     * 添加公参 header
     */
    private Map<String, Object> getCommonHeader() {
        if (this.header == null) {
            this.header = new HashMap<>();
        }
        return this.header;
    }

    /**
     * 添加公参 body
     */
    private Map<String, Object> getCommonBody() {
        if (this.body == null) {
            this.body = new HashMap<>();
        }
        return this.body;
    }
}