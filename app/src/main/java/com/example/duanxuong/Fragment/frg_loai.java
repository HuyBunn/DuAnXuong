package com.example.duanxuong.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanxuong.Adapter.LoaiAdapter;
import com.example.duanxuong.Dao.LoaiDao;
import com.example.duanxuong.Model.Loai;
import com.example.duanxuong.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frg_loai extends Fragment {
ListView lv;
FloatingActionButton fltAdd;
LoaiAdapter adapter;
ArrayList<Loai> list;
Dialog dialog;
LoaiDao dao;
Loai item;
Button btnXN,btnHuy;
EditText txtten;
TextView txtma;

    public frg_loai() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_frg_loai, container, false);
        lv=v.findViewById(R.id.LvL);
        fltAdd = v.findViewById(R.id.fltAdd_Loai);
        dao= new LoaiDao(getActivity());
        capNhatLv();
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialog(getActivity(), 0);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                opendialog(getActivity(), 1);
                return false;
            }
        });

        return v;
    }
    public void opendialog(Context context,int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_loai);
        txtma=dialog.findViewById(R.id.txtmaloai);
        txtten=dialog.findViewById(R.id.txttenloai);
        btnXN=dialog.findViewById(R.id.btnXacNhan);
        btnHuy=dialog.findViewById(R.id.btnHuy);
        if(type!=0){
            txtma.setText(""+item.getMaLoai());
            txtten.setText(item.getTenLoai());
        }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnXN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(txtten.getText().toString())){
                    Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                item = new Loai();
                item.setTenLoai(txtten.getText().toString());
                if(type==0){
                    if(dao.insert(item)>0){
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    item.setMaLoai(Integer.parseInt(txtma.getText().toString()));
                    if(dao.update(item)>0){
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void xoa(String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có chắc chắn muốn xóa");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    public void capNhatLv() {
        list = (ArrayList<Loai>) dao.getAll();
        adapter = new LoaiAdapter(getActivity(), list, this);
        lv.setAdapter(adapter);
    }
}