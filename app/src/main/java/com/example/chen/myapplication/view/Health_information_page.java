package com.example.chen.myapplication.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.data.HealthPedia;
import com.example.chen.myapplication.utils.Http_Bitmap;

/**
 * Created by Chen on 2016/6/17.
 */
public class Health_information_page extends Activity {

    HealthPedia healthPedia = null;
    TextView health_information_page_title_text = null, health_information_page_fromtime_text = null;
    TextView health_information_page_content_text = null;
    ImageView health_information_page_image = null;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            health_information_page_image.setImageBitmap(bitmap);
            HTTP_data.healthPedia = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_information_page);

        init();

        init_http_data();

    }

    private void init() {
        health_information_page_title_text = (TextView) findViewById(R.id.health_information_page_title_text);
        health_information_page_fromtime_text = (TextView) findViewById(R.id.health_information_page_fromtime_text);
        health_information_page_content_text = (TextView) findViewById(R.id.health_information_page_content_text);

        health_information_page_image = (ImageView) findViewById(R.id.health_information_page_image);

    }

    private void init_http_data() {
        healthPedia = HTTP_data.healthPedia;

        health_information_page_title_text.setText(healthPedia.getTitle());
        health_information_page_fromtime_text.setText(healthPedia.getTime());
        health_information_page_content_text.setText(healthPedia.getContent());
        init_http_bitmap();

    }

    private void init_http_bitmap() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap = new Http_Bitmap().GetLocalOrNetBitmap_NoCompression(healthPedia.getArtwork());

                Message message = new Message();
                message.obj = bitmap;
                handler.sendMessage(message);

            }
        }).start();

    }

}
