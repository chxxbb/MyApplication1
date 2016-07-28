package com.example.chen.myapplication.page;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.data.Comment;
import com.example.chen.myapplication.data.Doctor;
import com.example.chen.myapplication.data.HTTP_data;
import com.example.chen.myapplication.data.User;
import com.example.chen.myapplication.view.Diagnosis_management;
import com.example.chen.myapplication.view.Disease_library;
import com.example.chen.myapplication.view.Doctor_details;
import com.example.chen.myapplication.view.Doctor_warehouse;
import com.example.chen.myapplication.view.Health_management;
import com.example.chen.myapplication.view.Map_page;
import com.example.chen.myapplication.view.Medical_registration;
import com.example.chen.myapplication.view.Message_activity;
import com.example.chen.myapplication.view.School;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Chen on 2016/6/4.
 */
public class ListAdapter extends BaseAdapter {

    private Activity activity;
    private List<ListItem> list;
    TopViewHolder holder_top = null;
    Timer timer;

    public ListAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setList(List<ListItem> list) {
        this.list = list;
    }


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    User user = null;

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null && position < list.size()) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        //判断是否为空,是否已经到list结尾
        if (list != null && position < list.size()) {
            return list.get(position).getType();
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return ListItem.TYPE_COUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);   //确认当前要加载的布局的类型
        switch (type) { //根据不同状态加载不同的布局
            case ListItem.TYPE_TOP: {
                TopViewHolder holder = null;    //创建该布局的持有人,通过持有人来改变布局内部

                convertView = activity.getLayoutInflater().inflate(R.layout.home_item_top, null);   //加载相应布局
                holder = new TopViewHolder();
                holder_top = holder;
                holder.mBanner = (Banner) convertView.findViewById(R.id.home_banner);
                holder.home_doctor_warehouse_relayout = (RelativeLayout) convertView.findViewById(R.id.home_doctor_warehouse_relayout);
                holder.home_doctor_warehouse_relayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, Doctor_warehouse.class);
                        activity.startActivity(intent);
                    }
                });
                holder.home_angel_Disease_library_relativelayout = (RelativeLayout) convertView.findViewById(R.id.home_angel_Disease_library_relativelayout);
                holder.home_angel_Disease_library_relativelayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, Disease_library.class);
                        activity.startActivity(intent);
                    }
                });


                //点击一键挂号按钮跳转到一键挂号按钮
                holder.home_top_register = (RelativeLayout) convertView.findViewById(R.id.home_top_register);
                holder.home_top_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, Medical_registration.class);
                        activity.startActivity(intent);
                    }
                });


                //点击右上角消息按钮跳转到消息页面
                holder.home_message_imageview = (ImageView) convertView.findViewById(R.id.home_message_imageview);
                holder.home_message_imageview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, Message_activity.class);
                        activity.startActivity(intent);
                    }
                });

                //处理布局,可通过持有人自由处理
                holder.mBanner.setDelayTime(50000);
                if (holder.imageViewUrl != null) {
                    holder.mBanner.setImages(holder.imageViewUrl);
                }


                //点击诊疗管理按钮跳转到诊疗管理页面
                holder.home_diagnosis_management_relativelayout = (RelativeLayout) convertView.findViewById(R.id.home_diagnosis_management_relativelayout);
                holder.home_diagnosis_management_relativelayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, Diagnosis_management.class);
                        activity.startActivity(intent);
                    }
                });

                holder.home_angel_Map_relativelayout = (RelativeLayout) convertView.findViewById(R.id.home_angel_Map_relativelayout);
                holder.home_angel_Map_relativelayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, Map_page.class);
                        activity.startActivity(intent);
                    }
                });

                holder.home_angel_school = (RelativeLayout) convertView.findViewById(R.id.home_angel_school);
                holder.home_angel_school.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, School.class);
                        activity.startActivity(intent);
                    }
                });

                holder.home_navigation_relativelayout = (RelativeLayout) convertView.findViewById(R.id.home_navigation_relativelayout);
                holder.home_navigation_relativelayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, Health_management.class);
                        activity.startActivity(intent);
                    }
                });

                break;
            }

            case ListItem.TYPE_BOTTON: {
                BottonViewHolder holder = null;
                if (convertView == null) {  //if判断该布局是否以前创建过,若是,则直接填充数据.此方法优化运行速度和缓存
                    convertView = activity.getLayoutInflater().inflate(R.layout.home_item_botton, null);
                    holder = new BottonViewHolder();

                    //给布局初始化.因为优化,本处的初始化只有第一次启动的时候执行,一般用来获取控件.
                    holder.home_doctor_imageView = (ImageView) convertView.findViewById(R.id.home_doctor_imageView);

                    holder.home_doctor_introduction = (TextView) convertView.findViewById(R.id.home_doctor_introduction);
                    holder.home_doctor_name = (TextView) convertView.findViewById(R.id.home_doctor_name_textView);
                    holder.home_doctor_department = (TextView) convertView.findViewById(R.id.home_doctor_department);

                    //凭借该方法添加标志,以判断是否以前创建过布局
                    convertView.setTag(holder);
                } else {
                    holder = (BottonViewHolder) convertView.getTag();
                }

                //给布局初始化(接着上面)该处的初始化每次创建都会被执行,一般用来输入数据.

                Doctor doctor = list.get(position).getDoctor();

                if (doctor.getBio() != null) {
                    holder.spanString = new SpannableString(doctor.getBio());
                    holder.spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加加粗效果
                    holder.spanString.setSpan(new ForegroundColorSpan(0xFF666666), 2, holder.spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加颜色

                    holder.home_doctor_introduction.setText(holder.spanString);

                }
                if (doctor.getSection() != null && doctor.getName() != null) {
                    holder.home_doctor_department.setText(doctor.getTitle() + "/" + doctor.getSection());
                    holder.home_doctor_name.setText(doctor.getName());
                }
                if (doctor.getIcon_bitmap() != null) {
                    holder.home_doctor_imageView.setImageBitmap(doctor.getIcon_bitmap());
                } else {
                    holder.home_doctor_imageView.setImageResource(R.mipmap.home_doctor_imageview_test);
                }


                break;
            }

            case ListItem.TYPE_DOCTOR_WAREHOUSE: {
                DoctorWarehouseHolder holder = null;
                final Doctor doctor = list.get(position).getDoctor();
                if (convertView == null) {
                    convertView = activity.getLayoutInflater().inflate(R.layout.doctor_warehouse_tiem, null);
                    holder = new DoctorWarehouseHolder();

                    //给布局初始化.因为优化,本处的初始化只有第一次启动的时候执行,一般用来获取控件.
                    holder.doctor_warehouse_imageView = (ImageView) convertView.findViewById(R.id.doctor_warehouse_imageView);
                    holder.doctor_warehouse_introduction = (TextView) convertView.findViewById(R.id.doctor_warehouse_introduction);
                    holder.doctor_warehouse_name = (TextView) convertView.findViewById(R.id.doctor_warehouse_name_textView);
                    holder.doctor_warehouse_department = (TextView) convertView.findViewById(R.id.doctor_warehouse_department);
                    holder.doctor_warehouse_title = (TextView) convertView.findViewById(R.id.doctor_warehouse_title);
                    holder.doctor_warehouse_relativelayout = (RelativeLayout) convertView.findViewById(R.id.doctor_warehouse_relativelayout);

                    holder.doctor_warehouse_relativelayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HTTP_data.doctor = doctor;
                            Intent intent = new Intent(activity, Doctor_details.class);
                            activity.startActivity(intent);
                        }
                    });
                    //凭借该方法添加标志,以判断是否以前创建过布局
                    convertView.setTag(holder);
                } else {
                    holder = (DoctorWarehouseHolder) convertView.getTag();

                }
                //给布局初始化(接着上面)该处的初始化每次创建都会被执行,一般用来输入数据.


                holder.doctor_warehouse_department.setText("科室:" + doctor.getSection());
                holder.doctor_warehouse_name.setText(doctor.getName());
                holder.doctor_warehouse_title.setText(doctor.getTitle());
                holder.doctor_warehouse_introduction.setText("擅长:" + doctor.getAdept());

                if (doctor.getIcon_bitmap() != null) {
                    holder.doctor_warehouse_imageView.setImageBitmap(doctor.getIcon_bitmap());
                } else {
                    holder.doctor_warehouse_imageView.setImageResource(R.mipmap.home_doctor_imageview_test);
                }

                break;
            }

            case ListItem.TYPE_DISEASE_SELF_TEST: {
                Disease_self_testHolder holder = null;
                if (convertView == null) {
                    convertView = activity.getLayoutInflater().inflate(R.layout.disease_self_test_item, null);
                    holder = new Disease_self_testHolder();

                    //给布局初始化.因为优化,本处的初始化只有第一次启动的时候执行,一般用来获取控件.
                    holder.disease_self_test_text = (TextView) convertView.findViewById(R.id.disease_self_test_item_text);

                    //凭借该方法添加标志,以判断是否以前创建过布局
                    convertView.setTag(holder);
                } else {
                    holder = (Disease_self_testHolder) convertView.getTag();

                }
                //给布局初始化(接着上面)该处的初始化每次创建都会被执行,一般用来输入数据.
                holder.disease_self_test_text.setText(list.get(position).getName());
                break;
            }

            case ListItem.TYPE_DOCTOR_DETAILS_COMMENTS: {
                Doctor_detailsHolder holder = null;
                Comment comment = list.get(position).getComment();
                if (convertView == null) {
                    convertView = activity.getLayoutInflater().inflate(R.layout.doctor_details_item, null);
                    holder = new Doctor_detailsHolder();

                    holder.doctor_details_item_name_textview = (TextView) convertView.findViewById(R.id.doctor_details_item_name_textview);
                    holder.doctor_details_item_comments_textview = (TextView) convertView.findViewById(R.id.doctor_details_item_comments_textview);

                    //给布局初始化.因为优化,本处的初始化只有第一次启动的时候执行,一般用来获取控件.

                    //凭借该方法添加标志,以判断是否以前创建过布局
                    convertView.setTag(holder);
                } else {
                    holder = (Doctor_detailsHolder) convertView.getTag();
                }
                //给布局初始化(接着上面)该处的初始化每次创建都会被执行,一般用来输入数据.
                holder.doctor_details_item_name_textview.setText(comment.getUserName());
                holder.doctor_details_item_comments_textview.setText(comment.getContent());

                break;
            }
            case ListItem.TYPE_DOCTOR_SCHEDULING: {
                Doctor_schedulingHolder holder = null;
                if (convertView == null) {
                    convertView = activity.getLayoutInflater().inflate(R.layout.doctor_scheduling_item, null);
                    holder = new Doctor_schedulingHolder();

                    //给布局初始化.因为优化,本处的初始化只有第一次启动的时候执行,一般用来获取控件.

                    //凭借该方法添加标志,以判断是否以前创建过布局
                    convertView.setTag(holder);
                } else {
                    holder = (Doctor_schedulingHolder) convertView.getTag();
                }
                //给布局初始化(接着上面)该处的初始化每次创建都会被执行,一般用来输入数据.

                break;
            }

            case ListItem.TYPE_MESSAGE: {
                Message_Holder holder = null;
                if (convertView == null) {
                    convertView = activity.getLayoutInflater().inflate(R.layout.message_item, null);
                    holder = new Message_Holder();

                    //给布局初始化.因为优化,本处的初始化只有第一次启动的时候执行,一般用来获取控件.

                    //凭借该方法添加标志,以判断是否以前创建过布局
                    convertView.setTag(holder);
                } else {
                    holder = (Message_Holder) convertView.getTag();
                }
                //给布局初始化(接着上面)该处的初始化每次创建都会被执行,一般用来输入数据.

                break;
            }

            case ListItem.TYPE_CLASSROOM: {
                Classroom_Holder holder = null;
                if (convertView == null) {
                    convertView = activity.getLayoutInflater().inflate(R.layout.classroom_item, null);
                    holder = new Classroom_Holder();

                    //给布局初始化.因为优化,本处的初始化只有第一次启动的时候执行,一般用来获取控件.
                    holder.classroom_img1 = (ImageView) convertView.findViewById(R.id.classroom_item_img_1);
                    holder.classroom_img2 = (ImageView) convertView.findViewById(R.id.classroom_item_img_2);
                    holder.classroom_text1 = (TextView) convertView.findViewById(R.id.classroom_item_text_1);
                    holder.classroom_text2 = (TextView) convertView.findViewById(R.id.classroom_item_text_2);

                    holder.classroom_img1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(activity, "播放视频1", Toast.LENGTH_LONG).show();
                        }
                    });
                    holder.classroom_img2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(activity, "播放视频2", Toast.LENGTH_LONG).show();
                        }
                    });

                    //凭借该方法添加标志,以判断是否以前创建过布局
                    convertView.setTag(holder);
                } else {
                    holder = (Classroom_Holder) convertView.getTag();
                }
                //给布局初始化(接着上面)该处的初始化每次创建都会被执行,一般用来输入数据.
                Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher);

                holder.classroom_text1.setText((String) list.get(position).getMap().get("title"));
                if (list.get(position).getMap().get("title2") != null) {
                    holder.classroom_text2.setText((String) list.get(position).getMap().get("title2"));
                    holder.classroom_img2.setImageBitmap((Bitmap) list.get(position).getMap().get("cover2"));
                } else {

                }
                holder.classroom_img1.setImageBitmap((Bitmap) list.get(position).getMap().get("cover"));

                break;
            }


            default:
                break;
        }

        return convertView;
    }

    static class TopViewHolder {    //持有人
        Banner mBanner = null;
        String[] imageViewUrl = HTTP_data.Banner_img;

        RelativeLayout home_doctor_warehouse_relayout = null;
        RelativeLayout home_top_register = null;
        ImageView home_message_imageview = null;
        RelativeLayout home_diagnosis_management_relativelayout = null;
        RelativeLayout home_angel_Disease_library_relativelayout = null;
        RelativeLayout home_angel_Map_relativelayout = null;
        RelativeLayout home_angel_school = null;
        RelativeLayout home_navigation_relativelayout = null;
    }

    static class BottonViewHolder {
        ImageView home_doctor_imageView = null;
        SpannableString spanString = null;
        TextView home_doctor_name = null, home_doctor_department = null, home_doctor_introduction = null;

    }

    static class DoctorWarehouseHolder {
        ImageView doctor_warehouse_imageView = null;
        TextView doctor_warehouse_introduction = null, doctor_warehouse_name = null, doctor_warehouse_department = null, doctor_warehouse_title = null;
        RelativeLayout doctor_warehouse_relativelayout = null;
    }

    static class Disease_self_testHolder {
        TextView disease_self_test_text = null;
    }

    static class Doctor_detailsHolder {
        TextView doctor_details_item_name_textview = null, doctor_details_item_comments_textview = null;
    }

    static class Doctor_schedulingHolder {

    }

    static class Message_Holder {

    }

    static class Classroom_Holder {
        ImageView classroom_img1 = null, classroom_img2 = null;
        TextView classroom_text1 = null, classroom_text2 = null;

    }

}
