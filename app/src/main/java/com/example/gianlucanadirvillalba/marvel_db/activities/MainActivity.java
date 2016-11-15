package com.example.gianlucanadirvillalba.marvel_db.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private Snackbar mLoadSnackbar;
    private Snackbar mNoServerSnackbar;
    private Snackbar mFullSnackbar;
    private int mPageCount;
    private boolean isScrolled = true;
    private boolean isFull;


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
            public void onScrollStateChanged(AbsListView absListView, int scrollState)
            {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstItem, int visibleItemCount, int totalItems)
            {
                int total = firstItem + visibleItemCount;
                //Toast.makeText(MainActivity.this, "End of the list", Toast.LENGTH_SHORT).show();
                //if (mPageCount != 0 && total == totalItems && isScrolled)
                if (total == totalItems && isScrolled)
                {
                    //Toast.makeText(MainActivity.this, "Loading Superheroes", Toast.LENGTH_SHORT).show();
                    setLoadSnackbar();
                    if (mPageCount > 0) mLoadSnackbar.show();
                    setUpRequest(15, mPageCount * 15); //15
                    isScrolled = false;
                }
            }
        });
        //mRecyclerView = (RecyclerView) findViewById(R.id.charactersList);
        //mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

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
                if (mCharactersList.isEmpty())
                {
                    isScrolled = false;
                    mLoadSnackbar.dismiss();
                    isFull = true;
                } else isScrolled = true;

                //mAdapter.setData(mCharactersList);
                if (mPageCount == 0)
                {
                    mGridAdapter.setData(mCharactersList);
                    mLoadSnackbar.dismiss();
                } else
                {
                    mGridAdapter.addNewData(mCharactersList);
                    mLoadSnackbar.dismiss();
                }
                mPageCount++;
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                mLoadSnackbar.dismiss();
                setNoServerSnackbar();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.refresh)
        {
            if (!isFull)
            {
                mLoadSnackbar.show();
                setUpRequest(15, mPageCount * 15);
            } else setFullSnackbar();
        }
        return true;
    }

    private void setLoadSnackbar()
    {
        mLoadSnackbar = Snackbar.make(mGridView, "Loading Superheroes", Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackbarView = (Snackbar.SnackbarLayout) mLoadSnackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(
                MyApplication.getAppContext(), R.color.colorPrimaryDark));
        snackbarView.addView(new ProgressBar(MyApplication.getAppContext()));
    }

    private void setNoServerSnackbar()
    {
        mNoServerSnackbar = Snackbar
                .make(mGridView, "No server response!", Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        mNoServerSnackbar.dismiss();
                        mLoadSnackbar.show();
                        setUpRequest(15, mPageCount * 15);
                    }
                });
        mNoServerSnackbar.setActionTextColor(Color.RED);

        View sbView = mNoServerSnackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        mNoServerSnackbar.show();
    }

    private void setFullSnackbar()
    {
        mFullSnackbar = Snackbar.make(mGridView, "All Superheroes are shown!", Snackbar.LENGTH_SHORT);
        Snackbar.SnackbarLayout snackbarView = (Snackbar.SnackbarLayout) mFullSnackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(
                MyApplication.getAppContext(), R.color.colorPrimaryDark));
        mFullSnackbar.show();
    }
}
