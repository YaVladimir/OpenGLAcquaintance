package com.example.p0301_activityresult;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_COLOR = 1;
    private static final int REQUEST_CODE_ALIGN = 2;
    private static final String COLOR = "color";
    private static final String ALIGNMENT = "alignment";


    private TextView mTextView;
    private Button mBtnColor;
    private Button mBtnAlign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnAlign = findViewById(R.id.btnAlign);
        mBtnColor = findViewById(R.id.btnColor);

        mTextView = findViewById(R.id.tvText);

        mBtnAlign.setOnClickListener(this);
        mBtnColor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnAlign:
                intent = new Intent(this, AlignActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ALIGN);
                break;
            case R.id.btnColor:
                intent = new Intent(this, ColorActivity.class);
                startActivityForResult(intent, REQUEST_CODE_COLOR);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("myLogs",
                    "requestCode = " + requestCode + ", resultCode = " + resultCode);
            switch (requestCode) {
                case REQUEST_CODE_ALIGN:
                    int align = data.getIntExtra(ALIGNMENT, Gravity.CENTER);
                    mTextView.setGravity(align);
                    break;
                case REQUEST_CODE_COLOR:
                    int color = data.getIntExtra(COLOR, Color.BLACK);
                    mTextView.setTextColor(color);
                    break;
            }
        }
    }
}
