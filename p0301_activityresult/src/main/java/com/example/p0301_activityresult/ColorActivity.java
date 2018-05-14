package com.example.p0301_activityresult;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ColorActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String COLOR = "color";

    private Button btnRed;
    private Button btnGreen;
    private Button btnBlue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        btnRed = findViewById(R.id.btnRed);
        btnBlue = findViewById(R.id.btnBlue);
        btnGreen = findViewById(R.id.btnGreen);

        btnRed.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btnRed:
                intent.putExtra(COLOR, Color.RED);
                break;
            case R.id.btnBlue:
                intent.putExtra(COLOR, Color.BLUE);
                break;
            case R.id.btnGreen:
                intent.putExtra(COLOR, Color.GREEN);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
