package com.example.sibchicken.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sibchicken.tables.Categories;
import com.example.sibchicken.tables.Menu1Week;

import java.util.List;

@Dao
public interface MenuDao {

    @Query("SELECT * FROM Menu1Week")
    LiveData<List<Menu1Week>> LoadAllMenu1Week();

    @Query("DELETE FROM Menu1Week")
    void deleteMenu1Week();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMenu1Week(Menu1Week menu1Week);

    @Query("SELECT * FROM Categories")
    LiveData<List<Categories>> LoadAllCategories();

    @Query("DELETE FROM Categories")
    void deleteCategories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategories(Categories categories);



}
