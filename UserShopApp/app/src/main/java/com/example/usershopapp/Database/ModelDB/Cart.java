package com.example.usershopapp.Database.ModelDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cart")
public class Cart {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    public int id;

    @ColumnInfo(name="name")
    public String name;


    @ColumnInfo(name="amount")
    public int amount;

    @ColumnInfo(name="price")
    public double price;

    @ColumnInfo(name="size")
    public int size;

    @ColumnInfo(name="with")
    public String with;

    @ColumnInfo(name="link")
    public String link;
}
