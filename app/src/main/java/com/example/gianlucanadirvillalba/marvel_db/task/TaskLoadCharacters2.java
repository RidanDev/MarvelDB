package com.example.gianlucanadirvillalba.marvel_db.task;

import android.os.AsyncTask;

import com.example.gianlucanadirvillalba.marvel_db.Interfaces.SuperHeroesListener;
import com.example.gianlucanadirvillalba.marvel_db.extras.MyApplication;
import com.example.gianlucanadirvillalba.marvel_db.json.Parser3;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by gianlucanadirvillalba on 30/05/2017.
 * <p>
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
                if (Parser3.marvelSearchList.get(i).getName().equals(superHeroesSortedByName.get(j).getName())
                        || Parser3.marvelSearchList.get(i).getName().equals(superHeroesSortedByName.get(j).getRealNameWiki())
                        || Parser3.marvelSearchList.get(i).getRecordSuperName().equals(superHeroesSortedByName.get(j).getName())
                        || Parser3.marvelSearchList.get(i).getRecordSuperName().equals(superHeroesSortedByName.get(j).getRealNameWiki()))
                {
                    //aggiungo i nuovi campi al superhero
                    contains = true;
                    superHeroesSortedByName.get(j).setRecordAliases(Parser3.marvelSearchList.get(i).getRecordAliases());
                    superHeroesSortedByName.get(j).setRecordAuthors(Parser3.marvelSearchList.get(i).getRecordAuthors());
                    superHeroesSortedByName.get(j).setRecordDescription(Parser3.marvelSearchList.get(i).getRecordDescription());
                    superHeroesSortedByName.get(j).setRecordBackground(Parser3.marvelSearchList.get(i).getRecordBackground());
                    superHeroesSortedByName.get(j).setRecordImagePath(Parser3.marvelSearchList.get(i).getRecordImagePath());
                    superHeroesSortedByName.get(j).setRecordMainColor(Parser3.marvelSearchList.get(i).getRecordMainColor());
                    superHeroesSortedByName.get(j).setRecordPartners(Parser3.marvelSearchList.get(i).getRecordPartners());
                    superHeroesSortedByName.get(j).setRecordPowers(Parser3.marvelSearchList.get(i).getRecordPowers());
                    superHeroesSortedByName.get(j).setRecordSecretIdentities(Parser3.marvelSearchList.get(i).getRecordSecretIdentities());
                    superHeroesSortedByName.get(j).setRecordSpecies(Parser3.marvelSearchList.get(i).getRecordSpecies());
                    superHeroesSortedByName.get(j).setRecordSuperName(Parser3.marvelSearchList.get(i).getRecordSuperName());
                    superHeroesSortedByName.get(j).setRecordTeams(Parser3.marvelSearchList.get(i).getRecordTeams());

                    break;
                } else if (!contains)
                {
                    for (String secretIdentity : Parser3.marvelSearchList.get(i).getRecordSecretIdentities())
                    {
                        if (secretIdentity.equals(superHeroesSortedByName.get(j).getName()))
                        {
                            //aggiungo i nuovi campi al superhero
                            contains = true;
                            superHeroesSortedByName.get(j).setRecordAliases(Parser3.marvelSearchList.get(i).getRecordAliases());
                            superHeroesSortedByName.get(j).setRecordAuthors(Parser3.marvelSearchList.get(i).getRecordAuthors());
                            superHeroesSortedByName.get(j).setRecordDescription(Parser3.marvelSearchList.get(i).getRecordDescription());
                            superHeroesSortedByName.get(j).setRecordBackground(Parser3.marvelSearchList.get(i).getRecordBackground());
                            superHeroesSortedByName.get(j).setRecordImagePath(Parser3.marvelSearchList.get(i).getRecordImagePath());
                            superHeroesSortedByName.get(j).setRecordMainColor(Parser3.marvelSearchList.get(i).getRecordMainColor());
                            superHeroesSortedByName.get(j).setRecordPartners(Parser3.marvelSearchList.get(i).getRecordPartners());
                            superHeroesSortedByName.get(j).setRecordPowers(Parser3.marvelSearchList.get(i).getRecordPowers());
                            superHeroesSortedByName.get(j).setRecordSecretIdentities(Parser3.marvelSearchList.get(i).getRecordSecretIdentities());
                            superHeroesSortedByName.get(j).setRecordSpecies(Parser3.marvelSearchList.get(i).getRecordSpecies());
                            superHeroesSortedByName.get(j).setRecordSuperName(Parser3.marvelSearchList.get(i).getRecordSuperName());
                            superHeroesSortedByName.get(j).setRecordTeams(Parser3.marvelSearchList.get(i).getRecordTeams());
                            break;
                        }
                    }
                    for (String secretIdentity : Parser3.marvelSearchList.get(i).getRecordSecretIdentities())
                    {
                        if (secretIdentity.equals(superHeroesSortedByName.get(j).getRealNameWiki()))
                        {
                            //aggiungo i nuovi campi al superhero
                            contains = true;
                            superHeroesSortedByName.get(j).setRecordAliases(Parser3.marvelSearchList.get(i).getRecordAliases());
                            superHeroesSortedByName.get(j).setRecordAuthors(Parser3.marvelSearchList.get(i).getRecordAuthors());
                            superHeroesSortedByName.get(j).setRecordDescription(Parser3.marvelSearchList.get(i).getRecordDescription());
                            superHeroesSortedByName.get(j).setRecordBackground(Parser3.marvelSearchList.get(i).getRecordBackground());
                            superHeroesSortedByName.get(j).setRecordImagePath(Parser3.marvelSearchList.get(i).getRecordImagePath());
                            superHeroesSortedByName.get(j).setRecordMainColor(Parser3.marvelSearchList.get(i).getRecordMainColor());
                            superHeroesSortedByName.get(j).setRecordPartners(Parser3.marvelSearchList.get(i).getRecordPartners());
                            superHeroesSortedByName.get(j).setRecordPowers(Parser3.marvelSearchList.get(i).getRecordPowers());
                            superHeroesSortedByName.get(j).setRecordSecretIdentities(Parser3.marvelSearchList.get(i).getRecordSecretIdentities());
                            superHeroesSortedByName.get(j).setRecordSpecies(Parser3.marvelSearchList.get(i).getRecordSpecies());
                            superHeroesSortedByName.get(j).setRecordSuperName(Parser3.marvelSearchList.get(i).getRecordSuperName());
                            superHeroesSortedByName.get(j).setRecordTeams(Parser3.marvelSearchList.get(i).getRecordTeams());
                            break;
                        }
                    }
                }
                if (contains) break;
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
            Log.d("TAG", "new: " + superHero.getName());*/

        /*for (SuperHero superHero : superHeroesSortedByName)
        {
            if (superHero.getName().equals("Armor (Hisako Ichiki)"))
            {
                //Log.d("TAG", superHero.toString());
                Log.d("TAG", "name: " + superHero.getName());
                Log.d("TAG", "Abilities: " + superHero.getAbilitiesWiki());
                Log.d("TAG", "Weapons: " + superHero.getWeaponsWiki());
                Log.d("TAG", "Paraphernalia: " + superHero.getParaphernaliaWiki());
            }
        }*/



        /*LEGGO IL TXT CON I NOMI DEI PERSONAGGI, PRELEVO LE ISTANZE SUPERHERO CORRISPONDENTI E LE
        AGGIUNGO NELLA LISTA DI SUPERHERO CHE HO. ORDINO IL TUTTO PER NOME E LI AGGIUNGO IN UNA LISTA FINALE*/
      /*  BufferedReader reader = null;
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
        }*/

        //return Parser3.marvelSearchList;
        //return newCharacters;
        return superHeroesSortedByName;
    }

    @Override
    protected void onPostExecute(ArrayList<SuperHero> superHeroes)
    {
        if (myComponent != null)
            //myComponent.onAddSuperHeroes(finalCharacters);
            myComponent.onAddSuperHeroes(superHeroes);
    }
}
