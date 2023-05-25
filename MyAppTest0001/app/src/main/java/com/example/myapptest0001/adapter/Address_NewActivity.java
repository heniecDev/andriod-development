package com.example.myapptest0001.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapptest0001.R;

public class Address_NewActivity extends AppCompatActivity {
    ImageView Image;
    EditText Name,Phone,Classify,Address;
    Button bt_cancel,bt_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_new);

        Image = findViewById(R.id.Image);
        Name = findViewById(R.id.Name);
        Phone = findViewById(R.id.Phone);
        Classify = findViewById(R.id.Classify);
        Address = findViewById(R.id.Address);
        bt_cancel = findViewById(R.id.bt_cancel);
        bt_confirm = findViewById(R.id.bt_confirm);

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Address_NewActivity.this,AddressBookActivity.class);
                intent.putExtra("new_name",Name.getText().toString());
                intent.putExtra("new_phone",Phone.getText().toString());
                intent.putExtra("new_classify", Classify.getText().toString());
                intent.putExtra("new_address",Address.getText().toString());

                AddressBookActivity.who=1;
                startActivity(intent);

                setResult(1);       //新建信息确定，返回结果码为1
                finish();
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(2);       //新建信息取消，返回结果码为2
                finish();
            }
        });

    }
}