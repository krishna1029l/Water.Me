package com.example.deyana.waterme_v01;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder>{

    private ArrayList<Plant> plants;
    final private OnListItemClickListener onListItemClickListener;

    public PlantAdapter(ArrayList<Plant> plants, OnListItemClickListener onListItemClickListener) {
        this.plants = plants;
        this.onListItemClickListener = onListItemClickListener;
    }

    @Override
    public PlantAdapter.PlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_plant, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlantAdapter.PlantViewHolder holder, int position) {
        holder.plantSpecies.setText(plants.get(position).getPlantSpecies());
        holder.daysBetweenWatering.setText("Watered every " + plants.get(position).getDaysBetweenWatering() + " days");
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

    public class PlantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView plantSpecies;
        TextView daysBetweenWatering;

        public PlantViewHolder(View itemView) {
            super(itemView);
            plantSpecies = itemView.findViewById(R.id.plantSpecies);
            daysBetweenWatering = itemView.findViewById(R.id.daysBetweenWatering);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String selectedPlant = plantSpecies.getText().toString();
            onListItemClickListener.onListItemClick(selectedPlant);
        }
    }

    public interface OnListItemClickListener {
        void onListItemClick(String plantSpecies);
    }
}
