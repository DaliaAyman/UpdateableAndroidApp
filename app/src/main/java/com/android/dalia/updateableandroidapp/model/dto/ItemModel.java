package com.android.dalia.updateableandroidapp.model.dto;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.android.dalia.updateableandroidapp.model.db.DateConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Dalia on 9/28/2017.
 */

@Entity
public class ItemModel {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("item")
    @Expose
    private String itemName;
    @SerializedName("person")
    @Expose
    private String personName;

    @TypeConverters(DateConverter.class)
    @SerializedName("date")
    @Expose
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
