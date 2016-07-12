package com.example.chen.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chen.myapplication.page.ListAdapter;
import com.example.chen.myapplication.page.ListItem;
import com.example.chen.myapplication.R;
import com.zxl.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chen on 2016/6/7.
 */
public class Doctor_warehouse extends AppCompatActivity {

    //菜单定义区
    DropDownMenu mDropDownMenu;     //菜单控件
    private String headers[] = {"医院", "科室", "职称"};     //菜单标题,必须和types变量相等条目数
    private int[] types = new int[]{DropDownMenu.TYPE_LIST_CITY, DropDownMenu.TYPE_LIST_SIMPLE, DropDownMenu.TYPE_LIST_SIMPLE};     //菜单类型(CITY:文字在左侧,选中后右侧有勾选图案,SIMPLE:文字居中,选中后文字变色,CUSTOM:自定义,GRID:网状)
    private String citys[] = {"不限", "成都", "深圳"};
    private String department[] = {"不限", "行为发育", "小儿神经", "小儿内分泌", "儿童保健", "儿童皮肤", "耳鼻咽喉科", "过敏反应", "小儿泌尿外科", "眼科", "儿童口腔",
            "消化系统", "新生儿", "呼吸系统", "心血管", "小儿肾病", "小儿风湿病"};
    private String title[] = {"不限", "主治医师", "教授", "副教授"};


    //内容列表定义区
    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_warehouse);
        mDropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);
        initView();
    }

    private void initView() {


        View contentView = getLayoutInflater().inflate(R.layout.doctor_warehouse_view, null);  //内容页面载入
        listView = (ListView) contentView.findViewById(R.id.doctor_warehouse_listview);
        ListAdapter listAdapter = new ListAdapter(this);
        listView.setAdapter(listAdapter);
        List<ListItem> list = new ArrayList<ListItem>();
        //设定该窗口类型,并发送一个数据(该数据可自定义)
        ListItem item = new ListItem(2, "wori");
        list.add(item);
        item = null;
        //设定该窗口类型,并发送一个数据(该数据可自定义)
        item = new ListItem(2, "wori");
        list.add(item);
        item = null;
        //设定该窗口类型,并发送一个数据(该数据可自定义)
        item = new ListItem(2, "wori");
        list.add(item);
        item = null;
        //设定该窗口类型,并发送一个数据(该数据可自定义)
        item = new ListItem(2, "wori");
        list.add(item);
        item = null;
        //设定该窗口类型,并发送一个数据(该数据可自定义)
        item = new ListItem(2, "wori");
        list.add(item);
        item = null;
        //设定该窗口类型,并发送一个数据(该数据可自定义)
        item = new ListItem(2, "wori");
        list.add(item);
        item = null;
        //设定该窗口类型,并发送一个数据(该数据可自定义)
        item = new ListItem(2, "wori");
        list.add(item);
        item = null;//设定该窗口类型,并发送一个数据(该数据可自定义)
        item = new ListItem(2, "wori");
        list.add(item);
        item = null;
        //设定该窗口类型,并发送一个数据(该数据可自定义)
        item = new ListItem(2, "wori");
        list.add(item);
        item = null;
        //设定该窗口类型,并发送一个数据(该数据可自定义)
        item = new ListItem(2, "wori");
        list.add(item);
        item = null;
        //设定该窗口类型,并发送一个数据(该数据可自定义)
        item = new ListItem(2, "wori");
        list.add(item);
        item = null;


        //将List发送给自定义适配器
        listAdapter.setList(list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Doctor_warehouse.this, Doctor_details.class);
                startActivity(intent);
            }
        });
        //在自定义适配器里面通知List改变.触发自定义适配器的getView方法
        listAdapter.notifyDataSetChanged();


        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), initViewData(), contentView); //给菜单装载数据和布局
        //该监听回调只监听默认类型，如果是自定义view请自行设置，参照demo
        mDropDownMenu.addMenuSelectListener(new DropDownMenu.OnDefultMenuSelectListener() {
            @Override
            public void onSelectDefaultMenu(int index, int pos, String clickstr) {
                //index:点击的tab索引，pos：单项菜单中点击的位置索引，clickstr：点击位置的字符串
                Toast.makeText(getBaseContext(), clickstr, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 设置类型和数据源：
     * DropDownMenu.KEY对应类型（DropDownMenu中的常量，参考上述核心源码）
     * DropDownMenu.VALUE对应数据源：key不是TYPE_CUSTOM则传递string[],key是TYPE_CUSTOM类型则传递对应view
     */
    private List<HashMap<String, Object>> initViewData() {
        List<HashMap<String, Object>> viewDatas = new ArrayList<>();
        HashMap<String, Object> map;
        for (int i = 0; i < headers.length; i++) {
            map = new HashMap<String, Object>();
            map.put(DropDownMenu.KEY, types[i]);
            switch (types[i]) {
                case DropDownMenu.TYPE_LIST_CITY:   //按照CITY方式装载
                    map.put(DropDownMenu.VALUE, citys); //citys是要装载的数据
                    map.put(DropDownMenu.SELECT_POSITION, null);
                    break;
                case DropDownMenu.TYPE_LIST_SIMPLE:
                    if (i == 2) {   //有两个菜单需要同样的界面,所以需要if来判断一下.i代表headers的第X位
                        map.put(DropDownMenu.VALUE, title);
                        map.put(DropDownMenu.SELECT_POSITION, null);
                    } else {
                        map.put(DropDownMenu.VALUE, department);
                        map.put(DropDownMenu.SELECT_POSITION, null);   //5代表默认选中第5条.若为null,则显示菜单标题(headers)
                    }

                    break;
                //装载网状菜单和自定义菜单用
//                case DropDownMenu.TYPE_GRID:
//                    map.put(DropDownMenu.VALUE, constellations);
//                    break;
//                default:
//                    map.put(DropDownMenu.VALUE, getCustomView());
//                    break;
            }
            viewDatas.add(map);
        }
        return viewDatas;
    }

    //自定义菜单用来初始化
//    private View getCustomView() {
//        View v = getLayoutInflater().inflate(R.layout.custom, null);
//        TextView btn = (TextView) v.findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDropDownMenu.setTabText(2, "自定义");//设置tab标签文字
//                mDropDownMenu.closeMenu();//关闭menu
//            }
//        });
//        return v;
//    }

    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}
