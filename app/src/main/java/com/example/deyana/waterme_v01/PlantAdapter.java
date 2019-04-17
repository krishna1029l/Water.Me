package com.example.deyana.waterme_v01;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantViewHolder>{

    private ArrayList<Plant> plants;
    final private PlantViewHolder.OnListItemClickListener onListItemClickListener;

    PlantAdapter(ArrayList<Plant> plants, PlantViewHolder.OnListItemClickListener onListItemClickListener) {
        this.plants = plants;
        this.onListItemClickListener = onListItemClickListener;
    }

    @Override
    public PlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_plant, parent, false);
        return new PlantViewHolder(view, onListItemClickListener);
    }

    @Override
    public void onBindViewHolder(PlantViewHolder holder, int position) {
        holder.setPlantSpecies(plants.get(position).getPlantSpecies());
        holder.setWhenToWater("Needs to be watered " + plants.get(position).whenToWater());
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

}
