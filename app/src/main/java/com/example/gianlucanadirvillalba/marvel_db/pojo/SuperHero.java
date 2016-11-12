package com.example.gianlucanadirvillalba.marvel_db.pojo;

/**
 * Created by gianlucanadirvillalba on 09/11/2016.
 */
public class SuperHero
{
    private String name;
    private String description;
    private String imagePath;
    private long id;
    private int comicsNumber;

    public String getName()
    {
        return name;
    }

    public String getImagePath() {return imagePath;}

    public void setName(String name)
    {
        this.name = name;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }

    public void setComicsNumber(int comicsNumber)
    {
        this.comicsNumber = comicsNumber;
    }
}
