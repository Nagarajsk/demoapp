package com.example.bitjini.demoapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class MainActivity extends Navigation_Drawer {

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};

    //Recycler view
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;


    String[] title;
    int [] Img_res = {R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3,R.drawable.icon_4,R.drawable.icon_5};
    ArrayList<DataProvider> arrayList = new ArrayList<DataProvider>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();

        recyclerMethod();


    }// End of ONCREATE()


    public void initializeVariables(){

        //Carousel view
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
//        carouselView.setIndicatorVisibility(View.INVISIBLE);
        carouselView.setSlideInterval(3000);
        
        //RecyclerView
        recyclerView = (RecyclerView)findViewById(R.id.cardList);
        title = getResources().getStringArray(R.array.cardview_title);

    }


    public void recyclerMethod(){

        //RecyclerView
        int i= 0;//initially i=0 for first image in cardview

        for (String name : title)
        {
            DataProvider dataprovider = new DataProvider(Img_res[i],name);
            arrayList.add(dataprovider);

            i++;
        }

        adapter = new RecyclerAdapter(arrayList,this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //RecyclerView

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(MainActivity.this, "Position :"+position, Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0 :
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/appointment.html");
                        break;

                    case 1 :
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



    //This is called for CarouselView of images
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };



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
}