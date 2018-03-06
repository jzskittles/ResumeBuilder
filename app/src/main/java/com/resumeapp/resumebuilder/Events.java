package com.resumeapp.resumebuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Events extends AppCompatActivity {

    Spinner eventType;
    List<String> categories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        eventType = (Spinner)findViewById(R.id.eventType);

        // Spinner Drop down elements
        categories = new ArrayList<String>();
        categories.add("Public Job Fair");
        categories.add("Summer Internship");
        categories.add("Full Time Job Offering");
        categories.add("Part Time Job Offering");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        eventType.setAdapter(dataAdapter);

        eventType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(categories.get(i).equals("Public Job Fair")){

                }
            }
        });
    }
}
