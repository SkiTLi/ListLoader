package com.sktl.listloader.views;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.sktl.listloader.R;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by USER-PC on 05.03.2018.
 */


public class DetailActivity extends Activity {

    @InjectView(R.id.textViewTitle)
    TextView mTextViewTitle;

    @InjectView(R.id.textViewBody)
    TextView mTextViewBody;

    @InjectView(R.id.imageViewPhoto)
    ImageView mImageViewPhotos;


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
        displayPost();
    }


    public void displayPost() {

        mTextViewTitle.setText(mPostTitle);
        mTextViewBody.setText(mPostBody);
        Log.d("sss", "class DetailActivity, method displayPost() .. " +
                "mPostBody = " + mPostBody);

        Picasso.with(this).load(mPhotoUrl).into(mImageViewPhotos);
        Log.d("sss", "class DetailActivity, method displayPost() .. " +
                "mPhotoUrl = " + mPhotoUrl);
    }


}
