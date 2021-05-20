package com.example.and_2021_293120_waterbalanceapp.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecordLiveData extends LiveData<List<Record>> {
    private final ValueEventListener valueEventListener = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            List<Record> records = new ArrayList<>();
            for (DataSnapshot child : snapshot.getChildren()) {
                records.add(child.getValue(Record.class));
            }
            setValue(records);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    DatabaseReference databaseReference;

    public RecordLiveData(DatabaseReference ref) {
        databaseReference = ref;
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(valueEventListener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(valueEventListener);
    }
}
