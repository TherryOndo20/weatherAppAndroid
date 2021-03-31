package com.example.myweatherapp;

public class Subcity  {

    private int id;
    private String temperature;
    private  String description, city_name, country_name;
    private String imageView;
    private String time;
    private String humidity;

    public Subcity(int id, String temperature, String description, String city_name, String country_name, String imageView, String time, String humidity) {
        this.id = id;
        this.temperature = temperature;
        this.description = description;
        this.city_name = city_name;
        this.country_name = country_name;
        this.imageView = imageView;
        this.time = time;
        this.humidity = humidity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
