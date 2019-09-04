package com.example.lrjgreendaodemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.lrjgreendaodemo.gen.DaoMaster;
import com.example.lrjgreendaodemo.gen.DaoSession;
import com.example.lrjgreendaodemo.gen.StudentDao;

import java.util.List;

public class StudentList extends AppCompatActivity {
    private List<Student> students_list;
    private DaoMaster master;
    private DaoSession session;
    private StudentDao studentDao;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list);
        openDb();
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
        StudentAdapter adapter = new StudentAdapter(StudentList.this, R.layout.student_list_item, students_list);
        ListView listView = findViewById(R.id.student_info_list);
        listView.setAdapter(adapter);
    }
}
