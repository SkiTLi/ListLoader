package com.sktl.listloader.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.sktl.listloader.R;
import com.sktl.listloader.models.Photo;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by USER-PC on 09.03.2018.
 */

public class PhotosAdapter extends ArrayAdapter<Photo> {


    public PhotosAdapter(Context ctx, ArrayList<Photo> photos) {

        super(ctx, 0, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Photo comment = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_detail, parent, false);

        CommentViewWrapper view = new CommentViewWrapper();
        ButterKnife.inject(view, convertView);
        view.load(comment);

        return convertView;
    }

    class CommentViewWrapper {

//        @InjectView(R.id.listViewPhotos)
//        protected ImageView imageView;

        @InjectView(R.id.imageViewPhoto)
        protected ImageView imageView;


//        @InjectView(R.id.textViewCommentEmail)
//        protected TextView email;
//
//        @InjectView(R.id.textViewCommentBody)
//        protected TextView body;

        public void load(Photo photo) {
//        https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
            Uri myUri = Uri.parse("https://farm"
                    + photo.farm + ".staticflickr.com/"
                    + photo.server + "/"
                    + photo.id + "_"
                    + photo.secret + "_q.jpg");
//            imageView.setImageURI(myUri);
            Picasso.with(getContext()).load(myUri).into(imageView);
        }
    }
}
