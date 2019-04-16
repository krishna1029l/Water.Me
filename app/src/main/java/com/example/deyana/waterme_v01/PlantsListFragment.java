package com.example.deyana.waterme_v01;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private RecyclerView.Adapter contentAdapter;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plants_list, container, false);

        content = view.findViewById(R.id.plantsList);
        content.hasFixedSize();
        content.setLayoutManager(new LinearLayoutManager(getContext()));
        contentAdapter = new PlantAdapter(loadPlantsList(), this);
        content.setAdapter(contentAdapter);

        return view;
    }

    @Override
    public void onListItemClick(View view) {
        //do something
    }

    public ArrayList<Plant> loadPlantsList(){
        UUID defaultUserId = UUID.randomUUID();
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
}
