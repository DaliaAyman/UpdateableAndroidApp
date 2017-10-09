package com.android.dalia.updateableandroidapp.model.service;

import com.android.dalia.updateableandroidapp.model.dto.ItemModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Dalia on 10/9/2017.
 */

public interface ItemsService {

    @GET("items")
    Call<List<ItemModel>> getItemsList();

}
