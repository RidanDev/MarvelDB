package com.example.gianlucanadirvillalba.marvel_db.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.gianlucanadirvillalba.marvel_db.R;

public class DisplayActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_display);

        TextView textView = (TextView) findViewById(R.id.full_text);
        textView.setText(getIntent().getStringExtra("text"));
    }
}
