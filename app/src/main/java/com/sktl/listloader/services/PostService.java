package com.sktl.listloader.services;



import com.sktl.listloader.models.Post;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by USER-PC on 05.03.2018.
 */

public class PostService {

    private static final String POST_SERVER_URL = "http://jsonplaceholder.typicode.com";


    private PostApi mPostApi;


    public PostService() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("SkTL", "app/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(POST_SERVER_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mPostApi = restAdapter.create(PostApi.class);
    }

    public PostApi getApi() {

        return mPostApi;
    }

    public interface PostApi {

        @GET("/posts")
        public Observable<List<Post>>
        getPosts();

    }
}
