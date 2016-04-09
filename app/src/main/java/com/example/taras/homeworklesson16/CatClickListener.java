package com.example.taras.homeworklesson16;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * Created by taras on 09.04.16.
 */
public class CatClickListener implements View.OnClickListener {
    private Bitmap image;
    private MainActivity mainActivity;

    public CatClickListener(MainActivity mainActivity, Bitmap image) {
        this.image = image;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
        ShowCatFragment showCatFragment = new ShowCatFragment(image);

        mainActivity
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rl_container_AM, showCatFragment, Constants.SHOW_CAT_FRAGMENT_TAG)
                .addToBackStack(Constants.SHOW_CAT_FRAGMENT_TAG)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
