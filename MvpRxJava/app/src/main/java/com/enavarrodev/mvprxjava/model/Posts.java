package com.enavarrodev.mvprxjava.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by enava on 13/2/2018.
 */

public class Posts {

    @SerializedName("userId")
    @Expose
    public int userId;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("body")
    @Expose
    public String body;

}
