package com.android.dalia.updateableandroidapp.model;

import android.arch.lifecycle.LiveData;

import com.android.dalia.updateableandroidapp.model.dto.ItemModel;

import java.util.List;

/**
 * Created by Dalia on 10/9/2017.
 */

public interface DataSourceStrategy {
    public LiveData<List<ItemModel>> getData();

    public void addItem(ItemModel itemModel);

    public void deleteItem(ItemModel itemModel);
}
