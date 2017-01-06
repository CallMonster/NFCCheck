package com.tj.chaersi.okhttputils.builder;

import com.tj.chaersi.okhttputils.OkHttpUtils;
import com.tj.chaersi.okhttputils.request.OtherRequest;
import com.tj.chaersi.okhttputils.request.RequestCall;

/**
 * Created by chaersi on 16/3/2.
 */
public class HeadBuilder extends GetBuilder {
    @Override
    public RequestCall build() {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers, id).build();
    }
}
