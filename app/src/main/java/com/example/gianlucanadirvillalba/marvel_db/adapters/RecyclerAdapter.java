package com.example.gianlucanadirvillalba.marvel_db.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gianlucanadirvillalba.marvel_db.R;
import com.example.gianlucanadirvillalba.marvel_db.extras.MyApplication;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by gianlucanadirvillalba on 09/11/2016.
 * <p>
 * Il funzionamento è come quello del GridAdapter (guardare javadoc)
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CharactersHolder>
{
    private LayoutInflater mLayoutInflater;
    private List<SuperHero> data = Collections.emptyList();

    public RecyclerAdapter(Context context)
    {
        mLayoutInflater = mLayoutInflater.from(context);
    }

    public void setData(List<SuperHero> data)
    {
        this.data = data;
        notifyItemRangeChanged(0, data.size());
    }

    public void addNewData(List<SuperHero> newData)
    {
        for (int i = 0; i < newData.size(); i++)
            data.add(newData.get(i));
        notifyDataSetChanged();
    }

    @Override
    public CharactersHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mLayoutInflater.inflate(R.layout.custom_grid_character, parent, false);
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
            Picasso.with(MyApplication.getAppContext())
                    .load(imagePath)
                    .noFade()
                    .into(holder.imageView);
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
            textView = (TextView) itemView.findViewById(R.id.character_grid_name);
            imageView = (ImageView) itemView.findViewById(R.id.character_grid_image);
        }
    }
}
