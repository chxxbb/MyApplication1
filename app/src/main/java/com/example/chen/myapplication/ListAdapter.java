package com.example.chen.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chen.myapplication.utils.ContextUtil;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by Chen on 2016/6/4.
 */
public class ListAdapter extends BaseAdapter {

    private Activity activity;
    private List<ListItem> list;

    public ListAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setList(List<ListItem> list) {
        this.list = list;
    }

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
                holder.mBanner = (Banner) convertView.findViewById(R.id.home_banner);
                holder.home_doctor_warehouse_relayout = (RelativeLayout) convertView.findViewById(R.id.home_doctor_warehouse_relayout);
                holder.home_doctor_warehouse_relayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, Doctor_warehouse.class);
                        activity.startActivity(intent);
                    }
                });

                holder.home_top_register = (RelativeLayout) convertView.findViewById(R.id.home_top_register);
                holder.home_top_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, Medical_registration.class);
                        activity.startActivity(intent);
                    }
                });

                //处理布局,可通过持有人自由处理
                holder.mBanner.setDelayTime(50000);
                holder.mBanner.setImages(holder.imageViewUrl);

                break;
            }

            case ListItem.TYPE_BOTTON: {
                BottonViewHolder holder = null;
                if (convertView == null) {  //if判断该布局是否以前创建过,若是,则直接填充数据.此方法优化运行速度和缓存
                    convertView = activity.getLayoutInflater().inflate(R.layout.home_item_botton, null);
                    holder = new BottonViewHolder();

                    //给布局初始化.因为优化,本处的初始化只有第一次启动的时候执行
                    holder.home_doctor_imageView = (ImageView) convertView.findViewById(R.id.home_doctor_imageView);

                    holder.home_doctor_introduction = (TextView) convertView.findViewById(R.id.home_doctor_introduction);
                    holder.spanString = new SpannableString("简介 : 的发送打飞机阿拉款到即发拉客敬佛i为此秒的vmaiomdaoi没法哦is的马佛is的没法哦的矛盾发生大幅阿斯蒂芬发给阿飞");

                    holder.spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加加粗效果
                    holder.spanString.setSpan(new ForegroundColorSpan(0xFF666666), 4, holder.spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  //添加颜色

                    //凭借该方法添加标志,以判断是否以前创建过布局
                    convertView.setTag(holder);
                } else {
                    holder = (BottonViewHolder) convertView.getTag();
                }

                //给布局初始化(接着上面)该处的初始化每次创建都会被执行.
                holder.home_doctor_introduction.setText(holder.spanString);
                holder.home_doctor_imageView.setImageResource(R.mipmap.home_doctor_imageview_test);
                break;
            }

            case ListItem.TYPE_DOCTOR_WAREHOUSE:
                DoctorWarehouseHolder holder = null;
                if (convertView == null) {
                    convertView = activity.getLayoutInflater().inflate(R.layout.doctor_warehouse_tiem, null);
                    holder = new DoctorWarehouseHolder();

                    //给布局初始化.因为优化,本处的初始化只有第一次启动的时候执行
                    holder.doctor_warehouse_imageView = (ImageView) convertView.findViewById(R.id.doctor_warehouse_imageView);

                    holder.doctor_warehouse_introduction = (TextView) convertView.findViewById(R.id.doctor_warehouse_department);

                    //凭借该方法添加标志,以判断是否以前创建过布局
                    convertView.setTag(holder);
                } else {
                    holder = (DoctorWarehouseHolder) convertView.getTag();

                }
                //给布局初始化(接着上面)该处的初始化每次创建都会被执行.
                holder.doctor_warehouse_introduction.setText("科室：行为发育科");
                holder.doctor_warehouse_imageView.setImageResource(R.mipmap.home_doctor_imageview_test);
                break;

            default:
                break;
        }

        return convertView;
    }

    static class TopViewHolder {    //持有人
        Banner mBanner = null;
        String[] imageViewUrl = new String[]{   //Banner图片源,可以文件,可以URL
                "http://img540.ph.126.net/5PyPfeSlM3ZWt_npImBosw==/1348265138445286339.jpg", "http://cdn.duitang.com/uploads/item/201112/27/20111227143751_TtLkL.jpg",
                "http://pic27.nipic.com/20130227/7224820_020411089000_2.jpg"
        };
        RelativeLayout home_doctor_warehouse_relayout = null;
        RelativeLayout home_top_register = null;
    }

    static class BottonViewHolder {
        ImageView home_doctor_imageView = null;
        TextView home_doctor_introduction = null;
        SpannableString spanString = null;
    }

    static class DoctorWarehouseHolder {
        ImageView doctor_warehouse_imageView = null;
        TextView doctor_warehouse_introduction = null;
    }

}
