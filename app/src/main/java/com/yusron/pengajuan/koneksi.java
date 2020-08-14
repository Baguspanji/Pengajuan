package com.yusron.pengajuan;

public class koneksi {

    public static final String URL_ADD="https://sukorejo.info/pengajuan/tambahAjuan.php";
    public static final String URL_GET_ALL = "https://sukorejo.info/pengajuan/tampilAjuan.php";
    public static final String URL_EDIT = "https://sukorejo.info/pengajuan/editAjuan.php?id=";
    public static final String URL_UPDATE_EMP = "https://sukorejo.info/pengajuan/updateAjuan.php";
    public static final String URL_DELETE_EMP = "https://sukorejo.info/pengajuan/hapusAjuan.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NOKK = "noKK";
    public static final String KEY_EMP_NIK = "nik";
    public static final String KEY_EMP_NAMA = "nama";
    public static final String KEY_EMP_ALAMAT = "alamat";
    public static final String KEY_EMP_PENGAJUAN = "pengajuan";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NOKK = "noKK";
    public static final String TAG_NIK = "nik";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_PENGAJUAN = "pengajuan";

    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";

}
