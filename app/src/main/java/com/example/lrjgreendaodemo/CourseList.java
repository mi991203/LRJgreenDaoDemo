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

import com.example.lrjgreendaodemo.gen.CourseDao;
import com.example.lrjgreendaodemo.gen.DaoMaster;
import com.example.lrjgreendaodemo.gen.DaoSession;

import java.util.ArrayList;
import java.util.List;

public class CourseList extends AppCompatActivity {
    private SQLiteDatabase db;
    private DaoSession session;
    private DaoMaster master;
    private CourseDao courseDao;
    private EditText etSearchCourseByCno;
    private Button btSearchInCourseList;
    String str = "";
    List<Course> courses_list;
    List<Course> courses_list_by_cno;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_list);
        openDB();

        courses_list_by_cno = new ArrayList<>();
        etSearchCourseByCno = findViewById(R.id.et_search_course_by_sno);
        btSearchInCourseList = findViewById(R.id.bt_search_in_courseList);

        btSearchInCourseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = etSearchCourseByCno.getText().toString();
                Log.e("SH", str);
                courses_list.clear();
                courses_list_by_cno.clear();
                load();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        load();
    }

    private void load() {
        courses_list = courseDao.queryBuilder().list();
        if (!str.equals("")) {
            Log.e("SH", "有条件查询课程表");
            for (int i = 0; i < courses_list.size(); i++) {
                if (Long.toString(courses_list.get(i).getCno()).equals(str)) {
                    courses_list_by_cno.add(courses_list.get(i));
                    break;
                }
            }
            CourseAdapter adapter = new CourseAdapter(CourseList.this, R.layout.course_list_item, courses_list_by_cno);
            ListView listView = findViewById(R.id.course_info_list);
            listView.setAdapter(adapter);
        } else {
            CourseAdapter adapter = new CourseAdapter(CourseList.this, R.layout.course_list_item, courses_list);
            ListView listView = findViewById(R.id.course_info_list);
            listView.setAdapter(adapter);
        }
    }

    private void openDB() {
        db = new DaoMaster.DevOpenHelper(this, "course.db", null).getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        courseDao = session.getCourseDao();
    }
}
