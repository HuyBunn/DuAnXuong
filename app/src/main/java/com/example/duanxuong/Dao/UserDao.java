package com.example.duanxuong.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanxuong.Database.DbHelper;
import com.example.duanxuong.Model.SanPham;
import com.example.duanxuong.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    SQLiteDatabase db;
    public UserDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long insert (User obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName",obj.getUserName());
        contentValues.put("Pass",obj.getPass());
        contentValues.put("FullName",obj.getFullName());
        return db.insert("User",null,contentValues);
    }
    public int update(User obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName",obj.getUserName());
        contentValues.put("Pass",obj.getPass());
        contentValues.put("FullName",obj.getFullName());
        return db.update("User",contentValues,"UserName=?",new String[]{String.valueOf(obj.getUserName())});
    }
    public int delete(String id){
        return db.delete("User","UserName=?",new String[]{String.valueOf(id)});
    }
    @SuppressLint("Range")
    private List<User> getData  (String sql, String...selectionArgs){
        List<User> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            User obj = new User();
            obj.setUserName(c.getString(c.getColumnIndex("UserName")));
            obj.setPass(c.getString(c.getColumnIndex("Pass")));
            obj.setFullName(c.getString(c.getColumnIndex("FullName")));
            list.add(obj);
        }
        c.close();
        return list;
    }
    public List<User> getAll() {
        String sql = "select * from User";
        return getData(sql);
    }

    public User getID(String id) {
        String sql = "select * from User where UserName=?";
        List<User> list = getData(sql, id);
        return list.get(0);
    }
    public int CheckLogin(String id,String password){
        String sql = "select * from User where UserName=? and Pass=?";
        List<User> list=getData(sql,id,password);
        if (list.size()==0){
            return -1;
        }return 1;
    }
}
