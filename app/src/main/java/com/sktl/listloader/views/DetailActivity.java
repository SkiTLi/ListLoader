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
    PhotoService mPhotoService;

    protected int mPostId;
    protected int mPhotoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.inject(this);

        mPostId = getIntent().getIntExtra("postId", 0);


        mPostService = new PostService();

        mPhotoService = new PhotoService();

        mDetailPresenter = new DetailPresenter(this, mPostService, mPhotoService);

        mDetailPresenter.loadPost();
        mDetailPresenter.loadPhotos();


    }

    public int getPostId() {

        return mPostId;
    }

    public void displayPhotos(List<Photo> photos) {


        Log.d("sss", "class DetailActivity, method displayPhotos(List<Photo> photos) .." +
                " photos.size()= " + photos.size());

//        Uri myUri = Uri.parse("https://farm5.staticflickr.com/4783/40003740144_a1ca19fdb3_q.jpg");
        Photo photo = photos.get(getPostId());

        Uri myUri = Uri.parse("https://farm"
                + photo.farm + ".staticflickr.com/"
                + photo.server + "/"
                + photo.id + "_"
//                + photo.secret + "_q.jpg");
                + photo.secret + ".jpg");


        Picasso.with(this).load(myUri).into(mImageViewPhotos);

        Log.d("sss", "class DetailActivity, method displayPhotos(List<Photo> photos) .." +
                " myUri.toString()= " + myUri.toString());
    }

    public void displayPost(Post post) {

        mTextViewTitle.setText(post.title);
        mTextViewBody.setText(post.body);
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
