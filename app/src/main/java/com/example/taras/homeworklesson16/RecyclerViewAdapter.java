package com.example.taras.homeworklesson16;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by taras on 08.04.16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private MainActivity mainActivity;

    public RecyclerViewAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);

        CustomViewHolder holder = new CustomViewHolder(view);
        mainActivity.addCatsRow(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        CashData.initCatsRow(holder, position);
    }

    @Override
    public int getItemCount() {
        return CashData.getItemCount();
    }
}
