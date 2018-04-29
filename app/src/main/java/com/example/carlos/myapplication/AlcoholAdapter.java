package com.example.carlos.myapplication;

/**
 * Created by Carlos on 30/4/18.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AlcoholAdapter extends RecyclerView.Adapter<AlcoholAdapter.MyViewHolder> {

    private List<Alcohol> alcoholList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, volume, price;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            volume = (TextView) view.findViewById(R.id.volume);
            price = (TextView) view.findViewById(R.id.price);
        }
    }


    public AlcoholAdapter(List<Alcohol> alcoholList) {
        this.alcoholList = alcoholList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alcohol_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Alcohol alcohol = alcoholList.get(position);
        holder.name.setText(alcohol.getName());
        // Mirar aquí formato
        holder.volume.setText(Double.toString(alcohol.getVolume())+" ml");
        holder.price.setText(Double.toString(alcohol.getPrice())+" €");
    }

    @Override
    public int getItemCount() {
        return alcoholList.size();
    }
}
