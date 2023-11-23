package com.example.duanxuong.Model;

import java.util.Date;

public class HoaDon {
    private int maHoaDon,loaiHoaDon;
    private String maUser;
    private Date ngay;

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public void setLoaiHoaDon(int loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }

    public String getMaUser() {
        return maUser;
    }

    public void setMaUser(String maUser) {
        this.maUser = maUser;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int loaiHoaDon, String maUser, Date ngay) {
        this.maHoaDon = maHoaDon;
        this.loaiHoaDon = loaiHoaDon;
        this.maUser = maUser;
        this.ngay = ngay;
    }
}
