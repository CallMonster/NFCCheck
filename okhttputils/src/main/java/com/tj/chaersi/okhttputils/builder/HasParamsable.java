package com.tj.chaersi.okhttputils.builder;

import java.util.Map;

/**
 * Created by chaersi on 16/3/1.
 */
public interface HasParamsable {
    OkHttpRequestBuilder params(Map<String, String> params);

    OkHttpRequestBuilder addParams(String key, String val);
}
