package com.example.and_2021_293120_waterbalanceapp.repository;

import com.example.and_2021_293120_waterbalanceapp.data.CurrentData;
import com.example.and_2021_293120_waterbalanceapp.data.CurrentLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CurrentDataRepository {
    private static CurrentDataRepository instance;
    private DatabaseReference myRef;
    private CurrentLiveData currentLiveData;


    private CurrentDataRepository() {
    }

    public static synchronized CurrentDataRepository getInstance() {
        if (instance == null)
            instance = new CurrentDataRepository();
        return instance;
    }

    public void init() throws IllegalAccessException {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String UID = user.getUid();
        if (UID != null) {
            myRef = FirebaseDatabase.getInstance().getReference().child("users").child(UID).child("currentData");
            currentLiveData = new CurrentLiveData(myRef);
        } else
            throw new IllegalAccessException("5051");

    }

    public void saveCurrentData(Double goal, Double progress) {
        myRef.setValue(new CurrentData(goal, progress));
    }

    public CurrentLiveData getCurrentLiveData() {
        return currentLiveData;
    }
}
