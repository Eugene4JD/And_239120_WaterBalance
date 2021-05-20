package com.example.and_2021_293120_waterbalanceapp.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.and_2021_293120_waterbalanceapp.R;
import com.example.and_2021_293120_waterbalanceapp.ui.SignInActivity;
import com.example.and_2021_293120_waterbalanceapp.viewmodel.HomeViewModel;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private HomeViewModel homeViewModel;
    private Button saveButton;
    private Button resetButton;
    private Button buttonIncreaseTwentyFive;
    private Button buttonDecreaseTwentyFive;
    private Button buttonIncreaseFive;
    private Button buttonDecreaseFive;
    private Button buttonIncreaseHundred;
    private Button buttonDecreaseHundred;
    private TextView progress_textView;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        this.progress_textView = root.findViewById(R.id.text_progress);

        this.buttonDecreaseFive = root.findViewById(R.id.button_decr);

        this.buttonIncreaseFive = root.findViewById(R.id.button_incr);

        this.buttonDecreaseHundred = root.findViewById(R.id.button_decr_100);

        this.buttonIncreaseHundred = root.findViewById(R.id.button_inc_100);

        this.saveButton = root.findViewById(R.id.save_record_button);

        this.resetButton = root.findViewById(R.id.reset_button);

        this.progressBar = root.findViewById(R.id.progress_bar);

        this.buttonIncreaseTwentyFive = root.findViewById(R.id.button_incr_25);

        this.buttonDecreaseTwentyFive = root.findViewById(R.id.button_decr_25);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        try {
            homeViewModel.init();
        } catch (IllegalAccessException e) {
            startActivity(new Intent(getContext(), SignInActivity.class));
        }

        homeViewModel.getCurrentData().observe(getViewLifecycleOwner(), currentData -> {
            if (currentData != null) {
                progress_textView.setText(currentData.toString());
                progressBar.setProgress((int) (currentData.getProgress() / currentData.getGoal() * 100));
            } else
                homeViewModel.saveCurrentData(0.0);
        });

        homeViewModel.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        homeViewModel.getRecords().observe(getViewLifecycleOwner(), records -> {
            if (records != null) {
                homeViewModel.setDisplayList(records);
            }
        });

        saveButton.setOnClickListener(v -> {
            homeViewModel.saveRecord(homeViewModel.getCurrentData().getValue().getProgress());
            homeViewModel.saveCurrentData(0.0);
        });

        resetButton.setOnClickListener(v -> {
            homeViewModel.saveCurrentData(0.0);
        });

        buttonIncreaseHundred.setOnClickListener(v -> {
            this.homeViewModel.saveCurrentData(homeViewModel.getCurrentData().getValue().getProgress() + 100);
        });

        buttonDecreaseHundred.setOnClickListener(v -> {
            if (homeViewModel.getCurrentData().getValue().getProgress() - 100 < 0) {
                Snackbar.make(getView(), "The progress can not go below zero", Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.RED)
                        .show();
            } else
                this.homeViewModel.saveCurrentData(homeViewModel.getCurrentData().getValue().getProgress() - 100);
        });

        buttonIncreaseTwentyFive.setOnClickListener(v -> {
            this.homeViewModel.saveCurrentData(homeViewModel.getCurrentData().getValue().getProgress() + 25);
        });


        buttonDecreaseTwentyFive.setOnClickListener(v -> {
            if (homeViewModel.getCurrentData().getValue().getProgress() - 25 < 0) {
                Snackbar.make(getView(), "The progress can not go below zero", Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.RED)
                        .show();
            } else
                this.homeViewModel.saveCurrentData(homeViewModel.getCurrentData().getValue().getProgress() - 25);
        });

        buttonIncreaseFive.setOnClickListener(v -> {
            this.homeViewModel.saveCurrentData(homeViewModel.getCurrentData().getValue().getProgress() + 5);
        });

        buttonDecreaseFive.setOnClickListener(v -> {
            if (homeViewModel.getCurrentData().getValue().getProgress() - 5 < 0) {
                Snackbar.make(getView(), "The progress can not go below zero", Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.RED)
                        .show();
            } else
                this.homeViewModel.saveCurrentData(homeViewModel.getCurrentData().getValue().getProgress() - 5);
        });
    }


    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.homeViewModel.saveCurrentData(homeViewModel.getCurrentData().getValue().getProgress());
    }
}
