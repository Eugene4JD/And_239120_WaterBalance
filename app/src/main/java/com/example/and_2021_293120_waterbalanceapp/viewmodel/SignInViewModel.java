package com.example.and_2021_293120_waterbalanceapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_2021_293120_waterbalanceapp.repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class SignInViewModel extends AndroidViewModel {
    private final UserRepository userRepository;

    public SignInViewModel(Application app) {
        super(app);
        userRepository = UserRepository.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }
}
