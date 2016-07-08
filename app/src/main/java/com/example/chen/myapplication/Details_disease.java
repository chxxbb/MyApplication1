package com.example.chen.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen on 2016/7/6.
 */
public class Details_disease extends Activity {

    ExpandableListView details_disease_expandable = null;
    List<String> parent = null;
    Map<String, List<String>> map = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_disease);

        details_disease_expandable = (ExpandableListView) findViewById(R.id.details_disease_expandableListView);

        initData();

        details_disease_expandable.setAdapter(new MyAdapter());

    }

    private void initData() {
        parent = new ArrayList<String>();
        parent.add("症状");
        parent.add("危害");
        parent.add("治疗");
        parent.add("预防");

        map = new HashMap<String, List<String>>();

        List<String> list1 = new ArrayList<String>();
        list1.add("测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文" +
                "本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测" +
                "试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本" +
                "测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本");
        map.put("症状", list1);

        List<String> list2 = new ArrayList<String>();
        list2.add("child-1");
        map.put("危害", list2);

        List<String> list3 = new ArrayList<String>();
        list3.add("child-1");
        map.put("治疗", list3);

        List<String> list4 = new ArrayList<String>();
        list4.add("child-1");
        map.put("预防", list4);

    }


    class MyAdapter extends BaseExpandableListAdapter {

        //得到子item需要关联的数据
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            String key = parent.get(groupPosition);
            return (map.get(key).get(childPosition));
        }

        //得到子item的ID
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //设置子item的组件
        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            String key = Details_disease.this.parent.get(groupPosition);
            String info = map.get(key).get(childPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) Details_disease.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.details_disease_item_sun, null);
            }
            TextView tv = (TextView) convertView
                    .findViewById(R.id.details_disease_sun_item_text);
            tv.setText(info);


            return convertView;
        }

        //获取当前父item下的子item的个数
        @Override
        public int getChildrenCount(int groupPosition) {
            String key = parent.get(groupPosition);
            int size = map.get(key).size();
            return size;
        }

        //获取当前父item的数据
        @Override
        public Object getGroup(int groupPosition) {
            return parent.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return parent.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        //设置父item组件
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) Details_disease.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.details_disease_item_futher, null);
            }
            TextView tv = (TextView) convertView
                    .findViewById(R.id.details_disease_futher_item_text);
            tv.setText(Details_disease.this.parent.get(groupPosition));

            ImageView img = (ImageView) convertView.findViewById(R.id.details_disease_futher_item_image);
            if (details_disease_expandable.isGroupExpanded(groupPosition)) {

                img.setImageResource(R.mipmap.details_disease_image_up);

            } else {
                img.setImageResource(R.mipmap.details_disease_image_down);
            }

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

}