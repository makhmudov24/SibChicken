package com.example.sibchicken;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LifeCycle implements LifecycleObserver, LifecycleOwner {

    static FirebaseDatabase mDataBase;
    static DatabaseReference ref;
    static Boolean check = false;


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void initViewModel() {
        MainActivity.setVersion(MainActivity.getSharedPreferences().getFloat("Version", 0));

        MainActivity.getViewModel().getMenu1Week().observe(this, menu1Weeks -> {
                MainActivity.setMenu1Weeks(menu1Weeks);
            if (check) {
                Log.i("STAAAAAAART", "Check");
                MainActivity.setFM();
            } else if (RealtimeDB.i == menu1Weeks.size() && menu1Weeks.size() != 0) {
                MainActivity.setFM();
            }
            Log.i("STAAAAAAART", "initViewModel " + String.valueOf(menu1Weeks.size()));
        });
        MainActivity.getViewModel().getCategories().observe(this,
                categories -> MainActivity.setCategories(categories));

        mDataBase = FirebaseDatabase.getInstance();
        ref = mDataBase.getReference("Menu");

        if (MainActivity.getVersion() != 0) {
            if (!MainActivity.getCheckIt()) {
                check = true;
                Log.i("FirstLaunch","No Ethernet");
            } else {
                RealtimeDB.getDataFromDB();
            }
        } else {
            if (!MainActivity.getCheckIt()) {
                Log.i("FirstLaunch","FirstLaunch Without Ethernet");
            } else {
                RealtimeDB.getDataFromDB();
            }
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void savePref() {
        MainActivity.getSharedPreferences().edit().putFloat("Version",
                MainActivity.getVersion()).apply();

    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return MainActivity.lifecycle;
    }

    public static DatabaseReference getRef() {
        return ref;
    }
}
