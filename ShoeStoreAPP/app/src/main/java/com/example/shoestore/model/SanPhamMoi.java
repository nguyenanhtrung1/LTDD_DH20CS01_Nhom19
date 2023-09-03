package com.example.shoestore.model;

import java.io.Serializable;

public class SanPhamMoi implements Serializable{
    int masanphammoi;
    String tensanpham;
    String giasanpham;
    String hinhanh;
    String mota;
    int loaisanpham;

    public int getMasanphammoi() {
        return masanphammoi;
    }

    public void setMasanphammoi(int masanphammoi) {
        this.masanphammoi = masanphammoi;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(String giasanpham) {
        this.giasanpham = giasanpham;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getLoaisanpham() {
        return loaisanpham;
    }

    public void setLoaisanpham(int loaisanpham) {
        this.loaisanpham = loaisanpham;
    }
}
