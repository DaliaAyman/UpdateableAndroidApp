package com.android.dalia.updateableandroidapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.android.dalia.updateableandroidapp.model.DataSourceStrategy;
import com.android.dalia.updateableandroidapp.model.db.AppDatabase;
import com.android.dalia.updateableandroidapp.model.dto.ItemModel;

import java.util.List;

/**
 * Created by Dalia on 9/28/2017.
 */

public class ItemListViewModel extends ViewModel {
    private LiveData<List<ItemModel>> itemAndPersonList;
    DataSourceStrategy dataSourceStrategy;

    public ItemListViewModel(DataSourceStrategy dataSourceStrategy) {
        this.dataSourceStrategy = dataSourceStrategy;
    }

    public LiveData<List<ItemModel>> getItemAndPersonList() {
        itemAndPersonList = dataSourceStrategy.getData();

        return itemAndPersonList;
    }

    public void addItem(ItemModel itemModel){
        dataSourceStrategy.addItem(itemModel);
    }

    public void deleteItem(ItemModel itemModel){
        dataSourceStrategy.deleteItem(itemModel);
    }


}
