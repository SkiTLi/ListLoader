package com.sktl.listloader.views;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sktl.listloader.R;
import com.sktl.listloader.adapters.PhotosAdapter;
import com.sktl.listloader.models.Photo;
import com.sktl.listloader.models.Post;
import com.sktl.listloader.presenters.DetailPresenter;
import com.sktl.listloader.services.PhotoService;
import com.sktl.listloader.services.PostService;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by USER-PC on 05.03.2018.
 */

public class DetailActivity extends ActionBarActivity {

    @InjectView(R.id.textViewTitle)
    TextView mTextViewTitle;

    @InjectView(R.id.textViewBody)
    TextView mTextViewBody;

//    @InjectView(R.id.listViewPhotos)
//    ListView mListViewPhotos;

    @InjectView(R.id.imageViewPhoto)
    ImageView mImageViewPhotos;

    PhotosAdapter mPhotosAdapter;

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
//   нужно сделать
//        ArrayList<Photo> dummyPhotos = new ArrayList<Photo>();
//        mPhotosAdapter = new PhotosAdapter(this, dummyPhotos);
//        mListViewPhotos.setAdapter(mPhotosAdapter);
//        mImageViewPhotos.setAdapter(mPhotosAdapter);

//
        mPostService = new PostService();

        mPhotoService = new PhotoService();

        mDetailPresenter = new DetailPresenter(this, mPostService, mPhotoService);
//   нужно сделать
        mDetailPresenter.loadPost();
        mDetailPresenter.loadPhotos();

//        mDetailPresenter.loadPost();

    }

    public int getPostId() {

        return mPostId;
    }

    public int getPhotoId() {

        return mPhotoId;
    }

//    public void displayPhotos(List<Photo> photo) {
    public void displayPhoto(Photo photo) {

//        mPhotosAdapter.clear();
//        mPhotosAdapter.addAll(photo);
//        mPhotosAdapter.notifyDataSetInvalidated();


        Uri myUri = Uri.parse("https://farm5.staticflickr.com/4783/40003740144_a1ca19fdb3_q.jpg");
//        Uri myUri = Uri.parse("https://farm"
//                + photo.farm + ".staticflickr.com/"
//                + photo.server + "/"
//                + photo.id + "_"
//                + photo.secret + "_q.jpg");

//        mImageViewPhotos.setImageURI(myUri);
//        mImageViewPhotos.setImageResource(R.drawable.panther);

        Picasso.with(this).load(myUri).into(mImageViewPhotos);
//        Glide.with(this).load(myUri).into(mImageViewPhotos);//не работает
        Log.d("sss", "  myUri=" + myUri.toString());
    }

    public void displayPost(Post post) {

        mTextViewTitle.setText(post.title);
        mTextViewBody.setText(post.body);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
