package com.example.carsapp_week8.provider;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Car.class}, version = 2)
public abstract class CarDatabase extends RoomDatabase {
    public static final String CAR_DATABASE_NAME = "car_database";
    public abstract CarDao carDao();
    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile CarDatabase INSTANCE; // we want to always directly access the database from the (non-volatile) memory
    private static final int NUMBER_OF_THREADS = 4; // no. of threads to execute database methods
    static final ExecutorService databaseWriteExecutor = // used to execute SQL queries
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static CarDatabase getDatabase(final Context context) { // constructor that returns an instance of a database
        if (INSTANCE == null) {
            synchronized (CarDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CarDatabase.class, CAR_DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
