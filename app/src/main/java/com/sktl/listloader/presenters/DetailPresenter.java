package com.sktl.listloader.presenters;

import android.util.Log;

import com.sktl.listloader.models.Photo;
import com.sktl.listloader.models.Post;
import com.sktl.listloader.services.PhotoService;
import com.sktl.listloader.services.PostService;
import com.sktl.listloader.views.DetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by USER-PC on 08.03.2018.
 */

public class DetailPresenter {

    DetailActivity mView;
    PostService mPost;
    PhotoService mPhoto;
    List<Photo> list;

    public DetailPresenter(DetailActivity activity, PostService post, PhotoService photo) {

        mView = activity;
        mPost = post;
        mPhoto = photo;
    }

    public void loadPost() {
        //загрузка статьи по id
        mPost.getApi()
                .getPost(mView.getPostId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Post post) {

                        mView.displayPost(post);
                    }
                });
    }

    private Photo parseJSON(String json) {


        String flickrId;
        String flickrOwner;
        String flickrSecret;
        String flickrServer;
        String flickrFarm;
        String flickrTitle = "fromParseJSON";

        Photo photo = null;

        try {
            JSONObject JsonObject = new JSONObject(json);
            JSONObject Json_photos = JsonObject.getJSONObject("photos");
            JSONArray JsonArray_photo = Json_photos.getJSONArray("photo");

            //We have only one photo in this exercise
            JSONObject FlickrPhoto = JsonArray_photo.getJSONObject(0);

            flickrId = FlickrPhoto.getString("id");
            Log.d("sss", "  flickrId=" + flickrId);
            flickrOwner = FlickrPhoto.getString("owner");
            flickrSecret = FlickrPhoto.getString("secret");
            flickrServer = FlickrPhoto.getString("server");
            flickrFarm = FlickrPhoto.getString("farm");
            flickrTitle = FlickrPhoto.getString("title");

            photo = new Photo(flickrId, flickrOwner, flickrSecret,
                    flickrServer, flickrFarm, flickrTitle);

        } catch (JSONException e) {

            e.printStackTrace();

        }

        return photo;
    }


    public void loadPhotos() {

        mPhoto.getApi()
                .getPhotos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<Photo>>() {
                .subscribe(new Observer<Photo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
//                    public void onNext(List<Photo> comments) {
                    public void onNext(Photo comments) {
//                        mView.displayPhotos(comments);
                        mView.displayPhoto(comments);


//                        list = comments;
//                        mView.displayPhoto(parseJSON(comments.toString()));
//                        mView.displayPhoto(comments.get(55));

//                        for (Photo photo : list) {
//                            mView.displayPhoto(list.get(0));
//                        }


                    }
                });
    }
}
