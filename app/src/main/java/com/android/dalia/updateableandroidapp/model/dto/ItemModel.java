package com.android.dalia.updateableandroidapp.model.dto;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.android.dalia.updateableandroidapp.model.db.DateConverter;

import java.util.Date;

/**
 * Created by xqbz7438 on 9/28/2017.
 */

@Entity
public class ItemModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String itemName;
    private String personName;

    @TypeConverters(DateConverter.class)
    private Date borrowDate;

    public ItemModel(String itemName, String personName, Date borrowDate) {
        this.itemName = itemName;
        this.personName = personName;
        this.borrowDate = borrowDate;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPersonName() {
        return personName;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }
}
