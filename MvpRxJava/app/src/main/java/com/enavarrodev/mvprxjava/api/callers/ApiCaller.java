package com.enavarrodev.mvprxjava.api.callers;

import com.enavarrodev.mvprxjava.model.Posts;

import java.util.List;

import io.reactivex.Observable;

/**
 * Una clase que permite llamar a una api
 * <p>
 * Created by nelsonalfo on 14/03/17.
 *
 * @param <T> interface con la definicion de la api
 */
public abstract class ApiCaller<T> {
    /**
     * Instancia de la API
     */
    protected final T API;

    public ApiCaller(T api) {
        API = api;
    }

    /**
     * Realiza la llamada a la API
     */
    public abstract Observable<List<Posts>> callApi();
}
