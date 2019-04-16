package com.example.deyana.waterme_v01;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class PlantCRUD {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference plantsDatabase;
    String userId;

    public PlantCRUD() {
        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
    }

    public void create_plant(String plantSpecies, int daysBetweenWatering, String lastDateWatered){
        plantsDatabase = FirebaseDatabase.getInstance().getReference("user_plants").child(userId);
        Plant plant = new Plant(plantSpecies, daysBetweenWatering, lastDateWatered);
        plantsDatabase.child(plantSpecies).setValue(plant);
    }


    public interface PlantsReceivedListener{
        void onPlantsReceived(ArrayList<Plant> plants);
    }


    public ArrayList<Plant> read_plants(final PlantsReceivedListener listener){
        final ArrayList<Plant> plants = new ArrayList<>();
        plantsDatabase = FirebaseDatabase.getInstance().getReference("user_plants").child(userId);
        plantsDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                plants.clear();

                for (DataSnapshot userPlantsSnapshot : dataSnapshot.getChildren()){
                    Plant plant = userPlantsSnapshot.getValue(Plant.class);
                    plants.add(plant);
                }

                listener.onPlantsReceived(plants);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //do nothing
            }
        });

        return plants;
    }

    public Plant read_plant(final PlantReceivedListener plantReceivedListener, final String plantSpecies){
        final Plant plant = new Plant();
        plantsDatabase = FirebaseDatabase.getInstance().getReference("user_plants").child(userId).child(plantSpecies);
        plantsDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap plantFromDatabase = (HashMap) dataSnapshot.getValue();

                plant.setUserUUID(userId);
                plant.setPlantSpecies(plantFromDatabase.get("plantSpecies").toString());
                plant.setDaysBetweenWatering(Integer.parseInt(plantFromDatabase.get("daysBetweenWatering").toString()));
                plant.setLastDateWatered(plantFromDatabase.get("lastDateWatered").toString());
                plantReceivedListener.onPlantReceived(plant);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //do nothing
            }
        });

        return plant;
    }

    public interface PlantReceivedListener{
        void onPlantReceived(Plant plant);
    }

    //todo change constructors to Plant
    public void update_plant(String plantSpecies, int daysBetweenWatering, String lastDateWatered){
        plantsDatabase = FirebaseDatabase.getInstance().getReference("user_plants").child(userId);
        Plant plant = new Plant(plantSpecies, daysBetweenWatering, lastDateWatered);
        plantsDatabase.child(plantSpecies).setValue(plant);
    }

    public void delete_plant(String plantSpecies){
        plantsDatabase = FirebaseDatabase.getInstance().getReference("user_plants").child(userId).child(plantSpecies);
        plantsDatabase.removeValue();
    }

}
