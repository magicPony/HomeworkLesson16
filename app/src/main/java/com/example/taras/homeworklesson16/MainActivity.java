package com.example.taras.homeworklesson16;

import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public final class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.ll_AM);
        findViewById(R.id.btn_add_AM).setOnClickListener(this);
    }

    private void getCat(String size, final ImageView imageView, final ArrayList<Bitmap> cash, boolean initListener) {
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
                imageView.setOnClickListener(new CatClickListener(MainActivity.this, response));
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
        LinearLayout llCatsRow = (LinearLayout) getLayoutInflater().inflate(R.layout.row_layout, null);
        ImageView ivCat1, ivCat2, ivCat3;
        ivCat1 = (ImageView) llCatsRow.findViewById(R.id.iv_cat1_RL);
        ivCat2 = (ImageView) llCatsRow.findViewById(R.id.iv_cat2_RL);
        ivCat3 = (ImageView) llCatsRow.findViewById(R.id.iv_cat3_RL);

        getCat(Constants.SIZE_MED_TAG, ivCat1, CashData.mBmCat1, true);
        getCat(Constants.SIZE_MED_TAG, ivCat2, CashData.mBmCat2, true);
        getCat(Constants.SIZE_MED_TAG, ivCat3, CashData.mBmCat3, true);

        linearLayout.addView(llCatsRow);
        linearLayout.invalidate();
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
