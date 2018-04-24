package com.gameball.school;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

public class MMNowOnSalePagerAdapter extends PagerAdapter {
    private int mChildCount = 0;
    private Context mContext;
    private JsonArray mJsonArrayBanners = new JsonArray();
    private LayoutInflater myLayoutInflater;
    private View view;

    public void notifyDataSetChanged() {
        this.mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    public int getItemPosition(Object object) {
        if (this.mChildCount <= 0) {
            return super.getItemPosition(object);
        }
        this.mChildCount--;
        return -2;
    }

    public MMNowOnSalePagerAdapter(Context context) {
        this.mContext = context;
        this.myLayoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.mJsonArrayBanners.size();
    }

    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View view = this.myLayoutInflater.inflate(C0308R.layout.viewpager_now_on_sale, null);
        ImageView picture = (ImageView) view.findViewById(C0308R.id.imageview);
        TextView textView1 = (TextView) view.findViewById(C0308R.id.textview1);
        TextView textView2 = (TextView) view.findViewById(C0308R.id.textview2);
        TextView textView3 = (TextView) view.findViewById(C0308R.id.textview3);
        View view1 = view.findViewById(C0308R.id.view);
        View view2 = view.findViewById(C0308R.id.view2);
        final JsonObject banner = this.mJsonArrayBanners.get(position).getAsJsonObject();
        Picasso.with(this.mContext).load(banner.get("bannerImage").getAsString()).into(picture);
        textView1.setText(banner.get("bannerTitle").getAsString());
        textView2.setText("");
        textView3.setText("");
        switch (banner.get("bannerType").getAsInt()) {
            case 1:
                view1.setVisibility(8);
                view2.setVisibility(8);
                break;
            case 2:
                view1.setVisibility(8);
                view2.setVisibility(8);
                break;
        }
        picture.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MMApplication.sendGoogleAnalyticsEvent("home", "banner", banner.get("bannerNo").getAsString());
                switch (banner.get("bannerType").getAsInt()) {
                    case 1:
                        try {
                            MMNowOnSalePagerAdapter.this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(banner.get("bannerUrl").getAsString())));
                            return;
                        } catch (ActivityNotFoundException e) {
                            return;
                        }
                    case 2:
                        MMProductDetail.startActivity(MMNowOnSalePagerAdapter.this.mContext, banner.get("bannerUrl").getAsJsonObject());
                        return;
                    default:
                        return;
                }
            }
        });
        container.addView(view);
        return view;
    }

    public void setBanners(JsonArray banners) {
        this.mJsonArrayBanners = banners;
        notifyDataSetChanged();
    }
}
