package com.aliif.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;

    private Sensor accelerometer;
    private Sensor magnetometer;

    private TextView textAzimuth;
    private TextView textPitch;
    private TextView textRoll;

    private float[] accelerometerData = new float[3];
    private  float[] magnetometerData = new float[3];

    private ImageView spotTop;
    private ImageView spotBottom;
    private ImageView spotRight;
    private ImageView spotLeft;

    private static final float VALUE_DRIFT = 0.05f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textAzimuth = findViewById(R.id.value_azimuth);
        textPitch = findViewById(R.id.value_pitch);
        textRoll = findViewById(R.id.value_roll);

        spotTop = findViewById(R.id.spot_top);
        spotBottom = findViewById(R.id.spot_bottom);
        spotRight = findViewById(R.id.spot_right);
        spotLeft = findViewById(R.id.spot_left);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (magnetometer != null) {
            sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        int sensorType = sensorEvent.sensor.getType();

        switch (sensorType){
            case Sensor.TYPE_ACCELEROMETER:
                accelerometerData = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                magnetometerData = sensorEvent.values.clone();
                break;
            default:
                return;
        }

        float[] rotationMatrix = new float[9];
        SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerData, magnetometerData);

        float[] orientationValues = new float[3];
        SensorManager.getOrientation(rotationMatrix, orientationValues);

        float azimuth = orientationValues[0];
        float pitch = orientationValues[1];
        float roll = orientationValues[2];

        spotRight.setAlpha(0f);
        spotTop.setAlpha(0f);
        spotBottom.setAlpha(0f);
        spotLeft.setAlpha(0f);

        if(Math.abs(pitch) < VALUE_DRIFT){
            pitch = 0;
        }

        if(Math.abs(roll) < VALUE_DRIFT){
            roll = 0;
        }

        if(pitch > 0){
            spotBottom.setAlpha(pitch);
        }else {
            spotBottom.setAlpha(Math.abs(pitch));
        }

        if(roll > 0){
            spotLeft.setAlpha(roll);
        }else {
            spotLeft.setAlpha(Math.abs(roll));
        }

        textAzimuth.setText(getResources().getString(R.string.value_format, azimuth));
        textPitch.setText(getResources().getString(R.string.value_format, pitch));
        textRoll.setText(getResources().getString(R.string.value_format, roll));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}