package com.example.gianlucanadirvillalba.marvel_db.extras;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.gianlucanadirvillalba.marvel_db.database.SuperHeroesDatabase;

import java.io.IOException;

/**
 * Created by gianlucanadirvillalba on 10/11/2016.
 */

public class MyApplication extends Application
{
    private static MyApplication sInstance;
    private static SuperHeroesDatabase mDatabase;

    @Override
    public void onCreate()
    {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getInstance()
    {
        return sInstance;
    }

    public static Context getAppContext()
    {
        return sInstance.getApplicationContext();
    }

    public synchronized static SuperHeroesDatabase getDatabase()
    {
        if (mDatabase == null)
        {
            mDatabase = new SuperHeroesDatabase(getAppContext());
            Toast.makeText(sInstance, "Created new DB", Toast.LENGTH_SHORT).show();
        }
        return mDatabase;
    }

    //??
    public static boolean listAssetFiles(String jsonFile)
    {
        try
        {
            getAppContext().getAssets().open(jsonFile);
            return true;
        } catch (IOException e)
        {
            return false;
        }
    }
}
