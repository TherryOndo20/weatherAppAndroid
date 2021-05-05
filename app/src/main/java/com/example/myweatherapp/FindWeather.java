package com.example.myweatherapp;

import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class FindWeather {

    public static void excecute(final String anycity, TextView country, TextView sunrise, TextView sunset, TextView city,
                                TextView temperature, TextView max, TextView min, TextView humidity, TextView pressure,
                                TextView windspeed, TextView description, TextView time, List<City> cityList, ImageView imageView, AppCompatActivity activity) {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + anycity + "&appid=157b4de0b117433be579c0f828d2aa6c&units=metric";

        @SuppressLint("SetTextI18n") StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
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
                String pic = "http://openweathermap.org/img/wn/" + icon + "@2x.png";
//                     int value_icon = Integer.parseInt(pic);

                // find wind speed
                JSONObject object3 = jsonObject.getJSONObject("wind");
                String find_speed = object3.getString("speed");
                windspeed.setText(find_speed + " km/h");

                //find date and time
                Calendar calendar = Calendar.getInstance();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss \ndd/MM/yyyy");
                String date = stf.format(calendar.getTime());
                time.setText(date);

                //find description
                String find_description = obj.getString("description");
                description.setText(find_description);

                // Create city object
                int nextId = MyApplication.getNextId();
                int idSub = MyApplication.getSubcityList().size() + 1;
                City newCity = new City(nextId, find_temp + " °C", find_description, find_city, find_country, 1, date, find_humidity + " %");
                Subcity newSubCity = new Subcity(idSub, find_temp + " °C", find_description, find_city, find_country, pic, date, find_humidity + " %");
                // Add new city to list
                if (!cityList.contains(newCity)) {
                    cityList.add(newCity);
                    MyApplication.setNextId(++nextId);
                    MyApplication.AddSubcity(newSubCity);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            System.out.println("The error " + error);
            Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        });

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(activity);
            requestQueue.add(stringRequest);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
