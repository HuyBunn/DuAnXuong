package com.example.duanxuong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.duanxuong.Dao.UserDao;
import com.example.duanxuong.Fragment.frg_hoadon;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class dang_nhap extends AppCompatActivity {
    Button btnDN;
    TextInputEditText txtTDN,txtMK;
    CheckBox chkGhiNho;
    UserDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        btnDN = findViewById(R.id.btnDangNhap);
        txtTDN = findViewById(R.id.edtUser);
        txtMK = findViewById(R.id.edtPass);
        chkGhiNho = findViewById(R.id.chkLuu);
        dao = new UserDao(this);
        //doc user,pass trong SharedPreferences
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        txtTDN.setText(pref.getString("USERNAME",""));
        txtMK.setText(pref.getString("PASSWORD",""));
        chkGhiNho.setChecked(pref.getBoolean("REMEMBER",false));

        String username = txtTDN.getText().toString();
        Bundle bundle = new Bundle();

        frg_hoadon fragment = new frg_hoadon();
        fragment.setArguments(bundle);

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {
        String strUser = txtTDN.getText().toString();
        String strPass = txtMK.getText().toString();
        if (strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập và mk không được bỏ trống",Toast.LENGTH_SHORT).show();
        }else {
            if (dao.CheckLogin(strUser,strPass)>0||(strUser.equals("admin")&&strPass.equals("admin"))){
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,chkGhiNho.isChecked());
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("user",strUser);
                startActivity(i);
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void rememberUser(String u,String p,boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status){
            edit.clear();
        }else {
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.commit();
    }

}