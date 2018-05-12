package com.example.savedinstancestateexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TEXT = "text";
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.etSaved);
        if (savedInstanceState != null
                && savedInstanceState.get(TEXT) != null
                && ((CharSequence) savedInstanceState.get(TEXT)).length() >= 0) {
            mEditText.setText((CharSequence) savedInstanceState.get(TEXT));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putCharSequence(TEXT, mEditText.getText());
        super.onSaveInstanceState(outState);
    }
}
