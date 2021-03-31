package com.example.myweatherapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class AddCityPage extends AppCompatActivity {
    Button button, cancel;

    List<City> cityList;
    TextView temperature_line_city,description_line_city,country_line_city,city_line_city,humidity_line_city,time_line_city;


    MyApplication myApplication = (MyApplication) this.getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city_page);

        cityList = myApplication.getCityList();

        button = findViewById(R.id.searchadd);
        cancel = findViewById(R.id.CancelAddCity);


        // Create city object
        int nextId = myApplication.getNextId();

        // Add new city to list
        // back to main screen
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //findWeather();
                Intent intent = new Intent(AddCityPage.this,MainActivity.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCityPage.this,ListCity.class);
                startActivity(intent);
            }
        });
      }
}