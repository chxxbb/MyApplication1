package com.example.chen.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Chen on 2016/5/30.
 */
public class registered extends Activity implements View.OnClickListener {

    ImageView registered_exit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered);

        init();

    }

    private void init() {
        registered_exit = (ImageView) findViewById(R.id.registered_exit);
        registered_exit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registered_exit:

                finish();

                break;
        }
    }
}
