package com.example.deyana.waterme_v01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.sql.Date;
import java.time.LocalDate;

public class AddPlantActivity extends AppCompatActivity {

    private EditText plantSpeciesField;
    private EditText daysBetweenWateringField;
    private PlantCRUD plantCRUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);
        plantSpeciesField = findViewById(R.id.editPlantSpeciesField);
        daysBetweenWateringField = findViewById(R.id.editDaysBetweenWateringField);
        plantCRUD = new PlantCRUD();
    }

    //todo improve validation
    public void attemptSavePlant(View view){
        plantSpeciesField.setError(null);
        daysBetweenWateringField.setError(null);

        String plantSpecies = plantSpeciesField.getText().toString();
        String daysBetweenWateringString = daysBetweenWateringField.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(!validateDaysBetweenWatering(daysBetweenWateringString)){
            daysBetweenWateringField.setError(getString(R.string.error_invalid_password));
            focusView = daysBetweenWateringField;
            cancel = true;
        }

        int daysBetweenWatering = Integer.parseInt(daysBetweenWateringString);

        LocalDate localDate = LocalDate.now();
        Date lastDateWatered = Date.valueOf(localDate.minusDays(daysBetweenWatering).toString());

        if (cancel) {
            focusView.requestFocus();
        } else {
            plantCRUD.create_plant(plantSpecies, daysBetweenWatering, lastDateWatered.toString());
            finish();
        }
    }

    public boolean validateDaysBetweenWatering(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }



}
