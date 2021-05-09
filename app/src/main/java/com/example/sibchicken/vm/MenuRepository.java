package com.example.sibchicken.vm;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.sibchicken.db.MenuDao;
import com.example.sibchicken.db.MenuDatabase;
import com.example.sibchicken.tables.Categories;
import com.example.sibchicken.tables.Menu1Week;

import java.util.List;

public class MenuRepository {

    private final MenuDao menuDao;
    private final LiveData<List<Menu1Week>> menu1Week;
    private final LiveData<List<Categories>> categories;

    public MenuRepository(Application application) {
        menuDao = MenuDatabase.getDatabase(application.getApplicationContext()).menuDao();
        menu1Week = menuDao.LoadAllMenu1Week();
        categories = menuDao.LoadAllCategories();
    }

    LiveData<List<Menu1Week>> getMenu1Week() {
        return menu1Week;
    }

    LiveData<List<Categories>> getCategories() {
        return categories;
    }

    void insertMenu1(final Menu1Week menu1Week) {
        MenuDatabase.dbWriteExecutor.execute(() -> menuDao.insertMenu1Week(menu1Week));
    }

    void insertCategories(final Categories categories) {
        MenuDatabase.dbWriteExecutor.execute(() -> menuDao.insertCategories(categories));
    }

    void deleteMenu1() {
        menuDao.deleteMenu1Week();
    }

    void deleteCategories() {
        menuDao.deleteCategories();
    }

}
