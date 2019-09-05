package com.example.lrjgreendaodemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lrjgreendaodemo.gen.DaoMaster;
import com.example.lrjgreendaodemo.gen.DaoSession;
import com.example.lrjgreendaodemo.gen.StudentDao;

import java.util.ArrayList;
import java.util.List;

public class StudentList extends AppCompatActivity {
    private List<Student> students_list;
    private List<Student> students_list_by_sno;
    String str;
    private DaoMaster master;
    private DaoSession session;
    private StudentDao studentDao;
    private SQLiteDatabase db;
    EditText etSearchStudentBySno;
    Button btSearchInStudentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list);
        openDb();
        str = getIntent().getStringExtra("search_stu_by_sno");
        students_list_by_sno = new ArrayList<>();

        etSearchStudentBySno = findViewById(R.id.et_search_student_by_sno);
        btSearchInStudentList = findViewById(R.id.bt_search_in_studentList);
        btSearchInStudentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                students_list.clear();
                students_list_by_sno.clear();
                if (etSearchStudentBySno.getText().toString().length() != 0) {
                    str = etSearchStudentBySno.getText().toString();
                } else {
                    str = "";
                }
                load();
            }
        });
    }

    private void openDb() {
        db = new DaoMaster.DevOpenHelper(this, "student.db", null).getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        studentDao = session.getStudentDao();
    }

    @Override
    protected void onStart() {
        super.onStart();
        load();
    }

    private void load() {
        students_list = studentDao.queryBuilder().list();
        if (str.length() != 0) {
            Log.e("SH", "有条件查询学生表");
            for (int i = 0; i < students_list.size(); i++) {
                if (Long.toString(students_list.get(i).getSno()).equals(str)) {
                    students_list_by_sno.add(students_list.get(i));
                }
            }
            StudentAdapter adapter = new StudentAdapter(StudentList.this, R.layout.student_list_item, students_list_by_sno);
            ListView listView = findViewById(R.id.student_info_list);
            listView.setAdapter(adapter);
        } else {
            Log.e("SH", "无条件查询学生表");
            StudentAdapter adapter = new StudentAdapter(StudentList.this, R.layout.student_list_item, students_list);
            ListView listView = findViewById(R.id.student_info_list);
            listView.setAdapter(adapter);
        }
    }
}
