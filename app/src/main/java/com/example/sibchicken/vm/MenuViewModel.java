package com.example.sibchicken.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.sibchicken.db.MenuDatabase;
import com.example.sibchicken.tables.Categories;
import com.example.sibchicken.tables.Menu1Week;

import java.util.List;

public class MenuViewModel extends AndroidViewModel {

    private final LiveData<List<Menu1Week>> menu1Week;
    private final LiveData<List<Categories>> categories;

    MenuRepository repository;

    public MenuViewModel(@NonNull Application application) {
        super(application);
        repository = new MenuRepository(application);
        menu1Week = repository.getMenu1Week();
        categories = repository.getCategories();
    }

    public LiveData<List<Menu1Week>> getMenu1Week() {
        return menu1Week;
    }

    public LiveData<List<Categories>> getCategories() {
        return categories;
    }

    public void insertMenu1(final Menu1Week menu1Week) {
        repository.insertMenu1(menu1Week);
    }

    public void insertCategories(final Categories categories) {
        repository.insertCategories(categories);
    }

    public void deleteMenu1() {
        repository.deleteMenu1();
    }

    public void deleteCategories() {
        repository.deleteCategories();
    }

}
