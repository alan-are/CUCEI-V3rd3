package com.example.resea_profesores;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.core.view.WindowCompat;

import java.util.ArrayList;

public class lista extends Activity {

    private ListView lv;
    private ArrayList<String> names;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.list_view);

        lv = (ListView) findViewById(R.id.listview);

        names = new ArrayList<String>();
        names.add("Alan");
        names.add("Raul");
        names.add("Geovanny");
        names.add("Abraham ");
        names.add("Torres");
        names.add("Juan");
        names.add("Carlos");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        lv.setAdapter(adapter);


    }

}
