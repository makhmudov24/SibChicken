package com.example.sibchicken.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "Categories")
public class Categories {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int nextCategories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNextCategories() {
        return nextCategories;
    }

    public void setNextCategories(int nextCategories) {
        this.nextCategories = nextCategories;
    }
}
