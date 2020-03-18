package com.example.tema2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReviewDao {

    @Query("SELECT * FROM Review")
    List<Review> getAll();


    @Query("SELECT * FROM Review WHERE TRIM(fullName) LIKE :fullName LIMIT 1")
    Review findByName(String fullName);

    @Insert
    void insert(Review review);


    @Delete
    void delete(Review review);
}
