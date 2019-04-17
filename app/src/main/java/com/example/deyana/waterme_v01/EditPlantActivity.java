package com.example.deyana.waterme_v01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditPlantActivity extends AppCompatActivity {

    private EditText plantSpeciesField;
    private EditText daysBetweenWateringField;
    private PlantCRUD plantCRUD;
    private String old_plantSpecies;
    private String lastDateWatered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plant);

        plantSpeciesField = findViewById(R.id.editPlantSpeciesField);
        daysBetweenWateringField = findViewById(R.id.editDaysBetweenWateringField);

        Bundle bundle = getIntent().getExtras();
        old_plantSpecies = bundle.getString("plantToEditSpecies", "");
        int daysBetweenWatering = bundle.getInt("plantToEditDaysBetweenWatering", 0);
        lastDateWatered = bundle.getString("plantToEditLastDateWatered", "");

        plantSpeciesField.setText(old_plantSpecies);
        daysBetweenWateringField.setText(String.valueOf(daysBetweenWatering));
        plantCRUD = new PlantCRUD();
    }

    public void attempt_saveChanges(View view){
        plantSpeciesField.setError(null);
        daysBetweenWateringField.setError(null);

        String new_plantSpecies = plantSpeciesField.getText().toString();
        String new_daysBetweenWateringString = daysBetweenWateringField.getText().toString();
        int new_daysBetweenWatering = 0;

        boolean cancel = false;
        View focusView = null;

        if(old_plantSpecies.equals("")){
            plantSpeciesField.setError(getString(R.string.error_field_required));
            focusView = plantSpeciesField;
            cancel = true;
        }

        if (new_daysBetweenWateringString.equals("")){
            daysBetweenWateringField.setError(getString(R.string.error_field_required));
            focusView = daysBetweenWateringField;
            cancel = true;
        } else if (!isValidNumber(new_daysBetweenWateringString)){
            daysBetweenWateringField.setError(getString(R.string.error_invalid_number));
            focusView = daysBetweenWateringField;
            cancel = true;
        } else{
            new_daysBetweenWatering = Integer.parseInt(new_daysBetweenWateringString);
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            Plant plant_to_update = new Plant(new_plantSpecies, new_daysBetweenWatering, lastDateWatered);

            //the name of the species is the ID of the database record
            //the ID (name) cannot be updated in the database
            //if the user wants to update the name, the entry is deleted and a new record
            //is inserted to replace it
            if(new_plantSpecies.equals(old_plantSpecies)) {
                plantCRUD.update_plant(plant_to_update);
            } else {
                plantCRUD.delete_plant(plant_to_update);
                plantCRUD.create_plant(plant_to_update);
            }
            Toast.makeText(this, "Plant saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(view.getContext(), MainUserActivity.class));
        }
    }

    public boolean isValidNumber(String daysBetweenWateringString){
        try{
            Integer.parseInt(daysBetweenWateringString);
            return true;
        }catch (NumberFormatException e){
            daysBetweenWateringField.setError(getString(R.string.error_invalid_password));
        }
        return false;
    }

}
