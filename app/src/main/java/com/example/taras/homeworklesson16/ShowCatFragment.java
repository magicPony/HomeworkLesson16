package com.example.taras.homeworklesson16;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public final class ShowCatFragment extends Fragment implements View.OnClickListener {
    private Bitmap image;

    public ShowCatFragment(Bitmap image) {
        this.image = image;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_cat, container, false);
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_cat_FSC);
        imageView.setImageBitmap(image);
    }

    @Override
    public void onClick(View v) {
        getActivity().onBackPressed();
    }
}
