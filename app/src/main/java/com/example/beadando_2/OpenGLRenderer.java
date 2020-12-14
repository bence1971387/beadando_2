package com.example.beadando_2;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    public float[] getRgb() {
        return rgb;
    }

    public void setRgb(float[] rgb) {
        this.rgb = rgb;
    }

    private float[] rgb;

    public float[] getComp_rgb() {
        return comp_rgb;
    }

    public void setComp_rgb(float[] comp_rgb) {
        this.comp_rgb = comp_rgb;
    }

    private float[] comp_rgb;

    private Triangle mTriangle;
    private static final double DURATION = 30000;

    public OpenGLRenderer(float [] rgb) {

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        float[] temp_rgb = getComp_rgb();
        GLES20.glClearColor(temp_rgb[0], temp_rgb[1], temp_rgb[2], 1f);
        mTriangle = new Triangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        float[] temp_rgb = getComp_rgb();
        GLES20.glClearColor(temp_rgb[0], temp_rgb[1], temp_rgb[2], 1f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        mTriangle.setColor(getRgb());
        mTriangle.draw();
    }
    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)

        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)

        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it

        GLES20.glShaderSource(shader, shaderCode);

        GLES20.glCompileShader(shader);

        return shader;

    }
}
