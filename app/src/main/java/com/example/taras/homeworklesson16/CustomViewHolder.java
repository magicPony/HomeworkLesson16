package com.example.taras.homeworklesson16;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by taras on 09.04.16.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivCat1, ivCat2, ivCat3;

    public CustomViewHolder(View itemView) {
        super(itemView);
        ivCat1 = (ImageView) itemView.findViewById(R.id.iv_cat1_RL);
        ivCat2 = (ImageView) itemView.findViewById(R.id.iv_cat2_RL);
        ivCat3 = (ImageView) itemView.findViewById(R.id.iv_cat3_RL);
    }
}
