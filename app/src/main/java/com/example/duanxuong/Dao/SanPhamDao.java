package com.example.duanxuong.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanxuong.Database.DbHelper;
import com.example.duanxuong.Model.Loai;
import com.example.duanxuong.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDao {
SQLiteDatabase db;
public SanPhamDao(Context context){
    DbHelper dbHelper = new DbHelper(context);
    db=dbHelper.getWritableDatabase();
}
public long insert (SanPham obj){
    ContentValues contentValues = new ContentValues();
    contentValues.put("MaLoai",obj.getMaLoai());
    contentValues.put("TenSP",obj.getTenSP());
    contentValues.put("Gia",obj.getGia());
    contentValues.put("SoLuong",obj.getSoLuong());
    contentValues.put("MoTa",obj.getMoTa());
    return db.insert("SanPham",null,contentValues);
}
public int update(SanPham obj){
    ContentValues contentValues = new ContentValues();
    contentValues.put("MaLoai",obj.getMaLoai());
    contentValues.put("TenSP",obj.getTenSP());
    contentValues.put("Gia",obj.getGia());
    contentValues.put("SoLuong",obj.getSoLuong());
    contentValues.put("MoTa",obj.getMoTa());
    return db.update("SanPham",contentValues,"MaSP=?",new String[]{String.valueOf(obj.getMaSP())});
}
public int delete(String id){
return db.delete("SanPham","MaSP=?",new String[]{String.valueOf(id)});
}
@SuppressLint("Range")
private List<SanPham> getData  (String sql, String...selectionArgs){
List<SanPham> list = new ArrayList<>();
    Cursor c = db.rawQuery(sql,selectionArgs);
    while (c.moveToNext()){
        SanPham obj = new SanPham();
        obj.setMaSP(Integer.parseInt(c.getString(c.getColumnIndex("MaSP"))));
        obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("MaLoai"))));
        obj.setTenSP(c.getString(c.getColumnIndex("TenSP")));
        obj.setGia(Integer.parseInt(c.getString(c.getColumnIndex("Gia"))));
        obj.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("SoLuong"))));
        obj.setMoTa(c.getString(c.getColumnIndex("MoTa")));
        list.add(obj);
    }
return list;
}
    public List<SanPham> getAll() {
        String sql = "select * from SanPham";
        return getData(sql);
    }

    public SanPham getID(String id) {
        String sql = "select * from SanPham where MaSP=?";
        List<SanPham> list = getData(sql, id);
        return list.get(0);
    }
}
