package com.example.mapdemo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LayersDemoActivity extends AppCompatActivity {
    SensorManager mSensorManager;
    SensorEventListener gyroListener;
    Sensor gyroSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyrotest);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        gyroSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        gyroListener = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                float x = values[0];
                float y = values[1];
                float z = values[2];
                // 위의 x, y, z를 사용하여 프로그램을 만들면 된다.
                // 아래는 그 값을 표시한 것이다.
                TextView x_Value = (TextView)findViewById(R.id.text_x);
                TextView y_Value = (TextView)findViewById(R.id.text_y);
                TextView z_Value = (TextView)findViewById(R.id.text_z);
                x_Value.setText(String.format("X축 : %.1f", x));
                y_Value.setText(String.format("Y축 : %.1f", y));
                z_Value.setText(String.format("Z축 : %.1f", z));
            }
        };
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(gyroListener);
    }
    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(gyroListener, gyroSensor, SensorManager.SENSOR_DELAY_GAME);
    }
}