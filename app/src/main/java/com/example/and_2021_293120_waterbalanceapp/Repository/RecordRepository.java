package com.example.and_2021_293120_waterbalanceapp.Repository;

import com.example.and_2021_293120_waterbalanceapp.Data.Record;
import com.example.and_2021_293120_waterbalanceapp.Data.RecordLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecordRepository {
    private static RecordRepository instance;
    private DatabaseReference myRef;
    private RecordLiveData records;


    private RecordRepository(){
    }

    public static synchronized RecordRepository getInstance(){
        if (instance == null)
            instance = new RecordRepository();
        return instance;
    }

    public void init() throws IllegalAccessException {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String UID = user.getUid();
        if (UID != null) {
            myRef = FirebaseDatabase.getInstance().getReference().child("users").child(UID).child("Records");
            records = new RecordLiveData(myRef);
        } else
            throw new IllegalAccessException("5051");

    }

    public void saveRecord(List<Record> records) {
        myRef.setValue(records);
    }

    public void removeRecords() {
        myRef.setValue(new ArrayList<Record>());
    }

    public RecordLiveData getRecord() {return records;}
}
