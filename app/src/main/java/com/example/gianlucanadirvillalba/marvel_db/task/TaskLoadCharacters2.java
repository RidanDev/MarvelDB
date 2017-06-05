package com.example.gianlucanadirvillalba.marvel_db.task;

import android.os.AsyncTask;

import com.example.gianlucanadirvillalba.marvel_db.Interfaces.SuperHeroesListener;
import com.example.gianlucanadirvillalba.marvel_db.extras.MyApplication;
import com.example.gianlucanadirvillalba.marvel_db.json.Parser3;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by gianlucanadirvillalba on 30/05/2017.
 *
 * Questo Thread viene chiamato al termine del TaskLoadCharacters per iterare sui json in assets/records
 * e passarli al Parser3. Al termine del ciclo avrò una lista di superheroes che confronterò con quelli
 * del TaskLoadCharacters per cercare i superheroes mancanti.
 * Se un superhero è già presente devo aggiornarlo con i nuovi campi, altrimenti lo devo aggiungere
 * alla lista con i suoi campi.
 */

public class TaskLoadCharacters2 extends AsyncTask<Void, Void, ArrayList<SuperHero>>
{
    private ArrayList<SuperHero> superHeroesSortedByName = new ArrayList<>();
    private ArrayList<SuperHero> newCharacters = new ArrayList<>();
    private TreeSet<String> myTreeSet = new TreeSet<>();
    private ArrayList<SuperHero> finalCharacters = new ArrayList<>();
    private SuperHeroesListener myComponent;

    public TaskLoadCharacters2(ArrayList<SuperHero> superHeroesSortedByName, SuperHeroesListener myComponent)
    {
        this.superHeroesSortedByName = superHeroesSortedByName;
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
            list = MyApplication.getAppContext().getAssets().list("records");
            if (list.length > 0)
            {
                // This is a folder
                for (String file : list)
                {
                    InputStream is =
                            MyApplication.getAppContext().getResources().getAssets().open("records/" + file.toString());
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    json = new String(buffer, "UTF-8");
                    obj = new JSONObject(json);
                    Parser3.parseJsonCharacters(obj);
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
        //Prendo tutti i personaggi che sono nella marvelSearchList ma che non sono nella superHeroSortedByName
        // e li inserisco in newCharacters per controllare quali sono
        boolean contains = false;
        for (int i = 0; i < Parser3.marvelSearchList.size(); i++)
        {
            for (int j = 0; j < superHeroesSortedByName.size(); j++)
            {
                if (Parser3.marvelSearchList.get(i).getName().equals(superHeroesSortedByName.get(j).getName()))
                {
                    contains = true;
                    //aggiungo i nuovi campi al superhero
                    break;
                }
            }
            //mi salvo il superhero mancante
            if (!contains)
            {
                //newCharactersName.add(Parser3.marvelSearchList.get(i).getName());
                newCharacters.add(Parser3.marvelSearchList.get(i));
            } else
            {
                contains = false;
            }
        }
        /*Log.d("TAG", "new_number: " + newCharacters.size());
        for (SuperHero superHero : newCharacters)
            Log.d("TAG", "new: " + superHero.getName() +
                    ", " + superHero.getPageViewCount());*/


        /*LEGGO IL TXT CON I NOMI DEI PERSONAGGI, PRELEVO LE ISTANZE SUPERHERO CORRISPONDENTI E LE
        AGGIUNGO NELLA LISTA DI SUPERHERO CHE HO. ORDINO IL TUTTO PER NOME E LI AGGIUNGO IN UNA LISTA FINALE*/
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(
                    new InputStreamReader(MyApplication.getAppContext().getAssets().open("marvel_char.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null)
            {
                //Log.d("TAG", mLine);
                for (SuperHero superHero : newCharacters)
                    if (superHero.getName().equals(mLine))
                        superHeroesSortedByName.add(superHero);
            }
        } catch (IOException e)
        {
            //log the exception
        } finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                } catch (IOException e)
                {
                    //log the exception
                }
            }
        }

        for (int i = 0; i < superHeroesSortedByName.size(); i++)
            myTreeSet.add(superHeroesSortedByName.get(i).getName());

        for (String name : myTreeSet)
        {
            for (int i = 0; i < superHeroesSortedByName.size(); i++)
                if (superHeroesSortedByName.get(i).getName().equals(name))
                    finalCharacters.add(superHeroesSortedByName.get(i));
        }

        //return Parser3.marvelSearchList;
        return newCharacters;
    }

    @Override
    protected void onPostExecute(ArrayList<SuperHero> superHeroes)
    {
        if (myComponent != null)
            myComponent.onAddSuperHeroes(finalCharacters);
    }
}
