package com.example.myapptest0001.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myapptest0001.Bean.User;
import com.example.myapptest0001.R;

import java.util.ArrayList;
import java.util.List;

public class UserManageActivity extends AppCompatActivity {

    private List<User> listUserDate = new ArrayList<User>();
    ListView listView;

    EditText et_userName,et_passwd,et_sex,et_edu;
    Button bt_new,bt_del,bt_search,bt_update,bt_inputAll,bt_searchAll,bt_delAll;
    private UserAdapter userAdapter;
    private DBAdapter dbAdapter;
    private SQLiteDatabase db;
    User selectedUser;//记录列表中被点击选中的当前用户记录


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manage);
        dbAdapter = new DBAdapter(UserManageActivity.this);
        db = dbAdapter.open("mydb.db");
        findViews();

        //新建
        bt_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.replace("user",null,etToValue())<0){
                    Toast.makeText(UserManageActivity.this,"新增一条用户记录失败",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UserManageActivity.this,"新增一条用户记录成功",Toast.LENGTH_SHORT).show();
                    clearTv();
                }
            }
        });

        //导入
        bt_inputAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listUserDate.clear();
                listUserDate.add(new User("刘晓菲","123d","女","计算机",""));
                listUserDate.add(new User("李勇","456d","男","物联网",""));
                listUserDate.add(new User("刘菲","789d","女","物联网",""));
                listUserDate.add(new User("唐茵茵","678c","女","数学",""));
                listUserDate.add(new User("张建","789d","男","计算机",""));
                listUserDate.add(new User("王强","478s","男","物联网",""));

                int count = 0;
                for (int i = 0 ; i<listUserDate.size();i++){
                    if (db.replace("user",null,objToValue(listUserDate.get(i)))<0){
                        Toast.makeText(UserManageActivity.this,"新增第"+i+"条用户记录",Toast.LENGTH_SHORT).show();
                    }
                    else
                        count++;
                }
                Toast.makeText(UserManageActivity.this,"成功新增"+count+"条用户记录",Toast.LENGTH_SHORT).show();
            }
        });

        //显示所有
        bt_searchAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor results = db.query("user",new String[]{"id","name","passwd","sex","edu"},
                        null,null,null,null,null);
                ConvertToUserList(results);
                userAdapter = new UserAdapter(UserManageActivity.this,listUserDate);
                listView.setAdapter(userAdapter);
            }
        });

        //查找
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whereClause = "",userS,passwdS,sexS,eduS;
                userS = et_userName.getText().toString();
                passwdS = et_passwd.getText().toString();
                sexS = et_sex.getText().toString();
                eduS = et_edu.getText().toString();
                if(!userS.equals(""))
                    whereClause = whereClause + "name=\""+ userS+"\"";
                if (!passwdS.equals("")){
                    if (!whereClause.equals("")) whereClause = whereClause + "and";
                    whereClause = whereClause + "passwd=\"" + passwdS+"\"";
                }
                if (!sexS.equals("")){
                    if (!whereClause.equals("")) whereClause = whereClause + "and";
                    whereClause = whereClause + "sex=\"" + sexS+"\"";
                }
                if (!eduS.equals("")){
                    if (!whereClause.equals("")) whereClause = whereClause + "and";
                    whereClause = whereClause + "edu=\"" + eduS+"\"";
                }


                Cursor results = db.query("user",new String[]{"id","name","passwd","sex","edu"},
                        whereClause,null,null,null,null);
                ConvertToUserList(results);
                userAdapter = new UserAdapter(UserManageActivity.this,listUserDate);
                listView.setAdapter(userAdapter);

            }
        });


        //信息记录
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedUser = listUserDate.get(i);
                et_userName.setText(selectedUser.getName());
                et_passwd.setText(selectedUser.getPasswd());
                et_sex.setText(selectedUser.getSex());
                et_edu.setText(selectedUser.getEdu());
            }
        });

        //删除
        bt_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedUser == null){
                    Toast.makeText(UserManageActivity.this,"当前用户为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (db.delete("user","id="+selectedUser.getId(),null)==0)
                    Toast.makeText(UserManageActivity.this,"删除当前用户记录失败",Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(UserManageActivity.this,"删除当前用户记录成功",Toast.LENGTH_SHORT).show();
                    clearTv();
                }
            }
        });

        //删除所有
        bt_delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db.delete("user",null,null)==0)
                    Toast.makeText(UserManageActivity.this,"删除当前用户记录失败",Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(UserManageActivity.this,"删除当前用户记录成功",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //更新
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedUser == null){
                    Toast.makeText(UserManageActivity.this,"当前用户为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (db.update("user",etToValue(),"id="+selectedUser.getId(),null)==0)
                    Toast.makeText(UserManageActivity.this,"更新当前用户记录失败",Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(UserManageActivity.this,"更新当前用户记录成功",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    void clearTv(){
        et_userName.setText("");
        et_passwd.setText("");
        et_sex.setText("");
        et_edu.setText("");
    }


    //新建
    private ContentValues etToValue(){
        ContentValues newValues = new ContentValues();
        newValues.put("name",et_userName.getText().toString());
        newValues.put("passwd",et_passwd.getText().toString());
        newValues.put("sex",et_sex.getText().toString());
        newValues.put("edu",et_edu.getText().toString());
        return newValues;
    }

    //导入
    private ContentValues objToValue(User user){
        ContentValues newValues = new ContentValues();
        newValues.put("name",user.getName());
        newValues.put("passwd",user.getPasswd());
        newValues.put("sex",user.getSex());
        newValues.put("edu",user.getEdu());
        return newValues;
    }

    //显示所有
    @SuppressLint("Range")
    private int ConvertToUserList(Cursor cursor){
        listUserDate.clear();
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()){
            return 0;

        }
        for (int i = 0;i<resultCounts;i++){
            User userTmp = new User();
            userTmp.setId((cursor.getInt(0)));
            userTmp.setName((cursor.getString(cursor.getColumnIndex("name"))));
            userTmp.setPasswd((cursor.getString(cursor.getColumnIndex("passwd"))));
            userTmp.setSex((cursor.getString(cursor.getColumnIndex("sex"))));
            userTmp.setEdu((cursor.getString(cursor.getColumnIndex("edu"))));

            listUserDate.add(userTmp);
            cursor.moveToNext();
        }
        return resultCounts;
    }

    void findViews(){
        et_userName = findViewById(R.id.et_UserName);
        et_passwd = findViewById(R.id.et_password);
        et_sex = findViewById(R.id.et_sex);
        et_edu = findViewById(R.id.et_edu);
        bt_new  = findViewById(R.id.bt_new);
        bt_del  = findViewById(R.id.bt_del);
        bt_search  = findViewById(R.id.bt_search);
        bt_update  = findViewById(R.id.bt_update);
        bt_inputAll  = findViewById(R.id.bt_inputAll);
        bt_searchAll  = findViewById(R.id.bt_searchAll);
        bt_delAll  = findViewById(R.id.bt_delAll);
        listView = findViewById(R.id.user_list);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        dbAdapter.close();
    }
}