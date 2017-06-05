package com.example.gianlucanadirvillalba.marvel_db.pojo;

import java.util.ArrayList;

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
    private String universeWiki;
    private String bioTextWiki;
    private String bioWiki;
    private int pageViewCount;
    //private String recordName;
    private String recordImagePath;
    private String recordBackground;
    private int recordPageViewCount;
    private ArrayList<String> recordAliases = new ArrayList<>();
    private ArrayList<String> recordAuthors = new ArrayList<>();
    private ArrayList<String> recordPartners = new ArrayList<>();
    private ArrayList<String> recordPowers = new ArrayList<>();
    private ArrayList<String> recordSecretIdentities = new ArrayList<>();
    private ArrayList<String> recordSpecies = new ArrayList<>();
    private ArrayList<String> recordTeams = new ArrayList<>();
    private String recordDescription;
    private String recordMainColor;
    private String recordSuperName;

    public long getId() {return id;}
    public String getName()
    {
        return name;
    }
    public String getDescription() {return description;}
    public String getImagePath() {return imagePath;}
    public String getUniverseWiki() {return universeWiki;}
    public String getBioTextWiki() {return bioTextWiki;}
    public String getBioWiki() {return bioWiki;}
    public int getPageViewCount() {return pageViewCount;}
    //public String getRecordName() {return recordName;}
    public String getRecordImagePath() {return recordImagePath;}
    public int getRecordPageViewCount() {return recordPageViewCount;}
    public ArrayList<String> getRecordAliases() {return recordAliases;}
    public ArrayList<String> getRecordAuthors() {return recordAuthors;}
    public String getRecordDescription() {return recordDescription;}
    public ArrayList<String> getRecordPartners() {return recordPartners;}
    public ArrayList<String> getRecordPowers() {return recordPowers;}
    public String getRecordMainColor() {return recordMainColor;}
    public ArrayList<String> getRecordSecretIdentities() {return recordSecretIdentities;}
    public ArrayList<String> getRecordSpecies() {return recordSpecies;}
    public String getRecordSuperName() {return recordSuperName;}
    public ArrayList<String> getRecordTeams() {return recordTeams;}
    public String getRecordBackground() {return recordBackground;}


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
    public void setUniverseWiki(String universeWiki) {this.universeWiki = universeWiki;}
    public void setBioTextWiki(String bioTextWiki) {this.bioTextWiki = bioTextWiki;}
    public void setBioWiki(String bioWiki) {this.bioWiki = bioWiki;}
    public void setPageViewCount(int pageViewCount) {this.pageViewCount = pageViewCount;}
    //public void setRecordName(String recordName) {this.recordName = recordName;}
    public void setRecordImagePath(String recordImagePath) {this.recordImagePath = recordImagePath;}
    public void setRecordPageViewCount(int recordPageViewCount) {this.recordPageViewCount = recordPageViewCount;}
    public void setRecordAliases(ArrayList<String> recordAliases) {this.recordAliases = recordAliases;}
    public void setRecordAuthors(ArrayList<String> recordAuthors) {this.recordAuthors = recordAuthors;}
    public void setRecordDescription(String recordDescription) {this.recordDescription = recordDescription;}
    public void setRecordPartners(ArrayList<String> recordPartners) {this.recordPartners = recordPartners;}
    public void setRecordPowers(ArrayList<String> recordPowers) {this.recordPowers = recordPowers;}
    public void setRecordMainColor(String recordMainColor) {this.recordMainColor = recordMainColor;}
    public void setRecordSecretIdentities(ArrayList<String> recordSecretIdentities) {this.recordSecretIdentities = recordSecretIdentities;}
    public void setRecordSpecies(ArrayList<String> recordSpecies) {this.recordSpecies = recordSpecies;}
    public void setRecordSuperName(String recordSuperName) {this.recordSuperName = recordSuperName;}
    public void setRecordTeams(ArrayList<String> recordTeams) {this.recordTeams = recordTeams;}
    public void setRecordBackground(String recordBackground) {this.recordBackground = recordBackground;}



}
