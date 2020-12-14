package com.example.beadando_2;

public class ColorHelper {

    public static float[] hsvToRgb(float H, float S, float V){
        float r = 0, g = 0, b = 0;
        float h = H/360;
        float s = S/100;
        float v = V/100;
        float i = (float) Math.floor(h * 6);
        float f = h * 6 - i;
        float p = v * (1 - s);
        float q = v * (1 - f * s);
        float t = v * (1 - (1 - f) * s);

        if (i % 6 == 0) {
            r = v;
            g = t;
            b = p;
        } else if (i % 6 == 1) {
            r = q;
            g = v;
            b = p;
        } else if (i % 6 == 2) {
            r = p;
            g = v;
            b = t;
        } else if (i % 6 == 3) {
            r = p;
            g = q;
            b = v;
        } else if (i % 6 == 4) {
            r = t;
            g = p;
            b = v;
        } else if (i % 6 == 5) {
            r = v;
            g = p;
            b = q;
        }

        return new float[]{r, g, b};
    }
    public static float[] hsvToRgbComplementary(float H, float S, float V){
        float h = H;
        float s = S;
        float v = V;
        if(h+180<=360){
            h += 180;
        } else {
            h -= 180;
        }
        if(s+50<=100){
            s += 50;
        } else {
            s -= 50;
        }
        if(v+50<=100){
            v +=50;
        } else {
            v -= 50;
        }
        float[] c = hsvToRgb(h,s,v);
        return c;
    }
}
