package com.example.myapplication.ui.fragments.Logs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.ApplicationSingleton;
import com.example.myapplication.R;
import com.example.myapplication.model.MyLog;

import java.util.List;

public class LogsAdapter extends BaseAdapter {

    private List<MyLog> mListOfLogs;

    public LogsAdapter(List<MyLog> mListOfLogs) {
        this.mListOfLogs = mListOfLogs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.li_log,parent,false);
        final MyLog log = mListOfLogs.get(position);
        TextView mLogTextView = v.findViewById(R.id.log_text);
        Button mDeleteButton = v.findViewById(R.id.btn_delete);
        mLogTextView.setText(log.getId()+") "+log.getResult()+"\n"+log.getTime());
        mDeleteButton.setOnClickListener(v1 -> ((ApplicationSingleton)
                v1.getContext().getApplicationContext()).getmStorage().deleteLog(log));

        return v;
    }

    @Override
    public int getCount() {
        return mListOfLogs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}