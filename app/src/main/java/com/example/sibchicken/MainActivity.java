package com.example.sibchicken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.example.sibchicken.db.MenuDao;
import com.example.sibchicken.db.MenuDatabase;
import com.example.sibchicken.tables.Categories;
import com.example.sibchicken.tables.Menu1Week;
import com.example.sibchicken.vm.MenuViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static SharedPreferences sharedPreferences;

    public static FragmentManager fragmentManager;
    private static MenuViewModel viewModel;
    static Lifecycle lifecycle;
    private static Boolean checkIt;

    private static List<Categories> categories;
    private static List<Menu1Week> menu1Weeks;

    static float version = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkIt = hasConnection(getApplicationContext());

        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);

        LifeCycle setLifecycle = new LifeCycle();

        lifecycle = getLifecycle();
        lifecycle.addObserver(setLifecycle);

        sharedPreferences = this.getSharedPreferences("com.example.sibchecken", Context.MODE_PRIVATE);

        fragmentManager = getSupportFragmentManager();

    }

    public static boolean hasConnection(Context context) {

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }

    public static Boolean getCheckIt() {
        return checkIt;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static void setFM() {
        Log.i("STAAAART", " setFM");
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new MenuFragment())
                .commit();
    }

    public static MenuViewModel getViewModel() {
        return viewModel;
    }


    public static List<Categories> getCategories() {
        return categories;
    }

    public static void setCategories(List<Categories> categories) {
        MainActivity.categories = categories;
    }

    public static List<Menu1Week> getMenu1Weeks() {
        return menu1Weeks;
    }

    public static void setMenu1Weeks(List<Menu1Week> menu1Weeks) {
        MainActivity.menu1Weeks = menu1Weeks;
    }

    public static float getVersion() {
        return version;
    }

    public static void setVersion(float version) {
        MainActivity.version = version;
    }
}