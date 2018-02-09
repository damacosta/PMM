package com.example.alemen.examenev2;

import android.app.Fragment;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by alemen on 9/02/18.
 */

public class SimpleFragment extends Fragment {
    private static final java.lang.String ARG_PARAM = "";
    TextView item = null;
    TextView place = null;
    TextView description = null;
    //TextView importance = null;
    //TextView id = null;
    int mParam1_num;

    static SimpleFragment newInstance(int param1) {
        SimpleFragment f = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, param1);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParam1_num = getArguments().getInt(ARG_PARAM);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = null;
        View tv  = null;
        v = inflater.inflate(R.layout.detail_item, container, false);

        item = v.findViewById(R.id.item);
        place = v.findViewById(R.id.place);
        description  = v.findViewById(R.id.description);
        //importance  = v.findViewById(R.id.importance);
        //id = v.findViewById(R.id.identificator);
        populateFieldsFromDB(mParam1_num);

        return v;
    }
    private void populateFieldsFromDB(int numTarea) {
        try {

            MainActivity.mDbHelper.open();
            Cursor c = MainActivity.mDbHelper.getItem(numTarea);
            if (c.moveToFirst()) {
                item.setText(c.getString(c.getColumnIndexOrThrow(DataBaseHelper.SL_ITEM)));
                place.setText(c.getString(c.getColumnIndex(DataBaseHelper.SL_PLACE)));
                description.setText(c.getString(c.getColumnIndex(DataBaseHelper.SL_DESCRIPTION)));

            }
            c.close();
            MainActivity.mDbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}