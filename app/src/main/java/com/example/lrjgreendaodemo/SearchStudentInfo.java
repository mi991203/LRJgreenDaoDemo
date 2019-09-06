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

import java.util.List;

public class SearchStudentInfo extends AppCompatActivity {
    private DaoMaster master;
    private DaoSession session;
    private StudentDao studentDao;
    private SQLiteDatabase db;
    private List<Student> students_list;

    private EditText etSearchStudentInfoSno;
    private Button btSearchStudentInfoBySno;
    private String str_et_search_stu_by_sno;
    private String str;


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
        } else if (str.equals("update")) {
            btSearchStudentInfoBySno.setText("修改");
        }

        btSearchStudentInfoBySno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (str.equals("search")) {
                    Log.e("SH", "点击了查询");
                    Intent intent = new Intent(SearchStudentInfo.this, StudentList.class);
                    str_et_search_stu_by_sno = etSearchStudentInfoSno.getText().toString();
                    Log.e("SH", str_et_search_stu_by_sno);
                    intent.putExtra("search_stu_by_sno", str_et_search_stu_by_sno);
                    startActivity(intent);
                } else if (str.equals("delete")) { //如果是删除
                    if (isExist(etSearchStudentInfoSno.getText().toString())) {
                        Log.e("SH", "点击了删除");
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
                    } else {
                        Log.e("SH", "点击了删除");
                        AlertDialog.Builder alert_builder = new AlertDialog.Builder(SearchStudentInfo.this);
                        alert_builder.setMessage("数据库中没有该学生信息无法删除");
                        alert_builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alert_builder.show();
                    }
                } else if (str.equals("update")) {//如果是修改操作
                    Log.e("SH", "点击了修改");
                    if (isExist(etSearchStudentInfoSno.getText().toString())) {
                        Intent intent = new Intent(SearchStudentInfo.this, AddStudentInfo.class);
                        intent.putExtra("isUpdate", "Update");
                        intent.putExtra("update_sno", etSearchStudentInfoSno.getText().toString());
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SearchStudentInfo.this);
                        builder.setMessage("数据库中没有该学生信息,是否添加?");
                        builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SearchStudentInfo.this, AddStudentInfo.class);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SearchStudentInfo.this, crud.class);
                                startActivity(intent);
                            }
                        });
                        builder.show();
                    }
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

    private Boolean isExist(String string) {
        students_list = studentDao.queryBuilder().list();
        for (int i = 0; i < students_list.size(); i++) {
            if (Long.toString(students_list.get(i).getSno()).equals(string)) {
                return true;
            }
        }
        return false;
    }
}
