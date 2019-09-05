package com.example.lrjgreendaodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private int resourceId;
    public StudentAdapter(Context context, int textViewResourceId, List<Student> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = getItem(position);//获取当前的student实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        TextView stu_sno = ((TextView) view.findViewById(R.id.tv_student_sno));
        TextView stu_name = ((TextView) view.findViewById(R.id.tv_student_name));
        TextView stu_sex = ((TextView) view.findViewById(R.id.tv_student_sex));
        TextView stu_age = ((TextView) view.findViewById(R.id.tv_student_age));

        stu_sno.setText("学号:"+student.getSno()+"");
        stu_name.setText("姓名:"+student.getSname());
        stu_sex.setText("性别:"+student.getSsex());
        stu_age.setText("年龄:"+student.getSage()+"");

        return view;
    }
}
