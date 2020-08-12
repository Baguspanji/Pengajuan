package com.yusron.pengajuan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btntambah;
    Button btnlihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btntambah = (Button) findViewById(R.id.btnTambah);
        btnlihat = (Button) findViewById(R.id.btnLihat);

        btntambah.setOnClickListener(this);
        btnlihat.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btntambah){
            startActivity(new Intent(this,TambahAjuan.class));
        }

        if(v == btnlihat){
            startActivity(new Intent(this,LihatAjuan.class));
        }
    }

}
