package com.example.amresh.speechtotextsave;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMRESH on 1/12/2018.
 */

public class DataBaseFile extends SQLiteOpenHelper {
    public static final String Database_Name="company.db";
    public static final String Table_Name="user";
    public static final String Col_1="Name";
    final String createTable="create table "+ Table_Name+"( name text )";
    static SQLiteDatabase db;

    public DataBaseFile(Context context) {
        super(context, Database_Name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+ Table_Name);
        onCreate(db);
    }
    public boolean deleteFile( String name){
        db=this.getWritableDatabase();
        db.delete(Table_Name,"name=?",new String[]{name});
        return true;

    }
    public  Cursor getName(String name){
        db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select name from "+Table_Name+" where name=?",new String[]{name});

        return res;

    }

    public void insertData(String name){
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col_1,name);
        db.insert(Table_Name,null,contentValues);
    }
    public List <String>getData(){
        db=this.getWritableDatabase();
        List<String> filenames=new ArrayList<>();

        Cursor res=db.rawQuery("select * from "+Table_Name,null);
        if (res.moveToFirst()){
            do{ filenames.add(res.getString(0));
            }while (res.moveToNext());
        }
        return filenames;
    }
}
