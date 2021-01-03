package com.pri.fooddelivery.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"email"},
        unique = true)})
public class Profile {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public int id;
    public String email;
    public String name;
    public String password;
    public String contact;
    public String address;
    public String category;

    public Profile(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
