package com.example.dam.legoparts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by DAM on 2/2/17.
 */

public class Llista1 extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.llista1);
        ListView llista1 = (ListView) findViewById(R.id.listView1);

    }
}
