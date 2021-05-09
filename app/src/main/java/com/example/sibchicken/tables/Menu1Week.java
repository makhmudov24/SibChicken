package com.example.sibchicken.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Menu1Week")
public class Menu1Week {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String weight;

    private String price;

    private String foodCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
