package com.serkancay.doviz.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by S.Serkan Cay on 25.07.2019
 */

@Entity(tableName = "rates")
public class Rate {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "base")
    public String base;

    @ColumnInfo(name = "value")
    public float value;

    @ColumnInfo(name = "date")
    public String date;

    public Rate(String base, float value, String date) {
        this.base = base;
        this.value = value;
        this.date = date;
    }
}
