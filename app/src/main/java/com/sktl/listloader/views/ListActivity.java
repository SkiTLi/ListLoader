package com.sktl.listloader.views;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.sktl.listloader.R;
import com.sktl.listloader.adapters.PostsAdapter;
import com.sktl.listloader.models.Post;
import com.sktl.listloader.presenters.ListPresenter;
import com.sktl.listloader.services.PhotoService;
import com.sktl.listloader.services.PostService;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class ListActivity extends ActionBarActivity {
//public class ListActivity extends Activity {

    @InjectView(R.id.listViewPosts)
    ListView mListViewPosts;

    PostsAdapter mPostsAdapter;

    ListPresenter mListPresenter;
    PostService mPostService;
    PhotoService mPhotoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.inject(this);

        ArrayList<Post> dummyPosts = new ArrayList<Post>();
        mPostsAdapter = new PostsAdapter(this, dummyPosts);
        mListViewPosts.setAdapter(mPostsAdapter);

        mPostService = new PostService();
        mListPresenter = new ListPresenter(this, mPostService);
        mListPresenter.loadPosts();



    }

    @OnItemClick(R.id.listViewPosts)
    public void onPostSelect(int position) {
        Post p = mPostsAdapter.getItem(position);
        int postId = p.id;

        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("postId", postId);
        startActivity(detailIntent);
    }

    public void displayPosts(List<Post> posts) {
        mPostsAdapter.clear();
        mPostsAdapter.addAll(posts);
        mPostsAdapter.notifyDataSetInvalidated();
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
