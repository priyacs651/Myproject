package com.example.myproject;

import android.util.Log;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


public class AnimalRepo {

    private AnimalDao animalDao;
    Thread thread;

    public AnimalRepo() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("itispk", "AnimalDao started");
                animalDao = AppDataBase.getDbInstance().getAnimalDao();
                Log.d("itispk", "AnimalDao initialized");
            }
        });
        thread.start();
    }

    public void addAnimal(Animal animal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                animalDao.addAnimal(animal);
            }
        }).start();
    }

    public void deleteAnimal(Animal animal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                animalDao.deleteAnimal(animal);
            }
        }).start();
    }

    public List<Animal> getAnimal() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return animalDao.getAnimal();
    }
}
