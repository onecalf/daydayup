package com.onecalf.hard.http;

import java.util.Map;

public interface IHttpProcessor {

    void post(String url, Map<String,Object> params,ICallback callback);

    void get(String url, Map<String,Object> params,ICallback callback);
}
