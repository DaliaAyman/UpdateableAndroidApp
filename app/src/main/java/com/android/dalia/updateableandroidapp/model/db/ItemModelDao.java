package com.android.dalia.updateableandroidapp.model.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.android.dalia.updateableandroidapp.model.dto.ItemModel;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Dalia on 9/28/2017.
 */

@Dao
@TypeConverters(DateConverter.class)
public interface ItemModelDao {

    @Query("select * from ItemModel")
    LiveData<List<ItemModel>> getAllBorrowedItems();

    @Query("select * from ItemModel where id = :id")
    ItemModel getItemById(String id);

    @Insert(onConflict = REPLACE)
    void addItem(ItemModel itemModel);

    @Delete
    void deleteItem(ItemModel itemModel);
}
