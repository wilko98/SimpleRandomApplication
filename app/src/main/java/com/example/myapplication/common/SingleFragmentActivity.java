package com.example.myapplication.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication.R;


public abstract class SingleFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_container);
        if (savedInstanceState == null) {
            changeFragment(getFragment());
        }
    }

    protected abstract Fragment getFragment();


    public void changeFragment(Fragment fragment) {
        boolean addToBackStack = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer) != null;
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
    }

}
