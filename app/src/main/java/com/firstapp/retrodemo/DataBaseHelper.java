package com.firstapp.retrodemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.firstapp.retrodemo.model.RegionDatum;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "State_db";

    //table name
    private static final String TABLE_NAME = "covid_data";

    //Column name of pojo list
    private static final String KEY_ID = "id";
    private static final String KEY_REGION = "region";
    private static final String KEY_TOTAL_INFECTED = "totalInfected";
    private static final String KEY_NEW_INFECTED = "newInfected";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + KEY_REGION + " TEXT,"
            + KEY_TOTAL_INFECTED + " INTEGER, "
            + KEY_NEW_INFECTED + " INTEGER" + ")";




    @Override
    public void onCreate(SQLiteDatabase db) {

        // create Pojos table
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);

    }


        public long addData( String region, int  total_infected, int new_infected) {
           try {
               long dbid=0;
               SQLiteDatabase db = this.getWritableDatabase();
               ContentValues values = new ContentValues();
               values.put(KEY_REGION, region);
               values.put(KEY_TOTAL_INFECTED, total_infected);
               values.put(KEY_NEW_INFECTED, new_infected);
               // Inserting Row
               dbid =  db.insert(TABLE_NAME, null, values);
               Log.d("hhhhhhhhhhhhh", "inserted" + values);
               db.close();

               return dbid;

           } catch (Exception e) {
               e.printStackTrace();
           }

            return 0;
        }

    ArrayList<RegionDatum> getData() {
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<RegionDatum> data = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String region = cursor.getString(1);
                int totalInfected = cursor.getInt(2);
                int newInfected = cursor.getInt(3);
                data.add(new RegionDatum( region, totalInfected,newInfected));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    }



