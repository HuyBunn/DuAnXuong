package com.example.duanxuong.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanxuong.Database.DbHelper;
import com.example.duanxuong.Model.Loai;

import java.util.ArrayList;
import java.util.List;

public class    LoaiDao {
    private SQLiteDatabase db;

    public LoaiDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Loai obj) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("TenLoai", obj.getTenLoai());
        return db.insert("Loai", null, contentValues);
    }

    public int update(Loai obj) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("TenLoai", obj.getTenLoai());
        return db.update("Loai", contentValues, "MaLoai=?", new String[]{String.valueOf(obj.getMaLoai())});
    }

    public int delete(String id) {
        return db.delete("Loai", "MaLoai=?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    private List<Loai> getData(String sql, String... selectionArgs) {
        List<Loai> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Loai obj = new Loai();
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("MaLoai"))));
            obj.setTenLoai(c.getString(c.getColumnIndex("TenLoai")));
            list.add(obj);
        }
        return list;

    }

    public List<Loai> getAll() {
        String sql = "select * from Loai";
        return getData(sql);
    }

    public Loai getID(String id) {
        String sql = "select * from Loai where MaLoai=?";
        List<Loai> list = getData(sql, id);
        return list.get(0);
    }
}
