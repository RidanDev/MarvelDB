package com.example.gianlucanadirvillalba.marvel_db.task;

import android.os.AsyncTask;

import com.example.gianlucanadirvillalba.marvel_db.Interfaces.SuperHeroesListener;
import com.example.gianlucanadirvillalba.marvel_db.extras.MyApplication;
import com.example.gianlucanadirvillalba.marvel_db.json.Parser2;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by gianlucanadirvillalba on 25/11/2016.
 * <p>
 * In questo Thread mi scorro tutti json in assets/characters e li passo al Parser2.
 * Quando termino il ciclo allora avrò una lista di superheroes che ordino per nome con un treeset
 * e che posso passare all'adapter grazie al onPostExecute().
 */

public class TaskLoadCharacters extends AsyncTask<Void, Void, ArrayList<SuperHero>>
{
    private SuperHeroesListener myComponent;
    private TreeSet<String> treeSet = new TreeSet<>();
    private ArrayList<SuperHero> superHeroesSortedByName = new ArrayList<>();
    //public static boolean begin = true;

    public TaskLoadCharacters(SuperHeroesListener myComponent)
    {
        this.myComponent = myComponent;
    }

    @Override
    protected ArrayList<SuperHero> doInBackground(Void... voids)
    {
        String json = null;
        JSONObject obj = null;
        String[] list;

        try
        {
            list = MyApplication.getAppContext().getAssets().list("characters");
            //list = MyApplication.getAppContext().getAssets().list("records");
            if (list.length > 0)
            {
                // This is a folder
                for (String file : list)
                {
                    InputStream is =
                            MyApplication.getAppContext().getResources().getAssets().open("characters/" + file.toString());
                    //MyApplication.getAppContext().getResources().getAssets().open("records/" + file.toString());
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    json = new String(buffer, "UTF-8");
                    obj = new JSONObject(json);
                    Parser2.parseJsonCharacters(obj);
                    //Parser3.parseJsonCharacters(obj);
                }
            }

        } catch (IOException ex)
        {
            ex.printStackTrace();
            return null;
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        for (int i = 0; i < Parser2.charactersList.size(); i++)
            treeSet.add(Parser2.charactersList.get(i).getName());
        for (String name : treeSet)
        {
            for (int i = 0; i < Parser2.charactersList.size(); i++)
                if (Parser2.charactersList.get(i).getName().equals(name))
                    superHeroesSortedByName.add(Parser2.charactersList.get(i));
        }

        return superHeroesSortedByName; //ordinati
        //return Parser3.marvelSearchList;
    }

    @Override
    protected void onPostExecute(ArrayList<SuperHero> superHeroes)
    {
        //f (myComponent != null)
        //   myComponent.onAddSuperHeroes(superHeroes);
        new TaskLoadCharacters2(superHeroesSortedByName, myComponent).execute();
    }
}
