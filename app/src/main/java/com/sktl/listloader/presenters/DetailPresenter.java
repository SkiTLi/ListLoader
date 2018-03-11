package com.sktl.listloader.presenters;

import android.util.Log;

import com.sktl.listloader.models.Post;
import com.sktl.listloader.models.Smth;
import com.sktl.listloader.services.PhotoService;
import com.sktl.listloader.services.PostService;
import com.sktl.listloader.views.DetailActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by USER-PC on 08.03.2018.
 */

public class DetailPresenter {

    DetailActivity mDetailActivity;
    PostService mPostService;
    PhotoService mPhotoService;

    public DetailPresenter(DetailActivity activity, PostService post, PhotoService photo) {

        mDetailActivity = activity;
        mPostService = post;
        mPhotoService = photo;
    }

    public void loadPost() {
        //загрузка статьи по id
        mPostService.getApi()
                .getPost(mDetailActivity.getPostId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onCompleted() {
                        Log.d("sss", "class DetailPresenter, method loadPost() .." +
                                " onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Post post) {
                        Log.d("sss", "class DetailPresenter, method loadPost() .." +
                                " onNext(Post post) .." +
                                " post.id= " + post.id);
                        mDetailActivity.displayPost(post);
                    }
                });
    }


    public void loadPhotos() {

        mPhotoService.getApi()

                .getPhotos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<Smth>() {
                    @Override
                    public void onCompleted() {


                        Log.d("sss", "class DetailPresenter, method loadPhotos() .." +
                                " onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {


                        Log.d("sss", "class DetailPresenter, method onError(Throwable e) .. e=" + e);

                    }

                    @Override
                    public void onNext(Smth smth) {
                        Log.d("sss", "class DetailPresenter, method loadPhotos() .." +
                                " onNext(Photos photos) .." +
                                " smth.photos.total= " + smth.photos.total);
                        mDetailActivity.displayPhotos(smth.photos.photo);
                    }
                });
    }
}
