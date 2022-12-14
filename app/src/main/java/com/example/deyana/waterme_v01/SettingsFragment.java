package com.example.deyana.waterme_v01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    //TODO add more settings

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        TextView signOut = view.findViewById(R.id.sign_out);
        signOut.setOnClickListener(this);
        TextView usernamePlaceholder = view.findViewById(R.id.usernamePlaceholder);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(getString(R.string.username), "");
        String greeting = "Hello " + username + "!";
        usernamePlaceholder.setText(greeting);
        return view;
    }

    public void attemptSignout(View view){
        if(firebaseAuth.getCurrentUser() != null) {
            firebaseAuth.signOut();
            startActivity(new Intent(view.getContext(), MainActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        attemptSignout(view);
    }
}
