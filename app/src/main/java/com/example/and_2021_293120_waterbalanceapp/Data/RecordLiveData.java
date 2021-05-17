package com.example.and_2021_293120_waterbalanceapp.Data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecordLiveData extends LiveData<List<Record>> {
   private final ValueEventListener valueEventListener = new  ValueEventListener(){

       @Override
       public void onDataChange(@NonNull DataSnapshot snapshot) {
           List<Record> records = new ArrayList<Record>();
           for (DataSnapshot child :snapshot.getChildren()) {
              records.add(child.getValue(Record.class));
           }
           setValue(records);
       }

       @Override
       public void onCancelled(@NonNull DatabaseError error) {

       }
   };
    private final ChildEventListener listener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Record record = snapshot.getValue(Record.class);
            List<Record> records = getValue();
            if (records == null) {
                records = new ArrayList<Record>();
            }
            records.add(record);
            setValue(records);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            List<Record> records = new ArrayList<Record>();
            for (DataSnapshot child :snapshot.getChildren()) {
                records.add(child.getValue(Record.class));
            }
            setValue(records);
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            List<Record> records = new ArrayList<Record>();
            for (DataSnapshot child :snapshot.getChildren()) {
                records.add(child.getValue(Record.class));
            }
            setValue(records);
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            List<Record> records = new ArrayList<Record>();
            for (DataSnapshot child :snapshot.getChildren()) {
                records.add(child.getValue(Record.class));
            }
            setValue(records);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    DatabaseReference databaseReference;

    public RecordLiveData(DatabaseReference ref) {databaseReference = ref;}

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(valueEventListener);
        databaseReference.addChildEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
        databaseReference.removeEventListener(valueEventListener);
    }
}
