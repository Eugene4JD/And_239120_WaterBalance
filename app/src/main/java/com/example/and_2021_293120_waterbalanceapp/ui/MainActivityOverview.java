package com.example.and_2021_293120_waterbalanceapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.and_2021_293120_waterbalanceapp.R;
import com.example.and_2021_293120_waterbalanceapp.viewmodel.MainActivityOverviewViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivityOverview extends AppCompatActivity {

    private MainActivityOverviewViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityOverviewViewModel.class);
        checkIfSignedIn();
        setContentView(R.layout.activity_main_overview2);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                String message = "Welcome " + user.getDisplayName();
                Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            } else
                startLoginActivity();
        });
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }
}