package com.example.gianlucanadirvillalba.marvel_db.json;

import com.example.gianlucanadirvillalba.marvel_db.extras.Constants;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_DESCRIPTION;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_NAME;

/**
 * Created by gianlucanadirvillalba on 30/05/2017.
 * <p>
 * Parser dei json in assets/records
 */

public class Parser3
{
    public static ArrayList<SuperHero> marvelSearchList = new ArrayList<>();

    public static void parseJsonCharacters(JSONObject response)
    {
        try
        {
            String recordName = Constants.NA;
            String recordDescription = Constants.NA;
            String recordImagePath = Constants.NA;
            String recordBackground = Constants.NA;
            int recordPageviewCount = Constants.NULL_INT;
            String recordMainColor = Constants.NA;
            String recordSuperName = Constants.NA;
            ArrayList<String> recordAliases = new ArrayList<>();
            ArrayList<String> recordAuthors = new ArrayList<>();
            ArrayList<String> recordPartners = new ArrayList<>();
            ArrayList<String> recordPowers = new ArrayList<>();
            ArrayList<String> recordSecretIdentities = new ArrayList<>();
            ArrayList<String> recordSpecies = new ArrayList<>();
            ArrayList<String> recordTeams = new ArrayList<>();

            JSONObject currentCharacter = response;
            JSONArray aliasesArray = (JSONArray) currentCharacter.get("aliases");
            JSONArray authorsArray = (JSONArray) currentCharacter.get("authors");
            JSONArray partnersArray = (JSONArray) currentCharacter.get("partners");
            JSONArray powersArray = (JSONArray) currentCharacter.get("powers");
            JSONArray secretIdentitiesArray = (JSONArray) currentCharacter.get("secretIdentities");
            JSONArray speciesArray = (JSONArray) currentCharacter.get("species");
            JSONArray teamsArray = (JSONArray) currentCharacter.get("teams");

            if(Utils.contains(currentCharacter, "aliases"))
                for(int i = 0; i < aliasesArray.length(); i++)
                    recordAliases.add(aliasesArray.getString(i));
            if(Utils.contains(currentCharacter, "authors"))
                for(int i = 0; i < authorsArray.length(); i++)
                    recordAuthors.add(authorsArray.getString(i));
            if(Utils.contains(currentCharacter, "partners"))
                for(int i = 0; i < partnersArray.length(); i++)
                    recordPartners.add(partnersArray.getString(i));
            if(Utils.contains(currentCharacter, "powers"))
                for(int i = 0; i < powersArray.length(); i++)
                    recordPowers.add(powersArray.getString(i));
            if(Utils.contains(currentCharacter, "secretIdentities"))
                for(int i = 0; i < secretIdentitiesArray.length(); i++)
                    recordSecretIdentities.add(secretIdentitiesArray.getString(i));
            if(Utils.contains(currentCharacter, "species"))
                for(int i = 0; i < speciesArray.length(); i++)
                    recordSpecies.add(speciesArray.getString(i));
            if(Utils.contains(currentCharacter, "teams"))
                for(int i = 0; i < teamsArray.length(); i++)
                    recordTeams.add(teamsArray.getString(i));

            if(Utils.contains(currentCharacter, KEY_DESCRIPTION))
                recordDescription = currentCharacter.getString(KEY_DESCRIPTION);
            if (Utils.contains(currentCharacter, KEY_NAME))
                recordName = currentCharacter.getString(KEY_NAME);
            if (Utils.contains(currentCharacter.getJSONObject("images"), "thumbnail"))
                recordImagePath = (String) currentCharacter.getJSONObject("images").get("thumbnail");
            if (Utils.contains(currentCharacter.getJSONObject("images"), "background"))
                recordBackground = (String) currentCharacter.getJSONObject("images").get("background");
            if (Utils.contains(currentCharacter.getJSONObject("ranking"), "pageviewCount"))
                recordPageviewCount = (int) currentCharacter.getJSONObject("ranking").get("pageviewCount");
            if(Utils.contains(currentCharacter, "mainColor"))
                recordMainColor = (String) currentCharacter.getJSONObject("mainColor").get("hexa");
            if(Utils.contains(currentCharacter, "superName"))
                recordSuperName = currentCharacter.getString("superName");

            if (!(recordImagePath.equals(Constants.IMAGE_NOT_FOUND_1)
                    || recordImagePath.equals(Constants.IMAGE_NOT_FOUND_2)
                    || recordPageviewCount < 1000))
            {
                SuperHero superHero = new SuperHero();
                //superHero.setRecordName(recordName);
                superHero.setName(recordName);
                superHero.setRecordImagePath(recordImagePath);
                superHero.setRecordPageViewCount(recordPageviewCount);
                superHero.setRecordAliases(recordAliases);
                superHero.setRecordAuthors(recordAuthors);
                superHero.setRecordDescription(recordDescription);
                superHero.setRecordPartners(recordPartners);
                superHero.setRecordPowers(recordPowers);
                superHero.setRecordMainColor(recordMainColor);
                superHero.setRecordSecretIdentities(recordSecretIdentities);
                superHero.setRecordSpecies(recordSpecies);
                superHero.setRecordSuperName(recordSuperName);
                superHero.setRecordTeams(recordTeams);
                superHero.setRecordBackground(recordBackground);

                /*if(superHero.getName().equals("Iron Man"))
                {
                    for(String aliases : superHero.getRecordAliases())
                        Log.d("TAG", "alias: " + aliases);
                    for(String authors : superHero.getRecordAuthors())
                        Log.d("TAG", "author: " + authors);
                    for(String partners : superHero.getRecordPartners())
                        Log.d("TAG", "partner: " + partners);
                    for(String powers : superHero.getRecordPowers())
                        Log.d("TAG", "power: " + powers);
                    for(String identities : superHero.getRecordSecretIdentities())
                        Log.d("TAG", "identity: " + identities);
                    for(String species : superHero.getRecordSpecies())
                        Log.d("TAG", "species: " + species);
                    for(String teams : superHero.getRecordTeams())
                        Log.d("TAG", "team: " + teams);
                    Log.d("TAG", "mainColor: " + superHero.getRecordMainColor());
                    Log.d("TAG", "superName: " + superHero.getRecordSuperName());
                    Log.d("TAG", "background: " + superHero.getRecordBackground());
                }*/
                marvelSearchList.add(superHero);
            }


        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
