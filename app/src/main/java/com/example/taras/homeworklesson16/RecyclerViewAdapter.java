package com.example.taras.homeworklesson16;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by taras on 09.04.16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);

        CustomViewHolder holder = new CustomViewHolder(view);
        holder.ivCat1.setImageBitmap(CashData.lastCat1());
        holder.ivCat2.setImageBitmap(CashData.lastCat2());
        holder.ivCat3.setImageBitmap(CashData.lastCat3());

        return holder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        CashData.initRow(holder, position);
    }

    @Override
    public int getItemCount() {
        return CashData.getItemCount();
    }
}
