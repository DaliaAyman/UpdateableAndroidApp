package com.android.dalia.updateableandroidapp.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.android.dalia.updateableandroidapp.model.DataSourceStrategy;
import com.android.dalia.updateableandroidapp.model.dto.ItemModel;

/**
 * Created by Dalia on 9/28/2017.
 */

@Database(entities = {ItemModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "borrow_db")
                    .build();
        }
        return INSTANCE;
    }

    //TIP: We have to create an abstract method for every DAO class that we create.
    public abstract ItemModelDao itemAndPersonModel();
}
