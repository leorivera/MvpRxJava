package com.enavarrodev.mvprxjava.api.definitions;

import com.enavarrodev.mvprxjava.model.Posts;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by enava on 12/2/2018.
 */

public interface PostRestApi {

    @GET("posts")
    Observable<List<Posts>> getPostsList();
}
