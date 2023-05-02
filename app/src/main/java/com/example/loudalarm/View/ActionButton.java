package com.example.loudalarm.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.loudalarm.R;
import com.example.loudalarm.databinding.ViewActionButtonBinding;

public class ActionButton extends FrameLayout  {

    ViewActionButtonBinding binding;

    public ActionButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public ActionButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    // тут происходит инициализация
    private void initView(AttributeSet attrs) {
        binding = ViewActionButtonBinding.inflate(LayoutInflater.from(getContext()), this, true);
        TypedArray attributes = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ActionButton, 0, 0);

        try {
            String text = attributes.getString(R.styleable.ActionButton_android_text);
            boolean textAlwaysCaps = attributes.getBoolean(R.styleable.ActionButton_android_textAllCaps, true);

            binding.action.setText(text);
            binding.action.setAllCaps(textAlwaysCaps);
        } finally {
            attributes.recycle();
        }
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        binding.getRoot().setOnClickListener(l);
    }
}
