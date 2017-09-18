package com.jlgproject.activity;

import com.bumptech.glide.Glide;
import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;
import com.jlgproject.model.Buness_School_Bean;
import com.jlgproject.util.ActivityCollector;
import com.jlgproject.util.L;
import com.jlgproject.util.ReturenListViews;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;
import com.jlgproject.view.MyLinearLayout;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jlgproject.adapter.JDViewAdapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jlgproject.R;


import com.jlgproject.adapter.Buness_School2_Adapter;

import com.jlgproject.base.BaseActivity;

import com.jlgproject.loader.GlideImageLoader;
import com.jlgproject.view.JDAdverView;
import com.jlgproject.view.UPMarqueeView;
import com.jlgproject.view.VpSwipeRefreshLayout;
import com.youth.banner.Banner;

import com.jlgproject.view.HomeListView;
import com.youth.banner.listener.OnBannerListener;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by sunbeibei on 2017/9/5.
 * 商学院
 */

public class Buness_School2 extends BaseActivity implements View.OnClickListener, HttpMessageInfo.IMessage, SwipeRefreshLayout.OnRefreshListener, OnBannerListener {

    private UPMarqueeView verticle_view;
    private ImageView title_left;
    private TextView title_name;
    private Banner banner;
    private List<String> images = new ArrayList<>();
    private LinearLayout video_class;
    private LinearLayout text_class;
    private LinearLayout activity_area;
    private LinearLayout rootList;
    private VpSwipeRefreshLayout refreshLayout;
    private TextView more_class;
    private Buness_School_Bean data;
    private List<Buness_School_Bean.DataBean.BannersBean> banners;

    @Override
    public int loadWindowLayout() {
        return R.layout.buness_school2;
    }

    @Override
    public void initDatas() {
        super.initDatas();

        requestBussneSchool();
    }

    @Override
    public void initViews() {
        refreshLayout = (VpSwipeRefreshLayout) findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        title_left = (ImageView) findViewById(R.id.iv_title_left);
        title_left.setVisibility(View.VISIBLE);
        title_left.setImageResource(R.mipmap.back);
        title_left.setOnClickListener(this);
        title_name = (TextView) findViewById(R.id.tv_title_name);
        title_name.setVisibility(View.VISIBLE);
        title_name.setText("商学院");
        banner = (Banner) findViewById(R.id.banner);
        verticle_view = (UPMarqueeView) findViewById(R.id.verticle_news);//垂直滚动控件
        video_class = (LinearLayout) findViewById(R.id.video_class);//视频课程
        video_class.setOnClickListener(this);
        text_class = (LinearLayout) findViewById(R.id.text_class);//图文课程
        text_class.setOnClickListener(this);
        activity_area = (LinearLayout) findViewById(R.id.activity_area);//活动专区
        activity_area.setOnClickListener(this);
        more_class = (TextView) findViewById(R.id.more_class);
        more_class.setOnClickListener(this);
        rootList = (LinearLayout) findViewById(R.id.ll_list_num);//父容器


    }

    private void initlistview(List<Buness_School_Bean.DataBean.CoursesBean> courses) {
        rootList.removeAllViews();
        LinearLayout linear = new LinearLayout(this);
        linear.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linear.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < courses.size(); i++) {
           //通过for循环来添加数据
            //视频
            LinearLayout linear1 = new LinearLayout(this);
            linear1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            linear1.setOrientation(LinearLayout.VERTICAL);
            //设置类型标题
            MyLinearLayout myLinearLayout = getIns(courses.get(i).getCollegeModuleVo().getModule_img(), courses.get(i).getCollegeModuleVo().getModule_name());
            HomeListView homeListView = new HomeListView(this);
            Buness_School2_Adapter buness_school2_adapter = new Buness_School2_Adapter(this);
            //传递类型
            buness_school2_adapter.setType(courses.get(i).getCollegeModuleVo().getModule_type());
            //传递列表
            buness_school2_adapter.setLeiBiaoLists(courses.get(i).getCourseDetails());
            Buness_School_Bean.DataBean.CoursesBean.CollegeModuleVoBean collegeModuleVo = courses.get(i).getCollegeModuleVo();
            Buness_School_Bean.DataBean.CoursesBean.TopImgVoBean topImgVo = courses.get(i).getTopImgVo();
            if(collegeModuleVo!=null&&topImgVo!=null){
                homeListView.addHeaderView(setHeaderData(collegeModuleVo.getModule_type(), topImgVo.getImg(), topImgVo.getTitle(), topImgVo.getUrl(),topImgVo.getId()));
                homeListView.setAdapter(buness_school2_adapter);
                linear1.addView(myLinearLayout);
                linear1.addView(homeListView);
                linear.addView(linear1);
            }


        }

        rootList.addView(linear);
    }

    private void initBanner() {
        //轮播图
        banner.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
    }

    private void requestBussneSchool() {
        //访问商学院接口
        if (ShowDrawDialog(this)) {
            HttpMessageInfo<Buness_School_Bean> info = new HttpMessageInfo<>(BaseUrl.BUNESSCHOOL, new Buness_School_Bean());
            info.setiMessage(this);
            AddHeaders header = new AddHeaders();
            header.add("Authorization", UserInfoState.getToken());
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, null, header, 1);
        }
    }

    private View setHeaderData(final String type, String img, final String name2, final String url2, final int id) {
        View view1 = LayoutInflater.from(this).inflate(R.layout.sxy_one, null);
        ImageView iv_tuijian = (ImageView) view1.findViewById(R.id.iv_tuijian);//置顶图片
        LinearLayout ll_newDate = (LinearLayout) view1.findViewById(R.id.ll_newDate);
        Glide.with(this).load(img).into(iv_tuijian);
        //查看全部的点击事件
        ll_newDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("1")) {//视频列表
                    ActivityCollector.startA(Buness_School2.this, Buness_Video_Player2.class);
                } else if (type.equals("2")) {//图文列表
                    ActivityCollector.startA(Buness_School2.this, Buness_Pic_Text.class);
                } else if (type.equals("3")) {//活动专区
                    ActivityCollector.startA(Buness_School2.this, ActivityPrefecture.class);
                }
            }
        });
        //置顶图片的点击事件
        iv_tuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("1")) {
                    Intent intent = new Intent();
                    intent.putExtra("id",  id );
                    intent.putExtra("url",url2);
                    intent.setClass(Buness_School2.this, BusinessVideoPlay.class);
                    startActivity(intent);
                } else if (type.equals("2")) {
                    Intent intent = new Intent();
                    intent.putExtra("url", url2);
                    intent.putExtra("name", name2);
                    intent.setClass(Buness_School2.this, NewsDetails.class);
                    startActivity(intent);
                } else if (type.equals("3")) {
                    Intent intent = new Intent();
                    intent.putExtra("url", url2);
                    intent.putExtra("name", name2);
                    intent.setClass(Buness_School2.this, NewsDetails.class);
                    startActivity(intent);
                }
            }
        });

        return view1;
    }

    @Override
    public void onClick(View v) {
        if (v == title_left) {
            finish();
        } else if (v == video_class) {//点击跳转视频课程
            ActivityCollector.startA(this, Buness_Video_Player2.class);

        } else if (v == text_class) {//点击跳转图文课程
            ActivityCollector.startA(this, Buness_Pic_Text.class);

        } else if (v == activity_area) {//点击跳转活动专区
            ActivityCollector.startA(Buness_School2.this, ActivityPrefecture.class);
        } else if (v == more_class) {//更多课程预告
            ActivityCollector.startA(this, LastNew_Pic_Text.class);
        }
    }

    public MyLinearLayout getIns(String image, String text) {
        MyLinearLayout myLin = new MyLinearLayout(this);
        myLin.setImage(image);
        myLin.setText(text);
        return myLin;
    }

    @Override
    public void getServiceData(Object o) {
        if (o instanceof Buness_School_Bean) {
            data = (Buness_School_Bean) o;
            DismissDialog();
            refreshLayout.setRefreshing(false);
            if (data.getState().equals("ok")) {
                banners = data.getData().getBanners();
                images.clear();
                for (int i = 0; i < banners.size(); i++) {
                    Buness_School_Bean.DataBean.BannersBean bannersBean = banners.get(i);
                    images.add(bannersBean.getImg());
                }
                //轮播图
                initBanner();
                //最新预告
                List<Buness_School_Bean.DataBean.ForeshowsBean> foreshows = data.getData().getForeshows();
                ArrayList<View> listViews = ReturenListViews.getListViews(this, foreshows);
                verticle_view.setViews(listViews);
                //三个列表
                List<Buness_School_Bean.DataBean.CoursesBean> courses = data.getData().getCourses();
                initlistview(courses);

            } else {
                ToastUtil.showShort(Buness_School2.this, data.getMessage());
            }
        }

    }

    @Override
    public void getErrorData(Call call, IOException e) {
        ToastUtil.showShort(this, "服务器繁忙,请稍后再试");
        refreshLayout.setRefreshing(false);
        DismissDialog();
    }

    @Override
    public void onRefresh() {
        if (ShowDrawDialog(this)) {
            requestBussneSchool();
        }
    }

    @Override
    public void OnBannerClick(int position) {
        Buness_School_Bean.DataBean.BannersBean bannersBean = banners.get(position);
        String url = bannersBean.getUrl();
        String name = bannersBean.getName();
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent();
            intent.putExtra("url", url);
            intent.putExtra("name", name);
            intent.setClass(this, NewsDetails.class);
            startActivity(intent);
        }

    }
}
