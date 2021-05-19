package com.example.and_2021_293120_waterbalanceapp.ui.dashboard;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;

public class DashboardViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private SharedPreferences sharedPreferences;

    public DashboardViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application.getBaseContext());

        System.out.println("ABOBAAAAAAAAAAAAAAAAAA"+ sharedPreferences.getString("goal",""));
    }

    public void getText() {
        System.out.println("ABOBAAAAAAAAAAAAAAAAAA"+ sharedPreferences.getString("goal",""));
        Log.i("ABOBA", sharedPreferences.getString("goal",""));
    }
}