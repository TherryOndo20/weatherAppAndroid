package com.example.myweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListCity extends AppCompatActivity {
    private static final String TAG = "Weather app";
        Button btn_add_city;

        MyApplication myApplication = (MyApplication) this.getApplication();

//        List<City> cityList ;
        List<Subcity> subcityList ;

        private RecyclerView recyclerView;
        private RecyclerView.Adapter adapter;
        private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_city);

        subcityList = myApplication.getSubcityList();


     Log.d(TAG,"onCreate:"+ subcityList.toString()) ;
       Toast.makeText(this,"City Count" + subcityList.size(), Toast.LENGTH_SHORT).show();

            btn_add_city = findViewById(R.id.btn_add_city);

        btn_add_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListCity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.city_list);
        recyclerView.setHasFixedSize(true);

        //use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //specify adapter
        adapter = new RecyclerViewAdapter(subcityList, this);
        recyclerView.setAdapter(adapter);

        //specify an adapter

    }


}