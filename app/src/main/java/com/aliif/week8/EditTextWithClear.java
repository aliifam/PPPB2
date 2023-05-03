package com.aliif.week8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

public class EditTextWithClear extends AppCompatEditText {

    Drawable clearButtonImage;

    private void init() {
        clearButtonImage = getResources().getDrawable(R.drawable.opaque_clear_24);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showClearButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        setOnTouchListener(new OnTouchListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(getCompoundDrawablesRelative()[2] != null) {
                    float clearButtonStart;
                    float clearButtonEnd;
                    boolean isClearButtonClicked = false;

                    if(getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        clearButtonEnd = clearButtonImage.getIntrinsicWidth() + getPaddingStart();
                        if(motionEvent.getX() < clearButtonEnd) {
                            isClearButtonClicked = true;
                        }
                    } else {
                        clearButtonStart = (getWidth() - getPaddingEnd() - clearButtonImage.getIntrinsicWidth());
                        if(motionEvent.getX() > clearButtonStart) {
                            isClearButtonClicked = true;
                        }
                    }

                    if(isClearButtonClicked) {
                        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            clearButtonImage = getResources().getDrawable(R.drawable.baseline_clear_24);
                            showClearButton();
                        }
                        if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            clearButtonImage = getResources().getDrawable(R.drawable.opaque_clear_24);
                            getText().clear();
                            hideClearButton();
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
                return false;
            }
        });
    }

    private void showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds
                (null, null, clearButtonImage, null);
    }

    private void hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds
                (null, null, null, null);
    }

    public EditTextWithClear(@NonNull Context context) {
        super(context);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
}
