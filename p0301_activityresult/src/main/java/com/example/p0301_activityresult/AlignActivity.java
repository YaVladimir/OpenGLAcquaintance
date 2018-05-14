package com.example.p0301_activityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class AlignActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String ALIGNMENT = "alignment";

    private Button mBtnLeft;
    private Button mBtnCenter;
    private Button mBtnRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_align);

        mBtnLeft = findViewById(R.id.btnLeft);
        mBtnCenter = findViewById(R.id.btnCenter);
        mBtnRight = findViewById(R.id.btnRight);

        mBtnLeft.setOnClickListener(this);
        mBtnCenter.setOnClickListener(this);
        mBtnRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btnLeft:
                intent.putExtra(ALIGNMENT, Gravity.START);
                break;
            case R.id.btnCenter:
                intent.putExtra(ALIGNMENT, Gravity.CENTER);
                break;
            case R.id.btnRight:
                intent.putExtra(ALIGNMENT, Gravity.END);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
