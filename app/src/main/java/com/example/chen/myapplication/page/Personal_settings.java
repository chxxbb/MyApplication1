package com.example.chen.myapplication.page;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.view.Personal_information_changes_page;

/**
 * Created by Chen on 2016/7/7.
 */
public class Personal_settings extends Fragment {

    RelativeLayout personal_settings_basic_information_relativelayout = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.personal_settings, container, false);

        personal_settings_basic_information_relativelayout = (RelativeLayout) view.findViewById(R.id.personal_settings_basic_information_relativelayout);

        personal_settings_basic_information_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Personal_information_changes_page.class);
                getActivity().startActivity(intent);
            }
        });


        return view;
    }
}
