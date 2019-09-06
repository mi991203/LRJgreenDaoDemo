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

import com.example.lrjgreendaodemo.gen.CourseDao;
import com.example.lrjgreendaodemo.gen.DaoMaster;
import com.example.lrjgreendaodemo.gen.DaoSession;

import java.util.List;

public class SearchCourseInfo extends AppCompatActivity {
    private SQLiteDatabase db;
    private DaoMaster master;
    private DaoSession session;
    private CourseDao courseDao;
    private List<Course> courses_list;


    private EditText etSearchCourseInfoCno;
    private Button btSearchCourseInfoByCno;

    private String str;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_course_info);
        openDB();

        str = getIntent().getStringExtra("search_or_delete_or_update");
        etSearchCourseInfoCno = findViewById(R.id.et_search_course_info_cno);
        btSearchCourseInfoByCno = findViewById(R.id.bt_search_course_info_byCno);

        if (str.equals("delete")) {
            btSearchCourseInfoByCno.setText(" 删除");
        } else if (str.equals("update")) {
            btSearchCourseInfoByCno.setText("修改");
        } else if (str.equals("search")) {
            btSearchCourseInfoByCno.setText("查询");
        }

        btSearchCourseInfoByCno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = etSearchCourseInfoCno.getText().toString();
                if (str.equals("delete")) {
                    Log.e("SH", "点击了删除");
                    if (isExist(string)) {
                        Course course = courseDao.queryBuilder().where(CourseDao.Properties.Cno.eq(string)).unique();
                        try {
                            courseDao.delete(course);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(SearchCourseInfo.this);
                        builder.setMessage("删除成功");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SearchCourseInfo.this, crud.class);
                                startActivity(intent);
                            }
                        });
                        builder.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SearchCourseInfo.this);
                        builder.setMessage("数据库中没有课程信息无法删除");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }

                }
                else if (str.equals("update")) {
                    Log.e("SH", "点击了修改");
                    if (isExist(string)){
                        Intent intent = new Intent(SearchCourseInfo.this, AddCourseInfo.class);
                        intent.putExtra("isUpdate", "update");
                        intent.putExtra("update_cno", string);
                        startActivity(intent);
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SearchCourseInfo.this);
                        builder.setMessage("课程表中未能找到该课程");
                        builder.setPositiveButton("添加", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SearchCourseInfo.this, AddCourseInfo.class);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }

                } else if (str.equals("search")) {


                }
            }
        });
    }

    private boolean isExist(String string) {
        courses_list = courseDao.queryBuilder().list();
        for (int i = 0; i < courses_list.size(); i++) {
            if (Long.toString(courses_list.get(i).getCno()).equals(string)) {
                return true;
            }
        }
        return false;
    }

    private void openDB() {
        db = new DaoMaster.DevOpenHelper(this, "course.db", null).getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        courseDao = session.getCourseDao();
    }
}
