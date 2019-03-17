package com.example.myapplication.ui;

import android.content.SharedPreferences;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import com.example.myapplication.R;
import com.example.myapplication.common.SingleFragmentActivity;
import com.example.myapplication.ui.fragments.LogsFragment;
import com.example.myapplication.ui.fragments.MainFragment;
import com.example.myapplication.ui.fragments.ModsFragment;

import java.util.Random;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return MainFragment.newInstance();
    }

    private BottomNavigationView mBnav;

    @Override
    protected void onStart() {
        mBnav = findViewById(R.id.bottom_navigation);
        mBnav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.main:
                    changeFragment(MainFragment.newInstance());
                    break;
                case R.id.mods:
                    changeFragment(ModsFragment.newInstance());
                    break;
                case R.id.myLogs:
                    changeFragment(LogsFragment.newInstance());
                    break;
            }
            return false;
        });

        super.onStart();
    }

    @Override
    protected void onDestroy() {
        //избегаю утечки памяти как могу
        mBnav = null;

        super.onDestroy();
    }
}