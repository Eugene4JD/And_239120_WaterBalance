package com.example.and_2021_293120_waterbalanceapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.and_2021_293120_waterbalanceapp.R;

public class DashboardFragment extends PreferenceFragmentCompat {

    private DashboardViewModel dashboardViewModel;
    private ListPreference listPreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences,rootKey);
    }
}