package com.onecalf.hard.http;

import java.util.HashMap;
import java.util.Map;

public class HttpHelper {
    private static IHttpProcessor mHttpProcessor = null;
    private static HttpHelper msInstance;
    private Map<String, Object> mParams;

    private HttpHelper() {
        mParams = new HashMap<>();
    }

    public static HttpHelper obtain() {
        if (msInstance == null) {
            synchronized (HttpHelper.class) {
                if (msInstance == null) {
                    msInstance = new HttpHelper();
                }
            }
        }

        return msInstance;
    }


    public static void init(IHttpProcessor httpProcessor) {
        mHttpProcessor = httpProcessor;
    }

}
