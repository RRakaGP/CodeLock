package com.example.codelock;

import  android.content.Context;
import  android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper;

public class DBController extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME="SqliteListviewDB";
    public DBController(Context application) {
        super(application, DATABASE_NAME, null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table to insert data
        String query;
        query = "CREATE TABLE IF NOT EXISTS UserDetails(NOTES VARCHAR);";
        db.execSQL(query);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query ;
        query = "DROP TABLE IF EXISTS UserDetails";
        db.execSQL(query);
        onCreate(db);
    }
}
