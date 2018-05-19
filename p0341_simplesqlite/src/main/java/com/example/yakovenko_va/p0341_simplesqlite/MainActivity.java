package com.example.yakovenko_va.p0341_simplesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getName();
    DBHelper mDBHelper;
    private Button mBtnAdd, mBtnRead, mBtnClear;
    private EditText mEtName, mEtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnAdd.setOnClickListener(this);

        mBtnRead = findViewById(R.id.btnRead);
        mBtnRead.setOnClickListener(this);

        mBtnClear = findViewById(R.id.btnClear);
        mBtnClear.setOnClickListener(this);

        mEtName = findViewById(R.id.etName);
        mEtEmail = findViewById(R.id.etEmail);

        mDBHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues contentValues = new ContentValues();

        String name = mEtName.getText().toString();
        String email = mEtEmail.getText().toString();

        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.btnAdd:
                Log.d(TAG, "Операция вставки в таблицу");
                contentValues.put("name", name);
                contentValues.put("email", email);

                long rowID = db.insert("user", null, contentValues);
                Log.d(TAG, "Строка вставлена, ID = " + rowID);
                break;
            case R.id.btnRead:
                Log.d(TAG, "Операция чтения из таблицы");
                Cursor c = db.query("user", null, null, null,
                        null, null, null);
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int emailColIndex = c.getColumnIndex("email");

                    do {
                        Log.d(TAG,
                                "ID = " + c.getInt(idColIndex) +
                                        ", name = " + c.getString(nameColIndex) +
                                        ", email = " + c.getString(emailColIndex));
                    } while (c.moveToNext());
                } else {
                    Log.d(TAG, "0 rows");
                }
                c.close();
                break;
            case R.id.btnClear:
                Log.d(TAG, "Очиска таблицы");
                int clearCount = db.delete("user", null, null);
                Log.d(TAG, "Количество удаленных строк = " + clearCount);
                break;
        }
        mDBHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {

        DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, "Создаем таблицу с полями");
            db.execSQL("create table user (" +
                    "id integer primary key autoincrement," +
                    "name varchar2(20)," +
                    "email varchar2(40)" +
                    ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}