package com.sktl.listloader.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sktl.listloader.R;
import com.sktl.listloader.models.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER-PC on 05.03.2018.
 */

public class PostsAdapter extends ArrayAdapter<Post> {

    public PostsAdapter(Context ctx, ArrayList<Post> posts) {
//        ArrayAdapter(Context context, int resource, List<T> objects)
        super(ctx, 0, posts);

        Log.d("sss", "class PostsAdapter(конструктор) .." +
                " posts= " + posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post post = getItem(position);
//        Log.d("sss", "class PostsAdapter," +
//                " method getView(int position, View convertView, ViewGroup parent) .." +
//                " post= " + post);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_post_item, parent, false);
//        Log.d("sss", "class PostsAdapter," +
//                " method getView(int position, View convertView, ViewGroup parent) .." +
//                " convertView= " + convertView);
        TextView title = (TextView) convertView.findViewById(R.id.textViewItemTitle);
        title.setText(position + 1 + ". " + post.getTitle());

        Log.d("sss", "class PostsAdapter," +
                " method getView(int position, View convertView, ViewGroup parent) .." +
                " position= " + position +
                ", post.title= " + post.getTitle());

        return convertView;
    }
}