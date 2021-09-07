package com.example.carsapp_week8;

public class CarBasic {

    private String maker;
    private String model;
    private String year;
    private String color;
    private String seats;
    private String price;

    // Constructor
    public CarBasic(String maker, String model, String year, String color, String seats, String price) {
//    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
        this.year = year;
        this.color = color;
        this.seats = seats;
        this.price = price;
    }

    // Getter
    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getSeats() {
        return seats;
    }

    public String getPrice() {
        return price;
    }

}
