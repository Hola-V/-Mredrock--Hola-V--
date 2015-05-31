package com.mrv.mytest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

/**
 * Created by Mr.V on 2015/5/30.
 */
public class beauty{
    private Bitmap pictureID;
    private String title;
    private String date;
    private int fuck;
    private int like;
    private int dislike;
    public beauty(Bitmap pictureID,
                  String title,
                  String date,
                  int fuck,
                  int like,
                  int dislike){
        this.pictureID=pictureID;
        this.date=date;
        this.like=like;
        this.dislike=dislike;
        this.fuck=fuck;
        this.title=title;
    }
    public Bitmap getpictureID(){
        return pictureID;
    }
    public int getFuck(){
        return fuck;
    }
    public int getDislike(){
        return dislike;
    }
    public int getLike(){
        return like;
    }
    public String getTitle(){
        return title;
    }
    public String getDate(){
        return date;
    }
/*
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.beuatyfragment,container,false);
    }
    */
}
