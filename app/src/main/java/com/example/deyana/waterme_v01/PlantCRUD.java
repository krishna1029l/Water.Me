package com.example.deyana.waterme_v01;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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

    public void read_plants(){
        final ArrayList<Plant> plants = new ArrayList<>();
        plantsDatabase = FirebaseDatabase.getInstance().getReference("plants").child(userId);
        plantsDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                plants.clear();

                for (DataSnapshot userPlantsSnapshot : dataSnapshot.getChildren()){
                    Plant plant = userPlantsSnapshot.getValue(Plant.class);
                    plants.add(plant);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //do nothing
            }
        });

        Log.e("empty", " it is empty" + plants.isEmpty());
    }

}
