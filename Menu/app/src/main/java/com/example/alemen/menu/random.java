package com.example.alemen.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class random extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
    }

    public void launchDrawShapes1(View clickedButton) {
        Intent draw1 = new Intent(random.this, DrawShapes1.class);
        startActivity(draw1);
    }

    public void launchDrawShapes2(View clickedButton) {
        Intent draw2 = new Intent(this, DrawShapes2.class);
        startActivity(draw2);
    }
}