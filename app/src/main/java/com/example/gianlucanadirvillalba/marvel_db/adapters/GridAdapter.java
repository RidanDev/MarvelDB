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
        //notifyItemRangeChanged(0, data.size());
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
        } else
        {
            holder = (ViewHolder) myView.getTag();
        }
        final SuperHero superHero = data.get(i);
        //TODO controllo stringa da ottimizzare
        String s = superHero.getName();
        if (s.length() > 13) s = s.substring(0, 13) + "...";
        holder.textView.setText(s);

        //holder.textView.setText(superHero.getName());
        String imagePath = superHero.getImagePath();
        if (imagePath != null)
        {
            final ViewHolder finalHolder = holder;
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
                    Toast.makeText(mContext, "No image for: " + superHero.getName(), Toast.LENGTH_SHORT).show();
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
