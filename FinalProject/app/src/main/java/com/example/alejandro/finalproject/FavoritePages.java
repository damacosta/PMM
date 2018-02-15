package com.example.alejandro.finalproject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FavoritePages extends ListActivity {

    private int mLastRowSelected = 0;
    public static DataBaseHelper mDbHelper = null;

    int mStackPosition = 1;
    String newUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_pages);
        mDbHelper = new DataBaseHelper(this);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //Intenta rellenar la lista y si no salta error
        try {
            fillData();
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage(R.string.dataError);
        }
        registerForContextMenu(getListView());
    }

    //Esto es el menú inferior generado automáticamente por Android Studio al elegir una actividad.
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent2 = new Intent(FavoritePages.this, MainActivity.class);
                    startActivity(intent2);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(FavoritePages.this, ViewPage.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    //Muestra toast con el String que ponga
    private void showMessage(int message){
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(message);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void fillData() {
        // se abre la base de datos y se obtienen los elementos
        mDbHelper.open();
        Cursor itemCursor = mDbHelper.getItems();
        ListEntry item = null;
        ArrayList<ListEntry> resultList = new ArrayList<ListEntry>();
        // se procesa el resultado
        while (itemCursor.moveToNext()) {
            int id = itemCursor.getInt(itemCursor.getColumnIndex(DataBaseHelper.SL_ID));
            String name = itemCursor.getString(itemCursor.getColumnIndex(DataBaseHelper.SL_NAME));
            String url = itemCursor.getString(itemCursor.getColumnIndex(DataBaseHelper.SL_URL));
            item = new ListEntry();
            item.id = id;
            item.name = name;
            item.url = url;
            resultList.add(item);
        }
        //cerramos la base de datos
        itemCursor.close();
        mDbHelper.close();
        //se genera el adaptador
        TaskAdapter items = new TaskAdapter(this, R.layout.row_list, resultList, getLayoutInflater());
        //asignar adaptador a la lista
        setListAdapter(items);
    }

    //Menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context, menu);
    }

    //Menu contextual para borrar la web seleccionada
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo delW = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        mLastRowSelected = delW.position;
        switch (item.getItemId()) {
            case R.id.delete_item:
                new AlertDialog.Builder(this).setTitle(
                        this.getString(R.string.delete)).setMessage(
                        R.string.delete).setPositiveButton(
                        android.R.string.ok, new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dlg, int i) {
                                deleteEntry();
                            }									}).setNegativeButton(android.R.string.cancel, null).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    //Metodo para borrar
    protected void deleteEntry() {
        try{
            mDbHelper.open();
            mDbHelper.delete(((ListEntry)getListAdapter().getItem(mLastRowSelected)).id);
            mDbHelper.close();
            fillData();
        }catch (SQLException e){
            e.printStackTrace();
            showMessage(R.string.dataError);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position",mStackPosition);
    }

    private class TaskAdapter extends ArrayAdapter<ListEntry> {
        private LayoutInflater mInflater;
        private List<ListEntry> mObjects;

        private TaskAdapter(Context context, int resource, List<ListEntry> objects, LayoutInflater mInflater) {
            super(context, resource, objects);
            this.mInflater = mInflater;
            this.mObjects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListEntry listEntry = mObjects.get(position);
            // obtención de la vista de la línea de la tabla
            View row = mInflater.inflate(R.layout.row_list, null);
            //rellenamos datos
            TextView name = (TextView) row.findViewById(R.id.row_name);
            TextView url = (TextView) row.findViewById(R.id.row_url);
            name.setText(listEntry.name);
            url.setText(listEntry.url);

            return row;
        }
    }
    private class ListEntry {
        int id;
        String name;
        String url;
    }

}
