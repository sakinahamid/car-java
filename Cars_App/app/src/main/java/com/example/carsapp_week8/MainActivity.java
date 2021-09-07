package com.example.carsapp_week8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carsapp_week8.provider.Car;
import com.example.carsapp_week8.provider.CarViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context self;

    String persCarMaker, persCarModel, persCarColor;
    int persCarYear, persCarSeats, persCarPrice;

    EditText textMaker, textModel, textYear, textColor, textSeats, textPrice;

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    View constraintLayout;
    MyRecyclerAdapter adapter;
    private CarViewModel mCarViewModel;
    FloatingActionButton fab;
    DatabaseReference myRef;
    GestureDetector gestureDetector;

    ArrayList<String> myList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);

        self = this;

        textMaker = findViewById(R.id.editTextMaker);
        textModel = findViewById(R.id.editTextModel);
        textYear = findViewById(R.id.editTextYear);
        textColor = findViewById(R.id.editTextColor);
        textSeats = findViewById(R.id.editTextSeats);
        textPrice = findViewById(R.id.editTextPrice);

        toolbar = findViewById(R.id.toolbarCar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerCar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navViewCar);
        navigationView.setNavigationItemSelectedListener(new MyNavigationListener());

        constraintLayout = findViewById(R.id.constraintLayout);

        adapter = new MyRecyclerAdapter();
        mCarViewModel = new ViewModelProvider(this).get(CarViewModel.class);
        mCarViewModel.getAllCars().observe(this, newData -> {
            adapter.setData(newData);
            adapter.notifyDataSetChanged();
        });

        fab = findViewById(R.id.fabAddCar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persCarMaker = textMaker.getText().toString();
                persCarModel = textModel.getText().toString();
                persCarYear = Integer.parseInt(textYear.getText().toString());
                persCarColor = textColor.getText().toString();
                persCarSeats = Integer.parseInt(textSeats.getText().toString());
                persCarPrice = Integer.parseInt(textPrice.getText().toString());

                // Add to CarDatabase
                Car car = new Car(persCarMaker, persCarModel, persCarYear, persCarColor, persCarSeats, persCarPrice);
                mCarViewModel.insert(car);

                //Add to Firebase
                myRef.push().setValue(car);
            }
        });

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Item/Car");

        gestureDetector = new GestureDetector(this, new MyGestureListener());

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                gestureDetector.onTouchEvent(event);

                return true;
            }
        });
    }

    class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.itemAddCar){
                persCarMaker = textMaker.getText().toString();
                persCarModel = textModel.getText().toString();
                persCarYear = Integer.parseInt(textYear.getText().toString());
                persCarColor = textColor.getText().toString();
                persCarSeats = Integer.parseInt(textSeats.getText().toString());
                persCarPrice = Integer.parseInt(textPrice.getText().toString());

                // Add to CarDatabase
                Car car = new Car(persCarMaker, persCarModel, persCarYear, persCarColor, persCarSeats, persCarPrice);
                mCarViewModel.insert(car);

                //Add to Firebase
                myRef.push().setValue(car);

            }

            else if (id == R.id.itemRemoveAll){

                // Remove from carDatabase
                mCarViewModel.deleteAll();

                // Remove from Firebase
                myRef.removeValue();

                Toast.makeText(self, "All cars have been removed.", Toast.LENGTH_SHORT).show();
            }

            else if (id == R.id.itemListAll){
                Intent myIntent = new Intent(self, RecyclerViewCarActivity.class);
                myIntent.putExtra("Key", "getAllCars");
                startActivity(myIntent);
            }

            else if (id == R.id.itemSearch){
                Intent myIntent = new Intent(self, SearchCarActivity.class);
                startActivity(myIntent);
            }


            drawer.closeDrawers();
            return true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.clearFields)
        {

            textMaker.setText("");
            textModel.setText("");
            textYear.setText("0");
            textColor.setText("");
            textSeats.setText("0");
            textPrice.setText("0");

            Toast toastMaker = Toast.makeText(this, "All fields have been cleared.", Toast.LENGTH_SHORT);
            toastMaker.show();
        }

        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList("LIST",myList);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            persCarSeats = Integer.parseInt(textSeats.getText().toString());
            persCarSeats += 1;
            textSeats.setText(persCarSeats + "");

            Log.i("w11", "onSingleTapConfirmed");
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {

            textMaker.setText("Proton");
            textModel.setText("Iriz");
            textYear.setText("2020");
            textColor.setText("Blue");
            textSeats.setText("5");
            textPrice.setText("30000");

            Log.i("w11", "onDoubleTap");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (distanceX > 0)
            {
                persCarPrice = Integer.parseInt(textPrice.getText().toString());
                persCarPrice = persCarPrice + (int) distanceX;

                if (persCarPrice < 50000)
                    textPrice.setText(persCarPrice + "");
                else
                    textPrice.setText("50000");

            }

            else if (distanceX < 0)
            {
                persCarPrice = Integer.parseInt(textPrice.getText().toString());
                persCarPrice = persCarPrice + (int) distanceX;

                if (persCarPrice > 0)
                    textPrice.setText(persCarPrice + "");
                else
                    textPrice.setText("0");
            }

            Log.i("w11", "onScroll");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (velocityY > 600 || velocityX > 600)
                moveTaskToBack(true);

            Log.i("w11", "onFling");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {

            textMaker.setText("");
            textModel.setText("");
            textYear.setText("0");
            textColor.setText("");
            textSeats.setText("0");
            textPrice.setText("0");

            Toast.makeText(self, "All fields have been reset.", Toast.LENGTH_SHORT).show();

            Log.i("w11", "onLongPress");
            return;
        }
    }
}