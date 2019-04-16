package com.example.deyana.waterme_v01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlantInfo extends AppCompatActivity {

    private TextView plantSpeciesHolder;
    private TextView daysBetweenWateringHolder;
    private Button editPlantButton;
    private Button deletePlantButton;
    private PlantCRUD plantCRUD;
    private Plant selectedPlant;
    private String selectedPlantString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);

        plantSpeciesHolder = findViewById(R.id.plantSpeciesHolder);
        daysBetweenWateringHolder = findViewById(R.id.daysBetweenWateringHolder);
        editPlantButton = findViewById(R.id.editPlantButton);
        deletePlantButton = findViewById(R.id.deletePlantButton);

        Bundle bundle = getIntent().getExtras();
        selectedPlantString = bundle.getString("selectedPlant");
    }
//todo move back to create method
    @Override
    protected void onStart() {
        super.onStart();
        plantCRUD = new PlantCRUD();
        selectedPlant = plantCRUD.read_plant(new PlantCRUD.PlantReceivedListener() {

            @Override
            public void onPlantReceived(Plant plant) {
                plantSpeciesHolder.setText(plant.getPlantSpecies());
                daysBetweenWateringHolder.setText(String.valueOf(plant.getDaysBetweenWatering()));
                editPlantButton.setOnClickListener(new EditButtonListener(plant));
                deletePlantButton.setOnClickListener(new DeleteButtonListener());
            }
        }, selectedPlantString);
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
            startActivity(editActivity);
        }
    }

    public class DeleteButtonListener implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        new AlertDialog.Builder(view.getContext())
                .setTitle("Are you sure?")
                .setMessage("If you go back you will loose any changes.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        plantCRUD.delete_plant(selectedPlantString);
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
