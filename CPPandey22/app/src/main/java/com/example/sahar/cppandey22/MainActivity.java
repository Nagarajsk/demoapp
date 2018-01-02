package com.example.sahar.cppandey22;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sahar on 23/12/17.
 */

public class MainActivity extends DrawerBaseActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ImageButton btn_facebook,btn_google,btn_twitter,btn_rss;

    ViewPager viewPager;
    int images[] = {R.drawable.images1, R.drawable.images2, R.drawable.images3, R.drawable.images4};
    CustomPagerAdapter myCustomPagerAdapter;

    private Handler handler;
    private int delay = 3000; //milliseconds
    private int page = 0;

    Runnable runnable = new Runnable() {
        public void run() {
            if (myCustomPagerAdapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            viewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        btn_facebook=(ImageButton) findViewById(R.id.facebook_btn);
        btn_google=(ImageButton)findViewById(R.id.google_btn);
        btn_twitter=(ImageButton)findViewById(R.id.twitter_btn);
        btn_rss=(ImageButton)findViewById(R.id.rss_feed_btn);

        btn_facebook.setOnClickListener(this);
        btn_google.setOnClickListener(this);
        btn_twitter.setOnClickListener(this);
        btn_rss.setOnClickListener(this);

        String[] title;
        int[] Img_res = {R.drawable.option1, R.drawable.option2, R.drawable.option3, R.drawable.option4, R.drawable.option5};
        ArrayList<DataProvider> arrayList = new ArrayList<DataProvider>();

        recyclerView = (RecyclerView) findViewById(R.id.cardList);
        title = getResources().getStringArray(R.array.cardview_title);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        handler = new Handler();

        myCustomPagerAdapter = new CustomPagerAdapter(MainActivity.this, images);
        viewPager.setAdapter(myCustomPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //recycler view
            int i = 0;
                 for (String name : title) {
                      DataProvider dataprovider = new DataProvider(Img_res[i], name);
                      arrayList.add(dataprovider);
                      i++;
                 }

        adapter = new RecyclerAdapter(arrayList,this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        /**RecyclerView*/
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
              switch (position) {
                    case 0:
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/appointment.html");
                        break;

                    case 1:
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/doctors.html");
                        break;

                    case 2:
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/services.html");
                        break;

                    case 3:
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/locations.html");
                        break;

                    case 4:
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/contact.html");
                        break;
              }
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

    public void gotoIntent(String url){
        Intent myIntent = new Intent(MainActivity.this, WebViewActivity.class);
        myIntent.putExtra("url",url);
        startActivity(myIntent);
    }

    @Override
    public void onClick(View v) {
        String url;
        switch (v.getId()) {
            case R.id.facebook_btn:
                url="https://en-gb.facebook.com/login/";
                gotoIntent(url);
                break;

            case R.id.google_btn:
                url="https://plus.google.com/apps/activities";
                gotoIntent(url);
                break;

            case R.id.twitter_btn:
                url="https://twitter.com/login";
                gotoIntent(url);
                break;

            case R.id.rss_feed_btn:
                url="https://www.rss.com/login";
                gotoIntent(url);
                break;
        }
    }

    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    @Override
        protected void onResume() {
            super.onResume();
            handler.postDelayed(runnable, delay);
        }
    @Override
        protected void onPause() {
            super.onPause();
            handler.removeCallbacks(runnable);
        }
}