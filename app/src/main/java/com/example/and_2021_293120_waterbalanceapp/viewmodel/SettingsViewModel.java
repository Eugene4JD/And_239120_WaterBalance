package com.example.and_2021_293120_waterbalanceapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;


import com.example.and_2021_293120_waterbalanceapp.repository.UserRepository;

public class SettingsViewModel extends AndroidViewModel {

    private final UserRepository userRepository;

    public SettingsViewModel(Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
    }


    public void signOut() {
        userRepository.signOut();
    }

    public boolean validateStringPreference(String value) {
        try {
            double d = Double.parseDouble(value);
            if (d > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
}