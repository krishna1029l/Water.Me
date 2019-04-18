package com.example.deyana.waterme_v01;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {
    private Context context;

    SliderAdapter(Context context){
        this.context = context;
    }

    private int[] slider_images = {
            R.drawable.plant1,
            R.drawable.plant2,
    };

    private String[] slider_headlines = {
            "Grow a happy plant!",
            "Keep track of its condition.",
    };

    private String[] slider_text = {
            "Water.me helps you maintain your indoors plants' health by sending you reminders when your plant needs water.",
            "Add a plant to your profile to keep track of its condition. If your plant is showing signs of sickness, " +
                    "you can consult our \"Diagnose plant\" page to identify the problem and fix it.",
    };

    @Override
    public int getCount() {
        return slider_headlines.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView image = view.findViewById(R.id.slideImage);
        TextView headline = view.findViewById(R.id.slideHeadline);
        TextView text = view.findViewById(R.id.slideText);

        image.setImageResource(slider_images[position]);
        headline.setText(slider_headlines[position]);
        text.setText(slider_text[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
