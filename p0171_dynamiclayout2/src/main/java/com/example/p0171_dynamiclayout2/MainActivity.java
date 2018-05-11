package com.example.p0171_dynamiclayout2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout mLinearLayout;
    RadioGroup mRadioGroup;
    EditText mEditText;
    Button mButtonCreate;
    Button mButtonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLinearLayout = findViewById(R.id.llMain);
        mRadioGroup = findViewById(R.id.rgGravity);
        mEditText = findViewById(R.id.etName);

        mButtonClear = findViewById(R.id.btnClear);
        mButtonClear.setOnClickListener(this);

        mButtonCreate = findViewById(R.id.btnCreate);
        mButtonCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreate:
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                int buttonGravity = Gravity.LEFT;
                switch (mRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.rbLeft:
                        buttonGravity = Gravity.LEFT;
                        break;
                    case R.id.rbCenter:
                        buttonGravity = Gravity.CENTER;
                        break;
                    case R.id.rbRight:
                        buttonGravity = Gravity.RIGHT;
                        break;
                }
                params.gravity = buttonGravity;
                Button buttonNew = new Button(this);
                buttonNew.setText(mEditText.getText().toString());
                mLinearLayout.addView(buttonNew, params);
                break;
            case R.id.btnClear:
                mLinearLayout.removeAllViews();
                Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
