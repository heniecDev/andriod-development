package com.example.myapptest0001.Memorandum;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class Note_NewActivity extends AppCompatActivity {

    private EditText editFile,editContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_new);

        editFile = (EditText)findViewById(R.id.note_edit_filename);
        editContent = (EditText)findViewById(R.id.note_edit_content);
    }
    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.note_edit,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){

        String filen =editFile.getText().toString();
        String cont = editContent.getText().toString();
        File newFile;
        switch (item.getItemId()){
            case R.id.note_edit_save_in:
                FileOutputStream fos = null;
                try{
                    fos = openFileOutput(filen, Context.MODE_PRIVATE);
                    fos.write(cont.getBytes());
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
            case R.id.note_edit_save_out:
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
                Note_NewActivity.this.finish();
                return true;
            default:
                return false;
        }
    }
}