package com.onecalf.hard.http;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HttpCallback<T> implements ICallback {
    @Override
    public void onSuccess(String response) {
        Gson gson = new Gson();
        Class<?> classz = analysisClassInfo(this);
        T result = (T) gson.fromJson(response,classz);
        onSuccess(result);
    }

    public abstract void onSuccess(T result);

    protected static Class<?> analysisClassInfo(Object object){
        Type genType = object.getClass().getGenericSuperclass();
        Type[] param = ((ParameterizedType)genType).getActualTypeArguments();
        return (Class<?>) param[0];
    }

    @Override
    public void onFailed(String error) {

    }
}
