package com.example.lrjgreendaodemo;

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
import com.example.lrjgreendaodemo.gen.StudentDao;

public class AddCourseInfo extends AppCompatActivity implements View.OnClickListener {
    private EditText etAddCourseCno;
    private EditText etAddCourseCname;
    private EditText etAddCourseCtime;
    private EditText etAddCourseCteacher;

    private DaoMaster master;
    private DaoSession session;
    private CourseDao courseDao;
    private SQLiteDatabase db;

    private Button btAddCourseReset;
    private Button btAddCourseSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course_info);
        openDB();

        etAddCourseCno = findViewById(R.id.et_add_course_cno);
        etAddCourseCname = findViewById(R.id.et_add_course_cname);
        etAddCourseCtime = findViewById(R.id.et_add_course_ctime);
        etAddCourseCteacher = findViewById(R.id.et_add_course_cteacher);
        btAddCourseReset = findViewById(R.id.bt_add_course_info_reset);
        btAddCourseSave = findViewById(R.id.bt_add_course_info_save);


    }

    private void openDB() {
        db = new DaoMaster.DevOpenHelper(this, "course.db", null).getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        courseDao = session.getCourseDao();
    }

    @Override
    protected void onStart() {
        super.onStart();
        load();
    }

    private void load() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_course_info_reset:
                etAddCourseCno.setText("");
                etAddCourseCname.setText("");
                etAddCourseCtime.setText("");
                etAddCourseCteacher.setText("");
                break;
            case R.id.bt_add_course_info_save:
                courseDao.insert(getStudentFromUI());
        }
    }

    private Course getStudentFromUI() {
        Long cno = Long.parseLong(etAddCourseCno.getText().toString());
        String cname = etAddCourseCname.getText().toString();
        int ctime = Integer.parseInt(etAddCourseCtime.getText().toString());
        String cteacher = etAddCourseCteacher.getText().toString();
        Course course = new Course(cno, cname, ctime, cteacher);
        return course;
    }
}
