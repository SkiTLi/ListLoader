package com.sktl.listloader.views;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sktl.listloader.BuildConfig;
import com.sktl.listloader.R;
import com.sktl.listloader.adapters.PostsAdapter;
import com.sktl.listloader.models.Post;
import com.sktl.listloader.models.PostDatabase;
import com.sktl.listloader.presenters.ListPresenter;
import com.sktl.listloader.services.PhotoService;
import com.sktl.listloader.services.PostService;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;


public class ListActivity extends Activity {

    @InjectView(R.id.version)
    TextView mTextVersion;
    @InjectView(R.id.listViewPosts)
    ListView mListViewPosts;
    PostsAdapter mPostsAdapter;
    ListPresenter mListPresenter;
    PostService mPostService;
    PhotoService mPhotoService;
    PostDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.inject(this);
        mTextVersion.setText(BuildConfig.VERSION_NAME);


        db = Room.databaseBuilder(getApplicationContext(),
                PostDatabase.class, "postsdatabase").allowMainThreadQueries().build();


        ArrayList<Post> dummyPosts = new ArrayList<Post>();

        mPostsAdapter = new PostsAdapter(this, dummyPosts);
        mListViewPosts.setAdapter(mPostsAdapter);

        mPostService = new PostService();
        mPhotoService = new PhotoService();
        mListPresenter = new ListPresenter(this, mPostService, mPhotoService);

        try {
            if (isOnline(this)) {
                Log.d("sss", "class ListActivity, method onCreate(Bundle savedInstanceState) .. " +
                        "isOnline(this) .." +
                        "before mListPresenter.loadPosts();");
                mListPresenter.loadPosts();
                Log.d("sss", "class ListActivity, method onCreate(Bundle savedInstanceState) .. " +
                        "isOnline(this) .." +
                        "after mListPresenter.loadPosts();");

            } else {
                Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
                mPostsAdapter.clear();

                List<Post> posts = db.getPostDao().getAllPost();
                Log.d("sss", "class ListActivity, method onCreate(Bundle savedInstanceState) .. " +
                        "posts.get(13).getBody()=" + posts.get(13).getBody());
                mPostsAdapter.addAll(posts);
                mPostsAdapter.notifyDataSetInvalidated();

            }
        } catch (Exception e) {
            Log.d("sss", "class ListActivity, method onCreate(Bundle savedInstanceState) .. " +
                    "isOnline(this) .." +
                    "catch (Exception e) .. " +
                    "e=" + e);
        }


    }

    @OnItemClick(R.id.listViewPosts)
    public void onPostSelect(int position) {
        Post p = mPostsAdapter.getItem(position);
        int postId = p.getId();
        String postUrlPhoto = p.getUrlPhoto();
        String postTitle = p.getTitle();
        String postBody = p.getBody();

        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("postId", postId);
        detailIntent.putExtra("postUrl", postUrlPhoto);
        detailIntent.putExtra("postTitle", postTitle);
        detailIntent.putExtra("postBody", postBody);

        Log.d("sss", "class ListActivity, method onPostSelect(int position) .. " +
                "postUrlPhoto = " + postUrlPhoto);

        startActivity(detailIntent);
    }


    public void displayPosts(List<Post> posts) {
        mPostsAdapter.clear();

        Log.d("sss", "class ListActivity, method displayPosts(List<Post> posts) .. " +
                "posts.get(2).getUrlPhoto() = " + posts.get(2).getUrlPhoto());

        if (db.getPostDao() == null) {
            db.getPostDao().insertAllList(posts);
        }
        mPostsAdapter.addAll(posts);
        mPostsAdapter.notifyDataSetInvalidated();

        Log.d("sss", "class ListActivity, method displayPosts(List<Post> posts) .. " +
                "posts.size() = " + posts.size());
    }


    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

}
