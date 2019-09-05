package com.example.lrjgreendaodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SelectTable extends AppCompatActivity implements View.OnClickListener {
    private Button btSelectTableStudent;
    private Button btSelectTableCourse;
    private Button btSelectTableScore;
    private String data;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_which_table);

        data = getIntent().getStringExtra("extra_data");
        btSelectTableStudent = findViewById(R.id.bt_select_table_student);
        btSelectTableCourse = findViewById(R.id.bt_select_table_course);
        btSelectTableScore = findViewById(R.id.bt_select_table_score);

        btSelectTableStudent.setOnClickListener(this);
        btSelectTableCourse.setOnClickListener(this);
        btSelectTableScore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_select_table_student:
                if (data.equals("Add")){
                    Log.e("SH", "添加学生具体信息");
                    Intent intent_0 = new Intent(this, AddStudentInfo.class);
                    startActivity(intent_0);
                }else if (data.equals("Delete")){


                }else if (data.equals("Update")){


                }else if (data.equals("Search")){
                    Log.e("SH", "查询学生信息");
                    Intent intent_3 = new Intent(this, SearchStudentInfo.class);
                    startActivity(intent_3);
                }
                break;
            case R.id.bt_select_table_course:

                break;
            case R.id.bt_select_table_score:

                break;
            default:
                break;
        }
    }
}
