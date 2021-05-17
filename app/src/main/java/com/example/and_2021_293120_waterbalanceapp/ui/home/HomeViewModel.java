package com.example.and_2021_293120_waterbalanceapp.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

    public HomeViewModel(Application app) {
        super(app);
        userRepository = UserRepository.getInstance(app);
        recordRepository = RecordRepository.getInstance();
        currentDataRepository = CurrentDataRepository.getInstance();
    }

    public void init() throws IllegalAccessException {
        currentDataRepository.init();
        recordRepository.init();
    }

    public LiveData<CurrentData> getCurrentData() {return currentDataRepository.getCurrentLiveData();}

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void saveRecord(Double goal, Double progress) {
        List<Record> recordList = recordRepository.getRecord().getValue();
        if (recordList == null) {
            recordList = new ArrayList<>();
        }
        recordList.add(new Record(goal,progress));
        recordList.add(new Record(goal,progress));
        recordList.add(new Record(goal,progress));
        recordRepository.saveRecord(recordList);
    }

    public void saveCurrentData(Double goal, Double progress) {
        currentDataRepository.saveCurrentData(goal,progress);
    }

    public void signOut() {
        userRepository.signOut();
    }


}