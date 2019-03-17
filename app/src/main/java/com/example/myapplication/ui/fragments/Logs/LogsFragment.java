package com.example.myapplication.ui.fragments.Logs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.ApplicationSingleton;
import com.example.myapplication.R;
import com.example.myapplication.database.Storage;

import java.util.Arrays;

public class LogsFragment extends Fragment {

    private Storage mStorage;
    private ListView mListView;
    private LogsAdapter mLogsAdapter;

    public static LogsFragment newInstance() {
        return new LogsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.logs_view, container, false);
        mStorage = ((ApplicationSingleton) getActivity().getApplication()).getmStorage();
        mListView = v.findViewById(R.id.list_view);
        mLogsAdapter = new LogsAdapter(mStorage.getAllLogs());
        mListView.setAdapter(mLogsAdapter);
        return v;
    }

}
