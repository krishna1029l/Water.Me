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
        String selectedPlantString = bundle.getString("selectedPlant");

        PlantCRUD plantCRUD = new PlantCRUD();
        Plant selectedPlant = plantCRUD.read_plant(new PlantCRUD.PlantReceivedListener() {

            @Override
            public void onPlantReceived(Plant plant) {
                plantSpeciesHolder.setText(plant.getPlantSpecies());
                daysBetweenWateringHolder.setText(String.valueOf(plant.getDaysBetweenWatering()));
            }
        }, selectedPlantString);

    }
}
