package com.example.duanxuong.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanxuong.Adapter.LoaiAdapter;
import com.example.duanxuong.Adapter.LoaiSpinerAdapter;
import com.example.duanxuong.Adapter.SanPhamAdapter;
import com.example.duanxuong.Dao.LoaiDao;
import com.example.duanxuong.Dao.SanPhamDao;
import com.example.duanxuong.Model.Loai;
import com.example.duanxuong.Model.SanPham;
import com.example.duanxuong.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frg_sp extends Fragment {
    ListView lvSP;
    SanPhamAdapter adapter;
    FloatingActionButton fltAdd;
    ArrayList<SanPham> list;
    ArrayList<Loai> list_l;
    Dialog dialog;
    SanPham item;
    SanPhamDao dao;
    Loai item_l;
    LoaiDao dao_l;
    int position, maLoai;
    EditText txtTensp, txtGia, txtsl, txtmota;
    Button btnSave, btnHuy;
    Spinner spinner;
    LoaiSpinerAdapter spinerAdapter;
    TextView txtma;


    public frg_sp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frg_sp, container, false);
        lvSP = v.findViewById(R.id.LvSP);
        dao = new SanPhamDao(getActivity());
        fltAdd = v.findViewById(R.id.fltAdd_SP);
        capNhatLv();
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(getActivity(), 0);
            }
        });
        lvSP.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        dialog.setContentView(R.layout.dialog_sanpham);
        txtma = dialog.findViewById(R.id.txtmasp);
        txtTensp = dialog.findViewById(R.id.txttensp);
        txtGia = dialog.findViewById(R.id.txtgia);
        txtmota = dialog.findViewById(R.id.txtmota);
        txtsl = dialog.findViewById(R.id.txtsoluong);
        spinner = dialog.findViewById(R.id.spnloai);
        btnSave = dialog.findViewById(R.id.btnXacNhan);
        btnHuy = dialog.findViewById(R.id.btnHuy);
        list_l = new ArrayList<Loai>();
        dao_l = new LoaiDao(context);
        list_l = (ArrayList<Loai>) dao_l.getAll();
        spinerAdapter = new LoaiSpinerAdapter(context, list_l);
        spinner.setAdapter(spinerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                maLoai = list_l.get(position).getMaLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (type != 0) {
            txtma.setText(item.getMaSP() + "");
            txtTensp.setText(item.getTenSP());
            txtmota.setText(item.getMoTa());
            txtsl.setText(item.getSoLuong() + "");
            txtGia.setText(item.getGia() + "");
            for (int i = 0; i < list_l.size(); i++) {
                if (item.getMaLoai() == (list_l.get(i).getMaLoai())) {
                    position = i;
                }
//        Log.i("zzzzzzzzzzzz", "posSach: " + position);
//        spinner.setSelection(position);
            }
        }
            btnHuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TextUtils.isEmpty(txtTensp.getText().toString()) || TextUtils.isEmpty(txtGia.getText().toString()) || TextUtils.isEmpty(txtsl.getText().toString()) || TextUtils.isEmpty(txtmota.getText().toString())) {
                        Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        int g = Integer.parseInt(txtGia.getText().toString());
                        if (g <= 0) {
                            Toast.makeText(getContext(), "Giá phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Giá phải là số", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        int sl = Integer.parseInt(txtsl.getText().toString());
                        if (sl <= 0) {
                            Toast.makeText(getContext(), "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Số lượng phải là số", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    item = new SanPham();
                    item.setMaLoai(maLoai);
                    item.setTenSP(txtTensp.getText().toString());
                    item.setGia(Integer.parseInt(txtGia.getText().toString()));
                    item.setMoTa(txtmota.getText().toString());
                    item.setSoLuong(Integer.parseInt(txtsl.getText().toString()));
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaSP(Integer.parseInt(txtma.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
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
        list = (ArrayList<SanPham>) dao.getAll();
        adapter = new SanPhamAdapter(getActivity(), list, this);
        lvSP.setAdapter(adapter);
    }
}