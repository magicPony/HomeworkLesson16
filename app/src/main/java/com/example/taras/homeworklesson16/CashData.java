package com.example.taras.homeworklesson16;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by taras on 09.04.16.
 */
public final class CashData implements Serializable {
    public static ArrayList<Bitmap> mBmCat1 = new ArrayList<>(),
                                    mBmCat2 = new ArrayList<>(),
                                    mBmCat3 = new ArrayList<>();

    public static int getItemCount() {
        return mBmCat1.size();
    }

    public static void initRow(CustomViewHolder holder, int position) {
        holder.ivCat1.setImageBitmap(mBmCat1.get(position));
        holder.ivCat2.setImageBitmap(mBmCat2.get(position));
        holder.ivCat3.setImageBitmap(mBmCat3.get(position));
    }

    public static Bitmap lastCat1() {
        return mBmCat1.get(mBmCat1.size() - 1);
    }

    public static Bitmap lastCat2() {
        return mBmCat2.get(mBmCat2.size() - 1);
    }

    public static Bitmap lastCat3() {
        return mBmCat3.get(mBmCat3.size() - 1);
    }
}
