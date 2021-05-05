package com.example.myweatherapp;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyApplication extends Application {

    private static List<City> cityList = new ArrayList<City>();
    public static List<Subcity> subcityList = new ArrayList<Subcity>();
    private static int nextId = 6;

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
