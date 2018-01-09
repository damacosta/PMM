package com.example.alemen.fragmentosdinamicos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SimpleFragment extends Fragment {
    int mNum;
    public static SimpleFragment newInstance(int number){
        SimpleFragment f = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt("num",number);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num");
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null;View tv;

        if (mNum % 2 == 0){
            v = layoutInflater.inflate(R.layout.frame_simple1,container,false);
            tv = v.findViewById(R.id.text);
        }
        else{
            v = layoutInflater.inflate(R.layout.frame_simple2,container,false);
            tv = v.findViewById(R.id.text2);
        }
        ((TextView)tv).setText("Fragmento numero "+ mNum);
        return v;
    }
}