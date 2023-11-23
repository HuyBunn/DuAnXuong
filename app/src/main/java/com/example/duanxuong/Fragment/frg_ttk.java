package com.example.duanxuong.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.duanxuong.Dao.UserDao;
import com.example.duanxuong.Model.User;
import com.example.duanxuong.R;
import com.google.android.material.textfield.TextInputEditText;

public class frg_ttk extends Fragment {

    TextInputEditText txtTDN,txtHT,txtMK,txtNLMK;
    Button btnLuu,btnHuy;
    UserDao dao;
    public frg_ttk() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frg_ttk, container, false);
        txtHT = v.findViewById(R.id.edtHoTen);
        txtMK = v.findViewById(R.id.edtMatKhau);
        txtNLMK = v.findViewById(R.id.edtNLMK);
        txtTDN = v.findViewById(R.id.edtTaiKhoan);
        btnLuu = v.findViewById(R.id.btnThem);
        btnHuy = v.findViewById(R.id.btnHuy);
        dao = new UserDao(getActivity());
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTDN.setText("");
                txtHT.setText("");
                txtMK.setText("");
                txtNLMK.setText("");
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User();
                u.setUserName(txtTDN.getText().toString());
                u.setFullName(txtHT.getText().toString());
                u.setPass(txtMK.getText().toString());
                if(validate()>0){
                    if(dao.insert(u)>0){
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        txtTDN.setText("");
                        txtHT.setText("");
                        txtMK.setText("");
                        txtNLMK.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }
    public int validate(){
        int check = 1;
        if(txtTDN.getText().length()==0||txtHT.getText().length()==0||txtMK.getText().length()==0||txtNLMK.getText().length()==0){
            Toast.makeText(getActivity(), "Hãy nhập đầy đủ ", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        else {
            String pass = txtMK.getText().toString();
            String Repass = txtNLMK.getText().toString();
            if(!pass.equals(Repass)){
                Toast.makeText(getActivity(), "Mật khẩu không trùng ", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }
}