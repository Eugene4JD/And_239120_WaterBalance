package com.example.and_2021_293120_waterbalanceapp.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.preference.PreferenceManager;

import com.example.and_2021_293120_waterbalanceapp.data.CurrentData;
import com.example.and_2021_293120_waterbalanceapp.data.Record;
import com.example.and_2021_293120_waterbalanceapp.repository.CurrentDataRepository;
import com.example.and_2021_293120_waterbalanceapp.repository.RecordRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private final RecordRepository recordRepository;
    private final CurrentDataRepository currentDataRepository;
    private SharedPreferences sharedPreferences;
    private ArrayList<Record> displayList;

    public HomeViewModel(Application app) {
        super(app);
        recordRepository = RecordRepository.getInstance();
        currentDataRepository = CurrentDataRepository.getInstance();
        displayList = new ArrayList<>();
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(app.getBaseContext());
    }

    public void init() throws IllegalAccessException {
        currentDataRepository.init();
        recordRepository.init();
    }

    public LiveData<CurrentData> getCurrentData() {
        return currentDataRepository.getCurrentLiveData();
    }


    public LiveData<List<Record>> getRecords() {
        return recordRepository.getRecord();
    }

    public void saveRecord(Double progress) {
        displayList.add(new Record(Double.parseDouble(sharedPreferences.getString("goal_pref", "2000.0")), progress));
        recordRepository.saveRecord(displayList);
    }

    public void saveCurrentData(Double progress) {
        currentDataRepository.saveCurrentData(Double.parseDouble(sharedPreferences.getString("goal_pref", "2000.0")), progress);
    }

    public void setDisplayList(List<Record> records) {
        this.displayList = (ArrayList<Record>) records;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}