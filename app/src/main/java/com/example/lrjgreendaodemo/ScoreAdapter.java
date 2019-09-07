package com.example.lrjgreendaodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ScoreAdapter extends ArrayAdapter<Score> {
    private int sourceId;

    public ScoreAdapter(Context context, int textViewResourceId, List<Score> scoreList) {
        super(context, textViewResourceId, scoreList);
        sourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Score sco = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(sourceId, parent, false);
        TextView sno = ((TextView) view.findViewById(R.id.tv_score_sno));
        TextView cno = ((TextView) view.findViewById(R.id.tv_score_cno));
        TextView score = ((TextView) view.findViewById(R.id.tv_score_score));
        sno.setText("学号："+Long.toString(sco.getSno()));
        cno.setText("课程号："+Long.toString(sco.getCno()));
        score.setText("成绩："+Long.toString(sco.getScore()));
        return view;
    }
}
