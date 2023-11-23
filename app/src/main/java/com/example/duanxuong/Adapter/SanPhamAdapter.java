package com.example.duanxuong.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanxuong.Dao.LoaiDao;
import com.example.duanxuong.Fragment.frg_sp;
import com.example.duanxuong.Model.Loai;
import com.example.duanxuong.Model.SanPham;
import com.example.duanxuong.R;

import java.util.ArrayList;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    Context context;
    ArrayList<SanPham> list;
    frg_sp frg;
    TextView txtma,txttensp,txtloai,txtgia,txtmota,txtsl;
    ImageView btnXoa;

    public SanPhamAdapter(@NonNull Context context, ArrayList<SanPham> list, frg_sp frg) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.frg = frg;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            v=inflater.inflate(R.layout.item_sanpham,null);
        }
        SanPham item = list.get(position);
        if(item!=null){
            LoaiDao loaiDao = new LoaiDao(context);
            Loai loai = loaiDao.getID(String.valueOf(item.getMaLoai()));
            txtma = v.findViewById(R.id.txtmasp);
            txttensp = v.findViewById(R.id.txttensp);
            txtloai = v.findViewById(R.id.txttenloai);
            txtgia = v.findViewById(R.id.txtgia);
            txtmota = v.findViewById(R.id.txtmota);
            txtsl=v.findViewById(R.id.txtsoluong);
            btnXoa = v.findViewById(R.id.btnxoa);

            txtma.setText("Mã sản phẩm: "+item.getMaSP());
            txtgia.setText("Giá: "+item.getGia());
            txttensp.setText("Tên sản phẩm: "+item.getTenSP());
            txtloai.setText("Loại: "+loai.getTenLoai());
            txtmota.setText("Mô tả: "+item.getMoTa());
            txtsl.setText("Số lượng: "+item.getSoLuong());
        }
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frg.xoa(String.valueOf(item.getMaSP()));
            }
        });
return v;
    }
}
