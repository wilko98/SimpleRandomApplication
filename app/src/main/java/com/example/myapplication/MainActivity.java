package com.example.myapplication;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

enum Mods {
    MOD_1,MOD_2,MOD_3
}

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private TextView mResultText;
    private RadioGroup mRadioGroup;
    private Button mGetResultBtn;
    private Random mRandom;
    BottomNavigationView mBnav;
    private RadioButton rbt;
    private View mMainView;
    private View mModsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainView = findViewById(R.id.view_main);
        mModsView = findViewById(R.id.view_mods);
        mRandom = new Random();
        mPrefs = getSharedPreferences(BuildConfig.PREFS_KEY, MODE_PRIVATE);
        mBnav = findViewById(R.id.bottom_navigation);
        mBnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.main:
                        mMainView.setVisibility(View.VISIBLE);
                        mModsView.setVisibility(View.GONE);
                        break;
                    case R.id.mods:
                        mMainView.setVisibility(View.GONE);
                        mModsView.setVisibility(View.VISIBLE);
                        break;

                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        mResultText = findViewById(R.id.tv_result);
        mGetResultBtn = findViewById(R.id.btn_result);
        mRadioGroup = findViewById(R.id.radio_group);
        switch (mPrefs.getInt("mod",1)){
            case 1:
                rbt = findViewById(R.id.rbtn_mod_1);
                rbt.setChecked(true);
                break;
            case 2:
                rbt = findViewById(R.id.rbtn_mod_2);
                rbt.setChecked(true);
                break;
            case 3:
                rbt = findViewById(R.id.rbtn_mod_3);
                rbt.setChecked(true);
                break;
        }

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (mRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.rbtn_mod_1:
                        mPrefs.edit().putInt("mod", 1).apply();
                    break;
                    case R.id.rbtn_mod_2:
                        mPrefs.edit().putInt("mod", 2).apply();
                        break;
                    case R.id.rbtn_mod_3:
                        mPrefs.edit().putInt("mod", 3).apply();
                        break;
                }
            }
        });

        mGetResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mRadioGroup.getCheckedRadioButtonId()){
                    case R.id.rbtn_mod_1:
                        mResultText.setText(getRandomResult(Mods.MOD_1));
                        break;
                    case R.id.rbtn_mod_2:
                        mResultText.setText(getRandomResult(Mods.MOD_2));
                        break;
                    case R.id.rbtn_mod_3:
                        mResultText.setText(getRandomResult(Mods.MOD_3));
                        break;
                }
            }
        });


        super.onStart();
    }

    @Override
    protected void onDestroy() {
        //избегаю утечки памяти как могу
        mModsView = null;
        mMainView = null;
        mRandom = null;
        mGetResultBtn = null;
        mRadioGroup = null;
        mResultText = null;

        super.onDestroy();
    }

    private String getRandomResult(Mods mod){
        String result = "";
        switch (mod){
            case MOD_1:
                result += mRandom.nextInt(2);
                return result;
            case MOD_2:
                result = mRandom.nextBoolean()?"Yes":"No";
                return result;
            case MOD_3:
                result += mRandom.nextInt(101);
                return result;
        }
        return result;
    }

}