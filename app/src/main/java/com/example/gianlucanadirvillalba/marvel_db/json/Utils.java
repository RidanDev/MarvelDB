package com.example.gianlucanadirvillalba.marvel_db.json;

import org.json.JSONObject;

/**
 * Created by gianlucanadirvillalba on 10/11/2016.
 */

public class Utils
{
    public static boolean contains(JSONObject jsonObject, String key)
    {
        return jsonObject != null
                && jsonObject.has(key)
                && !jsonObject.isNull(key) ? true : false;
    }
}
