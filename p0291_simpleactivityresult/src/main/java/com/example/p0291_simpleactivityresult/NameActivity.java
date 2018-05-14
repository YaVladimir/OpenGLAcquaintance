package com.example.p0291_simpleactivityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.p0291_simpleactivityresult.Constants.NAME;

public class NameActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText;
    private Button mButtonOk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        mEditText = findViewById(R.id.etName);
        mButtonOk = findViewById(R.id.btnOK);
        mButtonOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra(NAME, mEditText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
