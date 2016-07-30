package com.example.chen.myapplication.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.data.User;
import com.google.gson.Gson;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import okhttp3.OkHttpClient;

/**
 * Created by Chen on 2016/7/9.
 */
public class Personal_settings_head_portrait_change_page extends TakePhotoActivity {

    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    User user = null;

    String image_path = null;

    String image_path_name = null;

    ImageView personal_information_changes_page_head_portrait_change_menu = null;
    ImageView personal_settings_head_portrait_change_page_image = null;

    TakePhoto take = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_settings_head_portrait_change_page);

        take = getTakePhoto();

        personal_information_changes_page_head_portrait_change_menu = (ImageView) findViewById(R.id.personal_information_changes_page_head_portrait_change_menu);

        personal_information_changes_page_head_portrait_change_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Personal_settings_head_portrait_change_page.this.openOptionsMenu();
            }
        });

        personal_settings_head_portrait_change_page_image = (ImageView) findViewById(R.id.personal_settings_head_portrait_change_page_image);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, Menu.FIRST + 1, 1, "选择图片");

        menu.add(Menu.NONE, Menu.FIRST + 2, 2, "保存头像");

        menu.add(Menu.NONE, Menu.FIRST + 3, 2, "取消");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case Menu.FIRST + 1:
                Toast.makeText(this, "tp", Toast.LENGTH_LONG).show();
                take.onPicSelectOriginal();
                break;
            case Menu.FIRST + 2:
                Toast.makeText(this, "bc", Toast.LENGTH_LONG).show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (image_path_name != null) {
                            File file = new File(image_path);
                            OkHttpUtils.post()
                                    .addFile("image", image_path_name, file)
                                    .url(HTTP_data.http_data + "/test")
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(okhttp3.Call call, Exception e, int id) {
                                            System.out.println("连接失败!!!!!!!");
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            System.out.println("连接成功!!!!!!");
                                        }
                                    });

                        }
                    }
                }).start();

                break;
            case Menu.FIRST + 3:
                Toast.makeText(this, "qx", Toast.LENGTH_LONG).show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void takeSuccess(String imagePath) {
        Toast.makeText(this, imagePath, Toast.LENGTH_LONG).show();
        image_path = imagePath;
        System.out.println(image_path);

        String[] names = image_path.split("/");
        for (int i = 0; i < names.length; i++) {
            image_path_name = names[i];
        }
        System.out.println(image_path_name);

        if (image_path != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(imagePath, options);

            personal_settings_head_portrait_change_page_image.setImageBitmap(bm);
        }

        super.takeSuccess(imagePath);
    }

    @Override
    public void takeFail(String msg) {
        Log.w("info", "takeFail:" + msg);
        Toast.makeText(this, "takeFail:" + msg, Toast.LENGTH_LONG).show();
        super.takeFail(msg);
    }

    @Override
    public void takeCancel() {
        Log.w("info", "用户取消");
        Toast.makeText(this, "333333", Toast.LENGTH_LONG).show();
        super.takeCancel();
    }

}

