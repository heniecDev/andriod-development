package com.example.myapptest0001.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapptest0001.R;
import com.example.myapptest0001.customViews.Dragon;

import java.util.Random;


public class GameInActivity extends AppCompatActivity {
    TextView tv_tips02;
    int count=0;


    @SuppressLint({"ClickableViewAccessibility", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Random random = new Random();
        setContentView(R.layout.activity_game_in);
        int BubbleWidth = 200,BubbleHeight = 200;
        int ButterflyWidth = 90, ButterflyHeight = 80;

        tv_tips02 = findViewById(R.id.tv_tips02);
        //泡泡龙
        FrameLayout frameLayout1 = findViewById(R.id.frameLayout_game);
        Dragon dragon = new Dragon(GameInActivity.this);
        frameLayout1.addView(dragon);   //将绘制的泡泡龙加入帧布局
        dragon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Dragon d = (Dragon) v;
                    d.setBubbleX((int) event.getX());
                    d.setBubbleY((int) event.getY());
                    d.setButterflyY(random.nextInt(d.getMyHeight()));
                    d.setButterflyX(random.nextInt(d.getMyWidth()));

                    d.invalidate(); //该方法的调用会触发onDraw()的执行
                        if (d.getBubbleX() == d.getButterflyX() && Math.abs(d.getBubbleY()-d.getButterflyY())<BubbleHeight/2-ButterflyHeight/2){
                            tv_tips02.setVisibility(View.VISIBLE);
                            count+=1;
                            tv_tips02.setText("亲爱的泡泡龙，您已经抓住了"+count+"次蝴蝶！");
                            tv_tips02.setVisibility(View.VISIBLE);
                        }
                        else if (d.getBubbleY() == d.getButterflyY() && Math.abs(d.getBubbleX()-d.getButterflyX())<BubbleWidth/2-ButterflyWidth/2){
                            tv_tips02.setVisibility(View.VISIBLE);
                            count+=1;
                            tv_tips02.setText("亲爱的泡泡龙，您已经抓住了"+count+"次蝴蝶！");
                            tv_tips02.setVisibility(View.VISIBLE);
                        }
                        else if (Math.sqrt((d.getBubbleX()-d.getButterflyX())*(d.getBubbleX()-d.getButterflyX())
                                +(d.getBubbleY()-d.getButterflyY())*(d.getBubbleY()-d.getButterflyY()))
                                <BubbleWidth/2+Math.sqrt((ButterflyWidth/2*ButterflyWidth/2)
                                +(ButterflyHeight/2*ButterflyHeight/2))){
                           count+=1;
                            tv_tips02.setText("亲爱的泡泡龙，您已经抓住了"+count+"次蝴蝶！");
                            tv_tips02.setVisibility(View.VISIBLE);
                        }
                }
                return true;
            }
        });
    }
}