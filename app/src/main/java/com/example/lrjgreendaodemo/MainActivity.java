package com.example.lrjgreendaodemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText et_user_name;
    private EditText et_user_password;
    private Button bt_login;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_user_name = findViewById(R.id.et_user_name);
        et_user_password = findViewById(R.id.et_user_password);
        bt_login = findViewById(R.id.bt_login);
        checkBox = findViewById(R.id.cb);

        InitUI();

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    final String name = et_user_name.getText().toString();
                    final String password = et_user_password.getText().toString();
                    if (name != null && password != null) {
                        Log.e("SH", "添加信息");

                        SharedPreferences.Editor editor = getSharedPreferences("date", MODE_PRIVATE).edit();
                        editor.putString("name", name+"");
                        editor.putString("password", password+"");
                        editor.apply();
                    }
                }else {
                    SharedPreferences.Editor editor = getSharedPreferences("date", MODE_PRIVATE).edit();
                    editor.putString("name", "");
                    editor.putString("password", "");
                    editor.apply();
                }
                Intent intent = new Intent(MainActivity.this, crud.class);
                startActivity(intent);
            }
        });
    }

    private void InitUI() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        et_user_name.setText(getSharedPreferences("date", MODE_PRIVATE).getString("name", null));
        et_user_password.setText(getSharedPreferences("date", MODE_PRIVATE).getString("password", null));
    }
}
