package com.example.p0151_contextmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static com.example.p0151_contextmenu.Constants.BLUE;
import static com.example.p0151_contextmenu.Constants.GREEN;
import static com.example.p0151_contextmenu.Constants.MENU_COLOR_BLUE;
import static com.example.p0151_contextmenu.Constants.MENU_COLOR_GREEN;
import static com.example.p0151_contextmenu.Constants.MENU_COLOR_RED;
import static com.example.p0151_contextmenu.Constants.MENU_SIZE_22;
import static com.example.p0151_contextmenu.Constants.MENU_SIZE_26;
import static com.example.p0151_contextmenu.Constants.MENU_SIZE_30;
import static com.example.p0151_contextmenu.Constants.RED;
import static com.example.p0151_contextmenu.Constants.SIZE_22;
import static com.example.p0151_contextmenu.Constants.SIZE_26;
import static com.example.p0151_contextmenu.Constants.SIZE_30;

public class Main_Activity extends AppCompatActivity {
    private static final String TEXT_SIZE_PATTERN = "Text size = %s";
    private static final String TEXT_COLOR_PATTERN = "Text color = %s";
    TextView mTvColor;
    TextView mTvSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvColor = findViewById(R.id.tvColor);
        mTvSize = findViewById(R.id.tvSize);

        registerForContextMenu(mTvColor);
        registerForContextMenu(mTvSize);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.tvColor:
                menu.add(0, MENU_COLOR_RED, 0, RED);
                menu.add(0, MENU_COLOR_GREEN, 0, GREEN);
                menu.add(0, MENU_COLOR_BLUE, 0, BLUE);
                break;
            case R.id.tvSize:
                menu.add(0, MENU_SIZE_22, 0, SIZE_22);
                menu.add(0, MENU_SIZE_26, 0, SIZE_26);
                menu.add(0, MENU_SIZE_30, 0, SIZE_30);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_COLOR_RED:
                setColor(RED);
                break;
            case MENU_COLOR_BLUE:
                setColor(BLUE);
                break;
            case MENU_COLOR_GREEN:
                setColor(GREEN);
                break;
            case MENU_SIZE_22:
                setSize(SIZE_22);
                break;
            case MENU_SIZE_26:
                setSize(SIZE_26);
                break;
            case MENU_SIZE_30:
                setSize(SIZE_30);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void setSize(String size) {
        mTvSize.setTextSize(Float.valueOf(size));
        mTvSize.setText(String.format(TEXT_SIZE_PATTERN, size));
    }

    private void setColor(String color) {
        switch (color) {
            case RED:
                mTvColor.setTextColor(Color.RED);
                break;
            case GREEN:
                mTvColor.setTextColor(Color.GREEN);
                break;
            case BLUE:
                mTvColor.setTextColor(Color.BLUE);
                break;
        }
        mTvColor.setText(String.format(TEXT_COLOR_PATTERN, color));
    }
}
