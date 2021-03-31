package com.example.myweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
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

public class Paris extends AppCompatActivity {
    float x1, x2, y1, y2;
    ImageView imageView;
    TextView country, city, temperature, max, min, sunrise, sunset, pressure, humidity, windspeed, time, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paris);




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


        findWeather();
    }


    public void findWeather() {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=paris&appid=157b4de0b117433be579c0f828d2aa6c&units=metric";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Calling API

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    //find country
                    JSONObject object1 = jsonObject.getJSONObject("sys");
                    String find_country = object1.getString("country");
                    country.setText(find_country + " |");
                    // find sunrise
                    String find_sunrise = object1.getString("sunrise");
                    sunrise.setText(find_sunrise);
                    // find sunset
                    String find_sunset = object1.getString("sunset");
                    sunset.setText(find_sunset);

                    //find city
                    String find_city = jsonObject.getString("name");
                    city.setText(find_city);

                    //find temperature
                    JSONObject object2 = jsonObject.getJSONObject("main");
                    String find_temp = object2.getString("temp");
                    String find_temp_max = object2.getString("temp_max");
                    String find_temp_min = object2.getString("temp_min");

                    temperature.setText(find_temp + " °C");
                    max.setText(find_temp_max + " °C");
                    min.setText(find_temp_min + " °C");

                    //find humidity
                    String find_humidity = object2.getString("humidity");
                    humidity.setText(find_humidity + " %");

                    //find pressure
                    String find_pressure = object2.getString("pressure");
                    pressure.setText(find_pressure + "hPa");


                    //find image icon
                    JSONArray jsonArray = jsonObject.getJSONArray("weather");
                    JSONObject obj = jsonArray.getJSONObject(0);
                    String icon = obj.getString("icon");
                    Picasso.get().load("http://openweathermap.org/img/wn/" + icon + "@2x.png").into(imageView);

                    // find wind speed
                    JSONObject object3 = jsonObject.getJSONObject("wind");
                    String find_speed = object3.getString("speed");
                    windspeed.setText(find_speed + " km/h");

                    //find date and time
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss \ndd/MM/yyyy");
                    String date = stf.format(calendar.getTime());
                    time.setText(date);

                    //find description
                    String find_description = obj.getString("description");
                    description.setText(find_description);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Paris.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Paris.this);
        requestQueue.add(stringRequest);
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
                    Intent i = new Intent(Paris.this, Madrid.class);
                    startActivity(i);
                }else if(x1>x2) {
                    Intent i = new Intent(Paris.this, Berlin.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}