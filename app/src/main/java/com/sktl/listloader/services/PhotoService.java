package com.sktl.listloader.services;

import com.sktl.listloader.models.Photo;
import com.sktl.listloader.models.Post;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by USER-PC on 08.03.2018.
 */

public class PhotoService {

    //    private static final String PHOTO_SERVER_URL = "http://jsonplaceholder.typicode.com";
    private static final String PHOTO_SERVER_URL = "https://api.flickr.com/services/rest";

    private static final String KEY_SERVER_URL = "/?method=flickr.photos.getRecent" +
            "&api_key=c60e690083decc36387124f49b1b5e2c"
            +"&format=json"
            +"&nojsoncallback=1"
//            +"&auth_token=72157691257799702-a246aacc17560916" +
//            "&api_sig=23a692953a4765c1"
            ;


//    https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=c60e690083decc36387124f49b1b5e2c&format=json&nojsoncallback=1


    private PhotoService.PhotoApi mPhotoApi;


    public PhotoService() {


        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("AcceptSktl", "applicationSktl/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(PHOTO_SERVER_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mPhotoApi = restAdapter.create(PhotoService.PhotoApi.class);
    }

    public PhotoService.PhotoApi getApi() {
        return mPhotoApi;
    }


    public interface PhotoApi {

        @GET(KEY_SERVER_URL)
//        public Observable<List<Photo>>
        public Observable<Photo>
        getPhotos();

//        https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
//        https://farm{5}.staticflickr.com/{4783}/{40003740144}_{a1ca19fdb3}_q.jpg
//        https://farm5.staticflickr.com/4783/40003740144_a1ca19fdb3_q.jpg
//{ "id": "40003740144", "owner": "64236163@N00", "secret": "a1ca19fdb3", "server": "4783", "farm": 5, "title": "2018-03-09_12-59-25", "ispublic": 1, "isfriend": 0, "isfamily": 0 },

//        @GET("/posts/{id}")
//        public Observable<Post>
//        getPost(@Path("id") int postId);

//        @GET("/comments")
//        public Observable<List<Comment>>
//        getComments(@Query("postId") int postId);

//        @POST("/posts")
//        public Observable<Post>
//        postPost(Post post);
    }
}
