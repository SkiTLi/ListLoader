package com.sktl.listloader.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by USER-PC on 25.03.2018.
 */
@Dao
public interface PhotoDao {
    // Добавление Photo в бд
    @Insert
    void insertAll(Photo... photo);

    // Удаление Photo из бд
    @Delete
    void delete(Photo photo);

    // Получение всех Photo из бд
    @Query("SELECT * FROM photo")
    List<Photo> getAllPhoto();

//    // Получение всех Post из бд с условием
//    @Query("SELECT * FROM post WHERE favoriteColor LIKE :color")
//    List<Post> getAllPostWithFavoriteColor(String color);


}
