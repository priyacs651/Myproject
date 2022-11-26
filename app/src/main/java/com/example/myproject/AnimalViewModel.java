package com.example.myproject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class AnimalViewModel extends ViewModel {

    public MutableLiveData<List<Animal>> animalMutableLiveData = new MutableLiveData<>();

    public final AnimalRepo animalRepo = new AnimalRepo();

    public void addAnimal(Animal animals) {
        animalRepo.addAnimal(animals);
    }

    public void getAnimal() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                animalMutableLiveData.postValue(animalRepo.getAnimal());
            }
        }).start();
    }

    public void removeAnimal(Animal animal) {
        animalRepo.deleteAnimal(animal);
    }
}
