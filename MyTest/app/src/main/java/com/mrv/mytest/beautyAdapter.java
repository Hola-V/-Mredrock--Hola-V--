package com.mrv.mytest;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mr.V on 2015/5/30.
 */
public class beautyAdapter extends ArrayAdapter<beauty> {
    private int resourceID;

   public beautyAdapter(Context context,int textViewResourceID,List<beauty> objects){
       super(context,textViewResourceID,objects);
       resourceID=textViewResourceID;
   }
    public View getView(int position,View convertView,ViewGroup parent){
        beauty beauty = getItem(position);
        View view ;
        ViewHolder viewHolder;
        if(convertView==null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, null);
            viewHolder = new ViewHolder();
            viewHolder.beautyImage = (ImageView) view.findViewById(R.id.id_beautyImage);
            viewHolder.title = (TextView) view.findViewById(R.id.id_title);
            viewHolder.time = (TextView) view.findViewById(R.id.id_time);
            viewHolder.like = (TextView) view.findViewById(R.id.id_like);
            viewHolder.dislike = (TextView) view.findViewById(R.id.id_dislike);
            viewHolder.fuck = (TextView) view.findViewById(R.id.id_fuck);
        }else {
            view =convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
            viewHolder.beautyImage.setImageBitmap(beauty.getpictureID());
            viewHolder.title.setText(beauty.getTitle());
            viewHolder.time.setText(beauty.getDate());
            viewHolder.like.setText("oo" + " " + beauty.getLike());
            viewHolder.dislike.setText("xx" + " " + beauty.getDislike());
            viewHolder.fuck.setText("oo" + " " + beauty.getFuck());

            return view;
    }
    class ViewHolder{
        ImageView beautyImage;
        TextView title;
        TextView time;
        TextView like;
        TextView dislike;
        TextView fuck;
    }
}
