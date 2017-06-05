package com.example.gianlucanadirvillalba.marvel_db.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gianlucanadirvillalba.marvel_db.Interfaces.SuperHeroesListener;
import com.example.gianlucanadirvillalba.marvel_db.R;
import com.example.gianlucanadirvillalba.marvel_db.activities.MainActivity;
import com.example.gianlucanadirvillalba.marvel_db.activities.SubActivity;
import com.example.gianlucanadirvillalba.marvel_db.extras.MyApplication;
import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by gianlucanadirvillalba on 09/11/2016.
 * <p>
 * Il funzionamento è come quello del GridAdapter (guardare javadoc) ma con il riutilizzo delle viste
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CharactersHolder>
        implements SuperHeroesListener
{
    private final Context mContext;
    private LayoutInflater mLayoutInflater;
    //private List<SuperHero> data = Collections.emptyList();
    public static List<SuperHero> data = Collections.emptyList();

    public RecyclerAdapter(Context context)
    {
        mContext = context;
        mLayoutInflater = mLayoutInflater.from(context);
    }

    public void setData(List<SuperHero> data)
    {
        this.data = data;
        notifyItemRangeChanged(0, data.size());
    }

    //non lo utilizzo con i files in locale
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
        //View view = mLayoutInflater.inflate(R.layout.custom_list_character, parent, false);
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
                    .placeholder(ContextCompat.getDrawable(mContext, R.mipmap.portrait_fantastic))
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    @Override
    public void onAddSuperHeroes(List<SuperHero> data)
    {
        setData(data);
        MainActivity.mLoadSnackbar.dismiss();
    }

    public class CharactersHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView textView;
        private ImageView imageView;

        public CharactersHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.character_grid_name);
            imageView = (ImageView) itemView.findViewById(R.id.character_grid_image);
            //textView = (TextView) itemView.findViewById(R.id.characterName);
            //imageView = (ImageView) itemView.findViewById(R.id.characterImage);
        }

        @Override
        public void onClick(View view)
        {
            Intent intent = new Intent(mContext, SubActivity.class);
            intent.putExtra("bio", data.get(getAdapterPosition()).getBioWiki());
            mContext.startActivity(intent);
        }
    }
}
