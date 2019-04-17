package com.example.deyana.waterme_v01;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.time.LocalDate;
import java.util.Calendar;

public class AddPlantActivity extends AppCompatActivity {

    private EditText plantSpeciesField;
    private EditText daysBetweenWateringField;
    private EditText lastDateWateredField;
    private PlantCRUD plantCRUD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);
        plantSpeciesField = findViewById(R.id.editPlantSpeciesField);
        daysBetweenWateringField = findViewById(R.id.editDaysBetweenWateringField);
        lastDateWateredField = findViewById(R.id.lastDateWateredField);
        plantCRUD = new PlantCRUD();
    }

    public void attemptSavePlant(View view) {
        plantSpeciesField.setError(null);
        daysBetweenWateringField.setError(null);

        String plantSpecies = plantSpeciesField.getText().toString();
        String daysBetweenWateringString = daysBetweenWateringField.getText().toString();
        String lastDateWatered = lastDateWateredField.getText().toString();
        int daysBetweenWatering = 0;

        boolean cancel = false;
        View focusView = null;

        if(plantSpecies.equals("")){
            plantSpeciesField.setError(getString(R.string.error_field_required));
            focusView = plantSpeciesField;
            cancel = true;
        }

        if (daysBetweenWateringString.equals("")){
            daysBetweenWateringField.setError(getString(R.string.error_field_required));
            focusView = daysBetweenWateringField;
            cancel = true;
        } else if (!isValidNumber(daysBetweenWateringString)){
            daysBetweenWateringField.setError(getString(R.string.error_invalid_number));
            focusView = daysBetweenWateringField;
            cancel = true;
        } else{
            daysBetweenWatering = Integer.parseInt(daysBetweenWateringString);
        }

        if(lastDateWatered.equals("")){
            LocalDate today = LocalDate.now();
            lastDateWatered = today.minusDays(daysBetweenWatering).toString();;
        }

        if (cancel) {
            focusView.requestFocus();
        } else if (!cancel){
            //problem is that this replaces a plant if it has the same name
            Plant plant = new Plant(plantSpecies, daysBetweenWatering, lastDateWatered);
            plantCRUD.create_plant(plant);
            finish();
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

    public void openDatePicker(View view){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                lastDateWateredField.setText(year + "-" + getRightMonthString(month) + "-" + getRightDayString(dayOfMonth));
                view.clearFocus();
            }

            public String getRightMonthString(int month){
                month++;
                if(month < 10)
                    return "0" + month;
                return String.valueOf(month);
            }

            public String getRightDayString(int day){
                if(day < 10){
                    return "0" + day;
                }
                return String.valueOf(day);
            }

        }, year, month, day);
        datePickerDialog.show();
    }

}
