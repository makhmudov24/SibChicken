package com.example.sibchicken.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sibchicken.tables.Categories;
import com.example.sibchicken.tables.Menu1Week;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Menu1Week.class, Categories.class}, version = 1,
        exportSchema = false)
public abstract class MenuDatabase extends RoomDatabase {
    public abstract MenuDao menuDao();

    private static volatile MenuDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dbWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MenuDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MenuDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MenuDatabase.class, "database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

