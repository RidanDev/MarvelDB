package com.example.gianlucanadirvillalba.marvel_db;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by gianlucanadirvillalba on 09/11/2016.
 */

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharactersHolder>
{
    private LayoutInflater mLayoutInflater;
    private List<SuperHero> data = Collections.emptyList();

    public CharactersAdapter(Context context, List<SuperHero> data)
    {
        mLayoutInflater = mLayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public CharactersHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mLayoutInflater.inflate(R.layout.custom_character, parent, false);
        CharactersHolder charactersHolder = new CharactersHolder(view);
        return charactersHolder;
    }

    @Override
    public void onBindViewHolder(CharactersHolder holder, int position)
    {
        SuperHero superHero = data.get(position);
        holder.imageView.setImageResource(superHero.getImage());
        holder.textView.setText(superHero.getName());
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
