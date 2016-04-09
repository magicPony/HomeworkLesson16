package com.example.taras.homeworklesson16;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by taras on 09.04.16.
 */
public final class CashData {
    public static ArrayList<Bitmap> mBmCat1 = new ArrayList<>(),
                                    mBmCat2 = new ArrayList<>(),
                                    mBmCat3 = new ArrayList<>();

    public static int getItemCount() {
        return mBmCat1.size();
    }

    public static void initCatsRow(CustomViewHolder holder, int position) {
        holder.ivCat1.setImageBitmap(mBmCat1.get(position));
        holder.ivCat2.setImageBitmap(mBmCat2.get(position));
        holder.ivCat3.setImageBitmap(mBmCat3.get(position));
    }
}
