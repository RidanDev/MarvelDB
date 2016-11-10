package com.example.gianlucanadirvillalba.marvel_db.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gianlucanadirvillalba.marvel_db.R;
import com.example.gianlucanadirvillalba.marvel_db.adapters.CharactersAdapter;
import com.example.gianlucanadirvillalba.marvel_db.fragments.NavigationDrawerFragment;
import com.example.gianlucanadirvillalba.marvel_db.network.VolleySingleton;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    public static final String URL_MARVEL_REQUEST =
            "https://gateway.marvel.com/v1/public/characters?&ts=1&apikey=db1112584ea107626ad221639a9829bd&hash=763c3ded3ae7a339024d877a5c25068e";

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
        CharactersAdapter adapter = new CharactersAdapter(this, getData());
        mRecyclerView = (RecyclerView) findViewById(R.id.charactersList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpRequest()
    {
        RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();
        StringRequest request = new StringRequest(Request.Method.GET, URL_MARVEL_REQUEST, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        requestQueue.add(request);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private List<SuperHero> getData()
    {
        List<SuperHero> list = new ArrayList<>();
        for (int i = 0; i < 20; i++)
        {
            SuperHero superHero = new SuperHero();
            superHero.setImage(R.mipmap.ic_launcher);
            superHero.setName("Iron Man");
            list.add(superHero);
        }
        return list;
    }
}
