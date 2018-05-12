package com.example.p0201_simpleanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import static com.example.p0201_simpleanimation.Constants.MENU_ALPHA_ID;
import static com.example.p0201_simpleanimation.Constants.MENU_COMBO_ID;
import static com.example.p0201_simpleanimation.Constants.MENU_ROTATE_ID;
import static com.example.p0201_simpleanimation.Constants.MENU_SCALE_ID;
import static com.example.p0201_simpleanimation.Constants.MENU_TRANSLATE_ID;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tv);
        registerForContextMenu(mTextView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.tv:
                menu.add(0, MENU_ALPHA_ID, 0, "alpha");
                menu.add(0, MENU_SCALE_ID, 0, "scale");
                menu.add(0, MENU_TRANSLATE_ID, 0, "translate");
                menu.add(0, MENU_ROTATE_ID, 0, "rotate");
                menu.add(0, MENU_COMBO_ID, 0, "combo");
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Animation animation = null;
        switch (item.getItemId()) {
            case MENU_ALPHA_ID:
                animation = AnimationUtils.loadAnimation(this, R.anim.myalpha);
                break;
            case MENU_SCALE_ID:
                animation = AnimationUtils.loadAnimation(this, R.anim.myscale);
                break;
            case MENU_TRANSLATE_ID:
                animation = AnimationUtils.loadAnimation(this, R.anim.mytrans);
                break;
            case MENU_ROTATE_ID:
                animation = AnimationUtils.loadAnimation(this, R.anim.myrotate);
                break;
            case MENU_COMBO_ID:
                animation = AnimationUtils.loadAnimation(this, R.anim.mycombo);
        }
        mTextView.startAnimation(animation);
        return super.onContextItemSelected(item);
    }
}
