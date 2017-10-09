package com.android.dalia.updateableandroidapp.model.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Dalia on 9/28/2017.
 */

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timeStamp){
        return timeStamp == null ? null : new Date(timeStamp);
    }

    @TypeConverter
    public static Long toTimeStamp(Date date){
        return date == null ? null : date.getTime();
    }
}
