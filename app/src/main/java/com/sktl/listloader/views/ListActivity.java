package com.sktl.listloader.views;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.sktl.listloader.R;
import com.sktl.listloader.adapters.PostsAdapter;
import com.sktl.listloader.models.Photo;
import com.sktl.listloader.models.Post;
import com.sktl.listloader.models.PostWithPhoto;
import com.sktl.listloader.presenters.ListPresenter;
import com.sktl.listloader.services.PhotoService;
import com.sktl.listloader.services.PostService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

//public class ListActivity extends ActionBarActivity {
public class ListActivity extends Activity {


    @InjectView(R.id.listViewPosts)
    ListView mListViewPosts;

    PostsAdapter mPostsAdapter;

    ListPresenter mListPresenter;
    PostService mPostService;
    PhotoService mPhotoService;

//    List<Post> mListPostWithPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.inject(this);





        ArrayList<Post> dummyPosts = new ArrayList<Post>();

        mPostsAdapter = new PostsAdapter(this, dummyPosts);
        mListViewPosts.setAdapter(mPostsAdapter);

        mPostService = new PostService();
        mPhotoService = new PhotoService();
        mListPresenter = new ListPresenter(this, mPostService, mPhotoService);

        mListPresenter.loadPosts();


        Log.d("sss", "class ListActivity, method onCreate(Bundle savedInstanceState) .. " +
                "before mListPresenter.loadPosts();");
//        mListPresenter.loadPosts();
//        mListPresenter.loadPhotos();

        Log.d("sss", "class ListActivity, method onCreate(Bundle savedInstanceState) .. " +
                "after mListPresenter.loadPosts();");



//        mListPresenter.loadPhotos();
//        mListPostWithPhoto = new ArrayList<>();

    }

    @OnItemClick(R.id.listViewPosts)
    public void onPostSelect(int position) {
        Post p = mPostsAdapter.getItem(position);
        int postId = p.id;


//        int postId = mListPostWithPhoto.get(position).id;


        String postUrlPhoto = p.urlPhoto;
        String postTitle = p.title;
        String postBody = p.body;

        Intent detailIntent = new Intent(this, DetailActivity.class);




//        detailIntent.putExtra("postId", postId);
        detailIntent.putExtra("postId", postId);


        detailIntent.putExtra("postUrl", postUrlPhoto);
        detailIntent.putExtra("postTitle", postTitle);
        detailIntent.putExtra("postBody", postBody);



//        Log.d("sss", "class ListActivity, method onPostSelect(int position) .. " +
//                "postUrlPhoto = " + postUrlPhoto);

        startActivity(detailIntent);
    }


    public void displayPosts(List<Post> posts) {

        mPostsAdapter.clear();
        mPostsAdapter.addAll(posts);
//        mPostsAdapter.addAll(mListPostWithPhoto);
        mPostsAdapter.notifyDataSetInvalidated();

        Log.d("sss", "class ListActivity, method displayPosts(List<Post> posts) .." +
                "posts="+posts);
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
