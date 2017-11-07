package com.example.alemen.menu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DrawShapes1 extends AppCompatActivity {
    private RandomShapeView mDrawingArea;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_shapes1);
        mDrawingArea = findViewById(R.id.drawing_area);
    }

    public void redraw(View clickedButton) {
        mDrawingArea.invalidate();
    }
}