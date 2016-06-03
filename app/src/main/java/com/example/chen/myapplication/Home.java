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
            "http://www.xdnice.com/upload/2011_08/110810060968291.jpg", "http://www.fjsen.com/images/attachement/jpg/site1/2011-09-22/4529639385466683860.jpg", "http://c.hiphotos.baidu.com/baike/c0%3Dba" +
            "ike150%2C5%2C5%2C150%2C50/sign=8daa7e24252dd42a4b0409f9625230d0/4a36acaf2edda3cc2659936d01e93901213f9239.jpg", "http://f.hiphotos.baidu.com/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=f41968856b8" +
            "1800a7ae8815cd05c589f/9345d688d43f87948183d924d51b0ef41bd53abf.jpg"};

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
