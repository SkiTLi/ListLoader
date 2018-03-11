package com.sktl.listloader.presenters;

import com.sktl.listloader.models.Post;
import com.sktl.listloader.services.PostService;
import com.sktl.listloader.views.ListActivity;


import java.util.List;


import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by USER-PC on 08.03.2018.
 */

public class ListPresenter {

    ListActivity mView;
    PostService mForum;

    public ListPresenter(ListActivity view, PostService forum) {

        mView = view;
        mForum = forum;
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
        mForum.getApi()
                .getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Post> posts) {

                        mView.displayPosts(posts);
                    }
                });

    }
}
