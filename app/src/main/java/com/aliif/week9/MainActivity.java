package com.aliif.week9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Canvas canvas;
    private ImageView imageView;
    private Paint paint = new Paint();
    private Paint paintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap bitmap;

    private int colorBackground;
    private int colorRectangle;
    private int colorAccent;

    private final static int OFFSET = 12;
    private int mOffset = OFFSET;

    private Rect rect = new Rect();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.myimageview);

        colorBackground = getResources().getColor(R.color.colorBackground);
        colorRectangle = getResources().getColor(R.color.colorRectangle);
        colorAccent = getResources().getColor(R.color.teal_700);

        paintText.setColor(ResourcesCompat.getColor(getResources(), R.color.black, null));
        paintText.setTextSize(48);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int vWidth = view.getWidth();
                int vHeight = view.getHeight();

                int halfWidth = vWidth / 2;
                int halfHeight = vHeight / 2;

                if(mOffset == OFFSET) {
                    bitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
                    canvas = new Canvas(bitmap);
                    imageView.setImageBitmap(bitmap);

                    canvas.drawColor(colorBackground);

                    canvas.drawText("Keep Tapping", 100, 100, paintText);
                    mOffset += OFFSET;
                } else {
                    if(mOffset < halfWidth && mOffset < halfHeight) {
                        paint.setColor(colorRectangle - 100 * mOffset);
                        rect.set(mOffset, mOffset, vWidth - mOffset, vHeight - mOffset);
                        canvas.drawRect(rect, paint);
                        mOffset += OFFSET;
                    } else {
                        paint.setColor(colorAccent);
                        canvas.drawCircle(halfWidth, halfHeight, halfWidth / 3, paint);
                        String text = "Done";
                        Rect bounds = new Rect();
                        paintText.getTextBounds(text, 0, text.length(), bounds);

                        int x = bounds.centerX();
                        int y = bounds.centerY();

                        canvas.drawText(text, halfWidth - x, halfHeight - y, paintText);
                    }
                }
                view.invalidate();
            }
        });
    }
}