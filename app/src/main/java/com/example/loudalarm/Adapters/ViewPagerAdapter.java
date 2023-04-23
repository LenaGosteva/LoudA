package com.example.loudalarm.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.loudalarm.R;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    // изобржения
    int[] images = {
            R.drawable.alarm,
            R.drawable.icon_goal_2,
            R.drawable.icon_why_2,
            R.drawable.icon_game_2,
            R.drawable.icon_go_2

    };

    // заголовки
    int[] headings = {
            R.string.Hello,
            R.string.Goal,
            R.string.Why,
            R.string.Game,
            R.string.Go
    };

    // описание
    int[] descriptions = {
            R.string.HelloDescription,
            R.string.GoalDescription,
            R.string.WhyDescription,
            R.string.GameDescription,
            R.string.GoDescription
    };

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.slider_layout, container, false);

        ImageView slideTitleImage = view.findViewById(R.id.titleImage);

        TextView slideHeading = view.findViewById(R.id.textTitle);
        TextView slideDescription = view.findViewById(R.id.textDescription);

        slideTitleImage.setImageResource(images[position]);
        slideHeading.setText(headings[position]);
        slideDescription.setText(descriptions[position]);

        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}