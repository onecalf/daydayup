package com.onecalf.hard.http;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class VolleyProcessor implements IHttpProcessor{
    private static RequestQueue mQueue = null;

    public VolleyProcessor(Context context){
        mQueue = new RequestQueue(null,null,4);
    }


    @Override
    public void post(String url, Map<String, Object> params, final ICallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed(error.getLocalizedMessage());
            }
        });
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallback callback) {

    }

}
