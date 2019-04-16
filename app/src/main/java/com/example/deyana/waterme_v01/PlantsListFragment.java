package com.example.deyana.waterme_v01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.UUID;

public class PlantsListFragment extends Fragment implements PlantAdapter.OnListItemClickListener{

    private RecyclerView content;
    private PlantAdapter contentAdapter;
    private FloatingActionButton addPlantButton;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_plants_list, container, false);

        content = view.findViewById(R.id.plantsList);
        content.hasFixedSize();
        content.setLayoutManager(new LinearLayoutManager(getContext()));
        contentAdapter = new PlantAdapter(loadPlantsList(), this);
        content.setAdapter(contentAdapter);

        addPlantButton = view.findViewById(R.id.addPlantButton);
        addPlantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddPlantActivity(v);
            }
        });
        return view;
    }

    @Override
    public void onListItemClick(View view) {
        Intent plantInfoIntent = new Intent(getContext(), PlantInfo.class);
        plantInfoIntent.putExtra("selectedPlant", contentAdapter.getPlantSpeciesSelected());
        startActivity(plantInfoIntent);
    }

    public ArrayList<Plant> loadPlantsList(){
        String defaultUserId = UUID.randomUUID().toString();
        ArrayList<Plant> plants = new ArrayList<>();
        plants.add(new Plant(defaultUserId, "Peace Lily", 3));
        plants.add(new Plant(defaultUserId, "Aloe Vera", 7));
        plants.add(new Plant(defaultUserId, "Moth Orchid", 9));
        plants.add(new Plant(defaultUserId, "Snake Plant", 28));
        plants.add(new Plant(defaultUserId, "Asparagus Fern", 28));
        plants.add(new Plant(defaultUserId, "Begonia", 21));
        plants.add(new Plant(defaultUserId, "Jade Plant", 168));
        plants.add(new Plant(defaultUserId, "English Ivy", 7));
        plants.add(new Plant(defaultUserId, "Spider Plant", 7));
        plants.add(new Plant(defaultUserId, "Peperomia", 10));
        plants.add(new Plant(defaultUserId, "Yucca", 10));
        plants.add(new Plant(defaultUserId, "Asparagus Fern", 14));
        return plants;
    }

    public void startAddPlantActivity(View view){
        Intent addPlant = new Intent(getContext(), AddPlantActivity.class);
        startActivity(addPlant);
    }

    //todo implement the read all operation
//    @Override
//    public void onStart() {
//        super.onStart();
//    }
}
