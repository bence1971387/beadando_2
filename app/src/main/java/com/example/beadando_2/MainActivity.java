package com.example.beadando_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private OpenGLView openGLView;
    private OrientationSensor orientationSensor;
    private LightSensor lightSensor;
    private TextView t;
    private TextView t2;
    private TextView t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t3 = findViewById(R.id.light);
        openGLView = findViewById(R.id.openGLView);
        orientationSensor = new OrientationSensor(this);
        lightSensor = new LightSensor(this);
        ColorController.ColorControllerStart(this, openGLView, orientationSensor, lightSensor, t, t2, t3);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        openGLView.onPause();
        orientationSensor.unregister();
        lightSensor.unregister();
    }

    @Override
    protected void onResume() {
        super.onResume();
        openGLView.onResume();
        orientationSensor.register();
        lightSensor.register();
    }

}