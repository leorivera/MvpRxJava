package com.enavarrodev.mvprxjava.api.callbacks;

import android.util.Log;

import com.enavarrodev.mvprxjava.api.response.BaseResponse;
import com.enavarrodev.mvprxjava.ui.BaseContract;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by enava on 27/3/2018.
 */

public abstract class CallbackHandlingResponse<T> extends DisposableObserver<T> {

    private BaseContract.View mView;

    public CallbackHandlingResponse(BaseContract.View view) {
        this.mView = view;
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        if (t instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) t).response().errorBody();
            mView.onUnknownError(getErrorMessage(responseBody));
        } else if (t instanceof SocketTimeoutException) {
            mView.onTimeout();
        } else if (t instanceof IOException) {
            mView.onNetworkError();
        } else {
            mView.onUnknownError(t.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(T t);

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}