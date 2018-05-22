package com.example.savedinstancestateexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    int[] mPositionsId = {1, 2, 3, 4};
    String[] mPositionName = {"Директор", "Программер", "Бухгалтер", "Охранник"};
    int[] mPositionSalary = {150000, 13000, 10000, 8000};

    String[] mPeopleName = {"Иван", "Марья", "Петр", "Антон", "Даша", "Борис"};
    int[] mPeoplePosid = {2, 3, 2, 2, 3, 1, 2, 4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.d(TAG, "--- Table position ---");

        Cursor c = db.query("position", null, null, null, null, null, null);
        logCursor(c);
        c.close();
        Log.d(TAG, "--- ---");


        Log.d(TAG, "--- INNER JOIN with rawQuery ---");
        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary " +
                "from people as PL " +
                "inner join position as PS " +
                "on PL.posid = PS.id " +
                "where salary > ?";

        c = db.rawQuery(sqlQuery, new String[]{"12000"});
        logCursor(c);
        c.close();
        Log.d(TAG, "--- ---");

        Log.d(TAG, "--- INNER JOIN with query ---");
        String table = "people as PL inner join position as PS on PL.posid = PS.id";
        String[] columns = {"PL.name as Name", "PS.name as Position", "salary as Salary"};
        String selection = "salary < ?";
        String[] selectionArgs = {"12000"};
        c = db.query(table, columns, selection, selectionArgs, null, null, null);
        logCursor(c);
        c.close();
        Log.d(TAG, "--- ---");

        dbHelper.close();
    }

    private void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String s : c.getColumnNames()) {
                        str = str.concat(s + " = " + c.getString(c.getColumnIndex(s)));
                    }
                    Log.d(TAG, str);
                } while (c.moveToNext());
            } else {
                Log.d(TAG, "Cursor is null");
            }
        }
    }

    class DBHelper extends android.database.sqlite.SQLiteOpenHelper {
        DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, "Create db");
            ContentValues cv = new ContentValues();

            db.execSQL("create table position (" +
                    "id integer primary key," +
                    "name text," +
                    "salary integer" +
                    ");");

            for (int i = 0; i < mPositionsId.length; i++) {
                cv.clear();
                cv.put("id", mPositionsId[i]);
                cv.put("name", mPositionName[i]);
                cv.put("salary", mPositionSalary[i]);
                db.insert("position", null, cv);
            }

            db.execSQL("create table people (" +
                    "id integer primary key autoincrement," +
                    "name text," +
                    "posid integer" +
                    ");");

            for (int i = 0; i < mPeopleName.length; i++) {
                cv.clear();
                cv.put("name", mPeopleName[i]);
                cv.put("posid", mPeoplePosid[i]);
                db.insert("people", null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
