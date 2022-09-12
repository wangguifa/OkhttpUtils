package com.guifa.okhttp.builder;

import com.guifa.okhttp.bean.FileInput;
import com.guifa.okhttp.request.PostFormRequest;
import com.guifa.okhttp.request.RequestCall;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 构建 Post Form 请求 参数
 */
public class PostFormBuilder extends BaseRequestBuilder<PostFormBuilder> implements IAddParam<PostFormBuilder> {

    private final List<FileInput> files = new ArrayList<>();

    @Override
    public RequestCall build() {
        return new PostFormRequest(url, tag, params, headers, files, id).build();
    }

    @Override
    public PostFormBuilder addParam(String key, Object val) {
        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        this.params.put(key, val);
        return this;
    }

    @Override
    public PostFormBuilder addParams(Map<String, Object> params) {
        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        this.params.putAll(params);
        return this;
    }

    public PostFormBuilder addFile(String name, String filename, File file) {
        files.add(new FileInput(name, filename, file));
        return this;
    }

    public PostFormBuilder addFiles(String key, Map<String, File> files) {
        for (String filename : files.keySet()) {
            this.files.add(new FileInput(key, filename, files.get(filename)));
        }
        return this;
    }
}