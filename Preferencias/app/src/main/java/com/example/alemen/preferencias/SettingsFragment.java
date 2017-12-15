package com.example.alemen.preferencias;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by alemen on 15/12/17.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.opciones);
    }
}
