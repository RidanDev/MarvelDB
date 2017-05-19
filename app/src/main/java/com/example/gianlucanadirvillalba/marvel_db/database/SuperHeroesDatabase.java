package com.example.gianlucanadirvillalba.marvel_db.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import com.example.gianlucanadirvillalba.marvel_db.pojo.SuperHero;

import java.util.ArrayList;

/**
 * Created by gianlucanadirvillalba on 21/11/2016.
 */

public class SuperHeroesDatabase
{
    private DatabaseHelper mHelper;
    private SQLiteDatabase mDatabase;
    private Context mContext;
    private int added;
    private int total;

    public SuperHeroesDatabase(Context context)
    {
        mHelper = new DatabaseHelper(context);
        mDatabase = mHelper.getWritableDatabase();
        mContext = context;
        total = 0;
    }

    public void insertMovies(ArrayList<SuperHero> list, boolean clearPrevious)
    {
        if (clearPrevious) deleteSuperheroes();

        String sql = "INSERT INTO " + mHelper.TABLE_SUPERHEROES
                + " VALUES (?,?,?,?,?);";

        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();
        added = 0;
        for (int i = 0; i < list.size(); i++)
        {
            SuperHero superHero = list.get(i);
            statement.clearBindings();
            statement.bindLong(2, superHero.getId());
            statement.bindString(3, superHero.getName());
            statement.bindString(4, superHero.getDescription());
            statement.bindString(5, superHero.getImagePath());
            statement.execute();
            added ++;
            total ++;
        }

        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
        Toast.makeText(mContext, "Added: " + added + "\nTotal: " + total, Toast.LENGTH_SHORT).show();
    }

    private void deleteSuperheroes()
    {
        mDatabase.delete(DatabaseHelper.TABLE_SUPERHEROES, null, null);
    }

    private class DatabaseHelper extends SQLiteOpenHelper
    {
        public static final String DB_NAME = "superheroes_db";
        public static final int DB_VERSION = 1;
        public static final String TABLE_SUPERHEROES = "superheroes_table";
        public static final String COLUMN_UID = "_id";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGE_PATH = "image_path";

        public static final String CREATE_TABLE_SUPERHEROES = "CREATE TABLE " +
                TABLE_SUPERHEROES + " (" +
                COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ID + " INTEGER," +
                COLUMN_NAME + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_IMAGE_PATH + " TEXT" +
                ");";


        public DatabaseHelper(Context context)
        {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase)
        {
            try
            {
                sqLiteDatabase.execSQL(CREATE_TABLE_SUPERHEROES);
            } catch (SQLiteException exception)
            {

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
        {
            try
            {
                sqLiteDatabase.execSQL(" DROP TABLE " + TABLE_SUPERHEROES + "IF EXISTS;");
                onCreate(sqLiteDatabase);
            } catch (SQLiteException exception)
            {

            }
        }
    }
}
