package com.sktl.listloader.services;

import android.util.Log;

import com.sktl.listloader.models.Smth;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;

import rx.Observable;

/**
 * Created by USER-PC on 08.03.2018.
 */

public class PhotoService {
    private static final String PHOTO_SERVER_URL = "https://api.flickr.com/services/rest";

    private static final String KEY_SERVER_URL = "/?method=flickr.photos.getRecent" +
            "&api_key=c60e690083decc36387124f49b1b5e2c"
            + "&format=json"
            + "&nojsoncallback=1";

//    https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=c60e690083decc36387124f49b1b5e2c&format=json&nojsoncallback=1


    private PhotoService.PhotoApi mPhotoApi;


    public PhotoService() {


        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Sktl2", "appSktl/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(PHOTO_SERVER_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        Log.d("sss", "class PhotoService(конструктор)" +
                " restAdapter == " + restAdapter);

        mPhotoApi = restAdapter.create(PhotoService.PhotoApi.class);

        Log.d("sss", "class PhotoService(конструктор)" +
                " mPhotoApi == " + mPhotoApi);
    }

    public PhotoService.PhotoApi getApi() {

        Log.d("sss", "class PhotoService, method getApi() .." +
                " mPhotoApi = " + mPhotoApi);

        return mPhotoApi;
    }


    public interface PhotoApi {

        @GET(KEY_SERVER_URL)
        public Observable<Smth>
        getPhotos();
    }
}
