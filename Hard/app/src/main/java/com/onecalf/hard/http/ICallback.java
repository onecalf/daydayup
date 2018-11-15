package com.onecalf.hard.http;

public interface ICallback {
    void onSuccess(String response);
    void onFailed(String error);

}
