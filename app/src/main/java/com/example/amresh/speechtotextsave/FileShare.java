package com.example.amresh.speechtotextsave;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.constraint.solver.SolverVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileShare extends AppCompatActivity {
    DataBaseFile myDB;
    EditText e1;
    Button shareBtn;
    DataBaseFile dbFile;
    String nameoffile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_share);
        shareBtn= (Button) findViewById(R.id.button);
        dbFile=new DataBaseFile(this);
        e1= (EditText) findViewById(R.id.editText);
        myDB=new DataBaseFile(this);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name= e1.getText().toString();

                Cursor res=myDB.getName(name);
                if (res.getCount()==0)
                    Toast.makeText(FileShare.this, "nothing found", Toast.LENGTH_SHORT).show();
                else
                    while(res.moveToNext()){
                        nameoffile= res.getString(0);
                    }
//                Toast.makeText(FileShare.this, ""+nameoffile, Toast.LENGTH_LONG).show();
//                File f=new File(Environment.getExternalStorageDirectory().toString()+"/"+nameoffile);
//                Intent i=new Intent(Intent.ACTION_SEND);
//                i.setType("plain/text");
//                i.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+f.getAbsolutePath()));
//                startActivity(Intent.createChooser(i,"sharing Files "));

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_STREAM,Uri.parse("sdcard/"+nameoffile+".txt"));
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,"Send video via:"));






            }
        });
    }
}
