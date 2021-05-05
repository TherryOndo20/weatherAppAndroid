package com.example.myweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    float x1, x2, y1, y2;
    EditText editText;
    ImageView imageView;
    Button button, cancel;
    TextView country, city, temperature, max, min, sunrise, sunset, pressure, humidity, windspeed, time, description;

    List<City> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = MyApplication.getCityList();


        editText = findViewById(R.id.editTextTextPersonNameadd);
        button = findViewById(R.id.searchadd);
        cancel = findViewById(R.id.CancelAddCity);
        country = findViewById(R.id.country);
        city = findViewById(R.id.city);
        temperature = findViewById(R.id.temperature);
        imageView = findViewById(R.id.imageView);
        max = findViewById(R.id.maximumv);
        min = findViewById(R.id.minimumv);
        sunrise = findViewById(R.id.sunrisev);
        sunset = findViewById(R.id.sunsetv);
        pressure = findViewById(R.id.pressurev);
        humidity = findViewById(R.id.humidityv);
        windspeed = findViewById(R.id.windspeedv);
        time = findViewById(R.id.time);
        description = findViewById(R.id.description);


        button.setOnClickListener(v -> {
            String anycity = editText.getText().toString();
            if (anycity.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, ListCity.class);
                startActivity(intent);
            } else {
//                findWeather();
                FindWeather.excecute(anycity, country, sunrise, sunset, city,
                        temperature, max, min, humidity, pressure,
                        windspeed, description, time, cityList, imageView, this);
            }
        });

        cancel.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListCity.class);
            startActivity(intent);
        });

    }

    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x1 < x2) {
                    Intent i = new Intent(MainActivity.this, CapeTown.class);
                    startActivity(i);
                } else if (x1 > x2) {
                    Intent i = new Intent(MainActivity.this, Lisbon.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}