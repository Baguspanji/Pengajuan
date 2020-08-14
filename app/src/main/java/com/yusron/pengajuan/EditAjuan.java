package com.yusron.pengajuan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EditAjuan extends AppCompatActivity implements View.OnClickListener {

    private EditText editid;
    private EditText editnokk;
    private EditText editnik;
    private EditText editnama;
    private EditText editalamat;
    private Spinner spnpengajuan;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    private Toolbar ToolBarAtas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ajuan);

        ToolBarAtas = (Toolbar) findViewById(R.id.toolbar_satu);

        Intent intent = getIntent();

        id = intent.getStringExtra(koneksi.EMP_ID);

        editid = (EditText) findViewById(R.id.editid);
        editnokk = (EditText) findViewById(R.id.editnomorkk);
        editnik = (EditText) findViewById(R.id.editnik);
        editnama = (EditText) findViewById(R.id.editnama);
        editalamat = (EditText) findViewById(R.id.editalamat);
        spnpengajuan = (Spinner) findViewById(R.id.spinnerpengajuan);

        buttonUpdate = (Button) findViewById(R.id.btnUpdate);
        buttonDelete = (Button) findViewById(R.id.btnDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editid.setText(id);

        getEmployee();

        setSupportActionBar(ToolBarAtas);
        ToolBarAtas.setLogoDescription(getResources().getString(R.string.app_name));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditAjuan.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(koneksi.URL_EDIT,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(koneksi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String id = c.getString(koneksi.TAG_ID);
            String noKK = c.getString(koneksi.TAG_NOKK);
            String nik = c.getString(koneksi.TAG_NIK);
            String nama = c.getString(koneksi.TAG_NAMA);
            String alamat = c.getString(koneksi.TAG_ALAMAT);
            String pengajuan = c.getString(koneksi.TAG_PENGAJUAN);

            editid.setText(id);
            editnokk.setText(noKK);
            editnik.setText(nik);
            editnama.setText(nama);
            editalamat.setText(alamat);
            if (pengajuan.toString().equals("Surat Pengantar KTP")){
                spnpengajuan.setSelection(1);
            } else  if (pengajuan.toString().equals("Surat Pembaruan KK")){
                spnpengajuan.setSelection(2);
            } else  if (pengajuan.toString().equals("Surat Keterangan Usaha")){
                spnpengajuan.setSelection(3);
            } else  if (pengajuan.toString().equals("Surat Keterangan Kematian")){
                spnpengajuan.setSelection(4);
            } else  if (pengajuan.toString().equals("Surat Pengantar SKCK")){
                spnpengajuan.setSelection(5);
            } else  if (pengajuan.toString().equals("Surat Keterangan Tidak Mampu")){
                spnpengajuan.setSelection(6);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){
        final String id = editid.getText().toString().trim();
        final String nokk = editnokk.getText().toString().trim();
        final String nik = editnik.getText().toString().trim();
        final String nama = editnama.getText().toString().trim();
        final String alamat = editalamat.getText().toString().trim();
        final String pengajuan = spnpengajuan.getSelectedItem().toString();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditAjuan.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(EditAjuan.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(koneksi.KEY_EMP_ID,id);
                hashMap.put(koneksi.KEY_EMP_NOKK,nokk);
                hashMap.put(koneksi.KEY_EMP_NIK,nik);
                hashMap.put(koneksi.KEY_EMP_NAMA,nama);
                hashMap.put(koneksi.KEY_EMP_ALAMAT,alamat);
                hashMap.put(koneksi.KEY_EMP_PENGAJUAN,pengajuan);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(koneksi.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditAjuan.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(EditAjuan.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(koneksi.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Pegawai ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(EditAjuan.this,LihatAjuan.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",

                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            startActivity(new Intent(EditAjuan.this,LihatAjuan.class));
                        }
                    });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
            startActivity(new Intent(this,LihatAjuan.class));
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }
    }
}
