package com.example.and_2021_293120_waterbalanceapp.ui.settings;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.and_2021_293120_waterbalanceapp.R;
import com.example.and_2021_293120_waterbalanceapp.viewmodel.SettingsViewModel;
import com.google.android.material.snackbar.Snackbar;

public class SettingsFragment extends PreferenceFragmentCompat {

    private SettingsViewModel settingsViewModel;
    private EditTextPreference editTextPreference;
    private Preference signOutButton;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        signOutButton = findPreference(getString(R.string.sign_out));
        signOutButton.setOnPreferenceClickListener(preference -> {
            settingsViewModel.signOut();
            return true;
        });
        editTextPreference = (EditTextPreference) findPreference("goal_pref");
        editTextPreference.setOnPreferenceChangeListener((preference, newValue) -> {
            if (settingsViewModel.validateStringPreference(newValue.toString()))
                return true;
            else
                Snackbar.make(getView(), "Please enter a valid goal, it should be a number more than 0", Snackbar.LENGTH_LONG).show();
            return false;
        });


    }


}