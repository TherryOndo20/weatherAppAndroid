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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

 float x1, x2, y1, y2;
    EditText editText;
    ImageView imageView;
    Button button, cancel;
    TextView country, city, temperature, max, min, sunrise, sunset, pressure, humidity, windspeed, time, description;

   List<City> cityList;
//   List<Subcity> subcityList = new ArrayList<>();
    MyApplication myApplication = (MyApplication) this.getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       cityList = myApplication.getCityList();


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


       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String anycity = editText.getText().toString();
if (anycity.isEmpty()){
    Intent intent = new Intent(MainActivity.this,ListCity.class);
    startActivity(intent);
}else{
               findWeather();
           }}
       });

       cancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,ListCity.class);
               startActivity(intent);
           }
       });

    }


    public void findWeather() {
        String anycity = editText.getText().toString();
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+ anycity +"&appid=157b4de0b117433be579c0f828d2aa6c&units=metric";

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

                    temperature.setText(find_temp + "°C");
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
                     String pic= "http://openweathermap.org/img/wn/" + icon + "@2x.png";
//                     int value_icon = Integer.parseInt(pic);

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

                    // Create city object
                  int nextId = myApplication.getNextId();
                  int idSub = MyApplication.getSubcityList().size() + 1;
                  City newCity = new City(nextId, find_temp + " °C",find_description,find_city, find_country, 1 , date,find_humidity + " %");
                  Subcity newSubCity = new Subcity(idSub, find_temp + " °C",find_description,find_city, find_country, pic , date,find_humidity + " %");
                    // Add new city to list
                    cityList.add(newCity);
                    myApplication.setNextId(nextId++);

                    MyApplication.AddSubcity(newSubCity);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
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
                    Intent i = new Intent(MainActivity.this, CapeTown.class);
                    startActivity(i);
                }else if(x1>x2) {
                        Intent i = new Intent(MainActivity.this, Lisbon.class);
                        startActivity(i);
                    }
                    break;
                    }
                    return false;
    }
}