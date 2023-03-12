package com.aliif.week4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor light, proximity, pressure, ambient, magnetic, humidity;
    private TextView tLight, tProximity, tPressure, tAmbient, tMagnetic, tHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor>sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorText = new StringBuilder();
        for (Sensor currentSensor : sensorList
             ) {
            sensorText.append(currentSensor.getName())
                    .append(System.getProperty("line.separator"));
        }

        TextView sensorTextView = findViewById(R.id.sensorText);
        sensorTextView.setText(sensorText);

        tLight = findViewById(R.id.light);
        tProximity = findViewById(R.id.proximity);
        tPressure = findViewById(R.id.pressure);
        tAmbient = findViewById(R.id.ambient);
        tMagnetic = findViewById(R.id.magnetic);
        tHumidity = findViewById(R.id.humidity)

        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        ambient = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        humidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        String exception = "No Sensor";

        if (light == null) {
            tLight.setText(exception);
        }
        if (proximity == null) {
            tProximity.setText(exception);
        }
        if (pressure == null) {
            tPressure.setText(exception);
        }
        if (ambient == null) {
            tAmbient.setText(exception);
        }
        if (magnetic == null) {
            tMagnetic.setText(exception);
        }
        if (humidity == null) {
            tHumidity.setText(exception);
        }
    }

    protected void onStart()
    {
        super.onStart();
        if(light != null){
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(proximity != null){
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(pressure != null){
            sensorManager.registerListener(this, pressure, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(ambient != null){
            sensorManager.registerListener(this, ambient, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(magnetic != null){
            sensorManager.registerListener(this, magnetic, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(humidity != null){
            sensorManager.registerListener(this, humidity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    protected void onStop()
    {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    protected void changeColor(float f)
    {
        LinearLayout linearLayout = findViewById(R.id.background);
        if(f < 5.00)
        {
            linearLayout.setBackgroundColor(Color.BLUE);
        }else if(f == 5.00)
        {
            linearLayout.setBackgroundColor(Color.RED);
        }
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        int type =  sensorEvent.sensor.getType();
        float currentValue = sensorEvent.values[0];

        switch (type)
        {
            case Sensor.TYPE_LIGHT:
                tLight.setText(
                        String.format("Light sensor : %1$.2f", currentValue)
                );
                changeColor(currentValue);
                break;
            case Sensor.TYPE_PROXIMITY:
                tProximity.setText(
                        String.format("Proximity sensor : %1$.2f", currentValue)
                );
                changeColor(currentValue);
                break;
            case Sensor.TYPE_PRESSURE:
                tPressure.setText(
                        String.format("Pressure sensor : %1$.2f", currentValue)
                );
                changeColor(currentValue);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }
}