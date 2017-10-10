package com.android.dalia.updateableandroidapp.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.android.dalia.updateableandroidapp.model.db.AppDatabase;
import com.android.dalia.updateableandroidapp.model.dto.ItemModel;

import java.util.List;

/**
 * Created by Dalia on 10/9/2017.
 */

public class LocalDatabaseStrategy  implements DataSourceStrategy{
    private LiveData<List<ItemModel>> listLiveData;
    private AppDatabase appDatabase;

    public LocalDatabaseStrategy(Context context) {
        appDatabase = AppDatabase.getDatabase(context);

        listLiveData = appDatabase.itemAndPersonModel().getAllBorrowedItems();
    }

    @Override
    public LiveData<List<ItemModel>> getData() {
        return listLiveData;
    }

    public void deleteItem(ItemModel itemModel){
        new deleteAsyncTask(appDatabase).execute(itemModel);
    }

    public void addItem(ItemModel itemModel){
        new addAsyncTask(appDatabase).execute(itemModel);
    }



    private static class deleteAsyncTask extends AsyncTask<ItemModel, Void, Void> {
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
