package com.example.p0181_dynamiclayout3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    SeekBar mSeekBar;
    Button mButtonOne;
    Button mButtonTwo;
    LinearLayout.LayoutParams mParamsOne;
    LinearLayout.LayoutParams mParamsTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonOne = findViewById(R.id.btn1);
        mButtonTwo = findViewById(R.id.btn2);

        mSeekBar = findViewById(R.id.sbWeight);
        mSeekBar.setOnSeekBarChangeListener(this);

        mParamsOne = (LinearLayout.LayoutParams) mButtonOne.getLayoutParams();
        mParamsTwo = (LinearLayout.LayoutParams) mButtonTwo.getLayoutParams();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int leftValue = progress;
        int rightValue = seekBar.getMax() - progress;

        mParamsOne.weight = leftValue;
        mParamsTwo.weight = rightValue;

        mButtonOne.setText(String.valueOf(leftValue));
        mButtonTwo.setText(String.valueOf(rightValue));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
