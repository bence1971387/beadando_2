package com.example.beadando_2;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class OpenGLView extends GLSurfaceView {
    private OpenGLRenderer openGLRenderer;
    private float[] rgb;

    public OpenGLView(Context context) {
        super(context);
        init();
    }

    public OpenGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        openGLRenderer = new OpenGLRenderer(rgb);
        setRenderer(openGLRenderer);
    }
    public void ChangeColor(float[] rgb, float[] comp_rgb){
        openGLRenderer.setRgb(rgb);
        openGLRenderer.setComp_rgb(comp_rgb);
    }
}
