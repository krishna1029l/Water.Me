package com.example.deyana.waterme_v01;

import android.content.Intent;
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
    private TextView signOut;
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        signOut = view.findViewById(R.id.sign_out);
        signOut.setOnClickListener(this);
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
