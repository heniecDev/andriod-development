package com.example.myapptest0001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapptest0001.activities.GameInActivity;

public class BubbleActivity extends AppCompatActivity {

    Button bt_stamp,bt_suggestion;
    TextView tv_stampok;
    ImageView img_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble);

        bt_stamp=findViewById(R.id.bt_stamp);
        tv_stampok=findViewById(R.id.tv_stampok);
        img_enter=findViewById(R.id.img_enter);
        bt_suggestion=findViewById(R.id.bt_suggestion);


        bt_stamp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    tv_stampok.setVisibility(view.VISIBLE);
                }
                else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    tv_stampok.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
        img_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BubbleActivity.this, GameInActivity.class);
                startActivity(intent);
                Toast.makeText(BubbleActivity.this, "进入泡泡龙游戏", Toast.LENGTH_SHORT).show();
            }
        });
        bt_suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BubbleActivity.this, JobFourActivity.class);
                startActivity(intent);
                Toast.makeText(BubbleActivity.this, "进入建议", Toast.LENGTH_SHORT).show();
            }
        });

    }
}