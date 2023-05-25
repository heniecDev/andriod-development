package com.example.myapptest0001.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapptest0001.R;

public class RegInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_info);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String reg_usr = bundle.getString("user");
        String reg_sex = bundle.getString("sex");
        String reg_hobby = bundle.getString("hobby");

        TextView tv_regUser = findViewById(R.id.tv_regser);
        TextView tv_regSex =findViewById(R.id.tv_regsex);
        TextView tv_regHob = findViewById(R.id.tv_reghob);
        Button btn_login = findViewById(R.id.btn_anto_login);
        Button btn_return = findViewById(R.id.btn_return);

        tv_regUser.setText(reg_usr);
        tv_regSex.setText(reg_sex);
        tv_regHob.setText(reg_hobby);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(1);
                finish();
            }
        });
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(2);
                finish();
            }
        });

    }
}