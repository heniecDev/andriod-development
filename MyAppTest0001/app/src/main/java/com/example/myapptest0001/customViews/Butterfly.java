package com.example.myapptest0001.customViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.WindowManager;
import com.example.myapptest0001.R;
import java.util.Random;


public class Butterfly extends View {
    //获取屏幕的宽和高
    WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
    int width = (int)(wm.getDefaultDisplay().getWidth()*0.8);
    int height = (int)(wm.getDefaultDisplay().getHeight()*0.8);

    Random rand = new Random();
    int butterflyX = rand.nextInt(width);
    int butterflyY = rand.nextInt(height);

    public Butterfly(Context context) {
        super(context);
    }
    public void setButterflyX(int butterflyX){
        this.butterflyX = butterflyX;
    }
    public int getButterflyX() {
        return butterflyX;
    }
    public void setButterflyY(int butterflyY){
        this.butterflyY = butterflyY;
    }
    public int getButterflyY() {
        return butterflyY;
    }
    public int getMyWidth(){
        return width;
    }
    public int getMyHeight(){
        return height;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        //使用 canvas.drawText("text",X,Y,paint)绘制文字
        //从资源文件中生成位图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.butterflysmall);
        //在屏幕上绘制蝴蝶
        canvas.drawBitmap(bitmap,butterflyX,butterflyY,paint);
    }
}
