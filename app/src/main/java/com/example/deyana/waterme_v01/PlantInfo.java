package com.example.deyana.waterme_v01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PlantInfo extends AppCompatActivity {

    private TextView plantSpeciesHolder;
    private TextView daysBetweenWateringHolder;
    private TextView whenToWater;
    private Button waterButton;
    private Button editPlantButton;
    private Button deletePlantButton;
    private PlantCRUD plantCRUD;
    private String selectedPlantString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);

        plantSpeciesHolder = findViewById(R.id.plantSpeciesHolder);
        daysBetweenWateringHolder = findViewById(R.id.daysBetweenWateringHolder);
        whenToWater = findViewById(R.id.whenToWaterHolder);

        waterButton = findViewById(R.id.waterButton);
        editPlantButton = findViewById(R.id.editPlantButton);
        deletePlantButton = findViewById(R.id.deletePlantButton);

        Bundle bundle = getIntent().getExtras();
        selectedPlantString = bundle.getString("selectedPlantSpecies");
    }

    @Override
    protected void onStart() {
        super.onStart();
        plantCRUD = new PlantCRUD();
        plantCRUD.read_plant(new PlantCRUD.PlantReceivedListener() {
            @Override
            public void onPlantReceived(Plant plant) {
                plantSpeciesHolder.setText(plant.getPlantSpecies());
                daysBetweenWateringHolder.setText(String.valueOf(plant.getDaysBetweenWatering()));
                whenToWater.setText(plant.whenToWater());
                waterButton.setOnClickListener(new WaterButtonListener(plant));
                editPlantButton.setOnClickListener(new EditButtonListener(plant));
                deletePlantButton.setOnClickListener(new DeleteButtonListener());
            }
        }, selectedPlantString);
    }

    public class WaterButtonListener implements View.OnClickListener{
        Plant plant;

        public WaterButtonListener(Plant plant) {
            this.plant = plant;
        }

        @Override
        public void onClick(View view) {
            String new_lastWateredDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            plant.setLastDateWatered(new_lastWateredDate);
            plantCRUD.update_plant(plant);
            finish();
        }
    }

    public class EditButtonListener implements View.OnClickListener{
        Plant plant;

        public EditButtonListener(Plant plant) {
            this.plant = plant;
        }

        @Override
        public void onClick(View view) {
            Intent editActivity = new Intent(view.getContext(), EditPlantActivity.class);
            editActivity.putExtra("plantToEditSpecies", plant.getPlantSpecies());
            editActivity.putExtra("plantToEditDaysBetweenWatering", plant.getDaysBetweenWatering());
            editActivity.putExtra("plantToEditLastDateWatered", plant.getLastDateWatered());
            startActivity(editActivity);
        }
    }

    public class DeleteButtonListener implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        new AlertDialog.Builder(view.getContext())
                .setTitle("Are you sure?")
//                .setMessage("Are you sure you want to delete this plant?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Plant plant = new Plant();
                        plant.setPlantSpecies(selectedPlantString);
                        plantCRUD.delete_plant(plant);
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}


}
