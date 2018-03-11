package com.sktl.listloader.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sktl.listloader.R;
import com.sktl.listloader.models.Post;

import java.util.ArrayList;

/**
 * Created by USER-PC on 05.03.2018.
 */

public class PostsAdapter extends ArrayAdapter<Post> {

    public PostsAdapter(Context ctx, ArrayList<Post> posts) {

//        ArrayAdapter(Context context, int resource, List<T> objects)
        super(ctx, 0, posts);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        Post post = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_post_item, parent, false);

        TextView title = (TextView) convertView.findViewById(R.id.textViewItemTitle);
        title.setText(position+1+". "+post.title);

        return convertView;
    }
}