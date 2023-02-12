package com.app.repairo.app.adapter;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.app.repairo.app.R;
import com.app.repairo.app.model.homenewmodel.BannerList;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends PagerAdapter {

    Context applicationContext;
    ArrayList<BannerList> bannerList= new ArrayList<>();

    public BannerAdapter(Context applicationContext, ArrayList<BannerList> bannerList) {

        this.bannerList = bannerList;
        this.applicationContext = applicationContext;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) applicationContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View viewItem = inflater.inflate(R.layout.main_banner_items, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.image);
        Log.e("TAG", bannerList.get(position).getBanner_image());
        Glide.with(applicationContext)
                .load(bannerList.get(position).getBanner_image()).into(imageView);
        ((ViewPager)container).addView(viewItem);
         return viewItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return bannerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == ((View)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView((View) object);
    }
}
