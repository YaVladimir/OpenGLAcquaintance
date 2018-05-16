package com.example.yakovenko_va.p0311_simpleintents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_PERMISSION_READ_CONTACTS = 1;
    private static final String TAG = MainActivity.class.getName();
    private Button mBtnWeb;
    private Button mBtnMap;
    private Button mBtnCall;
    private Button mBtnContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnWeb = findViewById(R.id.btnWeb);
        mBtnMap = findViewById(R.id.btnMap);
        mBtnCall = findViewById(R.id.btnCall);
        mBtnContacts = findViewById(R.id.btnContacts);

        mBtnWeb.setOnClickListener(this);
        mBtnMap.setOnClickListener(this);
        mBtnCall.setOnClickListener(this);
        mBtnContacts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnWeb:
                startActivityWithIntentFromUri(Intent.ACTION_VIEW,
                        "http://developer.android.com");
                break;
            case R.id.btnMap:
                startActivityWithIntentFromUri(Intent.ACTION_VIEW, "geo:55.754283,37.62002");
                break;
            case R.id.btnCall:
                startActivityWithIntentFromUri(Intent.ACTION_VIEW, "tel:12345");
                break;
            case R.id.btnContacts:
                if (ContextCompat.checkSelfPermission(
                        this, Manifest.permission.READ_CONTACTS)
                        == PackageManager.PERMISSION_GRANTED) {
                    startActivityWithIntentFromUri(Intent.ACTION_VIEW,
                            ContactsContract.Contacts.CONTENT_URI);
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            REQUEST_CODE_PERMISSION_READ_CONTACTS);
                }
                break;
        }
    }

    /**
     * Старт активити из интента, содержащего дейтвсие и строку uri
     *
     * @param action действие
     * @param uri    строка, содержащая Uri
     */
    private void startActivityWithIntentFromUri(String action, String uri) {
        Log.d(TAG, "startActivityWithIntentFromUri: action " + action + ", uri " + uri);
        Intent intent;
        intent = new Intent(action);
        intent.setData(Uri.parse(uri));
        Log.d(TAG, "Before startActivity, intent: " + intent);
        startActivity(intent);
    }

    /**
     * Старт активити из интента, содержащего дейтвсие и строку uri
     *
     * @param action действие
     * @param uri    объект Uri
     */
    private void startActivityWithIntentFromUri(String action, Uri uri) {
        Log.d(TAG, "startActivityWithIntentFromUri: action " + action + ", uri " + uri);
        Intent intent;
        intent = new Intent(action);
        intent.setData(uri);
        Log.d(TAG, "Before startActivity, intent: " + intent);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_READ_CONTACTS:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivityWithIntentFromUri(Intent.ACTION_VIEW,
                            ContactsContract.Contacts.CONTENT_URI);
                }
        }
    }
}
