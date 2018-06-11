package com.enavarrodev.mvprxjava.ui.posts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.enavarrodev.mvprxjava.R;
import com.enavarrodev.mvprxjava.api.services.PostsRestService;
import com.enavarrodev.mvprxjava.ui.BaseContract;

/**
 * Created by enava on 26/3/2018.
 */

public class PostsActivity extends AppCompatActivity implements PostsContract.View {

    PostsContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PostsRestService service = new PostsRestService();

        setPresenter(new PostsPresenter(this, service));

        mPresenter.doPostsRequest();
    }


    @Override
    public void setPresenter(BaseContract.Presenter presenter) {
        this.mPresenter = (PostsContract.Presenter) presenter;
    }

    @Override
    public void onUnknownError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTimeout() {
        Toast.makeText(getApplicationContext(), "Time out", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNetworkError() {
        Toast.makeText(getApplicationContext(), "Network Error ", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void onConnectionError() {

    }

}
