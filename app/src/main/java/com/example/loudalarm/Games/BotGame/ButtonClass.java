package com.example.loudalarm.Games.BotGame;


import android.content.Context;
import android.util.AttributeSet;

public class ButtonClass extends androidx.appcompat.widget.AppCompatButton {

    int idX = 0;
    int idY = 0;
    private MyOnClickListener mClickListener;//наш интерфейс учета кликов для MainActivity

    //конструктор, в котором будем задавать координаты кнопки в матрице
    public ButtonClass(Context context, int x, int y) {
        super(context);
        idX = x;
        idY = y;
    }

    public ButtonClass(Context context) {
        super(context);
    }

    public ButtonClass(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonClass(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override //метод View для отлавливания кликов
    public boolean performClick() {
        super.performClick();

        mClickListener.OnTouchDigit(this);//будем дергать метод интерфейса
        return true;
    }

    public void setOnClickListener(MyOnClickListener listener) {
        mClickListener = listener;
    }

    public int getIdX() {
        return idX;
    }

    public int getIdY() {
        return idY;
    }


    //Интерфейс для MainActivity
    //************************************
    public interface MyOnClickListener {

        void OnTouchDigit(ButtonClass v);
    }
}