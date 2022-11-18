package com.example.myproject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {Animal.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

  public static AppDataBase appDataBase;

  public abstract AnimalDao getAnimalDao();

  static AppDataBase getDbInstance(){
      if(appDataBase == null){
          appDataBase = Room.databaseBuilder(MyApp.context,AppDataBase.class,"Animal").build();
      }
      return appDataBase;
  }

}
