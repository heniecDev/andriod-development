package com.example.myapptest0001.customViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.myapptest0001.R;

public class Dragon extends Butterfly {
    int bubbleX;
    int bubbleY;

    public Dragon(Context context) {
        super(context);
    }
    public void setBubbleX(int bubbleX){
        this.bubbleX = bubbleX;
    }
    public int getBubbleX() {
        return bubbleX;
    }
    public void setBubbleY(int bubbleY){
        this.bubbleY = bubbleY;
    }
    public int getBubbleY() {
        return bubbleY;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        //从资源文件中生成位图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bubble);
        //在屏幕相应位置绘制泡泡龙
        //如下没有指定位置，则泡泡龙每次出现在默认位置-左上角
        canvas.drawBitmap(bitmap,bubbleX,bubbleY,paint);

    }
}
