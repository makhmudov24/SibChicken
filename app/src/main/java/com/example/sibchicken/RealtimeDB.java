package com.example.sibchicken;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.sibchicken.tables.Categories;
import com.example.sibchicken.tables.Menu1Week;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class RealtimeDB {

    public static int i = 0;
    public static void getDataFromDB() {
        Log.i("STAAAART", "getDataFromDB");

        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (Double.parseDouble(String.valueOf(dataSnapshot.child("/Version")
                        .getValue())) == MainActivity.getVersion()) {
                    MainActivity.setFM();
                    Log.i("Version", " VERSION");

                } else {
                    MainActivity.getViewModel().deleteCategories();
                    MainActivity.getViewModel().deleteMenu1();

                    MainActivity.setVersion((float)
                            Double.parseDouble
                                    (String.valueOf(dataSnapshot.child("/Version").getValue())));



                    Categories categories = new Categories();
                    categories.setNextCategories(i);
                    MainActivity.getViewModel().insertCategories(categories);

                    dataSnapshot = dataSnapshot.child("/Week1/Categories");

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Log.i("STAAAART", ds.getKey());

                        for (DataSnapshot ds1 : ds.getChildren()) {
                            addDataToRoomDB(new Menu1Week(), ds.getKey(), ds1);
                            i++;
                        }
                        categories = new Categories();
                        categories.setNextCategories(i);
                        MainActivity.getViewModel().insertCategories(categories);
                        Log.i("STAAAART", String.valueOf(categories.getNextCategories()));
                    }
                }
                Log.i("STAAAART", "MainActivity/setFM");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        LifeCycle.getRef().addValueEventListener(vListener);

    }

    private static void addDataToRoomDB(Menu1Week menu, String category, DataSnapshot ds) {

        switch (category) {
            case "Drinks":
                menu.setFoodCategory("Напитки");
                break;

            case "First":
                menu.setFoodCategory("Первые блюда");
                break;

            case "Second":
                menu.setFoodCategory("Вторые блюда");
                break;

            case "Salads":
                menu.setFoodCategory("Салаты");
                break;

            case "Snacks":
                menu.setFoodCategory("Закуски");
                break;
        }
        menu.setName(String.valueOf(ds.child("Name").getValue()));
        menu.setWeight(String.valueOf(ds.child("Weight").getValue()));
        menu.setPrice(String.valueOf(ds.child("Price").getValue()));

        MainActivity.getViewModel().insertMenu1(menu);
    }


}
