package com.aliif.uas4;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = findViewById(R.id.myButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the color animation
                ObjectAnimator colorAnimator = ObjectAnimator.ofArgb(myButton, "backgroundColor",
                        getResources().getColor(R.color.warna_tombol),
                        getResources().getColor(R.color.warna_tombol_diklik));
                colorAnimator.setDuration(1000); // Set the duration of the animation

                // Create the reverse color animation
                ObjectAnimator reverseColorAnimator = ObjectAnimator.ofArgb(myButton, "backgroundColor",
                        getResources().getColor(R.color.warna_tombol_diklik),
                        getResources().getColor(R.color.warna_tombol));
                reverseColorAnimator.setDuration(1000); // Set the duration of the animation

                // Create the animation set
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(colorAnimator, reverseColorAnimator); // Play animations sequentially

                // Start the animation
                animatorSet.start();
            }
        });

    }
}