package com.mrv.sqtitetest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = openOrCreateDatabase("stu.db", MODE_PRIVATE, null);
        db.execSQL("create table if not exists stutb(_id integer primary key autoincrement,name text not null,age integer not null,sex text not null )");
        ContentValues values = new ContentValues();

        values.put("name", "JBJ");
        values.put("age", 100);
        values.put("sex", "woman");
        db.insert("stutb", null, values);
        values.clear();

        values.put("name", "JBJ2");
        values.put("age",99);
        values.put("sex","man");
        db.insert("stutb", null, values);
        values.clear();

        values.put("name","JB3");
        values.put("age",9999);
        values.put("sex","unknown");
        db.insert("stutb",null,values);
    }


}
