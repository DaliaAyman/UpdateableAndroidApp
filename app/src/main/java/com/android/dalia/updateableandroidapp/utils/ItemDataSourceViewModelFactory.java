package com.android.dalia.updateableandroidapp.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.android.dalia.updateableandroidapp.model.DataSourceStrategy;
import com.android.dalia.updateableandroidapp.viewmodel.ItemListViewModel;

/**
 * Created by Dalia on 10/9/2017.
 */

public class ItemDataSourceViewModelFactory implements ViewModelProvider.Factory{

    private final DataSourceStrategy dataSourceStrategy;

    public ItemDataSourceViewModelFactory(DataSourceStrategy dataSourceStrategy) {
        this.dataSourceStrategy = dataSourceStrategy;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ItemListViewModel.class)){
            return (T) new ItemListViewModel(dataSourceStrategy);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
