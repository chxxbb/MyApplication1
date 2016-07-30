package com.example.chen.myapplication.page;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.Doctor;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.data.User;
import com.example.chen.myapplication.data.User_data;
import com.example.chen.myapplication.utils.Http_Bitmap;
import com.example.chen.myapplication.view.My_doctor;
import com.example.chen.myapplication.view.My_reservation;
import com.example.chen.myapplication.view.Personal_information_changes_page;
import com.example.chen.myapplication.view.Setting;

/**
 * Created by Chen on 2016/7/7.
 */
public class Personal_settings extends Fragment {

    User user = null;

    RelativeLayout personal_settings_basic_information_relativelayout = null;
    RelativeLayout personal_settings_setting_relativelayout = null;
    RelativeLayout personal_settings_doctor_relativelayout = null;
    RelativeLayout personal_settings_reservation_relativelayout = null;

    TextView personal_settings_basic_information_name_text = null;

    ImageView personal_settings_basic_information_image = null;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    User_data.user.setBitmap_icon((Bitmap) msg.obj);
                    personal_settings_basic_information_image.setImageBitmap((Bitmap) msg.obj);
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.personal_settings, container, false);

        user = User_data.user;

        personal_settings_basic_information_image = (ImageView) view.findViewById(R.id.personal_settings_basic_information_image);
        personal_settings_basic_information_name_text = (TextView) view.findViewById(R.id.personal_settings_basic_information_name_text);

        personal_settings_basic_information_name_text.setText(user.getName());

        init_http_bitmap();

        personal_settings_basic_information_relativelayout = (RelativeLayout) view.findViewById(R.id.personal_settings_basic_information_relativelayout);

        personal_settings_basic_information_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Personal_information_changes_page.class);
                getActivity().startActivity(intent);
            }
        });

        personal_settings_setting_relativelayout = (RelativeLayout) view.findViewById(R.id.personal_settings_setting_relativelayout);
        personal_settings_setting_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Setting.class);
                getActivity().startActivity(intent);
            }
        });


        personal_settings_doctor_relativelayout = (RelativeLayout) view.findViewById(R.id.personal_settings_doctor_relativelayout);
        personal_settings_doctor_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), My_doctor.class);
                getActivity().startActivity(intent);
            }
        });


        personal_settings_reservation_relativelayout = (RelativeLayout) view.findViewById(R.id.personal_settings_reservation_relativelayout);
        personal_settings_reservation_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), My_reservation.class);
                getActivity().startActivity(intent);
            }
        });


        return view;
    }

    private void init_http_bitmap() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http_Bitmap http_bitmap = new Http_Bitmap();
                Bitmap bitmap = null;
                bitmap = http_bitmap.GetLocalOrNetBitmap_NoCompression(user.getIcon());

                if (bitmap != null) {
                    Message message = new Message();
                    message.what = 1;
                    message.obj = bitmap;
                    handler.sendMessage(message);
                }
            }
        }).start();


    }
}
