package com.example.chen.myapplication.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chen.myapplication.R;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;

/**
 * Created by Chen on 2016/7/9.
 */
public class Personal_settings_head_portrait_change_page extends TakePhotoActivity {

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
                break;
            case Menu.FIRST + 3:
                Toast.makeText(this, "qx", Toast.LENGTH_LONG).show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void takeSuccess(String imagePath) {
        Log.i("info", "takeSuccess：" + imagePath);
        Toast.makeText(this, "takeSuccess：" + imagePath, Toast.LENGTH_LONG).show();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bm = BitmapFactory.decodeFile(imagePath, options);

        personal_settings_head_portrait_change_page_image.setImageBitmap(bm);

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

