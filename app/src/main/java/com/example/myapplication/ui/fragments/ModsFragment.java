package com.example.myapplication.ui.fragments;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.ApplicationSingleton;
import com.example.myapplication.R;

public class ModsFragment extends Fragment {

    private RadioGroup mRadioGroup;
    private SharedPreferences mPreferences;

    public static ModsFragment newInstance() {
        return new ModsFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mods_view,container,false);
        mRadioGroup = v.findViewById(R.id.radio_group);
        mPreferences = ((ApplicationSingleton) getActivity().getApplication())
                .getmSharedPreferences();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        switch (mPreferences.getInt("mod",1)){
            case 1:
                RadioButton rb = v.findViewById(R.id.rbtn_mod_1);
                rb.setChecked(true);
                break;
            case 2:
                rb = v.findViewById(R.id.rbtn_mod_2);
                rb.setChecked(true);
                break;
            case 3:
                rb = v.findViewById(R.id.rbtn_mod_3);
                rb.setChecked(true);
                break;

        }
        mRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.rbtn_mod_1:
                    mPreferences.edit().putInt("mod",1).apply();
                    break;
                case R.id.rbtn_mod_2:
                    mPreferences.edit().putInt("mod",2).apply();
                    break;
                case R.id.rbtn_mod_3:
                    mPreferences.edit().putInt("mod",3).apply();
                    break;

            }
        });
    }
}
