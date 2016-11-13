package com.example.gianlucanadirvillalba.marvel_db.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gianlucanadirvillalba.marvel_db.R;
import com.example.gianlucanadirvillalba.marvel_db.adapters.GridAdapter;
import com.example.gianlucanadirvillalba.marvel_db.adapters.ListAdapter;
import com.example.gianlucanadirvillalba.marvel_db.extras.MyApplication;
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
    private ListAdapter mAdapter;
    private GridAdapter mGridAdapter;
    private GridView mGridView;
    private Snackbar mSnackbar;
    private int mPageCount;
    private boolean isScrolled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPageCount = 0;
        setUpDrawer();
        setUpRecyclerView();
        //setUpRequest(100, 0);
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

    //TODO potrei far decidere di mostrare i personaggi a lista o a griglia
    private void setUpRecyclerView()
    {
        //mAdapter = new CharactersAdapter(this);
        mGridAdapter = new GridAdapter(this);
        mGridView = (GridView) findViewById(R.id.grid_view);
        mGridView.setAdapter(mGridAdapter);
        mGridView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {}

            @Override
            public void onScroll(AbsListView absListView, int firstItem, int visibleItemCount, int totalItems)
            {
                int total = firstItem + visibleItemCount;
                //Toast.makeText(MainActivity.this, "End of the list", Toast.LENGTH_SHORT).show();
                //if (mPageCount != 0 && total == totalItems && isScrolled)
                if (total == totalItems && isScrolled)
                {
                    //Toast.makeText(MainActivity.this, "Loading Superheroes", Toast.LENGTH_SHORT).show();
                    mSnackbar = Snackbar.make(mGridView, "Loading Superheroes", Snackbar.LENGTH_INDEFINITE);
                    Snackbar.SnackbarLayout snackbarView = (Snackbar.SnackbarLayout) mSnackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(
                            MyApplication.getAppContext(), R.color.colorPrimaryDark));
                    snackbarView.addView(new ProgressBar(MyApplication.getAppContext()));
                    mSnackbar.show();
                    setUpRequest(100, mPageCount * 100);
                    isScrolled = false;
                }
            }
        });
        //mRecyclerView = (RecyclerView) findViewById(R.id.charactersList);
        //mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //TODO gestire caricamento degli altri personaggi dopo i primi X della lista
    private void setUpRequest(int limit, int offset)
    {
        mRequestQueue = VolleySingleton.getInstance().getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                EndPoints.getRequestUrl(limit, offset), (String) null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                mCharactersList = Parser.parseJsonCharacters(response);
                //mAdapter.setData(mCharactersList);
                if (mPageCount == 0)
                {
                    mGridAdapter.setData(mCharactersList);
                    mSnackbar.dismiss();
                }
                else
                {
                    mGridAdapter.addNewData(mCharactersList);
                    mSnackbar.dismiss();
                }
                mPageCount++;
                isScrolled = true;
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(MainActivity.this, "No server response (main)", Toast.LENGTH_LONG).show();
                mSnackbar.dismiss();
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
