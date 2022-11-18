package com.example.myproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Animal implements Serializable {
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
