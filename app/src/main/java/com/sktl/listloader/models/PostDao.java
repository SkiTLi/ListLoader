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
public interface PostDao {


    // Добавление Post в бд
    @Insert
    void insertAll(Post... post);

    @Insert
    void insertAllList(List<Post> posts);


    // Добавление Post в бд
    @Insert
    void insert(Post post);

    // Удаление Post из бд
    @Delete
    void delete(Post post);

    // Получение всех Post из бд
    @Query("SELECT * FROM post")
    List<Post> getAllPost();

    // Получение Post из бд по id
    @Query("SELECT * FROM post WHERE id = :id")
    Post getPostWithId(int id);

//    // Получение всех Post из бд с условием
//    @Query("SELECT * FROM post WHERE favoriteColor LIKE :color")
//    List<Post> getAllPostWithFavoriteColor(String color);


}
