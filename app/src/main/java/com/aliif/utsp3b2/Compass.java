package com.aliif.utsp3b2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Compass implements SensorEventListener {

    public interface CompassListener {
        void onNewAzimuth(float azimuth);
    }

    private CompassListener listener;

    private SensorManager sensorManager;
    private Sensor gravitySensor;
    private Sensor magneticSensor;

    private float[] gravity = new float[3];
    private float[] magnetic = new float[3];

    private float[] R = new float[9];
    private float[] I = new float[9];

    private float azimuth;
    private float currectAzimuth;

    public Compass(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public void start() {
        sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }

    public void setCurrectAzimuth(float currectAzimuth) {
        this.currectAzimuth = currectAzimuth;
    }

    public void resetCurrectAzimuth() {
        this.currectAzimuth = 0;
    }

    public void setListener(CompassListener listener) {
        this.listener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        final float alpha = 0.97f;

        synchronized (this)
        {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            {
                gravity[0] = alpha * gravity[0] + (1 - alpha) * sensorEvent.values[0];
                gravity[1] = alpha * gravity[1] + (1 - alpha) * sensorEvent.values[1];
                gravity[2] = alpha * gravity[2] + (1 - alpha) * sensorEvent.values[2];
            }

            if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            {
                magnetic[0] = alpha * magnetic[0] + (1 - alpha) * sensorEvent.values[0];
                magnetic[1] = alpha * magnetic[1] + (1 - alpha) * sensorEvent.values[1];
                magnetic[2] = alpha * magnetic[2] + (1 - alpha) * sensorEvent.values[2];
            }

            boolean success = SensorManager.getRotationMatrix(R, I, gravity, magnetic);

            if (success)
            {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                azimuth = (float) Math.toDegrees(orientation[0]);
                azimuth = (azimuth + 360) % 360;

                currectAzimuth = Math.round(azimuth);

                if (listener != null)
                {
                    listener.onNewAzimuth(azimuth);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
