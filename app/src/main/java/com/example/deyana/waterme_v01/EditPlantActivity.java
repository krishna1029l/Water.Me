package com.example.deyana.waterme_v01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.sql.Date;
import java.time.LocalDate;

public class EditPlantActivity extends AppCompatActivity {

    private EditText plantSpeciesField;
    private EditText daysBetweenWateringField;
    private PlantCRUD plantCRUD;
    private String plantSpecies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plant);

        plantSpeciesField = findViewById(R.id.editPlantSpeciesField);
        daysBetweenWateringField = findViewById(R.id.editDaysBetweenWateringField);

        Bundle bundle = getIntent().getExtras();
        plantSpecies = bundle.getString("plantToEditSpecies");
        int daysBetweenWatering = bundle.getInt("plantToEditDaysBetweenWatering");

        plantSpeciesField.setText(plantSpecies);
        daysBetweenWateringField.setText(String.valueOf(daysBetweenWatering));
        plantCRUD = new PlantCRUD();
    }

    public void attempt_saveChanges(View view){
        plantSpeciesField.setError(null);
        daysBetweenWateringField.setError(null);

        String plantSpeciesNew = plantSpeciesField.getText().toString();
        String daysBetweenWateringStringNew = daysBetweenWateringField.getText().toString();

        boolean cancel = false;
        View focusView = null;

//        if(!validateDaysBetweenWatering(daysBetweenWateringString)){
//            daysBetweenWateringField.setError(getString(R.string.error_invalid_password));
//            focusView = daysBetweenWateringField;
//            cancel = true;
//        }

        int daysBetweenWatering = Integer.parseInt(daysBetweenWateringStringNew);

        LocalDate localDate = LocalDate.now();
        Date lastDateWatered = Date.valueOf(localDate.minusDays(daysBetweenWatering).toString());

        if (cancel) {
            focusView.requestFocus();
        } else {
            if(plantSpecies.equals(plantSpeciesNew)) {
                plantCRUD.update_plant(plantSpecies, daysBetweenWatering, lastDateWatered.toString());
            } else {
                plantCRUD.delete_plant(plantSpecies);
                plantCRUD.create_plant(plantSpeciesNew, daysBetweenWatering, lastDateWatered.toString());
            }
            startActivity(new Intent(view.getContext(), MainUserActivity.class));
        }
    }

    //todo improve this
    public void validateInput(){

    }
}
