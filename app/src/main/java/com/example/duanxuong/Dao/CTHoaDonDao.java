package com.example.duanxuong.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanxuong.Database.DbHelper;
import com.example.duanxuong.Model.CTHoaDon;
import com.example.duanxuong.Model.Loai;

import java.util.ArrayList;
import java.util.List;

public class CTHoaDonDao {
    private SQLiteDatabase db;

    public CTHoaDonDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(CTHoaDon obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaCTHD", obj.getMaCTHD());
        contentValues.put("MaHD", obj.getMaHoaDon());
        contentValues.put("MaSP", obj.getMaSanPham());
        contentValues.put("SoLuong", obj.getSoLuong());
        contentValues.put("DonGia", obj.getDonGia());
        return db.insert("CTHoaDon", null, contentValues);
    }

    public int update(CTHoaDon obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaCTHD", obj.getMaCTHD());
        contentValues.put("MaHD", obj.getMaHoaDon());
        contentValues.put("MaSP", obj.getMaSanPham());
        contentValues.put("SoLuong", obj.getSoLuong());
        contentValues.put("DonGia", obj.getDonGia());
        return db.update("CTHoaDon", contentValues, "MaCTHD=?", new String[]{String.valueOf(obj.getMaCTHD())});
    }

    public int delete(String id) {
        return db.delete("CTHoaDon", "MaCTHD=?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    private List<CTHoaDon> getData(String sql, String... selectionArgs) {
        List<CTHoaDon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            CTHoaDon obj = new CTHoaDon();
            obj.setMaCTHD(Integer.parseInt(c.getString(c.getColumnIndex("MaCTHD"))));
            obj.setMaHoaDon(Integer.parseInt(c.getString(c.getColumnIndex("MaHD"))));
            obj.setMaSanPham(Integer.parseInt(c.getString(c.getColumnIndex("MaSP"))));
            obj.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("SoLuong"))));
            obj.setDonGia(Integer.parseInt(c.getString(c.getColumnIndex("DonGia"))));
            list.add(obj);
        }
        c.close();
        return list;

    }

    public List<CTHoaDon> getAll() {
        String sql = "select * from CTHoaDon";
        return getData(sql);
    }

    public CTHoaDon getID(String id) {
        String sql = "select * from CTHoaDon where MaCTHD=?";
        List<CTHoaDon> list = getData(sql, id);
        return list.get(0);
    }
}
