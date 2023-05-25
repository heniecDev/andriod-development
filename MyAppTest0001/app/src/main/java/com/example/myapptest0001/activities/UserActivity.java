package com.example.myapptest0001.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myapptest0001.BubbleActivity;
import com.example.myapptest0001.JobThreeActivity;
import com.example.myapptest0001.Memorandum.NoteActivity;
import com.example.myapptest0001.R;
import com.example.myapptest0001.adapter.AddressBookActivity;
import com.example.myapptest0001.db.UserManageActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserActivity extends AppCompatActivity {
    private static final String TAG = "12003990621 何仁爽 Iot 二班";

    ImageView iv_calculator,iv_bubble;
    GridView gridView;
    List<Map<String,Object>> data_list;//数据源列表
    SimpleAdapter simpleAdapter;
    private int[] icon={R.drawable.address_book,R.drawable.calendar,R.drawable.camera,
            R.drawable.clock,R.drawable.games_control,R.drawable.messenger,
            R.drawable.ringtone,R.drawable.settings, R.drawable.speech_balloon,
            R.drawable.weather,R.drawable.world,R.drawable.youtube,R.drawable.calculator};
    private String[] iconName={"通讯录","数据库","照相机","时钟","游戏","备忘录",
            "铃声","设置","语音","天气","浏览器","视频","计算器"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i(TAG, "The user activity state（1）---->onCreate");
        setContentView(R.layout.activity_user);


//        iv_calculator=findViewById(R.id.iv_calculator);
//        iv_bubble=findViewById(R.id.iv_bubble);
        gridView = (GridView) findViewById(R.id.gview);
        //新建List
        data_list=new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        String[] from={"image","text"};
        int[] to={R.id.gimage,R.id.gtv};
        simpleAdapter = new SimpleAdapter(this, data_list, R.layout.grid_item, from, to);
        //配置适配器
        gridView.setAdapter(simpleAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object>mMap= (Map<String, Object>) simpleAdapter.getItem(position);
                Toast.makeText(UserActivity.this,mMap.get("text").toString(), Toast.LENGTH_SHORT).show();
//              System.out.println(position);
                switch (position){
                    case 0:
                        Intent intent2=new Intent(UserActivity.this, AddressBookActivity.class);
                        startActivity(intent2);
                        break;
                    case 1:
                        Intent intent5=new Intent(UserActivity.this, UserManageActivity.class);
                        startActivity(intent5);
                        break;
                    case 4:
                        Intent intent=new Intent(UserActivity.this,BubbleActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        Intent intent4=new Intent(UserActivity.this, NoteActivity.class);
                        startActivity(intent4);
                        break;
                    case 9:
                        Intent intent3=new Intent(UserActivity.this, JobThreeActivity.class);
                        startActivity(intent3);
                        break;
                    case 12:
                        Intent intent1=new Intent(UserActivity.this,CalculatorActivity.class);
                        startActivity(intent1);
                        break;

                }
            }
        });

        //原来泡泡龙和计算器图标的点击方法
//        iv_calculator.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(UserActivity.this, CalculatorActivity.class);
//                startActivity(intent);
//            }
//        });
//        iv_bubble.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(UserActivity.this, BubbleActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    public void getData(){
        for(int i=0;i<icon.length;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("image",icon[i]);
            map.put("text",iconName[i]);
            data_list.add(map);
        }
    }


//生命周期函数
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "The user activity state（2）---->onStart");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "The user activity state（3）---->onRestart");
    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, "The user activity state（4）---->onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.i(TAG, "The user activity state（5）---->onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG, "The user activity state（6）---->onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "The user activity state（7）---->onDestroy");
    }
}