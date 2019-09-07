package com.example.lrjgreendaodemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lrjgreendaodemo.gen.DaoMaster;
import com.example.lrjgreendaodemo.gen.DaoSession;
import com.example.lrjgreendaodemo.gen.ScoreDao;

import java.util.List;

public class SearchScoreInfo extends AppCompatActivity {
    SQLiteDatabase db;
    DaoMaster master;
    DaoSession session;
    ScoreDao scoreDao;
    String str_getfrom_et = "";
    EditText etSearchScoreInfoSno;
    EditText etSearchScoreInfoCno;
    Button btSearchScoreInfo;
    List<Score> score_list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_score_info);
        openDB();
        etSearchScoreInfoSno = findViewById(R.id.et_search_score_info_sno);
        etSearchScoreInfoCno = findViewById(R.id.et_search_score_info_cno);
        btSearchScoreInfo = findViewById(R.id.bt_search_score_info);
        if (getIntent().getStringExtra("search_or_delete_or_update").equals("delete")){
            btSearchScoreInfo.setText("删除");
        }
        btSearchScoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleteOrUpdate = getIntent().getStringExtra("search_or_delete_or_update");
                if (deleteOrUpdate.equals("delete")){
                    String str_sno = etSearchScoreInfoSno.getText().toString();
                    String str_cno = etSearchScoreInfoCno.getText().toString();
                    if (isExist(str_sno, str_cno)){
                        Score score = scoreDao.queryBuilder().where(ScoreDao.Properties.Sno.eq(str_sno),
                                ScoreDao.Properties.Cno.eq(str_cno)).unique();
                        Log.e("SH", score.getSno()+"   "+ score.getCno());
                        try{
                            scoreDao.delete(score);
                        }catch (Exception e){
                            Log.e("SH","删除失败");
                            e.printStackTrace();
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(SearchScoreInfo.this);
                        builder.setMessage("删除成功");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SearchScoreInfo.this, crud.class);
                                startActivity(intent);
                            }
                        });
                        builder.show();
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SearchScoreInfo.this);
                        builder.setMessage("填写信息不完整或信息填写错误");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                } else {
                    //这是查询表的操作


                }

            }
        });
    }

    private void openDB() {
        db = new DaoMaster.DevOpenHelper(this, "score.db", null).getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        scoreDao = session.getScoreDao();
    }

    private Boolean isExist(String str_sno, String str_cno){
        score_list = scoreDao.queryBuilder().list();
        for (int i = 0; i < score_list.size(); i++) {
            if (Long.toString(score_list.get(i).getSno()).equals(str_sno)
                    && Long.toString(score_list.get(i).getCno()).equals(str_cno) ){
                return true;
            }
        }
        return  false;
    }
}
