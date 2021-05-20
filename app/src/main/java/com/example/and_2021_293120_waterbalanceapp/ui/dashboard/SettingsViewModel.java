package com.example.and_2021_293120_waterbalanceapp.ui.dashboard;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import com.example.and_2021_293120_waterbalanceapp.Repository.UserRepository;

public class SettingsViewModel extends AndroidViewModel {

    private final UserRepository userRepository;
    private SharedPreferences sharedPreferences;

    public SettingsViewModel(Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application.getBaseContext());
    }


    public void signOut()
    {
        userRepository.signOut();
    }

    public boolean validateStringPreference(String value)
    {
        try {
            double d = Double.parseDouble(value);
            if (d>0)
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}