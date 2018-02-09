package com.example.alemen.examenev2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by alemen on 9/02/18.
 */

public class Options extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(android.R.id.content, new SettingsFragment());
        ft.commit();
    }
}
