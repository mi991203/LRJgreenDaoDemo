package com.example.lrjgreendaodemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lrjgreendaodemo.gen.CourseDao;
import com.example.lrjgreendaodemo.gen.DaoMaster;
import com.example.lrjgreendaodemo.gen.DaoSession;
import com.example.lrjgreendaodemo.gen.ScoreDao;

public class AddScoreInfo extends AppCompatActivity implements View.OnClickListener {
    private EditText etAddScoreSno;
    private EditText etAddScoreCno;
    private EditText etAddScoreScore;
    Button btAddScoreInfoReset;
    Button btAddScoreInfoSave;
    private DaoMaster master;
    private DaoSession session;
    private ScoreDao scoreDao;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_score_info);
        openDB();
        etAddScoreSno = findViewById(R.id.et_add_score_sno);
        etAddScoreCno = findViewById(R.id.et_add_score_cno);
        etAddScoreScore = findViewById(R.id.et_add_score_score);
        btAddScoreInfoReset = findViewById(R.id.bt_add_score_info_reset);
        btAddScoreInfoSave = findViewById(R.id.bt_add_score_info_save);

        btAddScoreInfoSave.setOnClickListener(this);
        btAddScoreInfoReset.setOnClickListener(this);
    }

    private void openDB() {
        db = new DaoMaster.DevOpenHelper(this, "score.db", null).getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        scoreDao = session.getScoreDao();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_score_info_reset:
                etAddScoreScore.setText("");
                etAddScoreCno.setText("");
                etAddScoreSno.setText("");
                break;
            case R.id.bt_add_score_info_save:
                scoreDao.insert(getScoreFromUI());
                AlertDialog.Builder builder = new AlertDialog.Builder(AddScoreInfo.this);
                builder.setMessage("添加学生信息成功");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AddScoreInfo.this, crud.class);
                        startActivity(intent);
                    }
                });
                builder.show();
                break;
            default:
                break;
        }
    }

    private Score getScoreFromUI() {
        Long sno = Long.parseLong(etAddScoreSno.getText().toString());
        Long cno = Long.parseLong(etAddScoreCno.getText().toString());
        int score = Integer.parseInt(etAddScoreScore.getText().toString());
        Score sco = new Score(sno, cno, score);
        return sco;
    }
}
