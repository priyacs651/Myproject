package com.example.myproject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;

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
