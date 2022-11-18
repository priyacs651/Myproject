package com.example.myproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Animal {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String types;
    public String description;

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", types='" + types + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
