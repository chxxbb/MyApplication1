package com.example.chen.myapplication.view;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chen.myapplication.data.Doctor;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.page.ListAdapter;
import com.example.chen.myapplication.page.ListItem;
import com.example.chen.myapplication.R;
import com.example.chen.myapplication.utils.Http_Bitmap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.zxl.library.DropDownMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Chen on 2016/6/7.
 */
public class Doctor_warehouse extends AppCompatActivity {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();

    List<Doctor> list_doctor = null;

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
    ListAdapter listAdapter;
    ListItem item;
    List<ListItem> list;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    List<Bitmap> list_bitmap = (List<Bitmap>) msg.obj;
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).getDoctor().setIcon_bitmap(list_bitmap.get(i));
                    }

                    break;
                case 2:
                    list_doctor = (List<Doctor>) msg.obj;
                    if (list_doctor != null) {
                        for (int i = 0; i < list_doctor.size(); i++) {
                            item = new ListItem(2, list_doctor.get(i));
                            list.add(item);
                            item = null;
                        }
                    }
                    break;
            }
            //将List发送给自定义适配器
            listAdapter.setList(list);
            //在自定义适配器里面通知List改变.触发自定义适配器的getView方法
            listAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_warehouse);
        mDropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);
        initView();
        init_http_data();
    }


    private void initView() {

        View contentView = getLayoutInflater().inflate(R.layout.doctor_warehouse_view, null);  //内容页面载入
        listView = (ListView) contentView.findViewById(R.id.doctor_warehouse_listview);
        listAdapter = new ListAdapter(this);
        listView.setAdapter(listAdapter);

        list = new ArrayList<ListItem>();

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(Doctor_warehouse.this, Doctor_details.class);
//                startActivity(intent);
//            }
//        });


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

    private void init_http_data() {

        new Thread(new Runnable() {     //准备医生数据
            @Override
            public void run() {

                RequestBody requestBody = RequestBody.create(JSON, "请求首页的医生列表");
                Request request = new Request.Builder().url(HTTP_data.http_data + "/findDoctorList").post(requestBody).build();

                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();

                        list_doctor = new ArrayList<Doctor>();

                        list_doctor = gson.fromJson(str, new TypeToken<List<Doctor>>() {
                        }.getType());

                        Message message = new Message();
                        message.what = 2;
                        message.obj = list_doctor;
                        handler.sendMessage(message);

                        Http_Bitmap http_bitmap = new Http_Bitmap();
                        List<Bitmap> list_bitmap = new ArrayList<Bitmap>();
                        for (int i = 0; i < list_doctor.size(); i++) {
                            list_bitmap.add(http_bitmap.GetLocalOrNetBitmap(list_doctor.get(i).getIcon()));
                        }

                        Message message1 = new Message();
                        message1.what = 1;
                        message1.obj = list_bitmap;
                        handler.sendMessage(message1);
                    }

                });

            }
        }).start();
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
