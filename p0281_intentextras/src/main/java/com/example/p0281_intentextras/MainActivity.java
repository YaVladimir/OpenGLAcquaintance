package com.example.p0281_intentextras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    EditText mEditTextFullName;
    EditText mEditTextLastName;

    Button mButtonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextFullName = findViewById(R.id.etFName);
        mEditTextLastName = findViewById(R.id.etLName);

        mButtonSubmit = findViewById(R.id.btnSubmit);
        mButtonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra(FIRST_NAME, mEditTextFullName.getText().toString());
        intent.putExtra(LAST_NAME, mEditTextLastName.getText().toString());
        startActivity(intent);
    }
}
