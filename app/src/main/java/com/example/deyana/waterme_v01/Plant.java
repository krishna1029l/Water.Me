package com.example.deyana.waterme_v01;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class Plant {
    private String userUUID;
    private String plantSpecies;
    private int daysBetweenWatering;
    private String lastDateWatered;

    public Plant() {
    }

    public Plant(String plantSpecies, int daysBetweenWatering, String lastDateWatered) {
        this.plantSpecies = plantSpecies;
        this.daysBetweenWatering = daysBetweenWatering;
        this.lastDateWatered = lastDateWatered;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getPlantSpecies() {
        return plantSpecies;
    }

    public void setPlantSpecies(String plantSpecies) {
        this.plantSpecies = plantSpecies;
    }

    public int getDaysBetweenWatering() {
        return daysBetweenWatering;
    }

    public void setDaysBetweenWatering(int daysBetweenWatering) {
        this.daysBetweenWatering = daysBetweenWatering;
    }

    public void setLastDateWatered(String lastDateWatered) {
        this.lastDateWatered = lastDateWatered;
    }

    public String getLastDateWatered() {
        return lastDateWatered;
    }

    public String whenToWater() {
        String whenToWater = "";

        LocalDate today = LocalDate.now();
        LocalDate lastWateredDate = LocalDate.parse(getLastDateWatered(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate whenToWaterNext = lastWateredDate.plusDays(getDaysBetweenWatering());

        if(whenToWaterNext.isBefore(today))
            whenToWater = "today";
        else if(whenToWaterNext.equals(today))
            whenToWater = "today";
        else if(whenToWaterNext.isAfter(today)){
            long daysLeftUntilWatering = DAYS.between(today, whenToWaterNext);
            if(daysLeftUntilWatering == 1)
                whenToWater = "tomorrow";
            else
                whenToWater = "in " + String.valueOf(daysLeftUntilWatering) + " days";
        }

        return whenToWater;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "userUUID='" + userUUID + '\'' +
                ", plantSpecies='" + plantSpecies + '\'' +
                ", daysBetweenWatering=" + daysBetweenWatering +
                ", lastDateWatered='" + lastDateWatered + '\'' +
                '}';
    }
}
