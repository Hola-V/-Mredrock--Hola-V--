package com.mrv.mytest;

/**
 * Created by Mr.V on 2015/5/30.
 */
public class duanzi {
    private String text;
    private String title;
    private String date;
    private int fuck;
    private int like;
    private int dislike;
    public duanzi(String text,
                  String title,
                  String date,
                  int fuck,
                  int like,
                  int dislike){
        this.text=text;
        this.date=date;
        this.like=like;
        this.dislike=dislike;
        this.fuck=fuck;
        this.title=title;
    }
    public String getText(){
        return text;
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
