package com.example.lrjgreendaodemo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lrjgreendaodemo.gen.DaoMaster;
import com.example.lrjgreendaodemo.gen.DaoSession;
import com.example.lrjgreendaodemo.gen.StudentDao;

public class AddStudentInfo extends AppCompatActivity implements View.OnClickListener {
    private EditText etAddStudentSno;
    private EditText etAddStudentSname;
    private EditText etAddStudentSsex;
    private EditText etAddStudentSage;
    private Button btAddStudentReset;
    private Button btAddStudentSave;

    DaoMaster master;
    DaoSession session;
    StudentDao studentDao;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openDb();

        etAddStudentSno = findViewById(R.id.et_add_student_sno);
        etAddStudentSname = findViewById(R.id.et_add_student_sname);
        etAddStudentSsex = findViewById(R.id.et_add_student_ssex);
        etAddStudentSage = findViewById(R.id.et_add_student_sage);
        btAddStudentReset = findViewById(R.id.bt_add_student_info_reset);
        btAddStudentSave = findViewById(R.id.bt_add_student_info_save);

        btAddStudentReset.setOnClickListener(this);
        btAddStudentSave.setOnClickListener(this);

    }

    private void openDb() {
        db = new DaoMaster.DevOpenHelper(this, "student.db", null).getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        studentDao = session.getStudentDao();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_student_info_reset:
                etAddStudentSno.setText("");
                etAddStudentSname.setText("");
                etAddStudentSsex.setText("");
                etAddStudentSage.setText("");
                break;
            case R.id.bt_add_student_info_save:
                studentDao.insert(getStudentFromUI());
                break;
            default:
                break;
        }
    }

    private Student getStudentFromUI() {
        Long sno = Long.parseLong(etAddStudentSno.getText().toString());
        String sname = etAddStudentSname.getText().toString();
        String ssex = etAddStudentSsex.getText().toString();
        int sage = Integer.parseInt(etAddStudentSage.getText().toString());
        Student student = new Student(sno, sname, ssex, sage);
        return student;
    }
}
