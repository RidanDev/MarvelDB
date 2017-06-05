package com.example.gianlucanadirvillalba.marvel_db.json;

import com.example.gianlucanadirvillalba.marvel_db.extras.Constants;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_AVAILABLE;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_BIO_TEXT_WIKI;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_BIO_WIKI;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_COMICS;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_DESCRIPTION;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_EXTENSION;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_ID;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_NAME;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_PATH;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_THUMBNAIL;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_UNIVERSE_WIKI;
import static com.example.gianlucanadirvillalba.marvel_db.extras.Keys.EndPointsSuperHeroes.KEY_WIKI;

/**
 * Created by gianlucanadirvillalba on 28/05/2017.
 *
 * Parser dei json in assets/characters
 */

public class Parser2
{
    public static ArrayList<SuperHero> charactersList = new ArrayList<>();

    public static void parseJsonCharacters(JSONObject response)
    {
        try
        {
            //TODO non gestisco le eccezioni a dovere (err: no value for wiki)
            long id = Constants.NULL_INT;
            int comicsNumber = Constants.NULL_INT;
            String name = Constants.NA;
            String description = Constants.NA;
            String imagePath = Constants.NA;
            String imageType = Constants.NA;
            String universeWiki = Constants.NA;
            String bioTextWiki = Constants.NA;
            String bioWiki = Constants.NA;

            JSONObject jsonWiki = response.getJSONObject(KEY_WIKI);
            JSONObject currentCharacter = response;

            if (Utils.contains(currentCharacter, KEY_ID))
                id = currentCharacter.getLong(KEY_ID);
            if (Utils.contains(currentCharacter, KEY_NAME))
                name = currentCharacter.getString(KEY_NAME);
            if (Utils.contains(currentCharacter, KEY_DESCRIPTION))
                description = currentCharacter.getString(KEY_DESCRIPTION);
            if (Utils.contains(jsonWiki, KEY_UNIVERSE_WIKI))
                universeWiki = jsonWiki.getString(KEY_UNIVERSE_WIKI);
            if (Utils.contains(jsonWiki, KEY_BIO_TEXT_WIKI))
                bioTextWiki = jsonWiki.getString(KEY_BIO_TEXT_WIKI);
            if (Utils.contains(jsonWiki, KEY_BIO_WIKI))
                bioWiki = jsonWiki.getString(KEY_BIO_WIKI);

            imagePath = (String) currentCharacter.getJSONObject(KEY_THUMBNAIL).get(KEY_PATH);
            comicsNumber = (int) currentCharacter.getJSONObject(KEY_COMICS).get(KEY_AVAILABLE);

            if (!(imagePath.equals(Constants.IMAGE_NOT_FOUND_1)
                    || imagePath.equals(Constants.IMAGE_NOT_FOUND_2)
                    || name.contains(Constants.ULTIMATE)
                    || name.contains(Constants.HOUSE_OF_M)
                    || name.contains(Constants.AGE_OF_APOCALYPSE)
                    || name.contains(Constants.PET_AVENGERS)
                    || name.contains(Constants.WEAPON_OMEGA))) //uguale a wild child ma con meno comics
                    //|| comicsNumber == 0
            {
                imageType = Constants.DOT + currentCharacter.getJSONObject(KEY_THUMBNAIL).get(KEY_EXTENSION);
                SuperHero superHero = new SuperHero();
                superHero.setId(id);
                superHero.setName(name);
                superHero.setDescription(description);
                if (superHero.getName().equals("Kylun"))
                    superHero.setImagePath(imagePath + Constants.PORTRAIT_UNCANNY + imageType);
                else superHero.setImagePath(imagePath + Constants.PORTRAIT_FANTASTIC + imageType);
                superHero.setComicsNumber(comicsNumber);
                superHero.setUniverseWiki(universeWiki);
                superHero.setBioTextWiki(bioTextWiki);
                superHero.setBioWiki(bioWiki);
                charactersList.add(superHero);
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
