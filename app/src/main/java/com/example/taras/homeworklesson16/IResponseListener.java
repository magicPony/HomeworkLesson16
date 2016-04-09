package com.example.taras.homeworklesson16;

/**
 * Created by taras on 07.04.16.
 */
public interface IResponseListener<T> {
    void onStart();
    void onFinish(final boolean isSuccess, final T response);
}
