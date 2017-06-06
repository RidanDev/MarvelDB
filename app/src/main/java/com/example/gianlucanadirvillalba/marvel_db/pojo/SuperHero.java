package com.example.gianlucanadirvillalba.marvel_db.pojo;

import com.example.gianlucanadirvillalba.marvel_db.extras.Constants;

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
    private String realNameWiki;
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
    private String blurbWiki;
    private String aliasesWiki;
    private String identityWiki;
    private String occupationWiki;
    private String citizenshipWiki;
    private String placeOfBirthWiki;
    private String relativesWiki;
    private String groupsWiki;
    private String educationWiki;
    private String heightWiki;
    private String weightWiki;
    private String eyesWiki;
    private String hairWiki;
    private String abilitiesWiki;
    private String weaponsWiki;
    private String paraphernaliaWiki;
    private String powersWiki;
    private String debutWiki;
    private String originWiki;
    private String significantIssuesWiki;
    private ArrayList<String> categoriesWiki = new ArrayList<>();


    @Override
    public String toString()
    {
        String info = "";
        if (getId() != Constants.NULL_INT) info += "Id: " + getId() + "\n";
        if (!getName().equals(Constants.NA)) info += "Name: " + getName() + "\n";
        if (!getRealNameWiki().equals(Constants.NA))
            info += "RealName: " + getRealNameWiki() + "\n";
        if (!getRecordSuperName().equals(Constants.NA))
            info += "SuperName: " + getRecordSuperName() + "\n";
        if (!getImagePath().equals(Constants.NA)) info += "Image: " + getImagePath() + "\n";
        if (!getRecordImagePath().equals(Constants.NA))
            info += "RecordImage: " + getRecordImagePath() + "\n";
        if (!getRecordBackground().equals(Constants.NA))
            info += "Background: " + getRecordBackground() + "\n";
        if (!getRecordMainColor().equals(Constants.NA))
            info += "MainColor: " + getRecordMainColor() + "\n";
        if (!getIdentityWiki().equals(Constants.NA))
            info += "Identity: " + getIdentityWiki() + "\n";
        if (!getCitizenshipWiki().equals(Constants.NA))
            info += "Citizenship: " + getCitizenshipWiki() + "\n";
        if (!getPlaceOfBirthWiki().equals(Constants.NA))
            info += "Place of bird: " + getPlaceOfBirthWiki() + "\n";
        if (!getEducationWiki().equals(Constants.NA))
            info += "Education: " + getEducationWiki() + "\n";
        if (!getHeightWiki().equals(Constants.NA)) info += "Height: " + getHeightWiki() + "\n";
        if (!getWeightWiki().equals(Constants.NA)) info += "Weight: " + getWeightWiki() + "\n";
        if (!getEyesWiki().equals(Constants.NA)) info += "Eyes: " + getEyesWiki() + "\n";
        if (!getHairWiki().equals(Constants.NA)) info += "Hair: " + getHairWiki() + "\n";


        String space = " ";
        if (!getRecordAliases().isEmpty())
        {
            for (int j = 0; j < "aliases".length(); j++) space += " ";
            info += "aliases: " + "\n";
            for (String alias : getRecordAliases())
                info += space + alias + "\n";
        }

        if (!getRecordAuthors().isEmpty())
        {
            space = " ";
            for (int j = 0; j < "authors".length(); j++) space += " ";
            info += "authors: " + "\n";
            for (String author : getRecordAuthors())
                info += space + author + "\n";
        }

        if (!getRecordPartners().isEmpty())
        {
            space = " ";
            for (int j = 0; j < "partners".length(); j++) space += " ";
            info += "partners: " + "\n";
            for (String partner : getRecordPartners())
                info += space + partner + "\n";
        }

        if (!getRecordPowers().isEmpty())
        {
            space = " ";
            for (int j = 0; j < "powers".length(); j++) space += " ";
            info += "powers: " + "\n";
            for (String power : getRecordPowers())
                info += space + power + "\n";
        }

        if (!getRecordSecretIdentities().isEmpty())
        {
            space = " ";
            for (int j = 0; j < "secretIdentitites".length(); j++) space += " ";
            info += "secretIdentities: " + "\n";
            for (String secretIdentity : getRecordSecretIdentities())
                info += space + secretIdentity + "\n";
        }

        if (!getRecordSpecies().isEmpty())
        {
            space = " ";
            for (int j = 0; j < "species".length(); j++) space += " ";
            info += "species: " + "\n";
            for (String species : getRecordSpecies())
                info += space + species + "\n";
        }

        if (!getRecordTeams().isEmpty())
        {
            space = " ";
            for (int j = 0; j < "teams".length(); j++) space += " ";
            info += "teams: " + "\n";
            for (String team : getRecordTeams())
                info += space + team + "\n";
        }
        return info;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String getImagePath()
    {
        return imagePath;
    }

    public String getUniverseWiki()
    {
        return universeWiki;
    }

    public String getBioTextWiki()
    {
        return bioTextWiki;
    }

    public String getBioWiki()
    {
        return bioWiki;
    }

    public int getPageViewCount()
    {
        return pageViewCount;
    }

    //public String getRecordName() {return recordName;}
    public String getRecordImagePath()
    {
        return recordImagePath;
    }

    public int getRecordPageViewCount()
    {
        return recordPageViewCount;
    }

    public ArrayList<String> getRecordAliases()
    {
        return recordAliases;
    }

    public ArrayList<String> getRecordAuthors()
    {
        return recordAuthors;
    }

    public String getRecordDescription()
    {
        return recordDescription;
    }

    public ArrayList<String> getRecordPartners()
    {
        return recordPartners;
    }

    public ArrayList<String> getRecordPowers()
    {
        return recordPowers;
    }

    public String getRecordMainColor()
    {
        return recordMainColor;
    }

    public ArrayList<String> getRecordSecretIdentities()
    {
        return recordSecretIdentities;
    }

    public ArrayList<String> getRecordSpecies()
    {
        return recordSpecies;
    }

    public String getRecordSuperName()
    {
        return recordSuperName;
    }

    public ArrayList<String> getRecordTeams()
    {
        return recordTeams;
    }

    public String getRecordBackground()
    {
        return recordBackground;
    }

    public String getRealNameWiki()
    {
        return realNameWiki;
    }


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

    public void setUniverseWiki(String universeWiki)
    {
        this.universeWiki = universeWiki;
    }

    public void setBioTextWiki(String bioTextWiki)
    {
        this.bioTextWiki = bioTextWiki;
    }

    public void setBioWiki(String bioWiki)
    {
        this.bioWiki = bioWiki;
    }

    public void setPageViewCount(int pageViewCount)
    {
        this.pageViewCount = pageViewCount;
    }

    //public void setRecordName(String recordName) {this.recordName = recordName;}
    public void setRecordImagePath(String recordImagePath)
    {
        this.recordImagePath = recordImagePath;
    }

    public void setRecordPageViewCount(int recordPageViewCount)
    {
        this.recordPageViewCount = recordPageViewCount;
    }

    public void setRecordAliases(ArrayList<String> recordAliases)
    {
        this.recordAliases = recordAliases;
    }

    public void setRecordAuthors(ArrayList<String> recordAuthors)
    {
        this.recordAuthors = recordAuthors;
    }

    public void setRecordDescription(String recordDescription)
    {
        this.recordDescription = recordDescription;
    }

    public void setRecordPartners(ArrayList<String> recordPartners)
    {
        this.recordPartners = recordPartners;
    }

    public void setRecordPowers(ArrayList<String> recordPowers)
    {
        this.recordPowers = recordPowers;
    }

    public void setRecordMainColor(String recordMainColor)
    {
        this.recordMainColor = recordMainColor;
    }

    public void setRecordSecretIdentities(ArrayList<String> recordSecretIdentities)
    {
        this.recordSecretIdentities = recordSecretIdentities;
    }

    public void setRecordSpecies(ArrayList<String> recordSpecies)
    {
        this.recordSpecies = recordSpecies;
    }

    public void setRecordSuperName(String recordSuperName)
    {
        this.recordSuperName = recordSuperName;
    }

    public void setRecordTeams(ArrayList<String> recordTeams)
    {
        this.recordTeams = recordTeams;
    }

    public void setRecordBackground(String recordBackground)
    {
        this.recordBackground = recordBackground;
    }

    public void setRealNameWiki(String realNameWiki)
    {
        this.realNameWiki = realNameWiki;
    }

    public String getBlurbWiki()
    {
        return blurbWiki;
    }

    public void setBlurbWiki(String blurbWiki)
    {
        this.blurbWiki = blurbWiki;
    }

    public String getAliasesWiki()
    {
        return aliasesWiki;
    }

    public void setAliasesWiki(String aliasesWiki)
    {
        this.aliasesWiki = aliasesWiki;
    }

    public String getIdentityWiki()
    {
        return identityWiki;
    }

    public void setIdentityWiki(String identityWiki)
    {
        this.identityWiki = identityWiki;
    }

    public String getOccupationWiki()
    {
        return occupationWiki;
    }

    public void setOccupationWiki(String occupationWiki)
    {
        this.occupationWiki = occupationWiki;
    }

    public String getCitizenshipWiki()
    {
        return citizenshipWiki;
    }

    public void setCitizenshipWiki(String citizenshipWiki)
    {
        this.citizenshipWiki = citizenshipWiki;
    }

    public String getPlaceOfBirthWiki()
    {
        return placeOfBirthWiki;
    }

    public void setPlaceOfBirthWiki(String placeOfBirthWiki)
    {
        this.placeOfBirthWiki = placeOfBirthWiki;
    }

    public String getRelativesWiki()
    {
        return relativesWiki;
    }

    public void setRelativesWiki(String relativesWiki)
    {
        this.relativesWiki = relativesWiki;
    }

    public String getGroupsWiki()
    {
        return groupsWiki;
    }

    public void setGroupsWiki(String groupsWiki)
    {
        this.groupsWiki = groupsWiki;
    }

    public String getEducationWiki()
    {
        return educationWiki;
    }

    public void setEducationWiki(String educationWiki)
    {
        this.educationWiki = educationWiki;
    }

    public String getHeightWiki()
    {
        return heightWiki;
    }

    public void setHeightWiki(String heightWiki)
    {
        this.heightWiki = heightWiki;
    }

    public String getWeightWiki()
    {
        return weightWiki;
    }

    public void setWeightWiki(String weightWiki)
    {
        this.weightWiki = weightWiki;
    }

    public String getEyesWiki()
    {
        return eyesWiki;
    }

    public void setEyesWiki(String eyesWiki)
    {
        this.eyesWiki = eyesWiki;
    }

    public String getHairWiki()
    {
        return hairWiki;
    }

    public void setHairWiki(String hairWiki)
    {
        this.hairWiki = hairWiki;
    }

    public String getAbilitiesWiki()
    {
        return abilitiesWiki;
    }

    public void setAbilitiesWiki(String abilitiesWiki)
    {
        this.abilitiesWiki = abilitiesWiki;
    }

    public String getWeaponsWiki()
    {
        return weaponsWiki;
    }

    public void setWeaponsWiki(String weaponsWiki)
    {
        this.weaponsWiki = weaponsWiki;
    }

    public String getParaphernaliaWiki()
    {
        return paraphernaliaWiki;
    }

    public void setParaphernaliaWiki(String paraphernaliaWiki)
    {
        this.paraphernaliaWiki = paraphernaliaWiki;
    }

    public String getPowersWiki()
    {
        return powersWiki;
    }

    public void setPowersWiki(String powersWiki)
    {
        this.powersWiki = powersWiki;
    }

    public String getDebutWiki()
    {
        return debutWiki;
    }

    public void setDebutWiki(String debutWiki)
    {
        this.debutWiki = debutWiki;
    }

    public String getOriginWiki()
    {
        return originWiki;
    }

    public void setOriginWiki(String originWiki)
    {
        this.originWiki = originWiki;
    }

    public String getSignificantIssuesWiki()
    {
        return significantIssuesWiki;
    }

    public void setSignificantIssuesWiki(String significantIssuesWiki)
    {
        this.significantIssuesWiki = significantIssuesWiki;
    }

    public ArrayList<String> getCategoriesWiki()
    {
        return categoriesWiki;
    }

    public void setCategoriesWiki(ArrayList<String> categoriesWiki)
    {
        this.categoriesWiki = categoriesWiki;
    }
}
