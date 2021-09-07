package com.example.carsapp_week8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchCarActivity extends AppCompatActivity {

    Context self;

    Button makerButton, yearButton, priceButton, searchAllButton;
    EditText makerEditText, yearEditText, minPriceEditText, maxPriceEditText;
    String maker;
    int year, minPrice, maxPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_car_main);

        self = this;

        makerEditText = findViewById(R.id.makerEditText);
        yearEditText = findViewById(R.id.yearEditText);
        minPriceEditText = findViewById(R.id.minPriceEditText);
        maxPriceEditText = findViewById(R.id.maxPriceEditText);

        makerButton = findViewById(R.id.makerButton);
        yearButton = findViewById(R.id.yearButton);
        priceButton = findViewById(R.id.priceButton);
        searchAllButton = findViewById(R.id.searchAllButton);

        makerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (makerEditText.getText().toString().length() == 0)
                {
                    Toast.makeText(self, "Please insert a valid car maker.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                    {
                        maker = makerEditText.getText().toString();
                        Intent i = new Intent(self, RecyclerViewCarActivity.class);
                        i.putExtra("maker", maker);
                        i.putExtra("Key", "getCarByMaker");
                        startActivity(i);
                    }
            }
        });

        yearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yearEditText.getText().toString().length() == 0)
                {
                    Toast.makeText(self, "Please insert a valid year.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    year = Integer.parseInt(yearEditText.getText().toString());
                    Intent i = new Intent(self, RecyclerViewCarActivity.class);
                    i.putExtra("year", year);
                    i.putExtra("Key", "getCarByYear");
                    startActivity(i);
                }
            }
        });

        priceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (minPriceEditText.getText().toString().length() == 0 || maxPriceEditText.getText().toString().length() == 0)
                {
                    Toast.makeText(self, "Please insert a valid price range.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    minPrice = Integer.parseInt(minPriceEditText.getText().toString());
                    maxPrice = Integer.parseInt(maxPriceEditText.getText().toString());
                    Intent i = new Intent(self, RecyclerViewCarActivity.class);
                    i.putExtra("minPrice", minPrice);
                    i.putExtra("maxPrice", maxPrice);
                    i.putExtra("Key", "getCarByPrice");
                    startActivity(i);
                }
            }
        });

        searchAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (makerEditText.getText().toString().length() == 0 || yearEditText.getText().toString().length() == 0 || minPriceEditText.getText().toString().length() == 0 || maxPriceEditText.getText().toString().length() == 0)
                {
                    Toast.makeText(self, "Please make sure that all fields are filled with valid values.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    maker = makerEditText.getText().toString();
                    year = Integer.parseInt(yearEditText.getText().toString());
                    minPrice = Integer.parseInt(minPriceEditText.getText().toString());
                    maxPrice = Integer.parseInt(maxPriceEditText.getText().toString());
                    Intent i = new Intent(self, RecyclerViewCarActivity.class);
                    i.putExtra("maker", maker);
                    i.putExtra("year", year);
                    i.putExtra("minPrice", minPrice);
                    i.putExtra("maxPrice", maxPrice);
                    i.putExtra("Key", "getCarByAll");
                    startActivity(i);
                }
            }
        });
    }

    public void goBack (View v)
    {
        finish();
    }

    public void clearAllFields (View v)
    {
        makerEditText.getText().clear();
        yearEditText.getText().clear();
        minPriceEditText.getText().clear();
        maxPriceEditText.getText().clear();
        Toast.makeText(this, "All fields have been cleared.", Toast.LENGTH_SHORT).show();
    }
}