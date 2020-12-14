package com.example.beadando_2;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.graphics.ColorUtils;

public class ColorController {
    private OrientationSensor orientationSensor;
    private LightSensor lightSensor;
    private static OpenGLView openGLView;
    private static float[] hsv;
    private static float[] rgb;
    private final int THRESHOLD = 10;
    private static TextView t;
    private static int direction = 1;
    private static boolean isSingleton = false;
    private static ColorController colorController;

    public static void ColorControllerStart(Context context, OpenGLView openGLView, OrientationSensor orientationSensor, LightSensor lightSensor, TextView t2, TextView t, TextView t3){
        if(!isSingleton){
            colorController = new ColorController(context,openGLView, orientationSensor, lightSensor, t2, t, t3);
        }
        hsv = new float[]{0.0f, 0.0f, 0.0f};
        rgb = new float[]{0.5f, 0.5f, 0.5f};
        isSingleton = true;
    }
    private ColorController(Context context, OpenGLView _openGLView, OrientationSensor _orientationSensor, LightSensor _lightSensor, TextView t2, TextView t, TextView t3) {
        this.orientationSensor = _orientationSensor;
        this.lightSensor = _lightSensor;
        this.openGLView = _openGLView;
        this.t = t3;
        orientationSensor = _orientationSensor;
        lightSensor = _lightSensor;
        orientationSensor.setListener(new OrientationSensor.Listener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTranslation(float azimuth, float pitch, float roll) {
                if(Math.abs(roll) > THRESHOLD){
                    if((hsv[0] -= roll/40)<=2){
                        hsv[0] = 357.9999f;
                    } else if((hsv[0] += roll/40)>=358){
                        hsv[0] = 2.0001f;
                    }
                    hsv[0] -= (roll / 40);
                    sendRgb();
                } else if(Math.abs(pitch) > THRESHOLD){
                    hsv[1] += pitch/70;
                    if((hsv[1] -= pitch/70)<=2){
                        hsv[1] = 98.9999f;
                    } else if((hsv[1] += pitch/70)>=99){
                        hsv[1] = 2.0001f;

                    }
                    sendRgb();
                }
            }
        });
        lightSensor.setListener(new LightSensor.Listener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onLight(float lx) {
                hsv[2] = lx;
                if(hsv[2]>=99) {
                    hsv[2] = 98.9999f;
                }
                sendRgb();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void sendRgb() {
        String sh;
        String ss;
        String sv;
        if(hsv[0]<=99f){
            if(hsv[0]<=9f){
                sh = "00" + Math.round(hsv[0]);
            } else {
                sh = "0" + Math.round(hsv[0]);
            }
        } else {
            sh = Math.round(hsv[0]) + "";
        }
        if(hsv[1]<=9){
            ss = "00" + Math.round(hsv[1]);
        } else {
            ss = "0" + Math.round(hsv[1]);
        }
        if(hsv[2]<=9){
            sv = "00" + Math.round(hsv[2]);
        } else {
            sv = "0" + Math.round(hsv[2]);
        }
        float[] temp_rgb = ColorHelper.hsvToRgbComplementary(hsv[0], hsv[1], hsv[2]);
        t.setTextColor(Color.rgb(temp_rgb[0], temp_rgb[1], temp_rgb[2]));
        t.setText(sh + "\n" + ss + "\n" + sv);
        openGLView.ChangeColor(ColorHelper.hsvToRgb(hsv[0], hsv[1], hsv[2]),
                ColorHelper.hsvToRgbComplementary(hsv[0], hsv[1], hsv[2]));
    }
}
