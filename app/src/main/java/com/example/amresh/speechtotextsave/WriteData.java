package com.example.amresh.speechtotextsave;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WriteData extends AppCompatActivity {
    DataBaseFile dataBaseFile;
    TextView content,title;

    Button speak,read,save,fileListBtn;
    String fileName;
   // ArrayList<String>  fileList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_data);
        dataBaseFile=new DataBaseFile(this);
        content= (TextView) findViewById(R.id.content);
        speak= (Button) findViewById(R.id.speak);
        read= (Button) findViewById(R.id.read);
        save= (Button) findViewById(R.id.save);
        fileListBtn= (Button) findViewById(R.id.list);
        title= (TextView) findViewById(R.id.fileName);

        Intent i=getIntent();
         fileName=i.getStringExtra("fileName");
        title.setText(fileName);
        dataBaseFile.insertData(fileName);
       // fileList.add(fileName);

//        try {
////            FileOutputStream fileOutputStream=openFileOutput("FileNames", Context.MODE_APPEND);
////            fileOutputStream.write(fileName.getBytes());
////            FileWriter writer=new FileWriter("FileName.txt");
//            FileOutputStream fileOutputStream=openFileOutput("listOfFiles",MODE_APPEND);
//            ObjectOutputStream os=new ObjectOutputStream(fileOutputStream);
//            os.writeObject(fileList);
//            os.close();
//            fileOutputStream.close();
//
//
//            } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //name.setText();
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_PROMPT,"speak your content");
                startActivityForResult(i,4);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                try {
//                    File myFile = new File("/sdcard/"+fileName+".txt");
//                    FileInputStream fIn = null;
//                    fIn = new FileInputStream(myFile);
//                    BufferedReader myReader = new BufferedReader(
//                            new InputStreamReader(fIn));
//                    String aDataRow = "";
//                    String aBuffer = "";
//                    while ((aDataRow = myReader.readLine()) != null) {
//                        aBuffer += aDataRow + "\n";
//                    }
//                     content.setText(aBuffer);
//                    myReader.close();
//                    Toast.makeText(getBaseContext(),
//                            "Done reading SD 'mysdfile.txt'",
//                            Toast.LENGTH_LONG).show();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }





                try {

//                    OutputStreamWriter out =
//
//                            new OutputStreamWriter(openFileOutput("/sdcard/"+fileName+".txt",MODE_APPEND));
//
//                    String str=content.getText().toString();
//                    out.write(str);
//                    content.setText(null);
//
//                    out.close();
                    File myFile = new File("/sdcard/"+fileName+".txt");
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter =
                            new OutputStreamWriter(fOut);
                    myOutWriter.append(content.getText());
                    myOutWriter.close();
                    fOut.close();
                } catch (IOException e) {
                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream fin=openFileInput(fileName);
                    InputStreamReader in=new InputStreamReader(fin);
                    BufferedReader bin=new BufferedReader(in);
                    String str;

                    StringBuilder buf=new StringBuilder();

                    while ((str = bin.readLine()) != null) {
                        buf.append(str + "\n");
                    }
                    content.setText(buf.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e){}

            }

        });
        fileListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(WriteData.this,FileList.class);
                //i.putExtra("fileList",fileList );
                startActivity(i);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 4:
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
               content.setText(result.get(0));
            }
        }
    }
}
