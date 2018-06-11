package com.enavarrodev.mvprxjava.ui.posts;

import com.enavarrodev.mvprxjava.ui.BaseContract;

/**
 * Created by enava on 26/3/2018.
 */

public class PostsContract {

    interface View extends BaseContract.View{

    }

    interface Presenter extends BaseContract.Presenter{
        void doPostsRequest();
    }
}
