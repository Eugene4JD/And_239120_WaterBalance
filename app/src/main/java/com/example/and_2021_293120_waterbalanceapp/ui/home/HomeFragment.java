package com.example.and_2021_293120_waterbalanceapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.and_2021_293120_waterbalanceapp.Data.CurrentData;
import com.example.and_2021_293120_waterbalanceapp.R;
import com.example.and_2021_293120_waterbalanceapp.ui.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button saveButton;
    private Button resetButton;
    private Button buttonIncrease;
    private Button buttonDecrease;
    private Button signOutButton;
    private TextView progress_textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        this.progress_textView = root.findViewById(R.id.text_progress);

        this.buttonDecrease = root.findViewById(R.id.button_decr);

        this.buttonIncrease = root.findViewById(R.id.button_incr);

        this.saveButton = root.findViewById(R.id.save_record_button);

        this.resetButton = root.findViewById(R.id.reset_button);

        this.signOutButton = root.findViewById(R.id.sign_out_button);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        try {
            homeViewModel.init();
        }
        catch (IllegalAccessException e)
        {
            startActivity(new Intent(getContext(), SignInActivity.class));
        }



        homeViewModel.getCurrentData().observe(getViewLifecycleOwner(), currentData -> {
            if (currentData!=null)
                progress_textView.setText(currentData.toString());
            else
                homeViewModel.saveCurrentData(100.0,0.0);
        });

        homeViewModel.getRecords().observe(getViewLifecycleOwner(), records -> {
            if (records!=null)
                homeViewModel.setDisplayList(records);
        });

        saveButton.setOnClickListener(v -> {
            homeViewModel.saveRecord(10.2, 30.8);
        });

        resetButton.setOnClickListener(v -> {

        });

        signOutButton.setOnClickListener(v -> {
            homeViewModel.signOut();
        });

        buttonIncrease.setOnClickListener(v -> {
                this.homeViewModel.saveCurrentData(homeViewModel.getCurrentData().getValue().getGoal(), homeViewModel.getCurrentData().getValue().getProgress() + 10);
        });


        buttonDecrease.setOnClickListener(v -> {
            if (homeViewModel.getCurrentData().getValue() == null)
                homeViewModel.saveCurrentData(100.0, 0.0);
            else
                this.homeViewModel.saveCurrentData(homeViewModel.getCurrentData().getValue().getGoal(), homeViewModel.getCurrentData().getValue().getProgress() - 10);
        });
    }
}