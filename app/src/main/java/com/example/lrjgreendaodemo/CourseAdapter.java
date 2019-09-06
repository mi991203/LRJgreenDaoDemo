package com.example.lrjgreendaodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CourseAdapter extends ArrayAdapter<Course> {
    private int sourceId;
    public CourseAdapter(Context context, int textViewResourceId, List<Course> objects){
        super(context, textViewResourceId, objects);
        sourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course course = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(sourceId, parent, false);
        TextView cno = view.findViewById(R.id.tv_course_cno);
        TextView cname = view.findViewById(R.id.tv_course_cname);
        TextView ctime = view.findViewById(R.id.tv_course_ctime);
        TextView cteacher = view.findViewById(R.id.tv_course_cteacher);
        cno.setText("课程号:"+course.getCno());
        cname.setText("课程名:"+course.getCname());
        ctime.setText("学时:"+course.getCtime());
        cteacher.setText("任课老师:"+course.getTeacher());
        return view;
    }
}
