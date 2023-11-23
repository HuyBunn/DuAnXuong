package com.example.duanxuong.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanxuong.Database.DbHelper;
import com.example.duanxuong.Model.HoaDon;
import com.example.duanxuong.Model.SanPham;
import com.example.duanxuong.Model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {
    SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public HoaDonDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long insert (HoaDon obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaUser",obj.getMaUser());
        contentValues.put("LoaiHD",obj.getLoaiHoaDon());
        contentValues.put("Ngay",sdf.format(obj.getNgay()));
        return db.insert("HoaDon",null,contentValues);
    }
    public int update(HoaDon obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaUser",obj.getMaUser());
        contentValues.put("LoaiHD",obj.getLoaiHoaDon());
        contentValues.put("Ngay",sdf.format(obj.getNgay()));
        return db.update("HoaDon",contentValues,"MaHoaDon=?",new String[]{String.valueOf(obj.getMaHoaDon())});
    }
    public int delete(String id){
        return db.delete("HoaDon","MaHoaDon=?",new String[]{String.valueOf(id)});
    }
    @SuppressLint("Range")
    private List<HoaDon> getData  (String sql, String...selectionArgs){
        List<HoaDon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            HoaDon obj = new HoaDon();
            obj.setMaHoaDon(Integer.parseInt(c.getString(c.getColumnIndex("MaHoaDon"))));
            obj.setMaUser(c.getString(c.getColumnIndex("MaUser")));
            obj.setLoaiHoaDon(Integer.parseInt(c.getString(c.getColumnIndex("LoaiHD"))));
            try {
                obj.setNgay(sdf.parse(c.getString(c.getColumnIndex("Ngay"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(obj);
        }
        c.close();
        return list;
    }
    public List<HoaDon> getAll() {
        String sql = "select * from HoaDon";
        return getData(sql);
    }

    public HoaDon getID(String id) {
        String sql = "select * from HoaDon where MaHoaDon=?";
        List<HoaDon> list = getData(sql, id);
        return list.get(0);
    }
    public HoaDon getHoaDonFromDatabase(int maHoaDon) {
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon=?";
        List<HoaDon> hoaDonList = getData(sql, String.valueOf(maHoaDon));
        if (!hoaDonList.isEmpty()) {
            return hoaDonList.get(0);
        }
        return null;
    }

}
