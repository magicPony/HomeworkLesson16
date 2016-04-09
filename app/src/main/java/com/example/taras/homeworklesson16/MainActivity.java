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
import android.widget.Toast;

import java.util.ArrayList;

public final class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_AM);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        findViewById(R.id.btn_add_AM).setOnClickListener(this);
    }

    private void getCat(String size, final ArrayList<Bitmap> cash, final boolean notify) {
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
                //imageView.setImageBitmap(response);
                cash.add(response);

                if (notify) {
                    adapter.notifyDataSetChanged();
                }
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
        getCat(Constants.SIZE_MED_TAG, CashData.mBmCat1, false);
        getCat(Constants.SIZE_MED_TAG, CashData.mBmCat2, false);
        getCat(Constants.SIZE_MED_TAG, CashData.mBmCat3, true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(Constants.CAT1_TAG, CashData.mBmCat1);
        outState.putSerializable(Constants.CAT2_TAG, CashData.mBmCat2);
        outState.putSerializable(Constants.CAT3_TAG, CashData.mBmCat3);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        CashData.mBmCat1 = (ArrayList<Bitmap>) savedInstanceState.getSerializable(Constants.CAT1_TAG);
        CashData.mBmCat2 = (ArrayList<Bitmap>) savedInstanceState.getSerializable(Constants.CAT2_TAG);
        CashData.mBmCat3 = (ArrayList<Bitmap>) savedInstanceState.getSerializable(Constants.CAT3_TAG);
    }
}