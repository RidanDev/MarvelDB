package com.example.gianlucanadirvillalba.marvel_db.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.gianlucanadirvillalba.marvel_db.R;
import com.example.gianlucanadirvillalba.marvel_db.network.VolleySingleton;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import java.util.Collections;
import java.util.List;

/**
 * Created by gianlucanadirvillalba on 11/11/2016.
 *
 * NON SFRUTTA IL RIUTILIZZO DELLE VISTE!!!(NO RECYCLER)
 * Il GridAdapter è l'Adapter che si occupa di prelevare dei dati e rappresentarli
 * in ogni custom_grid_character della GridView.
 * La GridView è solamente la View che rappresenta i miei characters e che è in attesa
 * di prendere in input un Adapter per poter legare i dati con la parte grafica
 * Devo quindi riempire la custom_grid_character che viene quindi passata in input al ViewHolder
 * attraverso il metodo getView
 * Il ViewHolder prende le singole View (ImageView e TextView) in cui rappresentare i dati
 * prelevati all'interno della lista "data"
 * la lista "data" viene riempita subito attraverso il metodo setData con i dati ottenuti
 * dal metodo setUpRequest chiamato nel MainActivity.
 * la lista "data" viene riempita a tempo di esecuzione attraverso il metodo addNewData
 * ogni volta che scrollo la GridView
 */

public class GridAdapter extends BaseAdapter
{
    private final Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<SuperHero> data = Collections.emptyList();
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;

    public GridAdapter(Context context)
    {
        mLayoutInflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
        this.mContext = context;
    }

    public void setData(List<SuperHero> data)
    {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addNewData(List<SuperHero> newData)
    {
        for (int i = 0; i < newData.size(); i++)
            data.add(newData.get(i));
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem(int i)
    {
        return data.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View myView = view;
        ViewHolder holder;

        if (myView == null)
        {
            //LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myView = mLayoutInflater.inflate(R.layout.custom_grid_character, viewGroup, false);
            holder = new ViewHolder(myView);
            myView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) myView.getTag();
        }
        final SuperHero superHero = data.get(i);
        String s = superHero.getName();
        //if (s.contains("(")) s = s.substring(0, s.indexOf("("));
        //if (s.contains("/")) s = s.substring(0, s.indexOf("/"));
        holder.textView.setText(s);
        //holder.textView.setText(superHero.getName());
        String imagePath = superHero.getImagePath();
        if (imagePath != null)
        {
            final ViewHolder finalHolder = holder;
//            Picasso.with(MyApplication.getAppContext())
//                    .load(imagePath)
//                    .noFade()
//                    .into(holder.imageView);
            imageLoader.get(imagePath, new ImageLoader.ImageListener()
            {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate)
                {
                    finalHolder.imageView.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error)
                {
                    //TODO gestire immagine non trovata per evitare caricamento infinito
                    Toast.makeText(mContext, "No image for: " + superHero.getName(), Toast.LENGTH_LONG).show();
                }
            });
        }
        return myView;
    }

    public class ViewHolder
    {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView)
        {
            imageView = (ImageView) itemView.findViewById(R.id.character_grid_image);
            textView = (TextView) itemView.findViewById(R.id.character_grid_name);
        }
    }
}
