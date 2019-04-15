package com.example.deyana.waterme_v01;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Deyana on 29/03/2019.
 */

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slider_images = {
            R.drawable.plant1,
            R.drawable.plant2,
            R.drawable.ic_launcher_foreground
    };

    public String[] slider_headlines = {
            "Grow a happy plant!",
            "Keep track of its condition.",
            "Lorem ipsum",
    };

    public String[] slider_text = {
            "Water.me helps you maintain your indoors plants' health by sending you reminders when your plant needs water.",
            "Add a plant to your profile to keep track of its condition. If your plant is showing signs of sickness, " +
                    "you can consult our \"Diagnose plant\" page to identify the problem and fix it.",
            "AAAAAAAAAAAAAAAAAAAAAA"

    };

    @Override
    public int getCount() {
        return slider_headlines.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ConstraintLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView image = (ImageView) view.findViewById(R.id.slideImage);
        TextView headline = (TextView) view.findViewById(R.id.slideHeadline);
        TextView text = (TextView) view.findViewById(R.id.slideText);

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
