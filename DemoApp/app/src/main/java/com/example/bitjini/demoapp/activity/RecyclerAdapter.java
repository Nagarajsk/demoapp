package com.example.bitjini.demoapp.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bitjini.demoapp.R;

import java.util.ArrayList;

/**
 * Created by bitjini on 16/12/17.
 */




public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private ArrayList<DataProvider> arrayList = new ArrayList<DataProvider>();



    public RecyclerAdapter(ArrayList<DataProvider> arrayList) {
        this.arrayList = arrayList;
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
        holder.title.setText(dataprovider.get_title());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageView;
        TextView title;
        public  RecyclerViewHolder(View view)
        {
            super(view);

            imageView = (ImageView) view.findViewById(R.id.image_icon);
            title = (TextView) view.findViewById(R.id.title);

        }
    }
}
