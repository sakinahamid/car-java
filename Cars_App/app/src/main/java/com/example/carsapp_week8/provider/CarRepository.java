package com.example.carsapp_week8.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CarRepository { // communicates with data sources

    private CarDao mCarDao;
    private LiveData<List<Car>> mCars;

    CarRepository(Application application) {
        CarDatabase db = CarDatabase.getDatabase(application); // instantiation of CarDatabase abstract class
        mCarDao = db.carDao(); // from CarDatabase
    }

    LiveData<List<Car>> getAllCars() {
        mCars = mCarDao.getAllCar();
        return mCars;
    }

    void insert(Car car) {
        CarDatabase.databaseWriteExecutor.execute(() -> mCarDao.addCar(car)); // databaseWriteExecutor must be declared in CarDatabase
    }

    void deleteAll(){
        CarDatabase.databaseWriteExecutor.execute(()->{
            mCarDao.deleteAllCars();
        });
    }

    LiveData<List<Car>> getCarByMaker(String maker){
        maker = maker.toUpperCase();
        mCars = mCarDao.getCarByMaker(maker);
        return mCars;
    }

    LiveData<List<Car>> getCarByYear(int year){
        mCars = mCarDao.getCarByYear(year);
        return mCars;
    }

    LiveData<List<Car>> getCarByPrice(int minPrice, int maxPrice){
        mCars = mCarDao.getCarByPrice(minPrice, maxPrice);
        return mCars;
    }

    LiveData<List<Car>> getCarByAll(String maker, int year, int minPrice, int maxPrice){
        maker = maker.toUpperCase();
        mCars = mCarDao.getCarByAll(maker, year, minPrice, maxPrice);
        return mCars;
    }

}
