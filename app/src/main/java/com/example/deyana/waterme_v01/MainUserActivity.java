package com.example.deyana.waterme_v01;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

public class MainUserActivity extends AppCompatActivity implements PlantAdapter.OnListItemClickListener{

    private TextView mTextMessage;
    private RecyclerView content;
    private RecyclerView.Adapter contentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        content = findViewById(R.id.recyclerView);
        content.hasFixedSize();
        content.setLayoutManager(new LinearLayoutManager(this));
        contentAdapter = new PlantAdapter(loadPlantsList(), this);
        content.setAdapter(contentAdapter);

        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.plants_item);
//                    return true;
//                case R.id.navigation_settings:
//                    mTextMessage.setText(R.string.settings_item);
//                    return true;
//            }
//            return false;
//        }
//    };

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

    @Override
    public void onListItemClick(View view) {
        //do something
    }
}
