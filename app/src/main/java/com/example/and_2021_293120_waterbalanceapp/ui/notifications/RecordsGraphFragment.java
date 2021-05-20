package com.example.and_2021_293120_waterbalanceapp.ui.notifications;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.anychart.AnyChartView;
import com.example.and_2021_293120_waterbalanceapp.R;
import com.example.and_2021_293120_waterbalanceapp.ui.SignInActivity;
import com.google.android.material.snackbar.Snackbar;

public class RecordsGraphFragment extends Fragment {

    private RecordsGraphViewModel recordsGraphViewModel;
    private AnyChartView anyChartView;
    private Button resetButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recordsgraph, container, false);

        this.anyChartView = root.findViewById(R.id.any_chart_view);

        this.resetButton = root.findViewById(R.id.reset_button);
        //anyChartView.setProgressBar(findViewById(R.id.progress_bar));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recordsGraphViewModel =
                new ViewModelProvider(this).get(RecordsGraphViewModel.class);
        try {
            recordsGraphViewModel.init();
        } catch (IllegalAccessException e) {
            startActivity(new Intent(getContext(), SignInActivity.class));
        }

        resetButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(true);
            builder.setTitle("Confirmation");
            builder.setMessage("Are you sure you want to delete all records history. There won't be any way back to restore it");
            builder.setPositiveButton("Confirm",
                    (dialog, which) -> {
                        recordsGraphViewModel.removeRecords();
                    });
            builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                Snackbar.make(getView(),"The action is canceled",Snackbar.LENGTH_SHORT).show();
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        recordsGraphViewModel.getRecords().observe(getViewLifecycleOwner(), records -> {
            if (records != null) {
                recordsGraphViewModel.setDisplayList(records);
                recordsGraphViewModel.updateGraph();
            }
        });
        anyChartView.setChart(recordsGraphViewModel.getChart());
    }
}