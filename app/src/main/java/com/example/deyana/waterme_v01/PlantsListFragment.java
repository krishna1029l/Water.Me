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

public class PlantsListFragment extends Fragment implements PlantViewHolder.OnListItemClickListener{

    private RecyclerView content;
    private PlantAdapter contentAdapter;
    private FloatingActionButton addPlantButton;
    private PlantCRUD plantCRUD;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_plants_list, container, false);

        content = view.findViewById(R.id.plantsListRecyclerView);
        content.hasFixedSize();
        content.setLayoutManager(new LinearLayoutManager(getContext()));

        addPlantButton = view.findViewById(R.id.addPlantButton);
        addPlantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddPlantActivity(v);
            }
        });

        plantCRUD = new PlantCRUD();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        final PlantViewHolder.OnListItemClickListener onListItemClickListener = this;
        plantCRUD.read_plants(new PlantCRUD.PlantsReceivedListener() {
            @Override
            public void onPlantsReceived(ArrayList<Plant> plants) {
                contentAdapter = new PlantAdapter(plants, onListItemClickListener);
                content.setAdapter(contentAdapter);
            }
        });
    }

    @Override
    public void onListItemClick(String selectedPlantSpecies) {
        Intent plantInfoIntent = new Intent(getContext(), PlantInfo.class);
        plantInfoIntent.putExtra("selectedPlantSpecies", selectedPlantSpecies);
        startActivity(plantInfoIntent);
    }

    public void startAddPlantActivity(View view){
        Intent addPlant = new Intent(getContext(), AddPlantActivity.class);
        startActivity(addPlant);
    }
}
