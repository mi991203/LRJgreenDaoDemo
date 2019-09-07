package com.example.lrjgreendaodemo;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lrjgreendaodemo.gen.DaoMaster;
import com.example.lrjgreendaodemo.gen.DaoSession;
import com.example.lrjgreendaodemo.gen.ScoreDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreList extends AppCompatActivity {
    SQLiteDatabase db;
    DaoMaster master;
    DaoSession session;
    ScoreDao scoreDao;
    List<Score> score_list;
    List<Score> score_list_by_sno_cno;
    String string_et_sno = "";
    String string_et_cno = "";
    EditText etSearchScoreBySno;
    EditText etSearchScoreByCno;
    Button btSearchInScoreList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_list);
        openDB();
        score_list_by_sno_cno = new ArrayList<>();
        etSearchScoreByCno = findViewById(R.id.et_search_score_by_cno);
        etSearchScoreBySno = findViewById(R.id.et_search_score_by_sno);
        btSearchInScoreList = findViewById(R.id.bt_search_in_score_list);
        btSearchInScoreList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etSearchScoreBySno.getText()) && TextUtils.isEmpty(etSearchScoreByCno.getText())) {
                    string_et_sno = "";
                    string_et_cno = "";
                    load();
                } else if (TextUtils.isEmpty(etSearchScoreBySno.getText()) || TextUtils.isEmpty(etSearchScoreByCno.getText())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScoreList.this);
                    builder.setTitle("请填写完整查询信息");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
                else {
                    string_et_cno = etSearchScoreByCno.getText().toString();
                    string_et_sno = etSearchScoreBySno.getText().toString();
                    score_list.clear();
                    score_list_by_sno_cno.clear();
                    load();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        load();
    }

    private void openDB() {
        db = new DaoMaster.DevOpenHelper(this, "score.db", null).getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        scoreDao = session.getScoreDao();
    }

    private void load() {
        score_list = scoreDao.queryBuilder().list();
        if (!string_et_sno.equals("") && !string_et_cno.equals("")){
            for (int i = 0; i < score_list.size(); i++) {
                if (Long.toString(score_list.get(i).getSno()).equals(string_et_sno)
                        &&  Long.toString(score_list.get(i).getCno()).equals(string_et_cno)){
                    score_list_by_sno_cno.add(score_list.get(i));
                }
            }
            ScoreAdapter adapter = new ScoreAdapter(ScoreList.this, R.layout.score_list_item, score_list_by_sno_cno);
            ListView listView = ((ListView) findViewById(R.id.score_info_list));
            listView.setAdapter(adapter);
        } else {
            ScoreAdapter adapter = new ScoreAdapter(ScoreList.this, R.layout.score_list_item, score_list);
            ListView listView = ((ListView) findViewById(R.id.score_info_list));
            listView.setAdapter(adapter);
        }
    }
}
