package com.example.carwale.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import com.example.carwale.R;

public class DetailActivity extends AppCompatActivity {

    TextView countryname;
    String CountryName = getIntent().getStringExtra("CountryName");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        countryname =findViewById(R.id.country);
        countryname.setText(CountryName);
    }


}
