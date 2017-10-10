package com.android.dalia.updateableandroidapp.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.android.dalia.updateableandroidapp.model.DataSourceStrategy;
import com.android.dalia.updateableandroidapp.model.dto.ItemModel;
import com.android.dalia.updateableandroidapp.model.service.ItemsService;
import com.android.dalia.updateableandroidapp.utils.Globals;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dalia on 10/9/2017.
 */

public class APIDataStrategy implements DataSourceStrategy {
    ItemsService itemsService;
    private static APIDataStrategy INSTANCE;

    public APIDataStrategy() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        //TODO this gitHubService instance will be injected using Dagger in part #2 ...
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        itemsService = retrofit.create(ItemsService.class);
    }

    public synchronized static APIDataStrategy getInstance(){
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if(INSTANCE == null){
            INSTANCE = new APIDataStrategy();
        }
        return INSTANCE;
    }

    @Override
    public LiveData<List<ItemModel>> getData() {
        final MutableLiveData<List<ItemModel>> data = new MutableLiveData<>();

        itemsService.getItemsList().enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ItemModel>> call, Throwable t) {
                // TODO better error handling in part #2 ...
                Log.d("API", "Error onFailure " + t.toString() + "," + t.getStackTrace());
                data.setValue(null);
            }
        });
        return data;
    }

    @Override
    public void addItem(ItemModel itemModel) {

    }

    @Override
    public void deleteItem(ItemModel itemModel) {

    }
}
