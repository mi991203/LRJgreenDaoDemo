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
import com.example.lrjgreendaodemo.gen.StudentDao;

public class SearchStudentInfo extends AppCompatActivity {
    DaoMaster master;
    DaoSession session;
    StudentDao studentDao;
    SQLiteDatabase db;

    EditText etSearchStudentInfoSno;
    Button btSearchStudentInfoBySno;
    String str_et_search_stu_by_sno;
    String str;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_student_info);
        openDb();

        str = getIntent().getStringExtra("search_or_delete_or_update");
        etSearchStudentInfoSno = findViewById(R.id.et_search_student_info_sno);
        btSearchStudentInfoBySno = findViewById(R.id.bt_search_student_info_bySno);
        if (str.equals("delete")) {
            btSearchStudentInfoBySno.setText("删除");
        }

        btSearchStudentInfoBySno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str.equals("search")) {
                    Intent intent = new Intent(SearchStudentInfo.this, StudentList.class);
                    str_et_search_stu_by_sno = etSearchStudentInfoSno.getText().toString();
                    Log.e("SH", str_et_search_stu_by_sno);
                    intent.putExtra("search_stu_by_sno", str_et_search_stu_by_sno);
                    startActivity(intent);
                } else { //如果时删除
                    boolean flag = false;
                    Student student = studentDao.queryBuilder().where(StudentDao.Properties.Sno.eq(etSearchStudentInfoSno.getText().toString())).unique();
                    try {
                        studentDao.delete(student);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    AlertDialog.Builder dialog = new AlertDialog.Builder(SearchStudentInfo.this);
                    dialog.setMessage("删除成功");
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(SearchStudentInfo.this, crud.class);
                            startActivity(intent);
                        }
                    });
                    dialog.show();
                }
            }
        });

    }

    private void openDb() {
        db = new DaoMaster.DevOpenHelper(this, "student.db", null).getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        studentDao = session.getStudentDao();
    }
}
