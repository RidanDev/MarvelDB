package com.example.gianlucanadirvillalba.marvel_db.Interfaces;

import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import java.util.List;

/**
 * Created by gianlucanadirvillalba on 25/05/2017.
 */

public interface SuperHeroesListener
{
    void onAddSuperHeroes(List<SuperHero> data);
}
