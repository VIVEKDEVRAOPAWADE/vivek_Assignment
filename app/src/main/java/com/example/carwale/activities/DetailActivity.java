package com.example.carwale.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import com.example.carwale.R;

public class DetailActivity extends AppCompatActivity {

    TextView countryname ,drecovered,ddeath , dconfirmed;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        countryname =findViewById(R.id.country);
        drecovered = findViewById(R.id.recoverdetail);
        ddeath =findViewById(R.id.deathsdetail);
        dconfirmed =findViewById(R.id.casesdetail);

    }

    @Override
    protected void onResume() {
        super.onResume();

        countryname.setText( "Country  :"+getIntent().getStringExtra("CountrymameDetail"));
        dconfirmed.setText("Total confirmed cases  :"+getIntent().getIntExtra("Totalconfirmed" ,0));
        drecovered.setText("Total recovered case   :"+getIntent().getIntExtra("Totalrecoverd",0));
        ddeath.setText("Total deaths  : "+getIntent().getIntExtra("Totaldeaths",0));
    }
}
