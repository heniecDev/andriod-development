package com.example.myapptest0001.Memorandum;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapptest0001.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NoteActivity extends AppCompatActivity {
    private ListView list_in,list_out;      //分别显示内部和外部存储目录
    private ArrayAdapter<String> adapterIn,adapterOut;      //分别适配内部和外部存储目录
    private String[] fileNameList_in,fileNameList_out;      //保存内部和外部存储名列表
    private String InterCurrentPath,OuterCurrentPath;       //保存当前显示目录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        //检查外部存储器是否可读写
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) || !Environment.getExternalStorageDirectory().exists()){
            Toast.makeText(NoteActivity.this,"外部存储器不可用",Toast.LENGTH_SHORT).show();
        }


        list_in = findViewById(R.id.list_in);
        list_out = findViewById(R.id.list_out);

    //内部存储器目录项的点击处理
        //如果是文件，就可以直接读取，然后将文件名和内容传到下一个Activity
        list_in.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String cont = "";
                FileInputStream fis = null;
                String fileName = ((TextView) view).getText().toString();
                String filePath = InterCurrentPath + File.separator + fileName;//separator分隔符
                File file = new File(filePath);
                try {
                    if (file.isFile()) {    //只有绝对路径的File才能使用 isFile()和 isDirectory() 判断

                        fis = new FileInputStream(file);            //FileInputStream是对文件数据以字节的形式进行读取操作
                        if (fis.available() == 0) {                 //available()返回估计可读的字节数
                            return;
                        }
                        byte[] readBytes = new byte[fis.available()];//初始化数组，字节长度为文件返回的可读字节数
                        while (fis.read(readBytes) != -1) ;//返回为-1表示读取到文件末尾
                        cont = new String(readBytes);
                        Intent intent = new Intent(NoteActivity.this, Note_EditActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putCharSequence("filename", fileName);//putCharSequence与String都可定义字符串，但是CharSequence的值是可读可写序列,String的值是只读序列。
                        bundle.putCharSequence("content", cont);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    //如果是目录，我们需要得到该目录下的文件名列表，并显示在当前列表中
                    else if (file.isDirectory()) {
                        fileNameList_in = file.list();
                        if (fileNameList_in != null) {
                            adapterIn = new ArrayAdapter<String>(NoteActivity.this, R.layout.note_item, fileNameList_in);
                            list_in.setAdapter(adapterIn);
                        }
                        InterCurrentPath = filePath;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因。
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

    //外部存储器目录项的点击处理
        //如果是文件则直接，则直接读取，然后将文件名和内容传到下一个Activity
        list_out.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String cont = "";
                FileInputStream fis = null;
                String fileName = ((TextView) view).getText().toString();
                String filePath = OuterCurrentPath + File.separator + fileName;//separator分隔符
                File file = new File(filePath);
                try {
                    if (file.isFile()) {
                        fis = new FileInputStream(file);//FileInputStream是对文件数据以字节的形式进行读取操作
                        if (fis.available() == 0) {//available()返回估计可读的字节数
                            return;
                        }
                        byte[] readBytes = new byte[fis.available()];//初始化数组，字节长度为文件返回的可读字节数
                        while (fis.read(readBytes) != -1) ;//返回为-1表示读取到文件末尾
                        cont = new String(readBytes);
                        Intent intent = new Intent(NoteActivity.this, Note_EditActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putCharSequence("filename", fileName);//putCharSequence与String都可定义字符串，但是CharSequence的值是可读可写序列,String的值是只读序列。
                        bundle.putCharSequence("content", cont);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    //如果是目录，得到该目录下的文件名列表
                    else if (file.isDirectory()) {
                        if (fileName.equals("Android")){
                            File fileDir = NoteActivity.this.getExternalFilesDir("myDir");
                            filePath = fileDir.getAbsolutePath();
                            file = new File(filePath);
                        }
                        fileNameList_out = file.list();
                        if (fileNameList_out!=null){
                            adapterOut= new ArrayAdapter<String>(NoteActivity.this, R.layout.note_item, fileNameList_out);
                            list_out.setAdapter(adapterOut);
                        }
                        OuterCurrentPath = filePath;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因。
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.note_menu_new:
                Toast.makeText(NoteActivity.this,"新建",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NoteActivity.this,Note_NewActivity.class);
                startActivity(intent);
                return true;
        }
        return true;
    }



    //目录显示

    //因为每次从备忘录编辑界面返回，都需要刷新备忘录界面目录。
    //因此将目录的显示放在 onStart()方法中实现
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onStart(){
        //TODO Auto-generated method stub
        super.onStart();
        InterCurrentPath = getDataDir().getAbsolutePath();
        fileNameList_in = getDataDir().list(); //显示/data/user/0下的所有目录

        if (fileNameList_in != null){
            adapterIn = new ArrayAdapter<String>(this,R.layout.note_item,fileNameList_in);
            list_in.setAdapter(adapterIn);
        }

        //外部存储器，编辑页面返回，放在onStart()方法里面返回自动刷新
        OuterCurrentPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        fileNameList_out = Environment.getExternalStorageDirectory().list();
        if (fileNameList_out != null){
            adapterOut = new ArrayAdapter<String>(this,R.layout.note_item,fileNameList_out);
            list_out.setAdapter(adapterOut);
        }
    }
}