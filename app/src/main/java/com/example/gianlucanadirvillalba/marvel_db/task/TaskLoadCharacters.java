package com.example.gianlucanadirvillalba.marvel_db.task;

import android.os.AsyncTask;

import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import java.util.ArrayList;

/**
 * Created by gianlucanadirvillalba on 25/11/2016.
 */

public class TaskLoadCharacters extends AsyncTask<Void, Void, ArrayList<SuperHero>>
{
    @Override
    protected ArrayList<SuperHero> doInBackground(Void... voids)
    {
        //MyApplication.getDatabase().insertMovies();
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<SuperHero> superHeroes)
    {
        super.onPostExecute(superHeroes);
    }
}
