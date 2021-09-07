package com.example.carsapp_week8;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MyConstraintLayout  extends ConstraintLayout {


    public MyConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) { // Hijack touchEvent from child View component

        if (ev.getY() > 1200)
            return true;
        else
            return false;
    }
}
