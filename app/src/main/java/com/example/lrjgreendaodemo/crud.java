package com.example.lrjgreendaodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class crud extends AppCompatActivity implements View.OnClickListener {
    Button btAdd;
    Button btDel;
    Button btUprad;
    Button btSearch;
    final String str_add = "Add";
    final String str_del = "Delete";
    final String str_update = "Update";
    final String str_search = "Search";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        btAdd = findViewById(R.id.bt_add);
        btDel = findViewById(R.id.bt_del);
        btUprad = findViewById(R.id.bt_uprad);
        btSearch = findViewById(R.id.bt_search);

        btAdd.setOnClickListener(this);
        btSearch.setOnClickListener(this);
        btUprad.setOnClickListener(this);
        btDel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                Intent intent_0 = new Intent(this, SelectTable.class);
                intent_0.putExtra("extra_data", str_add);
                startActivity(intent_0);
                break;
            case R.id.bt_del:
                Intent intent_1 = new Intent(this, SelectTable.class);
                intent_1.putExtra("extra_data", str_del);
                startActivity(intent_1);
                break;
            case R.id.bt_uprad:
                Intent intent_2 = new Intent(this, SelectTable.class);
                intent_2.putExtra("extra_data", str_update);
                startActivity(intent_2);
                break;
            case R.id.bt_search:
                Intent intent_3 = new Intent(this, SelectTable.class);
                intent_3.putExtra("extra_data", str_search);
                startActivity(intent_3);
                break;
            default:
                break;
        }
    }
}
