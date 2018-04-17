package com.example.amresh.speechtotextsave;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class FileList extends AppCompatActivity {
    ListView lv;
   List<String> fileList;
    DataBaseFile myDb;


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Action!");
        menu.add(1,1,1,"Edit");
        menu.add(1,2,1,"Delete");
        menu.add(1,3,1,"Share");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb=new DataBaseFile(this);
        setContentView(R.layout.activity_file_list);
        lv= (ListView) findViewById(R.id.listView);
        fileList=new ArrayList<String>();
        fileList=myDb.getData();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(FileList.this,android.R.layout.simple_list_item_1,fileList);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=parent.getItemAtPosition(position).toString();
                Intent i = new Intent(FileList.this,ContentShow.class);
                i.putExtra("content",name);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        int id=item.getItemId();
        switch (id){
            case 1:
            Toast.makeText(this, "Editing operation  code to be writte!", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "Deleting operation cod eto be written!", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "Sharing Code to be written!", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
    //        Bundle bundle=getIntent().getExtras();
//        fileList=(ArrayList<String>)bundle.getStringArrayList("fileList");
//
//        try {
//            FileInputStream fis=openFileInput("listOfFiles");
//            ObjectInputStream oin=new ObjectInputStream(fis);
//            fileList= (ArrayList<String>) oin.readObject();
//           // Toast.makeText(this, ""+fileList.get(0), Toast.LENGTH_SHORT).show();
//           // tv.setText(fileList.get(0));
//            oin.close();
//            fis.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }


        // Intent i=getIntent();
        // String newFile=i.getStringExtra("fileNmae");
       // fileList.add(newFile);
//        try {
//            InputStream fin=openFileInput("FileNames");
//            InputStreamReader in=new InputStreamReader(fin);
//            BufferedReader bin=new BufferedReader(in);
//            String string;
//            while ((string=bin.readLine())!=null){
//                fileList.add(string+"\n");
//
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //ArrayList<String> myList= (ArrayList<String>) i.getSerializableExtra("fileList");

    }

