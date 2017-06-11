package com.example.gianlucanadirvillalba.marvel_db.json;

import com.example.gianlucanadirvillalba.marvel_db.extras.Constants;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import org.json.JSONArray;
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
            String realNameWiki = Constants.NA;
            String blurbWiki = Constants.NA;
            String aliasesWiki = Constants.NA;
            String identityWiki = Constants.NA;
            String occupationWiki = Constants.NA;
            String citizenshipWiki = Constants.NA;
            String placeOfBirthWiki = Constants.NA;
            String relativesWiki = Constants.NA;
            String groupsWiki = Constants.NA;
            String educationWiki = Constants.NA;
            String heightWiki = Constants.NA;
            String weightWiki = Constants.NA;
            String eyesWiki = Constants.NA;
            String hairWiki = Constants.NA;
            String abilitiesWiki = Constants.NA;
            String weaponsWiki = Constants.NA;
            String paraphernaliaWiki = Constants.NA;
            String powersWiki = Constants.NA;
            String debutWiki = Constants.NA;
            String originWiki = Constants.NA;
            String significantIssuesWiki = Constants.NA;
            ArrayList<String> categoriesWiki = new ArrayList<>();

            JSONObject jsonWiki = response.getJSONObject(KEY_WIKI);
            JSONObject currentCharacter = response;


            JSONArray categoriesArray = jsonWiki.getJSONArray("categories");

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
            if(Utils.contains(jsonWiki, "real_name"))
                realNameWiki = jsonWiki.getString("real_name");
            if(Utils.contains(jsonWiki, "blurb"))
                blurbWiki = jsonWiki.getString("blurb");
            if(Utils.contains(jsonWiki, "aliases"))
                aliasesWiki = jsonWiki.getString("aliases");
            if(Utils.contains(jsonWiki, "identity"))
                identityWiki = jsonWiki.getString("identity");
            if(Utils.contains(jsonWiki, "occupation"))
                occupationWiki = jsonWiki.getString("occupation");
            if(Utils.contains(jsonWiki, "citizenship"))
                citizenshipWiki = jsonWiki.getString("citizenship");
            if(Utils.contains(jsonWiki, "place_of_birth"))
                placeOfBirthWiki = jsonWiki.getString("place_of_birth");
            if(Utils.contains(jsonWiki, "relatives"))
                relativesWiki = jsonWiki.getString("relatives");
            if(Utils.contains(jsonWiki, "groups"))
                groupsWiki = jsonWiki.getString("groups");
            if(Utils.contains(jsonWiki, "education"))
                educationWiki = jsonWiki.getString("education");
            if(Utils.contains(jsonWiki, "height"))
                heightWiki = jsonWiki.getString("height");
            if(Utils.contains(jsonWiki, "weight"))
                weightWiki = jsonWiki.getString("weight");
            if(Utils.contains(jsonWiki, "eyes"))
                eyesWiki = jsonWiki.getString("eyes");
            if(Utils.contains(jsonWiki, "hair"))
                hairWiki = jsonWiki.getString("hair");
            if(Utils.contains(jsonWiki, "abilities"))
                abilitiesWiki = jsonWiki.getString("abilities");
            if(Utils.contains(jsonWiki, "weapons"))
                weaponsWiki = jsonWiki.getString("weapons");
            if(Utils.contains(jsonWiki, "paraphernalia"))
                paraphernaliaWiki = jsonWiki.getString("paraphernalia");
            if(Utils.contains(jsonWiki, "powers"))
                powersWiki = jsonWiki.getString("powers");
            if(Utils.contains(jsonWiki, "debut"))
                debutWiki = jsonWiki.getString("debut");
            if(Utils.contains(jsonWiki, "origin"))
                originWiki = jsonWiki.getString("origin");
            if(Utils.contains(jsonWiki, "significant_issues"))
                significantIssuesWiki = jsonWiki.getString("significant_issues");
            if(Utils.contains(currentCharacter, "categories"))
                for(int i = 0; i < categoriesArray.length(); i++)
                    categoriesWiki.add(categoriesArray.getString(i));




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
                //if (superHero.getName().equals("Alpha Flight)")) {}

                if (superHero.getName().equals("Kylun"))
                    superHero.setImagePath(imagePath + Constants.PORTRAIT_UNCANNY + imageType);
                else superHero.setImagePath(imagePath + Constants.PORTRAIT_FANTASTIC + imageType);
                superHero.setComicsNumber(comicsNumber);
                superHero.setUniverseWiki(universeWiki);
                superHero.setBioTextWiki(bioTextWiki);
                superHero.setBioWiki(bioWiki);
                superHero.setRealNameWiki(realNameWiki);
                superHero.setBlurbWiki(blurbWiki);
                superHero.setAliasesWiki(aliasesWiki);
                superHero.setIdentityWiki(identityWiki);
                superHero.setOccupationWiki(occupationWiki);
                superHero.setCitizenshipWiki(citizenshipWiki);
                superHero.setPlaceOfBirthWiki(placeOfBirthWiki);
                superHero.setRelativesWiki(relativesWiki);
                superHero.setGroupsWiki(groupsWiki);
                superHero.setEducationWiki(educationWiki);
                superHero.setHeightWiki(heightWiki);
                superHero.setWeightWiki(weightWiki);
                superHero.setEyesWiki(eyesWiki);
                superHero.setHairWiki(hairWiki);
                superHero.setAbilitiesWiki(abilitiesWiki);
                superHero.setWeaponsWiki(weaponsWiki);
                superHero.setParaphernaliaWiki(paraphernaliaWiki);
                superHero.setPowersWiki(powersWiki);
                superHero.setDebutWiki(debutWiki);
                superHero.setOriginWiki(originWiki);
                superHero.setSignificantIssuesWiki(significantIssuesWiki);
                superHero.setCategoriesWiki(categoriesWiki);
                charactersList.add(superHero);
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
