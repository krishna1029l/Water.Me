package com.example.deyana.waterme_v01;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PlantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView plantSpecies;
    private TextView whenToWater;
    final private OnListItemClickListener onListItemClickListener;


    PlantViewHolder(View itemView, PlantViewHolder.OnListItemClickListener onListItemClickListener) {
        super(itemView);
        plantSpecies = itemView.findViewById(R.id.plantSpecies);
        whenToWater = itemView.findViewById(R.id.whenToWater);
        this.onListItemClickListener = onListItemClickListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onListItemClickListener.onListItemClick(plantSpecies.getText().toString());
    }

    public interface OnListItemClickListener {
        void onListItemClick(String plantSpecies);
    }

    void setPlantSpecies(String plantSpecies) {
        this.plantSpecies.setText(plantSpecies);
    }

    void setWhenToWater(String whenToWater) {
        this.whenToWater.setText(whenToWater);
    }
}
