package com.example.chen.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.youth.banner.Banner;

/**
 * Created by Chen on 2016/6/3.
 */
public class Home extends Activity {

    private Banner home_banner = null;
    //Banner图片地址
    String[] images = new String[]{"http://img.51ztzj.com/upload/image/20130912/dn201309124003_670x419.jpg", "http://g.hiphotos.baidu.com/zhidao/pic/item/6159252dd42a2834421cdfd25ab5c9ea14cebfb5.jpg",
            "http://top.chinadaily.com.cn/img/site1/20160525/64006a47a30318afd83a04.jpeg", "http://www.fjsen.com/images/attachement/jpg/site1/2011-09-22/4529639385466683860.jpg",
            "http://top.chinadaily.com.cn/img/site1/20160525/64006a47a30318afd83a06.jpeg", "http://www.lajiaobagua.com/file/image/2016/0526/14642521890209.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        initViewFlipper();

    }

    private void initViewFlipper() {
        home_banner = (Banner) findViewById(R.id.home_banner);
        home_banner.setDelayTime(50000);//设置轮播间隔时间
        home_banner.setImages(images);//可以选择设置图片网址，或者资源文件
        home_banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {//设置Banner点击事件
            @Override
            public void OnBannerClick(View view, int position) {

            }
        });

    }


}
