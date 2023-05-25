package com.example.myapptest0001.activities;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapptest0001.R;

import java.util.ArrayList;
import java.util.List;

public class RegActivity extends AppCompatActivity {
    EditText user,pwd1,pwd2;
    RadioButton regSex1,regSex2;
    CheckBox regCheck1,regCheck2,regCheck3,regCheck4,regCheck5,regCheck6,regCheck7,regCheck8;
    CheckBox ck_pro;
    TextView tv_pro,tv_info;
    Button ButtonPrev,ButtonReset,ButtonChanel;
    private String sex;
    Spinner spinner;
    List<String> listMajor = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        findViews();
        Intent intent = new Intent(RegActivity.this, RegInfoActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        //创建数据源
        listMajor.add("计算机科学与技术");
        listMajor.add("物联网工程");
        listMajor.add("网络工程");
        listMajor.add("信息管理");
        //创建Adapter对象   构造函数
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listMajor);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //使用Adapter对象   搭建 数据源到AdapterView之间的桥梁
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RegActivity.this, ((TextView)view).getText().toString()+"被选中", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        //被弃用
//        ActivityResultLauncher launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                if (result.getResultCode() == RESULT_OK) {
//                    //Log.d(TAG, "onActivityResult: data = " + result.getData().getStringExtra("data_return"));
//                }
//            }
//        });

        View.OnClickListener radiolistener = new View.OnClickListener() {
            public void onClick(View view) {
                RadioButton curRadio = (RadioButton) view;
                sex = curRadio.getText().toString();
                Toast.makeText(RegActivity.this, sex + "被选中", Toast.LENGTH_SHORT).show();
            }
        };
        regSex1.setOnClickListener(radiolistener);
        regSex2.setOnClickListener(radiolistener);


        CheckBox.OnClickListener CheckBoxlistener = new CheckBox.OnClickListener() {
            public void onClick(View view) {
                CheckBox curBox = (CheckBox) view;
                String checkedText = curBox.getText().toString();
                if (curBox.isChecked()) {
                    Toast.makeText(RegActivity.this, checkedText + "被选中", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegActivity.this, checkedText + "被取消", Toast.LENGTH_SHORT).show();
                }
            }
        };
        regCheck1.setOnClickListener(CheckBoxlistener);
        regCheck2.setOnClickListener(CheckBoxlistener);
        regCheck3.setOnClickListener(CheckBoxlistener);
        regCheck4.setOnClickListener(CheckBoxlistener);
        regCheck5.setOnClickListener(CheckBoxlistener);
        regCheck6.setOnClickListener(CheckBoxlistener);
        regCheck7.setOnClickListener(CheckBoxlistener);
        regCheck8.setOnClickListener(CheckBoxlistener);
        ck_pro.setOnClickListener(CheckBoxlistener);

        tv_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegActivity.this, ProActivity.class);
             startActivityForResult(intent,1);
  //              launcher.launch(intent);
            }
        });
        ck_pro.setOnClickListener((view) -> {
            CheckBox curBox = (CheckBox) view;
            if(curBox.isChecked()){
                ButtonPrev.setEnabled(true);
            }else{
                ButtonPrev.setEnabled(false);
            }
        });
        ButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegActivity.this, "预览", Toast.LENGTH_SHORT).show();
                String hobby="";
                if(regCheck1.isChecked()) hobby = hobby + " " + regCheck1.getText().toString();
                if(regCheck2.isChecked()) hobby = hobby + " " + regCheck2.getText().toString();
                if(regCheck3.isChecked()) hobby = hobby + " " + regCheck3.getText().toString();
                if(regCheck4.isChecked()) hobby = hobby + " " + regCheck4.getText().toString();
                if(regCheck5.isChecked()) hobby = hobby + " " + regCheck5.getText().toString();
                if(regCheck6.isChecked()) hobby = hobby + " " + regCheck6.getText().toString();
                if(regCheck7.isChecked()) hobby = hobby + " " + regCheck7.getText().toString();
                if(regCheck8.isChecked()) hobby = hobby + " " + regCheck8.getText().toString();

                Intent intent=new Intent(RegActivity.this,RegInfoActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("user",user.getText().toString());
                bundle.putString("sex",sex);
                bundle.putString("hobby",hobby);
                intent.putExtras(bundle);
                startActivityForResult(intent,2);
            }
        });

    }
        private void findViews () {
            user = findViewById(R.id.user);
            pwd1 = findViewById(R.id.pwd1);
            pwd2 = findViewById(R.id.pwd2);
            regSex1 = findViewById(R.id.regSex1);
            regSex2 = findViewById(R.id.regSex2);
            regCheck1 = findViewById(R.id.regCheck1);
            regCheck2 = findViewById(R.id.regCheck2);
            regCheck3 = findViewById(R.id.regCheck3);
            regCheck4 = findViewById(R.id.regCheck4);
            regCheck5 = findViewById(R.id.regCheck5);
            regCheck6 = findViewById(R.id.regCheck6);
            regCheck7 = findViewById(R.id.regCheck7);
            regCheck8 = findViewById(R.id.regCheck8);
            ck_pro = findViewById(R.id.ck_pro);
            tv_pro = findViewById(R.id.tv_pro);
            ButtonPrev = findViewById(R.id.ButtonPrev);
            ButtonReset = findViewById(R.id.ButtonReset);
            ButtonChanel = findViewById(R.id.ButtonChanel);
            tv_info = findViewById(R.id.tv_info);
            spinner = findViewById(R.id.spinner);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){ //看完协议返回
          ck_pro.setEnabled(true);
          tv_info.setText("看完协议返回");//
        } else if (requestCode==2) {//从RegInfoActivity返回
            if(resultCode==1){//注册信息确定
                //Intent intent = new Intent(RegActivity.this,MainActivity.class);
                Intent intent = new Intent();
                intent.putExtra("username",user.getText().toString());
                intent.putExtra("passwd",pwd2.getText().toString());
                setResult(1,intent);
                //startActivity(intent);
                finish();

            } else if (resultCode==2) {
                tv_info.setText("预览注册信息后返回并修改");
            }

        }
    }
}