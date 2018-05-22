package com.example.yakovenko_va.p0361_sqlitequery;

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
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    String[] countries = {"Китай", "США", "Бразилия", "Россия", "Япония", "Германия", "Египет",
            "Италия", "Франция", "Канада"};

    int[] people = {1400, 311, 195, 142, 128, 82, 80, 60, 66, 35};

    String[] region = {"Азия", "Америка", "Америка", "Европа", "Азия", "Европа", "Африка", "Европа",
            "Европа", "Америка"};

    Button mBtnAll, mBtnFunc, mBtnPeople, mBtnSort, mBtnGroup, mBtnHaving;
    EditText mEtFunction, mEtPeople, mEtRegionPeople;
    RadioGroup mRgSort;

    DBHelper mDBHelper;
    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnAll = findViewById(R.id.btnAll);
        mBtnAll.setOnClickListener(this);

        mBtnFunc = findViewById(R.id.btnFunc);
        mBtnFunc.setOnClickListener(this);

        mBtnPeople = findViewById(R.id.btnPeople);
        mBtnPeople.setOnClickListener(this);

        mBtnSort = findViewById(R.id.btnSort);
        mBtnSort.setOnClickListener(this);

        mBtnGroup = findViewById(R.id.btnGroup);
        mBtnGroup.setOnClickListener(this);

        mBtnHaving = findViewById(R.id.btnHaving);
        mBtnHaving.setOnClickListener(this);

        mEtFunction = findViewById(R.id.etFunc);
        mEtPeople = findViewById(R.id.etPeople);
        mEtRegionPeople = findViewById(R.id.etRegionPeople);

        mRgSort = findViewById(R.id.rgSort);

        mDBHelper = new DBHelper(this);

        mDatabase = mDBHelper.getWritableDatabase();

        Cursor c = mDatabase.query("user", null, null, null, null, null, null);
        if (c.getCount() == 0) {
            ContentValues values = new ContentValues();
            for (int i = 0; i < 10; i++) {
                values.put("name", countries[i]);
                values.put("people", people[i]);
                values.put("region", region[i]);
                Log.d(TAG, "id = " + mDatabase.insert("user", null, values));
            }
        }
        c.close();
        mDatabase.close();

        onClick(mBtnAll);
    }

    @Override
    public void onClick(View v) {
        mDatabase = mDBHelper.getWritableDatabase();

        String sFunc = mEtFunction.getText().toString();
        String sPeople = mEtPeople.getText().toString();
        String sRegionPeople = mEtRegionPeople.getText().toString();

        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor c = null;

        switch (v.getId()) {
            case R.id.btnAll:
                Log.d(TAG, "--- Все записи ---");
                c = mDatabase.query("user", null, null, null,
                        null, null, null);
                break;
            case R.id.btnFunc:
                Log.d(TAG, "--- Функция " + sFunc + " ---");
                columns = new String[]{sFunc};
                c = mDatabase.query("user", columns, null, null,
                        null, null, null);
                break;
            case R.id.btnPeople:
                Log.d(TAG, "---  Население больше " + sPeople + " ---");
                selection = "people > ?";
                selectionArgs = new String[]{sPeople};
                c = mDatabase.query("user", null, selection, selectionArgs,
                        null, null, null);
                break;
            case R.id.btnGroup:
                Log.d(TAG, "--- Население по региону ---");
                columns = new String[]{"region", "sum(people) as people"};
                groupBy = "region";
                having = "sum(people) > " + sRegionPeople;
                c = mDatabase.query("user", columns, null, null,
                        groupBy, having, null);
                break;
            case R.id.btnSort:
                switch (mRgSort.getCheckedRadioButtonId()) {
                    case R.id.rName:
                        Log.d(TAG, "--- Соритировка по наименованию ---");
                        orderBy = "name";
                        break;
                    case R.id.rPeople:
                        Log.d(TAG, "--- Сортировка по населению ---");
                        orderBy = "people";
                        break;
                    case R.id.rRegion:
                        Log.d(TAG, "--- Сортировка по региону ---");
                        orderBy = "region";
                        break;
                }
                c = mDatabase.query("user", null, null, null,
                        null, null, orderBy);
                break;
        }
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String s : c.getColumnNames()) {
                        str = str.concat(s + " = " + c.getString(c.getColumnIndex(s)) + "; ");
                    }
                    Log.d(TAG, str);
                } while (c.moveToNext());
            }
            c.close();
        } else {
            Log.d(TAG, "Cursor is null");
        }
        mDatabase.close();
    }

    class DBHelper extends SQLiteOpenHelper {
        DBHelper(Context context) {
            super(context, "user", null, 1);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, "Создаем таблицу");
            db.execSQL("create table user (" +
                    "id integer primary key autoincrement, " +
                    "name text," +
                    "people intenger," +
                    "region text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
