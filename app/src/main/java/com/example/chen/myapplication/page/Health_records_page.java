package com.example.chen.myapplication.page;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chen.myapplication.R;

/**
 * Created by Chen on 2016/7/19.
 */
public class Health_records_page extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.health_records_page, container, false);
        return view;
    }
}
