package com.yusron.pengajuan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class TambahAjuan extends AppCompatActivity implements View.OnClickListener{

    private EditText editnokk;
    private EditText editnik;
    private EditText editnama;
    private EditText editalamat;
    private EditText editpengajuan;

    private Button btntambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_ajuan);

        editnokk = (EditText) findViewById(R.id.editnomorkk);
        editnik = (EditText) findViewById(R.id.editnik);
        editnama = (EditText) findViewById(R.id.editnama);
        editalamat = (EditText) findViewById(R.id.editalamat);
        editpengajuan = (EditText) findViewById(R.id.editpengajuan);

        btntambah = (Button) findViewById(R.id.btnTambah);

        //Setting listeners to button
        btntambah.setOnClickListener(this);
    }

    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addEmployee(){

        final String nokk = editnokk.getText().toString().trim();
        final String nik = editnik.getText().toString().trim();
        final String nama = editnama.getText().toString().trim();
        final String alamat = editalamat.getText().toString().trim();
        final String pengajuan = editpengajuan.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahAjuan.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TambahAjuan.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(koneksi.KEY_EMP_NOKK,nokk);
                params.put(koneksi.KEY_EMP_NIK,nik);
                params.put(koneksi.KEY_EMP_NAMA,nama);
                params.put(koneksi.KEY_EMP_ALAMAT,alamat);
                params.put(koneksi.KEY_EMP_PENGAJUAN,pengajuan);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(koneksi.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == btntambah){
            addEmployee();
        }
    }
}
