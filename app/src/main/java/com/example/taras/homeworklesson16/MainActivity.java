package com.example.taras.homeworklesson16;

import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public final class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_AM);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        findViewById(R.id.btn_add_AM).setOnClickListener(this);
    }

    /*private void initCatsRow(ImageView ivCat1, ImageView ivCat2, ImageView ivCat3) {
        getCat(Constants.SIZE_SMALL_TAG, ivCat1);
        getCat(Constants.SIZE_SMALL_TAG, ivCat2);
        getCat(Constants.SIZE_SMALL_TAG, ivCat3);
    }*/

    private void getCat(String size, final ImageView imageView, final ArrayList<Bitmap> cash) {
        final AsyncRequest<?>[] asyncRequest = new AsyncRequest<?>[1];

        if (!checkStatusNetworks()) {
            Toast
                    .makeText(this, "No network connection", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        final IResponseListener<Bitmap> catBitmapListener = new IResponseListener<Bitmap>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish(boolean isSuccess, Bitmap response) {
                imageView.setImageBitmap(response);
                cash.add(response);
            }
        };

        IResponseListener<Cat> catListener = new IResponseListener<Cat>() {
            @Override
            public void onStart() {
                // do nothing
            }

            @Override
            public void onFinish(boolean isSuccess, Cat response) {
                if (!isSuccess) {
                    Toast
                            .makeText(MainActivity.this, "Can't load cat", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                asyncRequest[0] = new AsyncRequest<Bitmap>()
                        .classType(Bitmap.class)
                        .responseListener(catBitmapListener);
                asyncRequest[0].execute(response.url);
            }
        };

        imageView.setImageResource(0);

        asyncRequest[0] = new AsyncRequest<Cat>()
                .classType(Cat.class)
                .responseListener(catListener)
                .addParam("format", "xml")
                .addParam("results_per_page", 1)
                .addParam("size", size);
        asyncRequest[0].execute(Constants.BASE_URL + Constants.GET_IMAGES);
    }

    private boolean checkStatusNetworks() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo wifiInfo    = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return wifiInfo.isConnected() || networkInfo.isConnected();
    }

    @Override
    public void onClick(View v) {
        ImageView ivFake = new ImageView(this);
        getCat(Constants.SIZE_SMALL_TAG, ivFake, CashData.mBmCat1);
        getCat(Constants.SIZE_SMALL_TAG, ivFake, CashData.mBmCat2);
        getCat(Constants.SIZE_SMALL_TAG, ivFake, CashData.mBmCat3);
        //recyclerViewAdapter.notifyDataSetChanged();
        recyclerViewAdapter.notifyItemInserted(recyclerViewAdapter.getItemCount());

        Toast
                .makeText(this, Integer.toString(recyclerViewAdapter.getItemCount()), Toast.LENGTH_LONG)
                .show();
    }

    public void addCatsRow(CustomViewHolder holder) {
        getCat(Constants.SIZE_SMALL_TAG, holder.ivCat1, CashData.mBmCat1);
        getCat(Constants.SIZE_SMALL_TAG, holder.ivCat2, CashData.mBmCat2);
        getCat(Constants.SIZE_SMALL_TAG, holder.ivCat3, CashData.mBmCat3);
    }
}
