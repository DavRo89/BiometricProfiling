package com.example.davide.biometricprofiling;

import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Biometrics_features extends AppCompatActivity {
    public List<String> biometrics_feature=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        biometrics_feature.add("Volto");
        biometrics_feature.add("Iride");
        biometrics_feature.add("Mano");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biometrics_features);


        if (savedInstanceState == null) {
            MainFragment fragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }



    }

    public void getBiometrics(){

    }

}
