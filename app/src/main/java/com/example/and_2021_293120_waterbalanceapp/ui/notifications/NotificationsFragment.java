package com.example.and_2021_293120_waterbalanceapp.ui.notifications;

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

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian3d;
import com.anychart.core.cartesian.series.Area3d;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.hatchfill.HatchFillType;
import com.example.and_2021_293120_waterbalanceapp.Data.Record;
import com.example.and_2021_293120_waterbalanceapp.R;
import com.example.and_2021_293120_waterbalanceapp.ui.SignInActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private AnyChartView anyChartView;
    private Button resetButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        this.anyChartView = root.findViewById(R.id.any_chart_view);

        this.resetButton = root.findViewById(R.id.reset_button);
        //anyChartView.setProgressBar(findViewById(R.id.progress_bar));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        try {
            notificationsViewModel.init();
        } catch (IllegalAccessException e) {
            startActivity(new Intent(getContext(), SignInActivity.class));
        }

        resetButton.setOnClickListener(v -> {
            notificationsViewModel.removeRecords();
        });

        notificationsViewModel.getRecords().observe(getViewLifecycleOwner(), records -> {
            if (records != null) {
                notificationsViewModel.setDisplayList(records);
                notificationsViewModel.updateGraph();
            }
        });
        anyChartView.setChart(notificationsViewModel.getChart());
    }
}