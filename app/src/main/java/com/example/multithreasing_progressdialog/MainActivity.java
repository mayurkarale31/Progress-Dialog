package com.example.multithreasing_progressdialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownload = findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] files = {"https://bitcode.in/android/.demos.zip", "https://bitcode.in/java/.demos.zip"
                };
                new DownloadThread(MainActivity.this, new MyHandler()).execute(files);
            }
        });
    }
    private class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                btnDownload.setText((int)msg.obj + "%");
            }
            else{
                btnDownload.setText("Res=" +(float)msg.obj);
            }
        }
    }

    private void mt(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}