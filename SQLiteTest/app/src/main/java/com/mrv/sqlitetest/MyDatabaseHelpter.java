package com.mrv.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mr.V on 2015/5/31.
 */
public class MyDatabaseHelpter extends SQLiteOpenHelper{

    public static final String CREATE_STUDENT="create table student(" + "id integer primary key autoincrement,"+
            "SNO Varchar,"+
            "Name Varchar,"+
            "Age,integer"+
            "College Varchar)";
    public static final String CREATE_COURSE="create table course(" + "id integer primary key autoincrement,"+
            "CourseID Varchar,"+
            "CourseName Varchar,"+
            "CourseBeforeID Varchar)";
    public static final String CREATE_CHOOSE="create table choose(" + "id integer primary key autoincrement,"+
            "SND Varchar,"+
            "CourseID Varchar,"+
            "Score DECIMAL)";
    public MyDatabaseHelpter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT);
        db.execSQL(CREATE_COURSE);
        db.execSQL(CREATE_CHOOSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
