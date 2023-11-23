package com.example.duanxuong.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.duanxuong.Model.Loai;
import com.example.duanxuong.R;

import java.util.ArrayList;

public class LoaiSpinerAdapter extends ArrayAdapter<Loai> {
    private Context context;
    private ArrayList<Loai> list;
    TextView txtMa,txtTen;
    public LoaiSpinerAdapter(@NonNull Context context, ArrayList<Loai> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.spn_loai,null);
        }
        Loai item = list.get(position);
        if(item!=null){
            txtMa = v.findViewById(R.id.txtmaloai_spn);
            txtTen=v.findViewById(R.id.txttenloai_spn);
            txtMa.setText(item.getMaLoai()+".");
            txtTen.setText(item.getTenLoai());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.spn_loai,null);
        }
        Loai item = list.get(position);
        if(item!=null){
            txtMa = v.findViewById(R.id.txtmaloai_spn);
            txtTen=v.findViewById(R.id.txttenloai_spn);
            txtMa.setText(item.getMaLoai()+".");
            txtTen.setText(item.getTenLoai());
        }
        return v;
    }
}
