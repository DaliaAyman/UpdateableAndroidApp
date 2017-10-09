package com.android.dalia.updateableandroidapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.android.dalia.updateableandroidapp.model.db.AppDatabase;
import com.android.dalia.updateableandroidapp.model.dto.ItemModel;

import java.util.List;

/**
 * Created by Dalia on 9/28/2017.
 */

public class ItemListViewModel extends AndroidViewModel {
    private final LiveData<List<ItemModel>> itemAndPersonList;

    private AppDatabase appDatabase;

    public ItemListViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        itemAndPersonList = appDatabase.itemAndPersonModel().getAllBorrowedItems();
    }

    public LiveData<List<ItemModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(ItemModel itemModel){
        new deleteAsyncTask(appDatabase).execute(itemModel);
    }

    public void addItem(ItemModel itemModel){
        new addAsyncTask(appDatabase).execute(itemModel);
    }

    private static class deleteAsyncTask extends AsyncTask<ItemModel, Void, Void>{
        private AppDatabase db;

        public deleteAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(ItemModel... params) {
            db.itemAndPersonModel().deleteItem(params[0]);
            return null;
        }
    }

    private static class addAsyncTask extends AsyncTask<ItemModel, Void, Void>{
        private AppDatabase db;

        public addAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(ItemModel... params) {
            db.itemAndPersonModel().addItem(params[0]);
            return null;
        }
    }
}
