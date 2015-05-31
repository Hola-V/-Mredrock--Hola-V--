package com.mrv.sqlitetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {
    private MyDatabaseHelpter dbHelpter1;
    private MyDatabaseHelpter dbHelpter2;
    private MyDatabaseHelpter dbHelpter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelpter1=new MyDatabaseHelpter(this,"student.db",null,1);
        dbHelpter2=new MyDatabaseHelpter(this,"course.db",null,1);
        dbHelpter3=new MyDatabaseHelpter(this,"choose.db",null,1);

        Button createStudent = (Button) findViewById(R.id.createStudent);
        createStudent.setOnClickListener(this);

        Button createCourse = (Button) findViewById(R.id.createCourse);
        createCourse.setOnClickListener(this);

        Button createChoose = (Button) findViewById(R.id.createChoose);
        createChoose.setOnClickListener(this);

        Button stuadd = (Button) findViewById(R.id.insertStudent);
        stuadd.setOnClickListener(this);

        Button courseadd = (Button) findViewById(R.id.insertCourse);
        courseadd.setOnClickListener(this);

        Button chooseadd = (Button) findViewById(R.id.insertChoose);
        chooseadd.setOnClickListener(this);

        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createStudent:
                dbHelpter1.getWritableDatabase();
                Toast.makeText(MainActivity.this,"创建完成",Toast.LENGTH_LONG).show();
                break;
            case R.id.createCourse:
                dbHelpter2.getWritableDatabase();
                Toast.makeText(MainActivity.this,"创建完成",Toast.LENGTH_LONG).show();
                break;
            case R.id.createChoose:
                dbHelpter3.getWritableDatabase();
                Toast.makeText(MainActivity.this,"创建完成",Toast.LENGTH_LONG).show();
                break;
            case R.id.insertStudent:
                SQLiteDatabase db=dbHelpter1.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("SNO","S0001");
                values.put("Name","张三");
                values.put("Age","20");
                values.put("College","计算机学院");
                db.insert("student",null,values);
                values.clear();
                values.put("SNO","S0002");
                values.put("Name","李四");
                values.put("Age","19");
                values.put("College","通信学院");
                db.insert("student",null,values);
                values.clear();
                values.put("SNO","S0003");
                values.put("Name","王五");
                values.put("Age","20");
                values.put("College","计算机学院");
                db.insert("student",null,values);
                Toast.makeText(MainActivity.this,"插入完成",Toast.LENGTH_LONG).show();
                break;
            case R.id.insertCourse:
                SQLiteDatabase db2 = dbHelpter2.getWritableDatabase();
                ContentValues values2 = new ContentValues();
                values2.put("CourseID","C1");
                values2.put("CourseName","计算机导论");
                values2.put("CourseBeforeID","NULL");
                db2.insert("course",null,values2);
                values2.clear();
                values2.put("CourseID","C2");
                values2.put("CourseName","C语言");
                values2.put("CourseBeforeID","C1");
                db2.insert("course",null,values2);
                values2.clear();
                values2.put("CourseID","C3");
                values2.put("CourseName","数据结构");
                values2.put("CourseBeforeID","C2");
                db2.insert("course",null,values2);
                Toast.makeText(MainActivity.this,"插入完成",Toast.LENGTH_LONG).show();
                break;
            case R.id.insertChoose:
                SQLiteDatabase db3 =dbHelpter3.getReadableDatabase();
                ContentValues values3 = new ContentValues();
                values3.put("SNO","S0001");
                values3.put("CourseID","C1");
                values3.put("Score",95);
                db3.insert("choose",null,values3);
                values3.clear();
                values3.put("SNO","S0001");
                values3.put("CourseID","C2");
                values3.put("Score",80);
                db3.insert("choose",null,values3);
                values3.clear();
                values3.put("SNO","S0001");
                values3.put("CourseID","C3");
                values3.put("Score",84);
                db3.insert("choose",null,values3);
                values3.clear();
                values3.put("SNO","S0002");
                values3.put("CourseID","C1");
                values3.put("Score",80);
                db3.insert("choose",null,values3);
                values3.clear();
                values3.put("SNO","S0002");
                values3.put("CourseID","C2");
                values3.put("Score",85);
                db3.insert("choose",null,values3);
                values3.clear();
                values3.put("SNO","S0003");
                values3.put("CourseID","C1");
                values3.put("Score",78);
                db3.insert("choose",null,values3);
                values3.clear();
                values3.put("SNO","S0003");
                values3.put("CourseID","C3");
                values3.put("Score",70);
                db3.insert("choose",null,values3);
                Toast.makeText(MainActivity.this,"插入完成",Toast.LENGTH_LONG).show();
                break;
            case R.id.search:
                SQLiteDatabase db4 = dbHelpter1.getReadableDatabase();
                Cursor cursor = db4.query("student",null,null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do{
                        String SNO = cursor.getString(cursor.getColumnIndex("SNO"));
                        String name = cursor.getString(cursor.getColumnIndex("Name"));
                        String Age = cursor.getString(cursor.getColumnIndex("Age"));
                        String College = cursor.getString(cursor.getColumnIndex("College"));
                        Log.d("Mainactivity","SNO is "+SNO);
                        Log.d("Mainactivity","Name is "+name);
                        Log.d("Mainactivity","Age is "+Age);
                        Log.d("Mainactivity","College is "+College);
                    }while (cursor.moveToNext());
                }
                cursor.close();
                break;
        }
    }
}
