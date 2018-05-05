package ru.yakovenko.openglfe;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!supportES2()) {
            setContentView(R.layout.activity_main);
            Toast.makeText(this, "OpenGL ES 2.0 is not supported", Toast.LENGTH_LONG)
                    .show();
            finish();
        }
        mGLSurfaceView = new GLSurfaceView(this);
        mGLSurfaceView.setEGLContextClientVersion(2);
        mGLSurfaceView.setRenderer(new OpenGLRenderer(this));
        setContentView(mGLSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    /**
     * Определяем, поддерживает ли девайс OpenGL ES 2.0 и выше
     *
     * @return true, если поддерживает
     */
    private boolean supportES2() {
        ActivityManager activityManager =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        if (activityManager != null) {
            ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
            return (configurationInfo.reqGlEsVersion >= 0x20000);
        }
        throw new IllegalArgumentException("activityManager is null");
    }
}
