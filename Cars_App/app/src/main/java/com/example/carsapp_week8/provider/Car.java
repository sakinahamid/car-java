package com.example.carsapp_week8.provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "car")
public class Car {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "carID")
    private int car_id;

    @ColumnInfo(name = "carMaker")
    private String car_maker;

    @ColumnInfo(name = "carModel")
    private String car_model;

    @ColumnInfo(name = "carYear")
    private int car_year;

    @ColumnInfo(name = "carColor")
    private String car_color;

    @ColumnInfo(name = "carSeats")
    private int car_seats;

    @ColumnInfo(name = "carPrice")
    private int car_price;

    public Car(String car_maker, String car_model, int car_year, String car_color, int car_seats, int car_price) {
        this.car_maker = car_maker;
        this.car_model = car_model;
        this.car_year = car_year;
        this.car_color = car_color;
        this.car_seats = car_seats;
        this.car_price = car_price;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(@NonNull int car_id) {
        this.car_id = car_id;
    }

    public String getCar_maker() {
        return car_maker;
    }

    public void setCar_maker(String car_maker) {
        this.car_maker = car_maker;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public int getCar_year() {
        return car_year;
    }

    public void setCar_year(int car_year) {
        this.car_year = car_year;
    }

    public String getCar_color() {
        return car_color;
    }

    public void setCar_color(String car_color) {
        this.car_color = car_color;
    }

    public int getCar_seats() {
        return car_seats;
    }

    public void setCar_seats(int car_seats) {
        this.car_seats = car_seats;
    }

    public int getCar_price() {
        return car_price;
    }

    public void setCar_price(int car_price) {
        this.car_price = car_price;
    }
}
