package com.example.and_2021_293120_waterbalanceapp.ui.home;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;

import com.example.and_2021_293120_waterbalanceapp.Data.CurrentData;
import com.example.and_2021_293120_waterbalanceapp.Data.Record;
import com.example.and_2021_293120_waterbalanceapp.Repository.CurrentDataRepository;
import com.example.and_2021_293120_waterbalanceapp.Repository.RecordRepository;
import com.example.and_2021_293120_waterbalanceapp.Repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private final RecordRepository recordRepository;
    private final CurrentDataRepository currentDataRepository;
    private SharedPreferences sharedPreferences;
    private ArrayList<Record> displayList;

    public HomeViewModel(Application app) {
        super(app);
        userRepository = UserRepository.getInstance(app);
        recordRepository = RecordRepository.getInstance();
        currentDataRepository = CurrentDataRepository.getInstance();
        displayList = new ArrayList<>();
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(app.getBaseContext());
    }

    public void init() throws IllegalAccessException {
        currentDataRepository.init();
        recordRepository.init();
    }

    public LiveData<CurrentData> getCurrentData() {return currentDataRepository.getCurrentLiveData();}

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public LiveData<List<Record>> getRecords() {return  recordRepository.getRecord();}

    public void saveRecord(Double progress) {
        displayList.add(new Record(Double.parseDouble(sharedPreferences.getString("goal_pref","101.0")),progress));
        recordRepository.saveRecord(displayList);
    }

    public void saveCurrentData(Double progress) {
        currentDataRepository.saveCurrentData(Double.parseDouble(sharedPreferences.getString("goal_pref","101.0")),progress);
    }

    public void setDisplayList(List<Record> records)
    {
        this.displayList = (ArrayList<Record>) records;
    }

    public SharedPreferences getSharedPreferences() {return sharedPreferences;}
}