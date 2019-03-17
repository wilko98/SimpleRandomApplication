package com.example.myapplication.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.ApplicationSingleton;
import com.example.myapplication.R;
import com.example.myapplication.database.Storage;
import com.example.myapplication.model.MyLog;

import java.util.Calendar;
import java.util.Random;

public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private TextView mResultText;
    private Button mBtnRandomize;
    private Random mRandom;
    private SharedPreferences mPreferences;
    private Storage mStorage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_view,container,false);
        mStorage = ((ApplicationSingleton) getActivity().getApplication()).getmStorage();
        mResultText = v.findViewById(R.id.tv_result);
        mBtnRandomize = v.findViewById(R.id.btn_result);
        mPreferences = ((ApplicationSingleton) getActivity().getApplication())
                .getmSharedPreferences();
        mRandom =new Random();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mBtnRandomize.setOnClickListener(v->{
            MyLog log = new MyLog();
            log.setId(mStorage.getLastId());
            log.setTime(Calendar.getInstance().getTime().toString());
            switch (mPreferences.getInt("mod",1)){
                case 1:
                    String result = mRandom.nextInt(2)+"";
                    mResultText.setText(result);
                    log.setResult(result);
                    mStorage.inserLog(log);
                    break;
                case 2:
                    result = mRandom.nextBoolean()?"Yes":"No";
                    mResultText.setText(result);
                    log.setResult(result);
                    mStorage.inserLog(log);
                    break;
                case 3:
                    result = mRandom.nextInt(101)+"";
                    mResultText.setText(result);
                    log.setResult(result);
                    mStorage.inserLog(log);
                    break;
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
