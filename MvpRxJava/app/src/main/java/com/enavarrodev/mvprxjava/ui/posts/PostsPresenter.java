package com.enavarrodev.mvprxjava.ui.posts;

import android.util.Log;

import com.enavarrodev.mvprxjava.api.callbacks.CallbackHandlingResponse;
import com.enavarrodev.mvprxjava.api.services.PostsRestService;
import com.enavarrodev.mvprxjava.model.Posts;

import java.util.List;

/**
 * Created by enava on 26/3/2018.
 */

public class PostsPresenter implements PostsContract.Presenter {

    private  PostsRestService service;
    private PostsContract.View mView;


    public PostsPresenter(PostsContract.View view, PostsRestService service) {
        this.mView = view;
        this.service = service;
    }

    @Override
    public void doPostsRequest() {
        service.getPostsList()
                .callApi()
                .subscribeWith(new CallbackHandlingResponse<List<Posts>>(this.mView){

                    @Override
                    protected void onSuccess(List<Posts> posts) {
                        for (Posts post : posts) {
                            Log.d("Post->", post.title);
                        }
                    }
                });
                /*.subscribeWith(new DisposableObserver<List<Posts>>() {
                    @Override
                    public void onNext(List<Posts> posts) {
                        for (Posts post : posts) {
                            Log.d("Post->", post.title);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(this.getClass().getSimpleName(), e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }
}
