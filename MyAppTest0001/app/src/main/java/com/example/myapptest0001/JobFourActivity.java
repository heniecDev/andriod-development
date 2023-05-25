package com.example.myapptest0001;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JobFourActivity extends AppCompatActivity {
    Button bt_dilivery,bt_back01;
    Button tv_tip03;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_for);
        bt_dilivery=findViewById(R.id.bt_dilivery);
        bt_back01=findViewById(R.id.bt_back01);
        tv_tip03=findViewById(R.id.tv_tips03);
        bt_dilivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_tip03.setVisibility(view.VISIBLE);
            }
        });
        bt_back01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(JobFourActivity.this,BubbleActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}