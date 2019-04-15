package com.example.deyana.waterme_v01;

import java.util.Date;
import java.util.UUID;

public class Plant {
    private UUID userUUID;
    private String plantSpecies;
    private int daysBetweenWatering;
    private Date lastDateWatered;

    public Plant(UUID userUUID, String plantSpecies, int daysBetweenWatering) {
        this.userUUID = userUUID;
        this.plantSpecies = plantSpecies;
        this.daysBetweenWatering = daysBetweenWatering;
    }

    public UUID getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(UUID userUUID) {
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

    public Date getLastDateWatered() {
        return lastDateWatered;
    }

    public void setLastDateWatered(Date lastDateWatered) {
        this.lastDateWatered = lastDateWatered;
    }
}
