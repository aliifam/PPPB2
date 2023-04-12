package com.aliif.utsp3b2;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class QiblaActivity extends AppCompatActivity {

    private static final String TAG = "QiblaActivity";

    private Compass compass;

    private RelativeLayout arrowViewQibla;
    private ImageView arrowImageQibla;

    private float currentAzimuth;
    SharedPreferences sharedPreferences;
    GPSTracker gpsTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qibla);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        arrowViewQibla = findViewById(R.id.main_image_qiblat);

        gpsTracker = new GPSTracker(this);

        arrowImageQibla = findViewById(R.id.main_image_dial);

        arrowViewQibla.setVisibility(INVISIBLE);
        arrowViewQibla.setVisibility(GONE);

    }

    protected void onStart() {
        super.onStart();
        if (compass == null) {
            compass.start();
        }
    }

    protected void onStop()
    {
        super.onStop();
        if (compass != null) {
            compass.stop();
        }
    }

    protected void onPause() {
        super.onPause();
        if (compass != null) {
            compass.stop();
        }
    }

    protected void onResume() {
        super.onResume();
        if (compass == null) {
            compass.start();
        }
    }

    private void setupCompass()
    {
        Boolean permission_granted = GetB
    }

}