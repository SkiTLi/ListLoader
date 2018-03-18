package com.sktl.listloader.presenters;

import android.util.Log;

import com.sktl.listloader.models.Photo;
import com.sktl.listloader.models.Post;
import com.sktl.listloader.models.PostWithPhoto;
import com.sktl.listloader.models.Smth;
import com.sktl.listloader.services.PhotoService;
import com.sktl.listloader.services.PostService;
import com.sktl.listloader.views.ListActivity;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;


import rx.Observable;
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
//    List<Photo> mListPhotos = new ArrayList<>();
    List<Post> mListPostWithPhoto = new ArrayList<>();

    public ListPresenter(ListActivity view, PostService postService, PhotoService photoService) {


        mListActivity = view;

        this.mPostService = postService;

        this.mPhotoService = photoService;


//        mListPostWithPhoto = new ArrayList<>();


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
//        // пауза - 10 секунд
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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
//                        for (Post pst : posts) {
//                            mListPostWithPhoto.add(new PostWithPhoto(pst, null));
//                            Log.d("sss", "class ListActivity, method .. mListPostWithPhoto.listIterator().next().body=" + mListPostWithPhoto.listIterator().next().body);
//                        }

//                        loadPhotos();

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


                        Log.d("sss", "class DetailPresenter, method loadPhotos() .." +
                                " onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {


                        Log.d("sss", "class DetailPresenter, method loadPhotos() .." +
                                " onError(Throwable e) .." +
                                " e=" + e);

                    }

                    @Override
                    public void onNext(Smth smth) {
                        Log.d("sss", "class ListPresenter, method loadPhotos() .." +
                                " onNext(Photos photos) .." +
                                " smth.photos.total= " + smth.photos.total);
//                        mergePostsWithPhotos(smth.photos.photo);
//                        mListPhotos = smth.photos.photo;


                        mListPostWithPhoto = getMergePostsWithPhotos(mListTextPost, smth.photos.photo);

                    }
                });
    }


    public List<Post> getMergePostsWithPhotos(List<Post> posts, List<Photo> photos) {

//        Log.d("sss", "class ListPresenter, method mergePostsWithPhotos(List<Post> posts, List<Photo> photos) .." +
//
//                " photos= " + photos);
//
//        Log.d("sss", "class ListPresenter, method mergePostsWithPhotos(List<Post> posts, List<Photo> photos) .." +
//
//                " posts= " + posts);
//        mListPostWithPhoto = posts;
        Log.d("sss", "class ListPresenter, method mergePostsWithPhotos(List<Post> posts, List<Photo> photos) .." +

                " mListPostWithPhoto.size()= " + posts.size());

        for (int i = 0; i < posts.size(); i++) {
            String myUrl = "https://farm"
                    + photos.get(i).farm + ".staticflickr.com/"
                    + photos.get(i).server + "/"
                    + photos.get(i).id + "_"
//                + photo.secret + "_q.jpg");
                    + photos.get(i).secret + ".jpg";

            posts.get(i).urlPhoto = (myUrl);

//            mListPostWithPhoto.get(i).id=posts.get(i).id;
//            mListPostWithPhoto.get(i).title=posts.get(i).title;
//            mListPostWithPhoto.get(i).body=posts.get(i).body;


            Log.d("sss", "class ListPresenter, method mergePostsWithPhotos(List<Post> posts, List<Photo> photos) .." +

                    " posts.get(i).urlPhoto= " + posts.get(i).urlPhoto);
            Log.d("sss", "class ListPresenter, method mergePostsWithPhotos(List<Post> posts, List<Photo> photos) .." +

                    " posts.get(i).title= " + posts.get(i).title);
        }

        return posts;
    }


}
