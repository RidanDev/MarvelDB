package com.example.gianlucanadirvillalba.marvel_db.json;

import android.widget.Toast;

import com.example.gianlucanadirvillalba.marvel_db.extras.MyApplication;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_CHARACTERS;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_DATA;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_DESCRIPTION;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_ID;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_NAME;

/**
 * Created by gianlucanadirvillalba on 10/11/2016.
 */

public class Parser
{
    public static ArrayList<SuperHero> parseJsonCharacters(JSONObject response)
    {
        ArrayList<SuperHero> charactersList = new ArrayList<>();
        if (response != null || response.length() > 0)
        {
            try
            {
                Toast.makeText(MyApplication.getAppContext(), "Response", Toast.LENGTH_SHORT).show();
                JSONObject jsonObject = response.getJSONObject(KEY_DATA);
                JSONArray array = (JSONArray) jsonObject.get(KEY_CHARACTERS);
                //TODO gestire i vari controlli
                for (int i = 0; i < array.length(); i++)
                {
                    long id = -1;
                    int comicsNumber = -1;
                    String name = "NA";
                    String description = "NA";
                    String imagePath = "NA";

                    JSONObject currentCharacter = array.getJSONObject(i);
                    if (Utils.contains(currentCharacter, KEY_ID))
                        id = currentCharacter.getLong(KEY_ID);
                    if (Utils.contains(currentCharacter, KEY_NAME))
                        name = currentCharacter.getString(KEY_NAME);
                    if (Utils.contains(currentCharacter, KEY_DESCRIPTION))
                        description = currentCharacter.getString(KEY_DESCRIPTION);

                    imagePath = (String) currentCharacter.getJSONObject("thumbnail").get("path");
//                  comicsNumber = (int) currentCharacter.getJSONObject("comics").get("available");

                    SuperHero superHero = new SuperHero();
                    superHero.setId(id);
                    superHero.setName(name);
                    superHero.setDescription(description);
                    superHero.setImagePath(imagePath+"/portrait_uncanny.jpg"); //portrait_fantastic
                    superHero.setComicsNumber(comicsNumber);

                    //if (id != -1 && !name.equals("NA"))
                        charactersList.add(superHero);

                }
            } catch (JSONException e)
            {
                e.printStackTrace();
                Toast.makeText(MyApplication.getAppContext(), "No Server Response", Toast.LENGTH_SHORT).show();
            }

        }
        return charactersList;
    }
}
