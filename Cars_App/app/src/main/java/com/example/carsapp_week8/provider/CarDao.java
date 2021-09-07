package com.example.carsapp_week8.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarDao {

    @Query("select * from car")
    LiveData<List<Car>> getAllCar();

    @Insert
    void addCar(Car car);

    @Query("delete FROM car")
    void deleteAllCars();

    @Query("select * from car where upper(carMaker) = :maker")
    LiveData<List<Car>> getCarByMaker(String maker);

    @Query("select * from car where carYear = :year")
    LiveData<List<Car>> getCarByYear(int year);

    @Query("select * from car where carPrice between :minPrice and :maxPrice")
    LiveData<List<Car>> getCarByPrice(int minPrice, int maxPrice);

    @Query("select * from car where upper(carMaker) = :maker and carYear = :year and carPrice between :minPrice and :maxPrice")
    LiveData<List<Car>> getCarByAll(String maker, int year, int minPrice, int maxPrice);

}

