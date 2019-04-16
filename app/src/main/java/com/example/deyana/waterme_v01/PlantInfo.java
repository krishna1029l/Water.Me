package com.example.deyana.waterme_v01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class PlantInfo extends AppCompatActivity {

    private TextView plantSpeciesHolder;
    private TextView daysBetweenWateringHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);

        plantSpeciesHolder = findViewById(R.id.plantSpeciesHolder);
        daysBetweenWateringHolder = findViewById(R.id.daysBetweenWateringHolder);

        Bundle bundle = getIntent().getExtras();
        plantSpeciesHolder.setText(bundle.getString("selectedPlant"));
        daysBetweenWateringHolder.setText(String.valueOf(23));
    }
}
