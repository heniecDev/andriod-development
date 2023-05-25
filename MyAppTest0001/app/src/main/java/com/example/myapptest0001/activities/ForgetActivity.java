package com.example.myapptest0001.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapptest0001.R;

public class ForgetActivity extends AppCompatActivity {
    public static String username,userpass;
EditText et_xh,et_sfz,et_yhm,et_mm,et_zcsrmm,et_bgyj;
Button bt_czmm,bt_qdcz,bt_qxbfh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        et_xh=findViewById(R.id.et_xh);
        et_sfz=findViewById(R.id.et_sfz);
        et_yhm=findViewById(R.id.et_yhm);
        et_mm=findViewById(R.id.et_mm);
        et_zcsrmm=findViewById(R.id.et_zcsrmm);
        et_bgyj=findViewById(R.id.et_bgyj);
        bt_czmm=findViewById(R.id.bt_czmm);
        bt_qdcz=findViewById(R.id.bt_qdcz);
        bt_qxbfh=findViewById(R.id.bt_qxbfh);

        et_xh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_xh.setText("");
            }
        });
        et_sfz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_sfz.setText("");
            }
        });
        et_yhm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_yhm.setText("");
            }
        });
        et_mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_mm.setText("");
            }
        });
        et_zcsrmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_zcsrmm.setText("");
            }
        });
        et_bgyj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_bgyj.setText("");
            }
        });
        bt_czmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_mm.getText();
            }
        });
        bt_qdcz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_mm.getText().toString().equals(et_zcsrmm.getText().toString())){
                    et_yhm.getText();
                    et_zcsrmm.getText();
                    username=et_yhm.getText().toString();
                    userpass=et_mm.getText().toString();
                    MainActivity.Username=username;
                    MainActivity.Userpass=userpass;
                    Toast.makeText(ForgetActivity.this, "重置密码成功！", Toast.LENGTH_SHORT).show();
                    //Intent intent=new Intent(ForgetActivity.this,MainActivity.class);

                    Intent intent = new Intent();
                    intent.putExtra("username",et_yhm.getText().toString());
                    intent.putExtra("passwd",et_zcsrmm.getText().toString());

                    setResult(3,intent);//确定重置
                    finish();
                }
                else {
                    Toast.makeText(ForgetActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                }

            }
        });
        bt_qxbfh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(ForgetActivity.this,MainActivity.class);
                setResult(4);
                finish();
            }
        });
    }
}