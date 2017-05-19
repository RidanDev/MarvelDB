package com.example.gianlucanadirvillalba.marvel_db.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gianlucanadirvillalba.marvel_db.R;
import com.example.gianlucanadirvillalba.marvel_db.adapters.RecyclerAdapter;
import com.example.gianlucanadirvillalba.marvel_db.extras.MyApplication;
import com.example.gianlucanadirvillalba.marvel_db.fragments.NavigationDrawerFragment;
import com.example.gianlucanadirvillalba.marvel_db.json.EndPoints;
import com.example.gianlucanadirvillalba.marvel_db.json.Parser;
import com.example.gianlucanadirvillalba.marvel_db.network.VolleySingleton;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;
import com.example.gianlucanadirvillalba.marvel_db.task.TaskLoadCharacters;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private List<SuperHero> mCharactersList = new ArrayList<>();
    private RequestQueue mRequestQueue;
    private RecyclerAdapter mAdapter;
    private Snackbar mLoadSnackbar;
    private Snackbar mNoServerSnackbar;
    private Snackbar mFullSnackbar;
    private int mPageCount;
    private boolean isScrolled = true;
    private boolean isFull;
    private GridLayoutManager mGridLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisibleItems;
    private int limit = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPageCount = 0;
        setUpDrawer();
        setUpAsyncTask();
        setUpRecyclerView();
        setUpRequest(limit, mPageCount);
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

    private void setUpAsyncTask()
    {
        new TaskLoadCharacters().execute();
    }

    //TODO potrei far decidere di mostrare i personaggi a lista o a griglia
    private void setUpRecyclerView()
    {
        mAdapter = new RecyclerAdapter(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.charactersList);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if (dy > 0)
                {
                    visibleItemCount = mGridLayoutManager.getChildCount();
                    totalItemCount = mGridLayoutManager.getItemCount();
                    pastVisibleItems = mGridLayoutManager.findFirstVisibleItemPosition();
                    int total = visibleItemCount + pastVisibleItems;
                    if (total >= totalItemCount && isScrolled)
                    {
                        //Toast.makeText(MainActivity.this, "LAST ITEM", Toast.LENGTH_SHORT).show();
                        setLoadSnackbar();
                        if (mPageCount > 0) mLoadSnackbar.show();
                        setUpRequest(limit, mPageCount * limit);
                        isScrolled = false;
                    }
                }
            }
        });
        setLoadSnackbar();
        mLoadSnackbar.show();
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
                    //isFull = true;
                } else isScrolled = true;

                if (mPageCount == 0)
                {
                    mAdapter.setData(mCharactersList);
                    Toast.makeText(MainActivity.this, "SET DATA: " + mCharactersList.size(), Toast.LENGTH_SHORT).show();
                    mLoadSnackbar.dismiss();
                } else
                {
                    mAdapter.addNewData(mCharactersList);
                    Toast.makeText(MainActivity.this, "NEW DATA: " + mCharactersList.size(), Toast.LENGTH_SHORT).show();
                    mLoadSnackbar.dismiss();
                }
                mPageCount++;
                mLoadSnackbar.dismiss();

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

    //TODO ad ogni click deve refreshare quelli presenti (cancellarli e richiederli)
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.refresh)
        {
            if (!isFull)
            {
//                mLoadSnackbar.show();
//                mGridAdapter.setData(mCharactersList);
//                mGridAdapter.notifyDataSetChanged();
//                mLoadSnackbar.dismiss();
                //setUpRequest(15, mPageCount);
            } else setFullSnackbar();
        }
        return true;
    }

    private void setLoadSnackbar()
    {
        mLoadSnackbar = Snackbar.make(mRecyclerView, "Loading Superheroes", Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackbarView = (Snackbar.SnackbarLayout) mLoadSnackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(
                MyApplication.getAppContext(), R.color.colorPrimaryDark));
        snackbarView.addView(new ProgressBar(MyApplication.getAppContext()));
    }

    private void setNoServerSnackbar()
    {
        mNoServerSnackbar = Snackbar
                .make(mRecyclerView, "No server response!", Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        mNoServerSnackbar.dismiss();
                        mLoadSnackbar.show();
                        setUpRequest(limit, mPageCount * limit);
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
        mFullSnackbar = Snackbar.make(mRecyclerView, "All Superheroes are shown!", Snackbar.LENGTH_SHORT);
        Snackbar.SnackbarLayout snackbarView = (Snackbar.SnackbarLayout) mFullSnackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(
                MyApplication.getAppContext(), R.color.colorPrimaryDark));
        mFullSnackbar.show();
    }
}
