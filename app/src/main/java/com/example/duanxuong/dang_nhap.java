package com.example.duanxuong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class dang_nhap extends AppCompatActivity {
    TextInputEditText edtUser, edtPass;
    TextInputLayout tilPass;
    CheckBox chkluu;
    Button btnDN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        chkluu = findViewById(R.id.chkLuu);
        btnDN = findViewById(R.id.btnDangNhap);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        edtUser.setText(sharedPreferences.getString("USERNAME", ""));
        edtPass.setText(sharedPreferences.getString("PASSWORD", ""));
        chkluu.setChecked(sharedPreferences.getBoolean("REMEMBER", false));
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {
        String strUser = edtUser.getText().toString();
        String strPass = edtPass.getText().toString();
        if (strPass.isEmpty() || strUser.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            if (strUser.equalsIgnoreCase("Admin") && strPass.equalsIgnoreCase("Admin")) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                remember(strUser,strPass,chkluu.isChecked());
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("user",strUser);
                startActivity(i);
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "Username hoặc Password không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void remember(String u, String p, boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (!status) {
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        edit.commit();
    }

}