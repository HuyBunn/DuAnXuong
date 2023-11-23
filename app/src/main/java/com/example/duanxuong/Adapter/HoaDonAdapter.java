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

import com.example.duanxuong.Fragment.frg_hoadon;
import com.example.duanxuong.Model.HoaDon;
import com.example.duanxuong.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonAdapter extends ArrayAdapter<HoaDon> {
    Context context;
    ArrayList<HoaDon> list;
    frg_hoadon frg;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ImageView btnXoa;
    TextView txtma,txtloai,txtthukho,txtngay;
    public HoaDonAdapter(@NonNull Context context, ArrayList<HoaDon> list, frg_hoadon frg) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.frg = frg;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            v=inflater.inflate(R.layout.item_hoadon,null);
        }
        HoaDon item = list.get(position);
        if(item!=null){
            txtma=v.findViewById(R.id.txtmahd);
            txtloai=v.findViewById(R.id.txtloaihd);
            txtngay=v.findViewById(R.id.txtngay);
            txtthukho=v.findViewById(R.id.txtmaUser);
            btnXoa = v.findViewById(R.id.btnxoa);

            txtma.setText("Mã hóa đơn: "+item.getMaHoaDon());
            txtthukho.setText("Người tạo: "+item.getMaUser());
            txtngay.setText("Ngày: "+sdf.format(item.getNgay()));
            if(item.getLoaiHoaDon()==0){
                txtloai.setText("Nhập");
            }else if(item.getLoaiHoaDon()==1) {
                txtloai.setText("Xuất");
            }

        }
        return v;
    }
}
