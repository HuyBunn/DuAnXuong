package com.example.duanxuong.Fragment;

import static android.content.Intent.getIntent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanxuong.Adapter.HoaDonAdapter;
import com.example.duanxuong.Adapter.LoaiAdapter;
import com.example.duanxuong.Dao.HoaDonDao;
import com.example.duanxuong.Dao.UserDao;
import com.example.duanxuong.Model.HoaDon;
import com.example.duanxuong.Model.Loai;
import com.example.duanxuong.Model.User;
import com.example.duanxuong.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class frg_hoadon extends Fragment {
    ListView lvHD;
    HoaDon item;
    HoaDonDao dao;
    HoaDonAdapter adapter;
    FloatingActionButton fltAdd;
    Dialog dialog;
    ArrayList<HoaDon> list;
    Button btnXN, btnHuy;
    RadioButton rdoNhap, rdoXuat;
    TextView txtma,txtngay, txtThukho;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public frg_hoadon() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frg_hoadon, container, false);
        lvHD = v.findViewById(R.id.LvHD);
        fltAdd = v.findViewById(R.id.fltAdd_HD);
        dao = new HoaDonDao(getActivity());
        capNhatLv();

        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialog(getActivity(), 0);
            }
        });
        lvHD.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                opendialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }

    public void opendialog(Context context, int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_hoadon);
        txtma = dialog.findViewById(R.id.txtmahd);
        txtngay = dialog.findViewById(R.id.txtngay);
        txtThukho = dialog.findViewById(R.id.txtthukho);
        rdoNhap = dialog.findViewById(R.id.rdoNhap);
        rdoXuat = dialog.findViewById(R.id.rdoXuat);
        rdoNhap.setChecked(true);
        btnXN = dialog.findViewById(R.id.btnXacNhan);
        btnHuy = dialog.findViewById(R.id.btnHuy);
        txtngay.setText(sdf.format(new Date()));
        if (type != 0) {
            txtma.setText("Mã hóa đơn: " + item.getMaHoaDon());
            txtThukho.setText("Người tạo: "+item.getMaUser());
            txtngay.setText("Ngày thuê: "+sdf.format(item.getNgay()));
            if (item.getLoaiHoaDon() == 1) {
                rdoXuat.setChecked(true);
            } else {
                rdoNhap.setChecked(true);
            }
        }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Bundle bundle = getArguments();
        if (bundle != null) {
            String username = bundle.getString("usernamedn");
            txtThukho.setText(username);
        }
        btnXN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new HoaDon();
                item.setMaUser(txtThukho.getText().toString());
                item.setNgay(new Date());
                if (rdoNhap.isChecked()) {
                    item.setLoaiHoaDon(0);
                } else if (rdoXuat.isChecked()) {
                    item.setLoaiHoaDon(1);
                }


                if (type == 0) {
                    if (dao.insert(item) > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    item.setMaHoaDon(Integer.parseInt(txtma.getText().toString()));
                    if (dao.update(item) > 0) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void xoa(String Id) {
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
        list = (ArrayList<HoaDon>) dao.getAll();
        adapter = new HoaDonAdapter(getActivity(), list, this);
        lvHD.setAdapter(adapter);
    }
}