package com.example.loudalarm.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.loudalarm.Adapters.ThemeAdapter;
import com.example.loudalarm.App;
import com.example.loudalarm.R;
import com.example.loudalarm.databinding.ActivityThemeBinding;

public class ThemeActivity extends AppCompatActivity {
    TextView[] dots;
    ActivityThemeBinding binding;
    ThemeAdapter themeAdapter;
    int[] images = {
            R.drawable.background,
            R.drawable.background_1,
            R.drawable.background_light,
            R.drawable.background_light_3,
            R.drawable.background_light_2,
            R.drawable.background_violet_dark
    };
    int[] themes = {
            R.style.Background,
            R.style.Background_1,
            R.style.Background_light,
            R.style.Background_light_3,
            R.style.Background_light_2,
            R.style.Background_violet_dark,
    };

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpIndicator(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(App.getThemes()[App.getIndexOfTheme()]);

        binding = ActivityThemeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding.nameOfChangedTheme.setText(App.getNamesOfThemes().get(binding.slideViewPager.getCurrentItem()));

        binding.backBtn.setOnClickListener(v -> {
            binding.slideViewPager.setCurrentItem(images.length - 1);

        });

        binding.nextbtn.setOnClickListener(v -> {
            if (getItem(0) < images.length - 1) {
                binding.slideViewPager.setCurrentItem(getItem(1), true);
            } else {
                binding.slideViewPager.setCurrentItem(0);
            }
        });

        binding.changeButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this).setIcon(R.drawable.icon_change_theme)
                    .setTitle("Изменение темы")
                    .setMessage("Перезапустить приложение для смены фона?")
                    .setPositiveButton("ДА", (dialog, id) -> {
                        App.getDatabaseSP().saveIndexOfTheme(binding.slideViewPager.getCurrentItem());
                        dialog.dismiss();
                        Intent i = new Intent(this, SplashActivity.class);
                        startActivity(i);
                        finish();
                    })
                    .setNegativeButton("НЕТ", (dialog, id) -> {
                        dialog.dismiss();
                    }).create().show();


        });

        themeAdapter = new ThemeAdapter(this, images);

        binding.slideViewPager.setAdapter(themeAdapter);

        setUpIndicator(0);
        binding.slideViewPager.addOnPageChangeListener(viewListener);
    }

    public void setUpIndicator(int position) {
        dots = new TextView[images.length];
        binding.indicatorLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.main_text_and_icons_color, getApplicationContext().getTheme()));
            binding.indicatorLayout.addView(dots[i]);
            binding.nameOfChangedTheme.setText(App.getNamesOfThemes().get(binding.slideViewPager.getCurrentItem()));

        }

        dots[position].setTextColor(getResources().getColor(R.color.teal_200, getApplicationContext().getTheme()));
    }

    private int getItem(int i) {
        return binding.slideViewPager.getCurrentItem() + i;
    }

}