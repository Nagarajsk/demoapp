package com.example.bitjini.demoapp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bitjini on 16/12/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private ArrayList<DataProvider> arrayList = new ArrayList<DataProvider>();
    Context context;


    public RecyclerAdapter(ArrayList<DataProvider> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        final RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position)
    {

        DataProvider dataprovider = arrayList.get(position);
        holder.imageView.setImageResource(dataprovider.getImg_res());
        holder.card_name.setText(dataprovider.get_title());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageView;
        TextView card_name;
        public  RecyclerViewHolder(View view)
        {
            super(view);

            imageView = (ImageView) view.findViewById(R.id.image_icon);
            card_name = (TextView) view.findViewById(R.id.card_name);

            //To change the FONT FAMILY of cardview title
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/poppins-v5-latin-regular.ttf");
            card_name.setTypeface(typeface);

        }
    }
}
