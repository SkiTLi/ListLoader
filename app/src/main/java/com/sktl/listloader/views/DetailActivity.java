package com.sktl.listloader.views;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.sktl.listloader.R;
import com.sktl.listloader.models.Photo;
import com.sktl.listloader.models.Post;
import com.sktl.listloader.models.PostWithPhoto;
import com.sktl.listloader.presenters.DetailPresenter;
import com.sktl.listloader.services.PhotoService;
import com.sktl.listloader.services.PostService;
import com.squareup.picasso.Picasso;


import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by USER-PC on 05.03.2018.
 */

//public class DetailActivity extends ActionBarActivity {
public class DetailActivity extends Activity {

    @InjectView(R.id.textViewTitle)
    TextView mTextViewTitle;

    @InjectView(R.id.textViewBody)
    TextView mTextViewBody;

    @InjectView(R.id.imageViewPhoto)
    ImageView mImageViewPhotos;


    DetailPresenter mDetailPresenter;
    PostService mPostService;
//    PhotoService mPhotoService;

    protected int mPostId;
    protected String mPhotoUrl;
    protected String mPostTitle;
    protected String mPostBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.inject(this);

        mPostId = getIntent().getIntExtra("postId", 0);
        mPhotoUrl = getIntent().getStringExtra("postUrl");
        mPostTitle = getIntent().getStringExtra("postTitle");
        mPostBody = getIntent().getStringExtra("postBody");

        mPostService = new PostService();

//        mPhotoService = new PhotoService();

        mDetailPresenter = new DetailPresenter(this, mPostService);

        Log.d("sss", "class DetailActivity, method onCreate(Bundle savedInstanceState) .. " +
                "before mDetailPresenter.loadPost();");

        displayPost();
//        mDetailPresenter.loadPost();



        Log.d("sss", "class DetailActivity, method onCreate(Bundle savedInstanceState) .. " +
                "before mDetailPresenter.loadPhotos(); but after mDetailPresenter.loadPost();");
//        mDetailPresenter.loadPhotos();
        Log.d("sss", "class DetailActivity, method onCreate(Bundle savedInstanceState) .. " +
                "after mDetailPresenter.loadPhotos();");





    }

    public int getPostId() {

        return mPostId;
    }




//    public void displayPost(Post post) {
    public void displayPost() {

//        mTextViewTitle.setText(post.title);
        mTextViewTitle.setText(mPostTitle);
//        mTextViewBody.setText(post.body);
        mTextViewBody.setText(mPostBody);
        Log.d("sss", "class DetailActivity, method displayPost(Post post) .. " +
                "mPostBody = " + mPostBody);

        Picasso.with(this).load(mPhotoUrl).into(mImageViewPhotos);

        Log.d("sss", "class DetailActivity, method displayPost(Post post) .. " +
                "mPhotoUrl = " + mPhotoUrl);


    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_list, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
