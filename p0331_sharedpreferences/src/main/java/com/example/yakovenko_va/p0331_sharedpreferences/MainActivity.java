package com.example.yakovenko_va.p0331_sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SAVED_TEXT = "saved_text";
    private EditText mEditText;
    private Button mBtnSave;
    private Button mBtnLoad;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.etText);

        mBtnLoad = findViewById(R.id.btnLoad);
        mBtnSave = findViewById(R.id.btnSave);

        mBtnLoad.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);

        loadText();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoad:
                loadText();
                break;
            case R.id.btnSave:
                saveText();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }

    private void saveText() {
        mSharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(SAVED_TEXT, mEditText.getText().toString());
        editor.apply();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    private void loadText() {
        mSharedPreferences = getPreferences(MODE_PRIVATE);
        String string = mSharedPreferences.getString(SAVED_TEXT, "");
        mEditText.setText(string);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }
}
