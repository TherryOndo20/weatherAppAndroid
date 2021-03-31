package com.example.myweatherapp;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyApplication extends Application {

    private static List<City> cityList = new ArrayList<City>();
    public static List<Subcity> subcityList = new ArrayList<Subcity>();
    private static int nextId = 6;

    public MyApplication() {
        fillCityList();
        fillSubcityList();
    }

    private void fillSubcityList() {

    }

    private void fillCityList() {
        City city1 = new City(1,"21°C", "sunny", "paris", "fr",R.drawable.cloud, "time", "40%" );
        City city2 = new City(2,"21°C", "sunny", "paris", "fr",R.drawable.cloud, "time", "40%" );
        City city3 = new City(3,"21°C", "sunny", "paris", "fr",R.drawable.cloud, "time", "40%" );
        City city4 = new City(4,"21°C", "sunny", "paris", "fr",R.drawable.cloud ,"time", "40%" );
        City city5 = new City(5,"21°C", "sunny", "paris", "fr",R.drawable.cloud, "time", "40%" );
        City city6 = new City(6,"21°C", "sunny", "paris", "fr",R.drawable.cloud, "time", "40%" );

        cityList.addAll(Arrays.asList(new City[]{city1, city2,city3,city4, city5,city6}));
    }

    public static List<Subcity> getSubcityList() {
        return subcityList;
    }

    public static void AddSubcity(Subcity subCity){
        subcityList.add(subCity);
    }

    public static List<City> getCityList() {
        return cityList;
    }

    public static void setCityList(List<City> cityList) {
        MyApplication.cityList = cityList;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        MyApplication.nextId = nextId;
    }
}
