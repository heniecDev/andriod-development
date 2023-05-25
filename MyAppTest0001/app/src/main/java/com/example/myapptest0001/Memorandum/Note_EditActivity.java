package com.example.myapptest0001.Memorandum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapptest0001.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Note_EditActivity extends AppCompatActivity {

    private EditText editFile,editContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        editFile = (EditText)findViewById(R.id.note_edit_filename);
        editContent = (EditText)findViewById(R.id.note_edit_content);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        assert bundle != null;
        //获取文件名和内容
        editFile.setText(bundle.getString("filename",""));
        editContent.setText(bundle.getString("content",""));
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.note_edit,menu);
        return true;
    }


//因为我的APP中，是将“保存到内部存储器”作为菜单项的，所以有如下代码
    public boolean onOptionsItemSelected(MenuItem item){
        String filen =editFile.getText().toString();
        String cont = editContent.getText().toString();
        File newFile;
        switch (item.getItemId()){
            case R.id.note_edit_save_in:    //内部存储器
                FileOutputStream fos = null;
                try{
                    fos = openFileOutput(filen, Context.MODE_PRIVATE);
                    fos.write(cont.getBytes());     //cont是需要保存的内容，转换成字节数组
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (fos!=null){
                        try {
                            fos.flush();
                            fos.close();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
                return true;
            //保存到外部存储器
            case R.id.note_edit_save_out:   //外部存储器
                newFile = new File(getExternalFilesDir("myDir"),filen);
                newFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),filen);
                try {
                    fos = new FileOutputStream(newFile);
                    fos.write(cont.getBytes());
                }catch (IOException e){
                    e.printStackTrace();
                }
                return true;
            case R.id.note_edit_back:
                Note_EditActivity.this.finish();
                return true;
            default:
                return false;
        }
    }
}