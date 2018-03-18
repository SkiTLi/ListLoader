package com.sktl.listloader.presenters;

import android.util.Log;

import com.sktl.listloader.models.Photo;
import com.sktl.listloader.models.Post;
import com.sktl.listloader.models.Smth;
import com.sktl.listloader.services.PhotoService;
import com.sktl.listloader.services.PostService;
import com.sktl.listloader.views.ListActivity;


import java.util.ArrayList;
import java.util.List;


import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by USER-PC on 08.03.2018.
 */

public class ListPresenter {

    ListActivity mListActivity;
    PostService mPostService;
    PhotoService mPhotoService;
    List<Post> mListTextPost = new ArrayList<>();
    List<Post> mListPostWithPhoto = new ArrayList<>();

    public ListPresenter(ListActivity view, PostService postService, PhotoService photoService) {
        mListActivity = view;
        this.mPostService = postService;
        this.mPhotoService = photoService;
    }
//    private Disposable mDisposable;
//
//    public void execute(InParam param, DisposableObserver<OutParam> disposableObserver) {
//        mDisposable = buildUseCase(param)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.newThread())
//                .subscribeWith(disposableObserver);
//    }
//
//    public void dispose() {
//        if (mDisposable != null) {
//            mDisposable.dispose();
//        }
//    }

    public void loadPosts() {
        loadTextPosts();
        loadPhotos();
        mListActivity.displayPosts(mListPostWithPhoto);
    }

    public void loadTextPosts() {
        mPostService.getApi()
                .getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("sss", "class ListPresenter, method loadPosts() .." +
                                " onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("sss", "class ListPresenter, method loadPosts() .." +
                                " onError(Throwable e) .." +
                                " e=" + e);
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        Log.d("sss", "class ListPresenter, method loadPosts() .. " +
                                "onNext(List<Post> posts) .. " +
                                "posts.size()= " + posts.size());
                        mListTextPost = posts;
                        mListActivity.displayPosts(mListTextPost);
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
                        Log.d("sss", "class ListPresenter, method loadPhotos() .." +
                                " onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("sss", "class ListPresenter, method loadPhotos() .." +
                                " onError(Throwable e) .." +
                                " e=" + e);

                    }

                    @Override
                    public void onNext(Smth smth) {
                        Log.d("sss", "class ListPresenter, method loadPhotos() .." +
                                " onNext(Smth smth) .." +
                                " smth.photos.photo.size() = " + smth.photos.photo.size());
                        mListPostWithPhoto = getMergePostsWithPhotos(mListTextPost,
                                smth.photos.photo);
                    }
                });
    }


    public List<Post> getMergePostsWithPhotos(List<Post> posts, List<Photo> photos) {

        Log.d("sss", "class ListPresenter, method getMergePostsWithPhotos(List<Post> posts, List<Photo> photos) .." +
                " posts.size() = " + posts.size());
        Log.d("sss", "class ListPresenter, method getMergePostsWithPhotos(List<Post> posts, List<Photo> photos) .." +
                " photos.size() = " + photos.size());

        for (int i = 0; i < posts.size(); i++) {
            String myUrl = "https://farm"
                    + photos.get(i).farm + ".staticflickr.com/"
                    + photos.get(i).server + "/"
                    + photos.get(i).id + "_"
                    + photos.get(i).secret + ".jpg";

            posts.get(i).urlPhoto = (myUrl);
//            Log.d("sss", "class ListPresenter," +
//                    " method getMergePostsWithPhotos(List<Post> posts, List<Photo> photos) .." +
//                    " posts.get(i).urlPhoto= " + posts.get(i).urlPhoto);
//            Log.d("sss", "class ListPresenter," +
//                    " method getMergePostsWithPhotos(List<Post> posts, List<Photo> photos) .." +
//                    " posts.get(i).title= " + posts.get(i).title);
        }

        return posts;
    }


}
