package com.example.gianlucanadirvillalba.marvel_db.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.gianlucanadirvillalba.marvel_db.R;
import com.example.gianlucanadirvillalba.marvel_db.extras.MyApplication;
import com.squareup.picasso.Picasso;

public class SubActivity extends AppCompatActivity
{

    private String realName;
    private String identity;
    private String citizenship;
    private String placeOfBirth;
    private String education;
    private String height;
    private String weight;
    private String eyes;
    private String hair;
    private ScrollView parentScroll;
    private NestedScrollView childScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String imagePath = getIntent().getStringExtra("imagePath");
        String blurb = getIntent().getStringExtra("blurb");
        String name = getIntent().getStringExtra("name");
        if (name.contains("(")) name = name.substring(0, name.indexOf("("));
        realName = getIntent().getStringExtra("realName");
        identity = getIntent().getStringExtra("identity");
        citizenship = getIntent().getStringExtra("citizenship");
        placeOfBirth = getIntent().getStringExtra("placeOfBirth");
        education = getIntent().getStringExtra("education");
        height = getIntent().getStringExtra("height");
        weight = getIntent().getStringExtra("weight");
        eyes = getIntent().getStringExtra("eyes");
        hair = getIntent().getStringExtra("hair");
        String occupation = getIntent().getStringExtra("occupation");
        final String abilities = getIntent().getStringExtra("abilities");
        final String weapons = getIntent().getStringExtra("weapons");
        final String paraphernalia = getIntent().getStringExtra("paraphernalia");
        final String powers = getIntent().getStringExtra("powers");

        TextView textBlurb = (TextView) findViewById(R.id.blurb);
        TextView textName = (TextView) findViewById(R.id.name);
        TextView textRealName = (TextView) findViewById(R.id.real_name);
        TextView textIdentity = (TextView) findViewById(R.id.identity);
        TextView textCitizenship = (TextView) findViewById(R.id.citizenship);
        TextView textPlaceOfBirth = (TextView) findViewById(R.id.place_of_birth);
        TextView textEducation = (TextView) findViewById(R.id.education);
        TextView textHeight = (TextView) findViewById(R.id.height);
        TextView textWeight = (TextView) findViewById(R.id.weight);
        TextView textEyes = (TextView) findViewById(R.id.eyes);
        TextView textHair = (TextView) findViewById(R.id.hair);
        TextView textOccupation = (TextView) findViewById(R.id.occupation);
        final TextView textAbilities = (TextView) findViewById(R.id.abilities);
        final TextView textWeapons = (TextView) findViewById(R.id.weapons);
        final TextView textParaphernalia = (TextView) findViewById(R.id.paraphernalia);
        final TextView textPowers = (TextView) findViewById(R.id.powers);

        ImageView imageView = (ImageView) findViewById(R.id.character_image_icon);

        Picasso.with(MyApplication.getAppContext())
                .load(imagePath)
                .into(imageView);

        LinearLayout blurbLayout = (LinearLayout) findViewById(R.id.blurb_layout);
        LinearLayout biographyLayout = (LinearLayout) findViewById(R.id.biography_layout);
        LinearLayout appearanceLayout = (LinearLayout) findViewById(R.id.appearance_layout);
        LinearLayout realNameLayout = (LinearLayout) findViewById(R.id.real_name_layout);
        LinearLayout identityLayout = (LinearLayout) findViewById(R.id.identity_layout);
        LinearLayout citizenshipLayout = (LinearLayout) findViewById(R.id.citizenship_layout);
        LinearLayout placeOfBirthLayout = (LinearLayout) findViewById(R.id.place_of_birth_layout);
        LinearLayout educationLayout = (LinearLayout) findViewById(R.id.education_layout);
        LinearLayout heightLayout = (LinearLayout) findViewById(R.id.height_layout);
        LinearLayout weightLayout = (LinearLayout) findViewById(R.id.weight_layout);
        LinearLayout eyesLayout = (LinearLayout) findViewById(R.id.eyes_layout);
        LinearLayout hairLayout = (LinearLayout) findViewById(R.id.hair_layout);
        LinearLayout occupationLayout = (LinearLayout) findViewById(R.id.occupation_layout);
        LinearLayout abilitiesLayout = (LinearLayout) findViewById(R.id.abilities_layout);
        LinearLayout weaponsLayout = (LinearLayout) findViewById(R.id.weapons_layout);
        LinearLayout paraphernaliaLayout = (LinearLayout) findViewById(R.id.paraphernalia_layout);
        LinearLayout powersLayout = (LinearLayout) findViewById(R.id.powers_layout);


        textName.setText(name);
        if (MyApplication.notEmptyNA(blurb)) textBlurb.setText(blurb);
        else blurbLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(realName)) textRealName.setText(realName);
        else realNameLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(identity)) textIdentity.setText(identity);
        else identityLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(citizenship)) textCitizenship.setText(citizenship);
        else citizenshipLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(placeOfBirth)) textPlaceOfBirth.setText(placeOfBirth);
        else placeOfBirthLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(education)) textEducation.setText(education);
        else educationLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(height)) textHeight.setText(height);
        else heightLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(weight)) textWeight.setText(weight);
        else weightLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(eyes)) textEyes.setText(eyes);
        else eyesLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(hair)) textHair.setText(hair);
        else hairLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(occupation)) textOccupation.setText(occupation);
        else occupationLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(abilities)) textAbilities.setText(abilities);
        else abilitiesLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(weapons)) textWeapons.setText(weapons);
        else weaponsLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(paraphernalia)) textParaphernalia.setText(paraphernalia);
        else paraphernaliaLayout.setVisibility(View.GONE);
        if (MyApplication.notEmptyNA(powers)) textPowers.setText(powers);
        else powersLayout.setVisibility(View.GONE);


        if (emptyBiography()) biographyLayout.setVisibility(View.GONE);
        if (emptyAppearance()) appearanceLayout.setVisibility(View.GONE);

        parentScroll = (ScrollView) findViewById(R.id.parent_scroll);


        abilitiesLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (textAbilities.getLineCount() >= 10)
                {
                    Intent intent = new Intent(MyApplication.getAppContext(), DisplayActivity.class);
                    intent.putExtra("text", abilities);
                    startActivity(intent);
                }
            }
        });

        weaponsLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (textWeapons.getLineCount() >= 10)
                {
                    Intent intent = new Intent(MyApplication.getAppContext(), DisplayActivity.class);
                    intent.putExtra("text", weapons);
                    startActivity(intent);
                }
            }
        });

        paraphernaliaLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (textParaphernalia.getLineCount() >= 10)
                {
                    Intent intent = new Intent(MyApplication.getAppContext(), DisplayActivity.class);
                    intent.putExtra("text", paraphernalia);
                    startActivity(intent);
                }
            }
        });

        powersLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (textPowers.getLineCount() >= 10)
                {
                    Intent intent = new Intent(MyApplication.getAppContext(), DisplayActivity.class);
                    intent.putExtra("text", powers);
                    startActivity(intent);
                }
                //Log.d("tag", textPowers.getLineCount()+"");
            }
        });
    }

    public boolean emptyBiography()
    {
        return (!MyApplication.notEmptyNA(realName)
                && !MyApplication.notEmptyNA(identity)
                && !MyApplication.notEmptyNA(citizenship)
                && !MyApplication.notEmptyNA(placeOfBirth)
                && !MyApplication.notEmptyNA(education));
    }

    public boolean emptyAppearance()
    {
        return (!MyApplication.notEmptyNA(height)
                && !MyApplication.notEmptyNA(weight)
                && !MyApplication.notEmptyNA(eyes)
                && !MyApplication.notEmptyNA(hair));
    }
}
