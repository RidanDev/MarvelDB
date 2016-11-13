package com.example.gianlucanadirvillalba.marvel_db.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.gianlucanadirvillalba.marvel_db.R;
import com.example.gianlucanadirvillalba.marvel_db.network.VolleySingleton;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import java.util.Collections;
import java.util.List;

/**
 * Created by gianlucanadirvillalba on 09/11/2016.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.CharactersHolder>
{
    private LayoutInflater mLayoutInflater;
    private List<SuperHero> data = Collections.emptyList();
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;

    public ListAdapter(Context context)
    {
        mLayoutInflater = mLayoutInflater.from(context);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    public void setData(List<SuperHero> data)
    {
        this.data = data;
        notifyItemRangeChanged(0, data.size());
    }

    @Override
    public CharactersHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mLayoutInflater.inflate(R.layout.custom_character, parent, false);
        CharactersHolder charactersHolder = new CharactersHolder(view);
        return charactersHolder;
    }

    @Override
    public void onBindViewHolder(final CharactersHolder holder, int position)
    {
        SuperHero superHero = data.get(position);
        holder.textView.setText(superHero.getName());
        String imagePath = superHero.getImagePath();
        if (imagePath != null)
        {
            imageLoader.get(imagePath, new ImageLoader.ImageListener()
            {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate)
                {
                    holder.imageView.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error)
                {

                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    public class CharactersHolder extends RecyclerView.ViewHolder
    {
        private TextView textView;
        private ImageView imageView;

        public CharactersHolder(View itemView)
        {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.characterName);
            imageView = (ImageView) itemView.findViewById(R.id.characterImage);
        }
    }
}
