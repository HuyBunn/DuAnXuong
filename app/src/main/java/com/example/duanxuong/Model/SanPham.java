package com.example.duanxuong.Model;

public class SanPham {
    private int maSP,maLoai,gia,soLuong;
    private String tenSP,moTa;

    public SanPham(int maSP, int maLoai, int gia, int soLuong, String tenSP, String moTa) {
        this.maSP = maSP;
        this.maLoai = maLoai;
        this.gia = gia;
        this.soLuong = soLuong;
        this.tenSP = tenSP;
        this.moTa = moTa;
    }

    public SanPham() {
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
