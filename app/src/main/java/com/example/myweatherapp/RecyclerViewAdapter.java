package com.example.myweatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<City> cityList ;
    List<Subcity> subcityList ;
    Context context;

//    public RecyclerViewAdapter(List<City> cityList, Context context) {
//        this.cityList = cityList;
//        this.context = context;
//    }
    public RecyclerViewAdapter(List<Subcity> subcityList, Context context) {
        this.subcityList = subcityList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_city,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.city_line_city.setText(subcityList.get(position).getCity_name());
        holder.country_line_city.setText(subcityList.get(position).getCountry_name());
        holder.description_line_city.setText(subcityList.get(position).getDescription());


        holder.temperature_line_city.setText(String.valueOf(subcityList.get(position).getTemperature()));
        holder.humidity_line_city.setText(String.valueOf(subcityList.get(position).getHumidity()));
        holder.time_line_city.setText(String.valueOf(subcityList.get(position).getTime()));
//        Glide.with(this.context).load(subcityList.get(position).getImageView()).into(holder.icon_line_city);
        Picasso.get().load(subcityList.get(position).getImageView()).into(holder.icon_line_city);
    }

    @Override
    public int getItemCount() {
        return subcityList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView icon_line_city;
        TextView temperature_line_city,description_line_city,country_line_city,city_line_city,humidity_line_city,time_line_city;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            icon_line_city = itemView.findViewById(R.id.icon_line_city);
            temperature_line_city = itemView.findViewById(R.id.temperature_line_city);
            description_line_city = itemView.findViewById(R.id.description_line_city);
            country_line_city = itemView.findViewById(R.id.country_line_city);
            city_line_city = itemView.findViewById(R.id.city_line_city);
            humidity_line_city = itemView.findViewById(R.id.humidity_line_city);
            time_line_city = itemView.findViewById(R.id.time_line_city);

        }
    }
}
