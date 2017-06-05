package com.example.gianlucanadirvillalba.marvel_db.extras;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gianlucanadirvillalba.marvel_db.adapters.RecyclerAdapter;
import com.example.gianlucanadirvillalba.marvel_db.json.EndPoints;
import com.example.gianlucanadirvillalba.marvel_db.json.Parser;
import com.example.gianlucanadirvillalba.marvel_db.network.VolleySingleton;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gianlucanadirvillalba on 25/05/2017.
 */

public class SuperHeroesUtils
{
    private static RequestQueue mRequestQueue;
    public static ArrayList<SuperHero> mCharactersList = new ArrayList<>();
    public static boolean isListUsed;
    public static boolean mIsReady = true;

    public static ArrayList<SuperHero> loadSuperHeroes(
            final RecyclerAdapter mRecyclerAdapter, final int mRequestCount, int limit, int offset)
    {
        mIsReady = false;
        mRequestQueue = VolleySingleton.getInstance().getRequestQueue();
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                EndPoints.getRequestUrl(limit, offset), (String) null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                mCharactersList = Parser.parseJsonCharacters(response);
                Log.d("TAG", mCharactersList.size() + "");

                if (!mCharactersList.isEmpty())
                {
                    isListUsed = true;
                    if (mRequestCount == 0) mRecyclerAdapter.setData(mCharactersList);
                    else mRecyclerAdapter.addNewData(mCharactersList);
                    //else mRecyclerAdapter.addNewData(mCharactersList);
                } else
                {
                    isListUsed = false;
                    mIsReady = false;
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        mRequestQueue.add(request);
        mIsReady = true;
        //TODO fare un insert dei personaggi nel database
        return mCharactersList;
    }
}
