package com.example.deyana.waterme_v01;


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

    public Plant(String userUUID, String plantSpecies, int daysBetweenWatering) {
        this.userUUID = userUUID;
        this.plantSpecies = plantSpecies;
        this.daysBetweenWatering = daysBetweenWatering;
    }

    public Plant(String userUUID, String plantSpecies, int daysBetweenWatering, String lastDateWatered) {
        this.userUUID = userUUID;
        this.plantSpecies = plantSpecies;
        this.daysBetweenWatering = daysBetweenWatering;
        this.lastDateWatered = lastDateWatered;
    }

    public String getUserUUID() {
        return userUUID;
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

    public String getLastDateWatered() {
        return lastDateWatered;
    }

    public void setLastDateWatered(String lastDateWatered) {
        this.lastDateWatered = lastDateWatered;
    }
}
