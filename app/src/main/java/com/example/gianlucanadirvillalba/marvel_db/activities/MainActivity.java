package com.example.gianlucanadirvillalba.marvel_db.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gianlucanadirvillalba.marvel_db.R;
import com.example.gianlucanadirvillalba.marvel_db.adapters.CharactersAdapter;
import com.example.gianlucanadirvillalba.marvel_db.fragments.NavigationDrawerFragment;
import com.example.gianlucanadirvillalba.marvel_db.json.EndPoints;
import com.example.gianlucanadirvillalba.marvel_db.json.Parser;
import com.example.gianlucanadirvillalba.marvel_db.network.VolleySingleton;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private List<SuperHero> mCharactersList = new ArrayList<>();
    private RequestQueue mRequestQueue;
    private CharactersAdapter mAdapter;
    //private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpDrawer();
        setUpRecyclerView();
        setUpRequest();
    }

    private void setUpDrawer()
    {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true); //?
        NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navigationDrawerFragment.setUp(mToolbar, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    private void setUpRecyclerView()
    {
        mAdapter = new CharactersAdapter(this);
        //mGridView = (GridView) findViewById(R.id.grid_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.charactersList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    //TODO gestire caricamento degli altri personaggi dopo i primi 20 della lista
    private void setUpRequest()
    {
        mRequestQueue= VolleySingleton.getInstance().getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                EndPoints.getRequestUrl(20), (String) null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                mCharactersList = Parser.parseJsonCharacters(response);
                mAdapter.setData(mCharactersList);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        mRequestQueue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
