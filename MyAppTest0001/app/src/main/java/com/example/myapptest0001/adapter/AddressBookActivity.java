package com.example.myapptest0001.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapptest0001.R;
import com.example.myapptest0001.Bean.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddressBookActivity extends AppCompatActivity {

    Button NEW,Edit,Delete;
    private List<Person> list = new ArrayList<Person>();
    private ListView listView;

    private Address_BaseAdapter addAdapter;
    public static int who;
    public static int get_pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);

        NEW = findViewById(R.id.NEW);
        Edit = findViewById(R.id.Edit);
        Delete = findViewById(R.id.Delete);

        list.add(new Person("刘诗诗","13002039456","朋友","北京",R.drawable.liu));
        list.add(new Person("赵丽颖","13242423222","同学","上海",R.drawable.zhao));
        list.add(new Person("杨幂","16420394467","家人","重庆",R.drawable.yang));
        list.add(new Person("唐嫣","15935712345","朋友","安徽",R.drawable.tang));
        list.add(new Person("孙俪","12345678911","朋友","江苏",R.drawable.shun));
        list.add(new Person("玛丽莲梦露","14785236999","朋友","外国",R.drawable.ma));
        list.add(new Person("林心如","10234567899","朋友","广东",R.drawable.lin));

        listView = (ListView) findViewById(R.id.Lv_address);
        addAdapter = new Address_BaseAdapter(this, list);
        listView.setAdapter(addAdapter);

        if (who == 1) {
            String name = getIntent().getStringExtra("new_name");
            String phone = getIntent().getStringExtra("new_phone");
            String classify = getIntent().getStringExtra("new_classify");
            String address = getIntent().getStringExtra("new_address");

            list.add(new Person(name, phone, classify, address, R.drawable.lianxiren));
            addAdapter.notifyDataSetChanged();
        }
        if (who == 2){
            String name_edit = getIntent().getStringExtra("edit_name");
            String phone_edit = getIntent().getStringExtra("edit_phone");
            String classify_edit = getIntent().getStringExtra("edit_classify");
            String address_edit = getIntent().getStringExtra("edit_address");

            list.set(get_pos, new Person(name_edit,phone_edit,classify_edit,address_edit,list.get(get_pos).getImgId()));
            addAdapter.notifyDataSetChanged();
            //notifyDataSetChanged方法通过一个外部的方法控制，
            // 如果适配器的内容发生改变时，需要强制调用 getView() 来刷新每个item中的内容

        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AddressBookActivity.this,String.valueOf(i)+ list.get(i).getName()+"被点击",Toast.LENGTH_SHORT).show();
            }
        });
        //长按编辑功能
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AddressBookActivity.this,String.valueOf(i)+ list.get(i).getName()+"被长按进入编辑",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddressBookActivity.this,Address_EditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("address_item",list.get(i));
                intent.putExtras(bundle);
                startActivityForResult(intent,2);
                return true;
            }
        });


        NEW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddressBookActivity.this,"新建",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddressBookActivity.this,Address_NewActivity.class);
                startActivityForResult(intent,1);
            }
        });


        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddressBookActivity.this,"编辑",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddressBookActivity.this,Address_EditActivity.class);

                int n = 0;
                int position = 0;
                for (int i = 0 ;i<addAdapter.getCount();i++){
                    if(addAdapter.hasChecked(i) == true){
                        n=n+1;
                        position = i;
                    }
                }
                if (n!=1){
                    Toast.makeText(AddressBookActivity.this,"无法选中多个进行编辑",Toast.LENGTH_SHORT).show();
                }
                else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("address_item",list.get(position));
                    intent.putExtras(bundle);
                    Address_EditActivity.pos = position;
                }
                startActivityForResult(intent,2);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<Integer,Boolean> Check_delete = addAdapter.getMap();
                int Count = addAdapter.getCount();
                for(int i = 0;i<Count;i++){
                    int pos = i - (Count - addAdapter.getCount());
                    if(addAdapter.hasChecked(i)!=null && addAdapter.hasChecked(i)){
                        Check_delete.remove(i);
                        list.remove(pos);
                    }
                }
                addAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if (resultCode ==1) {
                if (who == 1){

                    String name = getIntent().getStringExtra("name");
                    String phone = getIntent().getStringExtra("phone");
                    String classify = getIntent().getStringExtra("classify");
                    String address = getIntent().getStringExtra("address");

                    list.add(new Person(name, phone, classify, address, R.drawable.lianxiren));
                    addAdapter.notifyDataSetChanged();
                }
                Toast.makeText(AddressBookActivity.this, "新建联系人后返回", Toast.LENGTH_SHORT).show();
            }
            else if(resultCode == 2){
                Toast.makeText(AddressBookActivity.this, "取消新建联系人", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode ==2){
            if (resultCode == 3){
                int n = 0;
                int pos = 0;
                for (int i = 0 ;i<addAdapter.getCount();i++){
                    if(addAdapter.hasChecked(i) == true){
                        n=n+1;
                        pos = i;
                    }
                }
                if (n!=1){
                    Toast.makeText(AddressBookActivity.this,"无法选中多个进行编辑",Toast.LENGTH_SHORT).show();
                }
                else if( n==1&& who ==2) {

                    list.get(pos).setName(data.getStringExtra("edit_name"));
                    list.get(pos).setPhone(data.getStringExtra("edit_phone"));
                    list.get(pos).setClassify(data.getStringExtra("edit_classify"));
                    list.get(pos).setAddress(data.getStringExtra("edit_address"));

                    addAdapter.notifyDataSetChanged();
                }

            }
            else if (resultCode == 4){
                Toast.makeText(AddressBookActivity.this,"取消编辑后返回",Toast.LENGTH_SHORT).show();
            }
        }
    }
}