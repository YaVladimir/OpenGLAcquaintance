package ru.yakovenko.openglfe;

import android.content.Context;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ru.yakovenko.openglfe.utils.ShaderUtils;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.GLES20.glViewport;
import static ru.yakovenko.openglfe.contstants.GLConstants.A_POSITION_NAME;
import static ru.yakovenko.openglfe.contstants.GLConstants.U_COLOR_NAME;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    private Context mContext;
    private int mProgramId;
    private FloatBuffer mVertexData;
    private int mUColorLocation;
    private int mAPositionLocation;

    OpenGLRenderer(Context context) {
        this.mContext = context;
        prepareData();
    }

    /**
     * Подготавливаем данные для передачи в шейдеры
     */
    private void prepareData() {
        float[] vertices = {-0.5f, -0.2f, 0.0f, 0.2f, 0.5f, -0.2f};
        mVertexData = ByteBuffer
                .allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mVertexData.put(vertices);
    }

    /**
     * Передаем данные в шейдеры
     */
    private void bindData() {
        mUColorLocation = glGetUniformLocation(mProgramId, U_COLOR_NAME);
        glUniform4f(mUColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
        mAPositionLocation = glGetAttribLocation(mProgramId, A_POSITION_NAME);
        mVertexData.position(0);
        glVertexAttribPointer(
                mAPositionLocation, 2, GL_FLOAT, false, 0, mVertexData);
        glEnableVertexAttribArray(mAPositionLocation);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0f, 0f, 0f, 1f);
        int vertexShaderId
                = ShaderUtils.createShader(mContext, GL_VERTEX_SHADER, R.raw.vertex_shader);
        int fragmentShaderId
                = ShaderUtils.createShader(mContext, GL_FRAGMENT_SHADER, R.raw.fragment_shader);
        mProgramId = ShaderUtils.createProgram(vertexShaderId, fragmentShaderId);
        glUseProgram(mProgramId);
        bindData();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);
        glDrawArrays(GL_TRIANGLES, 0, 3);
    }
}
