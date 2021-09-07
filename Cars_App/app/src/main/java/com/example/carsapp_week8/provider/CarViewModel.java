package com.example.carsapp_week8.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CarViewModel extends AndroidViewModel { // communicates between UI and repository

    private CarRepository mRepository;
    private LiveData<List<Car>> mCars;

    public CarViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CarRepository(application);
    }

    public LiveData<List<Car>> getAllCars() {
        mCars = mRepository.getAllCars();
        return mCars;
    }
    public void insert(Car car) {
        mRepository.insert(car);
    }
    public void deleteAll(){
        mRepository.deleteAll();
    }

    public LiveData<List<Car>> getCarByMaker(String maker){
        maker = maker.toUpperCase();
        mCars = mRepository.getCarByMaker(maker);
        return mCars;
    }
    public LiveData<List<Car>> getCarByYear(int year){
        mCars = mRepository.getCarByYear(year);
        return mCars;
    }
    public LiveData<List<Car>> getCarByPrice(int minPrice, int maxPrice){
        mCars = mRepository.getCarByPrice(minPrice, maxPrice);
        return mCars;
    }
    public LiveData<List<Car>> getCarByAll(String maker, int year, int minPrice, int maxPrice){
        maker = maker.toUpperCase();
        mCars = mRepository.getCarByAll(maker, year, minPrice, maxPrice);
        return mCars;
    }
}
