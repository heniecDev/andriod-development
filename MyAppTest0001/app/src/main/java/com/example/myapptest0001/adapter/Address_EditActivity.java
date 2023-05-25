package com.example.myapptest0001.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapptest0001.Bean.Person;
import com.example.myapptest0001.R;

public class Address_EditActivity extends AppCompatActivity {
    ImageView Image;
    EditText Name,Phone,Classify,Address;
    Button bt_cancel,bt_confirm;
    public static int pos;      //被编辑的位置
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        assert bundle != null;

        Person person = (Person)bundle.getSerializable("address_item");

        Image = findViewById(R.id.Image);
        Name = findViewById(R.id.Name);
        Phone = findViewById(R.id.Phone);
        Classify = findViewById(R.id.Classify);
        Address = findViewById(R.id.Address);
        bt_cancel = findViewById(R.id.bt_cancel);
        bt_confirm = findViewById(R.id.bt_confirm);

        assert person != null;

        Image.setImageResource(person.getImgId());
        Name.setText(person.getName());
        Phone.setText(person.getPhone());
        Classify.setText(person.getClassify());
        Address.setText(person.getAddress());

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                Intent intent = new Intent(Address_EditActivity.this,AddressBookActivity.class);
                intent.putExtra("edit_name",Name.getText().toString());
                intent.putExtra("edit_phone",Phone.getText().toString());
                intent.putExtra("edit_classify", Classify.getText().toString());
                intent.putExtra("edit_address",Address.getText().toString());

                AddressBookActivity.who=2;
                AddressBookActivity.get_pos = pos;

                startActivity(intent);
                setResult(3);       //确定的结果码为 3

            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(4);       //取消的结果码为 4
                finish();
            }
        });

    }
}