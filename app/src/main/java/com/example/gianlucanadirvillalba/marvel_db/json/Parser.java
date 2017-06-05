package com.example.gianlucanadirvillalba.marvel_db.json;

import android.widget.Toast;

import com.example.gianlucanadirvillalba.marvel_db.extras.Constants;
import com.example.gianlucanadirvillalba.marvel_db.extras.MyApplication;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_AVAILABLE;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_CHARACTERS;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_COMICS;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_DATA;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_DESCRIPTION;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_EXTENSION;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_ID;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_NAME;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_PATH;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_THUMBNAIL;

/**
 * Created by gianlucanadirvillalba on 10/11/2016.
 *
 * Parser dei json presi da marvel.com utilizzando le API
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
                //Toast.makeText(MyApplication.getAppContext(), "Response", Toast.LENGTH_SHORT).show();
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
                    String imageType = "";

                    JSONObject currentCharacter = array.getJSONObject(i);
                    if (Utils.contains(currentCharacter, KEY_ID))
                        id = currentCharacter.getLong(KEY_ID);
                    if (Utils.contains(currentCharacter, KEY_NAME))
                        name = currentCharacter.getString(KEY_NAME);
                    if (Utils.contains(currentCharacter, KEY_DESCRIPTION))
                        description = currentCharacter.getString(KEY_DESCRIPTION);

                    imagePath = (String) currentCharacter.getJSONObject(KEY_THUMBNAIL).get(KEY_PATH);
                    comicsNumber = (int) currentCharacter.getJSONObject(KEY_COMICS).get(KEY_AVAILABLE);
                    //TODO potrei decidere di elminare tutti i personaggi di tipo "Ultimate"
/*                    if (imagePath.equals(Constants.IMAGE_NOT_FOUND_1) || imagePath.equals(Constants.IMAGE_NOT_FOUND_2))
                    {
                        imageType = Constants.DOT + (String) currentCharacter.getJSONObject(KEY_THUMBNAIL).get(KEY_EXTENSION);
                        Log.d("TAG", "Image Path: "+ imagePath + Constants.PORTRAIT_FANTASTIC + imageType);
                    }*/
                    if (!(imagePath.equals(Constants.IMAGE_NOT_FOUND_1)
                            || imagePath.equals(Constants.IMAGE_NOT_FOUND_2)
                            || comicsNumber == 0
                            || name.contains(Constants.ULTIMATE)
                            || name.contains(Constants.HOUSE_OF_M)
                            || name.contains(Constants.AGE_OF_APOCALYPSE)
                            || name.contains(Constants.PET_AVENGERS)))
                    {
                        imageType = Constants.DOT + (String) currentCharacter.getJSONObject(KEY_THUMBNAIL).get(KEY_EXTENSION);
                        SuperHero superHero = new SuperHero();
                        superHero.setId(id);
                        superHero.setName(name);
                        superHero.setDescription(description);
                        superHero.setImagePath(imagePath + Constants.PORTRAIT_FANTASTIC + imageType); //grid
                        //Log.d("TAG", "Image Path: "+ imagePath + Constants.PORTRAIT_FANTASTIC +imageType);
                        //superHero.setImagePath(imagePath + Constants.PORTRAIT_UNCANNY + imageType);     //linear
                        superHero.setComicsNumber(comicsNumber);

                        //if (id != -1 && !name.equals("NA"))
                        charactersList.add(superHero);
                    }
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
