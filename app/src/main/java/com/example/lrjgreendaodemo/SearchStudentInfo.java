package com.example.lrjgreendaodemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchStudentInfo extends AppCompatActivity {
    EditText etSearchStudentInfoSno;
    Button btSearchStudentInfoBySno;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_student_info);

        etSearchStudentInfoSno = findViewById(R.id.et_search_student_info_sno);
        btSearchStudentInfoBySno = findViewById(R.id.bt_search_student_info_bySno);

        btSearchStudentInfoBySno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
