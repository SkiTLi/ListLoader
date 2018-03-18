package com.sktl.listloader.models;

import java.util.List;

/**
 * Created by USER-PC on 11.03.2018.
 */

public class Smth {

    public Photos photos;

    public class Photos {
        public String page;
        public String pages;
        public String perpage;
        public String total;

        public List<Photo> photo;
    }
}
