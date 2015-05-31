package com.mrv.mytest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class MainActivity extends Activity{
    private ViewPager viewPager;

    private int Myscreen1_3;
    private ImageView tabline;
    private int MyCurrentPagetIndex;

    private List<View> list = new ArrayList<View>();
    private MyPageAdapter adapter;

    private ListView beautylistview;
    private ListView duanzilistview;
    private ListView morelistview;

    private List<beauty> beautyList = new ArrayList<beauty>();
    private List<duanzi> duanziList = new ArrayList<duanzi>();

    private String data[] ={"走进科学","无厘头研究","天文","NASA","高科技","环保"
            ,"虚拟现实","数码产品","3D打印","无人机","机器人","TECK"};

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1){
                case 1: {

                }
        }
            }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        tabline = (ImageView) findViewById(R.id.id_tabline);
        adapter = new MyPageAdapter(list);

        beautylistview = (ListView) findViewById(R.id.id_beautylistview);
        duanzilistview = (ListView) findViewById(R.id.id_duanzilistview);
        morelistview = (ListView) findViewById(R.id.id_morelistview);


        initdata();
        inittabline();

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOff, int positionOffpxset) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)
                        tabline.getLayoutParams();
                if (position == 0 && MyCurrentPagetIndex == 0) {
                    lp.leftMargin = positionOffpxset * Myscreen1_3 +
                            MyCurrentPagetIndex * Myscreen1_3;
                } else if (position == 0 && MyCurrentPagetIndex == 1) {
                    lp.leftMargin = positionOffpxset * Myscreen1_3 + (position - 1) * Myscreen1_3;
                } else if (position == 1 && MyCurrentPagetIndex == 1) {
                    lp.leftMargin = positionOffpxset * Myscreen1_3 +
                            MyCurrentPagetIndex * Myscreen1_3;
                } else if (position == 1 && MyCurrentPagetIndex == 2) {
                    lp.leftMargin = positionOffpxset * Myscreen1_3 +
                            (position - 1) * Myscreen1_3;
                }
                tabline.setLayoutParams(lp);


            }

            @Override
            public void onPageSelected(int position) {
                MyCurrentPagetIndex = position;

            }


            @Override
            public void onPageScrollStateChanged(int position) {

            }
            //解决listview和viewpager的冲突问题
            public void onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        viewPager.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        viewPager.requestDisallowInterceptTouchEvent(false);
                        break;
                }
            }

        });

        viewPager.setAdapter(adapter);
    }

    private void initdata() {
        LayoutInflater inflater = getLayoutInflater();

        View beauty = inflater.inflate(R.layout.beuatyfragment, null);
        View morepicture = inflater.inflate(R.layout.morepicturefragment, null);
        View duanzi = inflater.inflate(R.layout.duanzifragment, null);

        ListView listView1 = (ListView) beauty.findViewById(R.id.id_beautylistview);
        ListView listView2 = (ListView) duanzi.findViewById(R.id.id_duanzilistview);
        ListView listView3 = (ListView) morepicture.findViewById(R.id.id_morelistview);

        beautyAdapter adapter2 = new beautyAdapter(MainActivity.this,
                R.layout.beautylayout, beautyList);
        duanziAdapter adapter3 = new duanziAdapter(MainActivity.this
                , R.layout.duanziylayout, duanziList);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_expandable_list_item_1,data);

        initduanzi();
        initbeauty();

        listView1.setAdapter(adapter2);
        listView3.setAdapter(adapter4);
        listView2.setAdapter(adapter3);
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(MainActivity.this,Second.class);
                    startActivity(intent);

            }
        });


        list.add(listView1);
        list.add(listView2);
        list.add(listView3);

    }


    private void initduanzi() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_duan_comments&page=1");
                HttpResponse httpResponse = null;
                try {
                    httpResponse = httpClient.execute(httpGet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = httpResponse.getEntity();
                    String response = null;
                    try {
                        response = EntityUtils.toString(entity, "utf-8");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {

                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String text = jsonObject.getString("comment_content");
                            String title = jsonObject.getString("comment_author");
                            String time = jsonObject.getString("comment_date");
                            int positive = jsonObject.getInt("vote_positive");
                            int negative = jsonObject.getInt("vote_negative");
                            int fuck = jsonObject.getInt("comment_approved");

                            duanzi duanzi = new duanzi(text, title, time, fuck, positive, negative);
                            duanziList.add(duanzi);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Message message=new Message();
                message.arg1=1;
                handler.sendMessage(message);
            }
        }).start();
    }


    private void initbeauty() {
        Bitmap a=readBitMap(MainActivity.this, R.drawable.touxiang);
        beauty beauty1 = new beauty(a, "JBJ1", "2015-03-30", 110, 250, 360);
        beautyList.add(beauty1);
        Bitmap b=readBitMap(MainActivity.this, R.drawable.touxiang);
        beauty beauty2 = new beauty(b, "JBJ2", "2015-03-30", 2222, 250, 360);
        beautyList.add(beauty2);
        Bitmap c=readBitMap(MainActivity.this, R.drawable.touxiang);
        beauty beauty3 = new beauty(c, "JBJ3", "2015-03-30", 110, 250, 360);
        beautyList.add(beauty3);
        Bitmap d=readBitMap(MainActivity.this, R.drawable.touxiang);
        beauty beauty4 = new beauty(d, "JBJ4", "2015-03-30", 110, 250, 3333);
        beautyList.add(beauty4);
        Bitmap e=readBitMap(MainActivity.this, R.drawable.touxiang);
        beauty beauty5 = new beauty(e, "JBJ5", "2015-03-30", 110, 25555, 360);
        beautyList.add(beauty5);
        Bitmap f=readBitMap(MainActivity.this, R.drawable.touxiang);
        beauty beauty6 = new beauty(f, "JBJ6", "2015-03-30", 110, 7777, 360);
        beautyList.add(beauty6);
        Bitmap g=readBitMap(MainActivity.this, R.drawable.touxiang);
        beauty beauty7 = new beauty(g, "JBJ7", "2015-03-30", 1546546, 250, 360);
        beautyList.add(beauty7);
    }



    public static Bitmap readBitMap(Context context, int resId){

        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is,null,opt);
    }



    class MyPageAdapter extends PagerAdapter {
        List<View> viewLists;

        public MyPageAdapter(List<View> viewLists) {
            this.viewLists = viewLists;
        }

        @Override
        public int getCount() {
            return viewLists.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            viewPager.setOffscreenPageLimit(2);
            for (View view : viewLists) {
                try {
                    ViewGroup p = (ViewGroup) view.getParent();
                    if (p.getParent() == null)
                        container.addView(viewLists.get(position));
                    else {
                        //viewPager.removeView(p);
                    }
                }catch (Exception e){

                }
            }
            return viewLists.get(position);
        }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewGroup)container).removeView(viewLists.get(position));
    }
}
    //实现滑动时指示器的滑动
    private void inittabline(){
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        Myscreen1_3 = (outMetrics.widthPixels)/3;
        ViewGroup.LayoutParams lp = tabline.getLayoutParams();
        lp.width=Myscreen1_3;
        tabline.setLayoutParams(lp);
    }

    /**
     * Created by Mr.V on 2015/5/30.
     */
}
