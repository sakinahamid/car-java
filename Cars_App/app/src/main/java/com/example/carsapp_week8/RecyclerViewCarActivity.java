package com.example.carsapp_week8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.carsapp_week8.provider.CarViewModel;

public class RecyclerViewCarActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyRecyclerAdapter adapter;

    public CarViewModel mCarViewModel;

    Bundle extras;
    String maker;
    int year, minPrice, maxPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_car);

        recyclerView = findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new MyRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        mCarViewModel = new ViewModelProvider(this).get(CarViewModel.class);

        extras = getIntent().getExtras();

        if (extras != null)
        {
            String key = extras.getString("Key");

            if (key.equals("getAllCars"))
            {
                mCarViewModel.getAllCars().observe(this, newData -> {
                    adapter.setData(newData);
                    adapter.notifyDataSetChanged();
                });
            }

            else if (key.equals("getCarByMaker"))
            {
                maker = extras.getString("maker");
                mCarViewModel.getCarByMaker(maker).observe(this, newData -> {
                    adapter.setData(newData);
                    adapter.notifyDataSetChanged();
                });
            }

            else if (key.equals("getCarByYear"))
            {
                year = extras.getInt("year");
                mCarViewModel.getCarByYear(year).observe(this, newData -> {
                    adapter.setData(newData);
                    adapter.notifyDataSetChanged();
                });
            }

            else if (key.equals("getCarByPrice"))
            {
                minPrice = extras.getInt("minPrice");
                maxPrice = extras.getInt("maxPrice");
                mCarViewModel.getCarByPrice(minPrice, maxPrice).observe(this, newData -> {
                    adapter.setData(newData);
                    adapter.notifyDataSetChanged();
                });
            }

            else if (key.equals("getCarByAll"))
            {
                maker = extras.getString("maker");
                year = extras.getInt("year");
                minPrice = extras.getInt("minPrice");
                maxPrice = extras.getInt("maxPrice");
                mCarViewModel.getCarByAll(maker, year, minPrice, maxPrice).observe(this, newData -> {
                    adapter.setData(newData);
                    adapter.notifyDataSetChanged();
                });
            }
        }

    }

    public void goBack (View v)
    {
        finish();
    }
}