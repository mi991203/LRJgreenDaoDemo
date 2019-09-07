package com.example.lrjgreendaodemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lrjgreendaodemo.gen.CourseDao;
import com.example.lrjgreendaodemo.gen.DaoMaster;
import com.example.lrjgreendaodemo.gen.DaoSession;

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
    private String string;
    private Boolean isUpdate = false;
    String str;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course_info);
        openDB();

        string = getIntent().getStringExtra("isUpdate");
        etAddCourseCno = findViewById(R.id.et_add_course_cno);
        etAddCourseCname = findViewById(R.id.et_add_course_cname);
        etAddCourseCtime = findViewById(R.id.et_add_course_ctime);
        etAddCourseCteacher = findViewById(R.id.et_add_course_cteacher);
        btAddCourseReset = findViewById(R.id.bt_add_course_info_reset);
        btAddCourseSave = findViewById(R.id.bt_add_course_info_save);

        if (string.equals("update")) {
            isUpdate = true;
            str = getIntent().getStringExtra("update_cno");
            Course course = courseDao.queryBuilder().where(CourseDao.Properties.Cno.eq(str)).unique();
            etAddCourseCno.setText(Long.toString(course.getCno()));
            etAddCourseCname.setText(course.getCname());
            etAddCourseCtime.setText(course.getCtime() + "");
            etAddCourseCteacher.setText(course.getTeacher());

        }

        btAddCourseReset.setOnClickListener(this);
        btAddCourseSave.setOnClickListener(this);

    }

    private void openDB() {
        db = new DaoMaster.DevOpenHelper(this, "course.db", null).getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        courseDao = session.getCourseDao();
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
                if (isUpdate){
                    Course course = courseDao.queryBuilder().where(CourseDao.Properties.Cno.eq(str)).unique();
                    try{
                        courseDao.delete(course);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                courseDao.insert(getCourseFromUI());
                AlertDialog.Builder builder = new AlertDialog.Builder(AddCourseInfo.this);
                builder.setMessage("添加课程信息成功");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AddCourseInfo.this, crud.class);
                        startActivity(intent);
                    }
                });
                builder.show();
                break;
        }
    }

    private Course getCourseFromUI() {
        Long cno = Long.parseLong(etAddCourseCno.getText().toString());
        String cname = etAddCourseCname.getText().toString();
        int ctime = Integer.parseInt(etAddCourseCtime.getText().toString());
        String cteacher = etAddCourseCteacher.getText().toString();
        Course course = new Course(cno, cname, ctime, cteacher);
        return course;
    }
}
