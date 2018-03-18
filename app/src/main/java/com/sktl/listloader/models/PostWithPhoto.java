package com.sktl.listloader.models;

/**
 * Created by USER-PC on 17.03.2018.
 */

public class PostWithPhoto extends Post {
    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    private String urlPhoto;

    public PostWithPhoto(Post post, String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
