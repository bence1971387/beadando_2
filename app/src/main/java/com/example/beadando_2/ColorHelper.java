package com.example.beadando_2;

public class ColorHelper {
    /*public static float[] hsvToRgb(float H, float S, float V) {

        float R, G, B;

        H /= 360f;
        S /= 100f;
        V /= 100f;

        if (S == 0)
        {
            R = V * 255;
            G = V * 255;
            B = V * 255;
        } else {
            float var_h = H * 6;
            if (var_h == 6)
                var_h = 0; // H must be < 1
            int var_i = (int) Math.floor((double) var_h); // Or ... var_i =
            // floor( var_h )
            float var_1 = V * (1 - S);
            float var_2 = V * (1 - S * (var_h - var_i));
            float var_3 = V * (1 - S * (1 - (var_h - var_i)));

            float var_r;
            float var_g;
            float var_b;
            if (var_i == 0) {
                var_r = V;
                var_g = var_3;
                var_b = var_1;
            } else if (var_i == 1) {
                var_r = var_2;
                var_g = V;
                var_b = var_1;
            } else if (var_i == 2) {
                var_r = var_1;
                var_g = V;
                var_b = var_3;
            } else if (var_i == 3) {
                var_r = var_1;
                var_g = var_2;
                var_b = V;
            } else if (var_i == 4) {
                var_r = var_3;
                var_g = var_1;
                var_b = V;
            } else {
                var_r = V;
                var_g = var_1;
                var_b = var_2;
            }

            R = var_r * 255; // RGB results from 0 to 255
            G = var_g * 255;
            B = var_b * 255;
        }
        return new float[] {R/255,G/255,B/255};
    }*/
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
