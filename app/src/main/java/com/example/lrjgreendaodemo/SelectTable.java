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
                if (data.equals("Add")) {
                    Log.e("SH", "添加学生信息");
                    Intent intent_0 = new Intent(this, AddStudentInfo.class);
                    intent_0.putExtra("isUpdate", "add");
                    startActivity(intent_0);
                } else if (data.equals("Delete")) {
                    Log.e("SH", "删除学生信息");
                    Intent intent_1 = new Intent(this, SearchStudentInfo.class);
                    intent_1.putExtra("search_or_delete_or_update", "delete");
                    startActivity(intent_1);
                } else if (data.equals("Update")) {
                    Log.e("SH", "修改学生信息");
                    Intent intent_2 = new Intent(this, SearchStudentInfo.class);
                    intent_2.putExtra("search_or_delete_or_update", "update");
                    startActivity(intent_2);

                } else if (data.equals("Search")) {
                    Log.e("SH", "查询学生信息");
                    Intent intent_3 = new Intent(this, StudentList.class);
                    startActivity(intent_3);
                }
                break;
            case R.id.bt_select_table_course:
                if (data.equals("Add")) {
                    Log.e("SH", "添加课程信息");
                    Intent intent_0 = new Intent(this, AddCourseInfo.class);
                    intent_0.putExtra("isUpdate", "add");
                    startActivity(intent_0);
                } else if (data.equals("Delete")) {
                    Log.e("SH", "删除课程信息");
                    Intent intent_1 = new Intent(this, SearchCourseInfo.class);
                    intent_1.putExtra("search_or_delete_or_update", "delete");
                    startActivity(intent_1);
                } else if (data.equals("Update")) {
                    Log.e("SH", "修改课程信息");
                    Intent intent_2 = new Intent(this, SearchCourseInfo.class);
                    intent_2.putExtra("search_or_delete_or_update", "update");
                    startActivity(intent_2);

                } else if (data.equals("Search")) {
                    Log.e("SH", "查询课程信息");
                    Intent intent_3 = new Intent(this, CourseList.class);
                    startActivity(intent_3);
                }
                break;
            case R.id.bt_select_table_score:
                if (data.equals("Add")) {
                    Log.e("SH", "添加成绩信息");
                    Intent intent_0 = new Intent(this, AddScoreInfo.class);
                    intent_0.putExtra("isUpdate", "add");
                    startActivity(intent_0);
                } else if (data.equals("Delete")) {
                    Log.e("SH", "删除成绩信息");
                    Intent intent_1 = new Intent(this, SearchScoreInfo.class);
                    intent_1.putExtra("search_or_delete_or_update", "delete");
                    startActivity(intent_1);
                } else if (data.equals("Update")) {
                    Log.e("SH", "修改成绩信息");
                    Intent intent_2 = new Intent(this, SearchScoreInfo.class);
                    intent_2.putExtra("search_or_delete_or_update", "update");
                    startActivity(intent_2);

                } else if (data.equals("Search")) {
                    Log.e("SH", "查询成绩信息");
                    Intent intent_3 = new Intent(this, ScoreList.class);
                    startActivity(intent_3);
                }
                break;
            default:
                break;
        }
    }
}
