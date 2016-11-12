package com.example.gianlucanadirvillalba.marvel_db.json;

import com.example.gianlucanadirvillalba.marvel_db.extras.UrlEndPoints;

/**
 * Created by gianlucanadirvillalba on 10/11/2016.
 */

//TODO eccezione con limit < 0 e limit > 100
public class EndPoints
{
    public static String getRequestUrl(int limit)
    {
        return UrlEndPoints.URL_MARVEL_CHARACTERS_ALL
                + UrlEndPoints.URL_CHAR_QUESTION
                + UrlEndPoints.LIMIT + limit
                + UrlEndPoints.URL_CHAR_AMPERSAND
                + UrlEndPoints.TIMESTAMP
                + UrlEndPoints.URL_CHAR_AMPERSAND
                + UrlEndPoints.API_KEY
                + UrlEndPoints.URL_CHAR_AMPERSAND
                + UrlEndPoints.HASH;
    }
}
