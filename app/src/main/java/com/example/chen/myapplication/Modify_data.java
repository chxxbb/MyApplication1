package com.example.chen.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Chen on 2016/6/27.
 */
public class Modify_data extends Activity {

    Button modify_data_gender_boy;
    Button modify_data_gender_girl;
    int gender = 0; //用来记录用户性别选中情况

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_data);

        modify_data_gender_boy = (Button) findViewById(R.id.modify_data_gender_boy);
        modify_data_gender_girl = (Button) findViewById(R.id.modify_data_gender_girl);

        modify_data_gender_boy.setBackgroundResource(R.drawable.modify_data_shape1);

        modify_data_gender_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gender == 0) {  //点击男按钮后，判断是否选择的男性，如果不是，则改变按钮背景

                } else {
                    gender = 0;
                    modify_data_gender_boy.setBackgroundResource(R.drawable.modify_data_shape1);
                    modify_data_gender_girl.setBackgroundResource(R.drawable.modify_data_shape2);
                }
            }
        });

        modify_data_gender_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gender == 1) {  //点击女按钮后，判断是否选择的男女性，如果不是，则改变按钮背景

                } else {
                    gender = 1;
                    modify_data_gender_girl.setBackgroundResource(R.drawable.modify_data_shape1);
                    modify_data_gender_boy.setBackgroundResource(R.drawable.modify_data_shape2);
                }
            }
        });


    }
}
