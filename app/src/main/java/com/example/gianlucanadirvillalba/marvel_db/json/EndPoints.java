package com.example.gianlucanadirvillalba.marvel_db.json;

import com.example.gianlucanadirvillalba.marvel_db.extras.UrlEndPoints;

/**
 * Created by gianlucanadirvillalba on 10/11/2016.
 */

//TODO controllare se è corretto l'uso del timestamp sul sito della marvel (forse deve essere random)
public class EndPoints
{
    public static String getRequestUrl(int limit, int offset)
    {
        return UrlEndPoints.URL_MARVEL_CHARACTERS_ALL
                + UrlEndPoints.URL_CHAR_QUESTION
                + UrlEndPoints.URL_CHAR_AMPERSAND
                + UrlEndPoints.TIMESTAMP
                + UrlEndPoints.URL_CHAR_AMPERSAND
                + UrlEndPoints.LIMIT + limit
                + UrlEndPoints.URL_CHAR_AMPERSAND
                + UrlEndPoints.OFFSET + offset
                + UrlEndPoints.URL_CHAR_AMPERSAND
                + UrlEndPoints.API_KEY
                + UrlEndPoints.URL_CHAR_AMPERSAND
                + UrlEndPoints.HASH;
    }
}
