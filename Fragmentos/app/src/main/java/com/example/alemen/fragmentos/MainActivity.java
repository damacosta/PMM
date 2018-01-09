package com.example.alemen.fragmentos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBox;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view =  (View) findViewById(R.id.ejemplo);

        checkBox = (CheckBox) findViewById(R.id.cBox);

        final ImageView imageView = (ImageView)findViewById(R.id.imagen);
        imageView.setImageResource(R.drawable.ic_launcher_background);
        imageView.setVisibility(View.INVISIBLE);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked())
                    imageView.setVisibility(View.INVISIBLE);
                else
                    imageView.setVisibility(View.VISIBLE);
            }
        });
    }
}