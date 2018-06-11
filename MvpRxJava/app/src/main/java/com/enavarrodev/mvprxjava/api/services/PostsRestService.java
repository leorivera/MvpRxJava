package com.enavarrodev.mvprxjava.api.services;

import com.enavarrodev.mvprxjava.BuildConfig;
import com.enavarrodev.mvprxjava.api.callers.PostsApiCaller;
import com.enavarrodev.mvprxjava.api.definitions.PostRestApi;

/**
 * Created by enava on 13/2/2018.
 */

public class PostsRestService extends AbstractRestService<PostRestApi> {


    public PostsApiCaller getPostsList(){
        return new PostsApiCaller(getService());
    }

    @Override
    protected Class<PostRestApi> getRestApiDefinitionInterface() {
        return PostRestApi.class;
    }

    @Override
    protected String getServiceEndPoint() {
        return BuildConfig.SETTINGS_API_END_POINT;
    }
}
