package com.sktl.listloader.models;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by USER-PC on 25.03.2018.
 */



    @Database(entities = {Post.class /*, AnotherEntityType.class, AThirdEntityType.class */}, version = 1)
    public abstract class PostDatabase extends RoomDatabase {
        public abstract PostDao getPostDao();

    }



