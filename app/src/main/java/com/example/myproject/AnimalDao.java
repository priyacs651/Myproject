package com.example.myproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface AnimalDao {
    @Insert
    void addAnimal(Animal animal);
    @Query("SELECT * FROM Animal")
    List<Animal> getAnimal();
}
