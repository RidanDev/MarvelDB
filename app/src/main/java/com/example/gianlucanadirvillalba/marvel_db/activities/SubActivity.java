package com.example.gianlucanadirvillalba.marvel_db.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.gianlucanadirvillalba.marvel_db.R;

public class SubActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String name = getIntent().getStringExtra("name");
        String powers = getIntent().getStringExtra("powers");
        String realName = getIntent().getStringExtra("realName");
        String identity = getIntent().getStringExtra("identity");
        String cityzenship = getIntent().getStringExtra("cityzenship");
        String placeOfBird = getIntent().getStringExtra("placeOfBird");
        String education = getIntent().getStringExtra("education");
        String height = getIntent().getStringExtra("height");
        String weight = getIntent().getStringExtra("weight");
        String eyes = getIntent().getStringExtra("eyes");
        String hair= getIntent().getStringExtra("hair");


        //TextView textView = (TextView) findViewById(R.id.bio_superhero);
        TextView textName = (TextView) findViewById(R.id.name);
        TextView textRealName = (TextView) findViewById(R.id.real_name);
        TextView textIdentity = (TextView) findViewById(R.id.identity);
        TextView textCitizenship = (TextView) findViewById(R.id.citizenship);
        TextView textPlaceOfBird = (TextView) findViewById(R.id.place_of_bird);
        TextView textEducation = (TextView) findViewById(R.id.education);
        TextView textHeight = (TextView) findViewById(R.id.height);
        TextView textWeight = (TextView) findViewById(R.id.weight);
        TextView textEyes = (TextView) findViewById(R.id.eyes);
        TextView textHair = (TextView) findViewById(R.id.hair);
        TextView textPowers = (TextView) findViewById(R.id.powers);


        textName.setText(name);
        textRealName.setText(realName);
        textIdentity.setText(identity);
        textCitizenship.setText(cityzenship);
        textPlaceOfBird.setText(placeOfBird);
        textEducation.setText(education);
        textHeight.setText(height);
        textWeight.setText(weight);
        textEyes.setText(eyes);
        textHair.setText(hair);
        textPowers.setText(powers);
    }
}
