package com.mrv.mytest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mr.V on 2015/5/30.
 */
public class duanziAdapter extends ArrayAdapter<duanzi> {
    private int resourceID;

   public duanziAdapter(Context context, int textViewResourceID, List<duanzi> objects){
       super(context,textViewResourceID,objects);
       resourceID=textViewResourceID;
   }
    public View getView(int position,View convertView,ViewGroup parent){
        duanzi duanzi = getItem(position);
        View view ;
        ViewHolder viewHolder;
        if(convertView==null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, null);
            viewHolder = new ViewHolder();
            viewHolder.duanziText = (TextView) view.findViewById(R.id.id_text);
            viewHolder.title = (TextView) view.findViewById(R.id.id_title);
            viewHolder.time = (TextView) view.findViewById(R.id.id_time);
            viewHolder.like = (TextView) view.findViewById(R.id.id_like);
            viewHolder.dislike = (TextView) view.findViewById(R.id.id_dislike);
            viewHolder.fuck = (TextView) view.findViewById(R.id.id_fuck);
        }else {
            view =convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
            viewHolder.duanziText.setText(duanzi.getText());
            viewHolder.title.setText(duanzi.getTitle());
            viewHolder.time.setText(duanzi.getDate());
            viewHolder.like.setText("oo" + " " + duanzi.getLike());
            viewHolder.dislike.setText("xx" + " " + duanzi.getDislike());
            viewHolder.fuck.setText("oo" + " " + duanzi.getFuck());
            return view;
    }
    class ViewHolder{
        TextView duanziText;
        TextView title;
        TextView time;
        TextView like;
        TextView dislike;
        TextView fuck;
    }
}
