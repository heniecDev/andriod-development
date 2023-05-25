package com.example.myapptest0001.activities;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapptest0001.R;

public class MainActivity extends AppCompatActivity {
    public static String Username = "herenshuang", Userpass = "12003990621";
    private static final String TAG = "12003990621 何仁爽 Iot 二班";

    public static final String PREFERENCE_NAME = "SaveSetting";
    public static int MODE = MODE_PRIVATE;

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Log.d(TAG, "The activity state---->onCreate");
//    }

    EditText et_user, et_pas;
    Button bt_log;
    Button bt_reset;
    Button bt_chanel;
    Button bt_wjmm;
    Button bt_regis;
    CheckBox check;

    TextView tv_tips01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "The activity state（1）---->onCreate");

        setContentView(R.layout.activity_main);
        et_user = findViewById(R.id.et_user);
        et_pas = findViewById(R.id.et_pas);
        bt_log = findViewById(R.id.bt_log);
        bt_reset = findViewById(R.id.bt_reset);
        bt_chanel = findViewById(R.id.bt_chanel);
        bt_wjmm = findViewById(R.id.bt_wjmm);
        bt_regis=findViewById(R.id.bt_regis);
        tv_tips01=findViewById(R.id.tv_tips01);
        check = findViewById(R.id.check);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME,MODE);
        String name = sharedPreferences.getString("Name","");
        String pswd = sharedPreferences.getString("PassW","");
        et_user.setText(name);
        et_pas.setText(pswd);

//        //被弃用，解决方法
//        ActivityResultLauncher launcher = registerForActivityResult(
//        new ActivityResultContracts.StartActivityForResult(),
//        new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                if (result.getResultCode() == 1) {
//                }
//            }
//        });

        bt_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if ((et_user.getText().toString().equals(Username)) && (et_pas.getText().toString().equals(Userpass))) {

                String user =et_user.getText().toString();
                String pas = et_pas.getText().toString();
                if((user.equals(Username)&&(pas.equals(Userpass)))){

                    if (check.isChecked()) {
                        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Name", et_user.getText().toString());
                        editor.putString("PassW", et_pas.getText().toString());
                        editor.commit();
                    }
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
//                    intent.putExtra("username",user);
//                    intent.putExtra("passwd",pas);
                    startActivity(intent);

                   // finish();
                } else {
                    Toast.makeText(MainActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_regis.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view){
                Intent intent=new Intent(MainActivity.this, RegActivity.class);
                startActivityForResult(intent,2);
//                launcher.launch(intent);
            }
        });

        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_user.setText("");
                et_pas.setText("");
            }
        });
        bt_chanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_user.setText("");
                et_pas.setText("");

            }
        });
        bt_wjmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForgetActivity.class);
                startActivityForResult(intent,3);//忘记密码页面返回
            }
        });
    }
//   用户注册回调函数
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){//注册界面返回
            if(resultCode==1) {
                if (data!=null) {
                    et_user.setText(data.getStringExtra("username"));
                    et_pas.setText(data.getStringExtra("passwd"));
                    tv_tips01.setText("用户注册后登录");
                }
            }
        }
        else if(requestCode==3){//忘记密码页面返回
            if(resultCode==3){
                et_user.setText(data.getStringExtra("username"));
                et_pas.setText(data.getStringExtra("passwd"));
                tv_tips01.setText("重置密码后登录");
            }
            else if(resultCode==4){
                tv_tips01.setText("放弃重置密码后返回");
            }
        }
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, "The activity state（2）---->onStart");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "The activity state（3）---->onRestart");
    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, "The activity state（4）---->onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.i(TAG, "The activity state（5）---->onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG, "The activity state（6）---->onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "The activity state（7）---->onDestroy");
    }



}