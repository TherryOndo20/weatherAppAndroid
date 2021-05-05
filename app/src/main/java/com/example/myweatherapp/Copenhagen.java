package com.example.myweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;



public class Copenhagen extends AppCompatActivity {
    float x1, x2, y1, y2;

    ImageView imageView;
    TextView country, city, temperature, max, min, sunrise, sunset, pressure, humidity, windspeed, time, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copenhagen);

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


        FindWeather.excecute("copenhagen", country, sunrise, sunset, city,
                temperature, max, min, humidity, pressure,
                windspeed, description, time, MyApplication.getCityList(), imageView, this);
    }


    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1<x2){
                    Intent i = new Intent(Copenhagen.this, Berlin.class);
                    startActivity(i);
                }else if(x1>x2) {
                    Intent i = new Intent(Copenhagen.this, Rome.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}