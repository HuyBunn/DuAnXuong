package com.example.duanxuong.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "Xuong";
    static final int dbVersion = 1;

    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //bang loai
        String tableLoai = "create table Loai(" +
                "MaLoai integer primary key autoincrement," +
                "TenLoai text not null)";
        sqLiteDatabase.execSQL(tableLoai);
        String data_l = "insert into Loai(TenLoai) values('Fake')," +
                "('Real')";
        sqLiteDatabase.execSQL(data_l);
        //bang sanpham
        String tableSanPham = "create table SanPham(" +
                "MaSP integer primary key autoincrement," +
                "MaLoai integer references Loai(MaLoai)," +
                "TenSP text not null," +
                "Gia integer not null," +
                "SoLuong integer not null," +
                "MoTa text not null)";
        sqLiteDatabase.execSQL(tableSanPham);
                String data_sp = "insert into SanPham(MaLoai,TenSP,Gia,SoLuong,MoTa) values(1,'Iphone 15',30000000,10,'Có CH Play')," +
                "(2,'Iphone 14',20000000,20,'Có App Store')";
        sqLiteDatabase.execSQL(data_sp);
        //bang User
        String tableUser = "create table User(" +
                "UserName text primary key not null," +
                "Pass text not null," +
                "FullName text not null)";
        sqLiteDatabase.execSQL(tableUser);
        String data_u = "insert into User(UserName,Pass,FullName) values('huy','huy','Phạm Huy')," +
                "('thanh','thanh','Phạm Thành')";
        sqLiteDatabase.execSQL(data_u);
        //bang HoaDon
        String tableHoaDon = "create table HoaDon(" +
                "MaHoaDon integer primary key autoincrement," +
                "MaUser text references User(UserName)," +
                "LoaiHD integer not null," +
                "Ngay date not null)";
        sqLiteDatabase.execSQL(tableHoaDon);
        String data_hd = "insert into HoaDon(MaUser,LoaiHD,Ngay) values('huy',1,'2023-11-23')," +
                "('thanh',0,'2023-11-22')";
        sqLiteDatabase.execSQL(data_hd);
        //bang CThoaDon
        String tableCTHoaDon = "create table CTHoaDon(" +
                "MaCTHD integer primary key autoincrement," +
                "MaHD integer references HoaDon(MaHD)," +
                "MaSP integer references SanPham(MaSP)," +
                "SoLuong integer not null," +
                "DonGia integer not null)";
        sqLiteDatabase.execSQL(tableCTHoaDon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Loai");
        sqLiteDatabase.execSQL("drop table if exists SanPham");
        sqLiteDatabase.execSQL("drop table if exists User");
        sqLiteDatabase.execSQL("drop table if exists CTHoaDon");
        sqLiteDatabase.execSQL("drop table if exists HoaDon");
    }
}
