package com.example.chen.myapplication.page;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.chen.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen on 2016/7/18.
 */
public class Health_management_page extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.health_management_page, container, false);
        //获取ListView控件,并创建自定义适配器
        ListView listView = (ListView) view.findViewById(R.id.health_management_page_listview);

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.health_management_page_item,
                new String[]{"time", "title", "content", "img"},
                new int[]{R.id.health_management_page_item_title_time_text, R.id.health_management_page_item_content_title_text,
                        R.id.health_management_page_item_content_text, R.id.health_management_page_item_content_image});

        listView.setAdapter(adapter);


        //这段是让SimpleAdapter可以放图片进去
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {

                //判断是否为我们要处理的对象
                if (view instanceof ImageView && data instanceof Bitmap) {
                    ImageView iv = (ImageView) view;
                    iv.setImageBitmap((Bitmap) data);
                    return true;
                } else

                    return false;
            }
        });

        return view;
    }

    public List<Map<String, Object>> getData() {

        //这里是要填入的图片,转成Bitmap格式
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.health_management_page_item);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("time", "2016-7-1");
        map.put("title", "睡眠 ： ");
        map.put("content", "21:00入睡 睡眠状况良好");
        map.put("img", bitmap);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("time", "2016-7-1");
        map.put("title", "睡眠 ： ");
        map.put("content", "21:00入睡 睡眠状况良好");
        map.put("img", bitmap);
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("time", "2016-7-1");
        map.put("title", "睡眠 ： ");
        map.put("content", "21:00入睡 睡眠状况良好");
        list.add(map);


        return list;
    }
}
