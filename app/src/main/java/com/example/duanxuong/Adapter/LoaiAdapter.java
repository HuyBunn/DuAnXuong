package com.example.duanxuong.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanxuong.Fragment.frg_loai;
import com.example.duanxuong.Model.Loai;
import com.example.duanxuong.R;

import java.util.ArrayList;

public class LoaiAdapter extends ArrayAdapter<Loai> {
    Context context;
    ArrayList<Loai> list;
    TextView txtma, txtten;
    ImageView btnxoa;
    frg_loai frg;

    public LoaiAdapter(@NonNull Context context, ArrayList<Loai> list, frg_loai frg) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.frg = frg;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            v=inflater.inflate(R.layout.item_loai,null);
        }
        Loai item = list.get(position);
        if(item!=null){
            txtma = v.findViewById(R.id.txtmaloai);
            txtten = v.findViewById(R.id.txttenloai);
            btnxoa = v.findViewById(R.id.btnxoa);
            txtma.setText("Mã loại: "+item.getMaLoai());
            txtten.setText("Tên loại: "+item.getTenLoai());
        }
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frg.xoa(String.valueOf(item.getMaLoai()));
            }
        });
        return v;
    }
}
