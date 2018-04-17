package com.example.amresh.speechtotextsave;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Locale;

public class ContentShow extends AppCompatActivity implements TextToSpeech.OnInitListener {
    TextView textView;
    Button speakBtn;
    EditText editFile;
    Button save;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_show);
        textView = (TextView) findViewById(R.id.content);
        editFile= (EditText) findViewById(R.id.editFile);
        speakBtn = (Button) findViewById(R.id.speakButton);
        save= (Button) findViewById(R.id.saveBtn);
        textToSpeech = new TextToSpeech(this, this);

        Intent i = getIntent();
        final String content = i.getStringExtra("content");
        try {

            InputStream fin = openFileInput(content);
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader bin = new BufferedReader(in);
            String str;

            StringBuilder buf = new StringBuilder();

            while ((str = bin.readLine()) != null) {
                buf.append(str + "\n");
            }
            textView.setText(buf.toString());
            editFile.setText(buf.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
        speakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OutputStreamWriter out =

                            new OutputStreamWriter(openFileOutput("/sdcard/"+content+".txt",MODE_APPEND));

                    String str=editFile.getText().toString();
                    out.write(str);
                    editFile.setText(null);

                    out.close();
                } catch (IOException e) {
                }

            }
        });

    }


    private void speakOut() {
        String text = textView.getText().toString();
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }
    @Override
    public void onInit ( int status){
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else
                speakBtn.setEnabled(true);
            speakOut();

        }

    }

}


