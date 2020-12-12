package com.example.splash;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class BloggerApi<postService> {

    private static final  String key = "AIzaSyCXSIIZBXRKVTV6GUHjglKociFTV3dneZo";
    private static final  String URL ="https://www.googleapis.com/blogger/v3/blogs/6091988214502313135/posts/";


    public static PostService postService = null;

    public static PostService getPostService() {


        if (postService == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
            postService = retrofit.create(PostService.class);

        }
        return postService;
    }


    public static interface  PostService {

        @GET("?key="+ key)
        Call<Posts> getPostList();
    }
}
