package com.enavarrodev.mvprxjava.api.callers;

import com.enavarrodev.mvprxjava.api.definitions.PostRestApi;
import com.enavarrodev.mvprxjava.model.Posts;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by enava on 27/3/2018.
 */

public class PostsApiCaller extends ApiCaller<PostRestApi>{


    public PostsApiCaller(PostRestApi api) {
        super(api);
    }

    @Override
    public Observable<List<Posts>> callApi() {
        return API.getPostsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
