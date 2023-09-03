package com.example.shoestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shoestore.R;
import com.example.shoestore.model.GioHang;
import com.example.shoestore.model.SanPhamMoi;
import com.example.shoestore.utils.Utils;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;

public class ChiTietSPActivity extends AppCompatActivity {
    TextView txtTen, txtGia, txtMota;
    Button btnThemGioHang;
    ImageView imgChiTietSP;
    FrameLayout frame_GioHang;
    Spinner spinnerSoLuongSP;
    Toolbar toolbarChiTiet;
    SanPhamMoi sanPhamMoi;
    NotificationBadge badgeSoLuongSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsp);
        initView();
        xemSanPham();
        ActionToolBar();
        initData();
        initConTrol();

    }

    private void initConTrol() {
        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themSanPham();
            }
        });
    }

    private void xemSanPham() {
        frame_GioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void themSanPham() {
        if (Utils.arrGioHang.size() > 0) {
            boolean flag = false;
            int soLuong = Integer.parseInt(spinnerSoLuongSP.getSelectedItem().toString());
            for (int i = 0; i < Utils.arrGioHang.size(); i++) {
                if (Utils.arrGioHang.get(i).getMasanpham() == sanPhamMoi.getMasanphammoi()) {
                    Utils.arrGioHang.get(i).setSoluong(soLuong + Utils.arrGioHang.get(i).getSoluong());
                    long gia = Long.parseLong(sanPhamMoi.getGiasanpham());
                    Utils.arrGioHang.get(i).setGiasanpham(gia);
                    flag = true;
                }
            }
            if (flag == false) {
                long gia = Long.parseLong(sanPhamMoi.getGiasanpham());
                GioHang gioHang = new GioHang();
                gioHang.setMasanpham(sanPhamMoi.getMasanphammoi());
                gioHang.setTensanpham(sanPhamMoi.getTensanpham());
                gioHang.setHinhanh(sanPhamMoi.getHinhanh());
                gioHang.setSoluong(soLuong);
                gioHang.setGiasanpham(gia);
                Utils.arrGioHang.add(gioHang);
            }

        } else {
            int soLuong = Integer.parseInt(spinnerSoLuongSP.getSelectedItem().toString());
            long gia = Long.parseLong(sanPhamMoi.getGiasanpham());
            GioHang gioHang = new GioHang();
            gioHang.setMasanpham(sanPhamMoi.getMasanphammoi());
            gioHang.setTensanpham(sanPhamMoi.getTensanpham());
            gioHang.setHinhanh(sanPhamMoi.getHinhanh());
            gioHang.setSoluong(soLuong);
            gioHang.setGiasanpham(gia);
            Utils.arrGioHang.add(gioHang);
        }
        int totalItem = 0;
        for (int i = 0; i < Utils.arrGioHang.size(); i++) {
            totalItem = totalItem + Utils.arrGioHang.get(i).getSoluong();

        }
        badgeSoLuongSP.setText(String.valueOf(totalItem));

    }

    private void initData() {
        sanPhamMoi = (SanPhamMoi) getIntent().getSerializableExtra("chitiet");
        txtTen.setText(sanPhamMoi.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGia.setText("Giá : " + decimalFormat.format(Double.parseDouble(sanPhamMoi.getGiasanpham())) + " Đ");
        txtMota.setText(sanPhamMoi.getMota());
        Glide.with(getApplicationContext()).load(sanPhamMoi.getHinhanh()).into(imgChiTietSP);
        Integer[] soLuongSP = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayAdapter<Integer> spinerAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, soLuongSP);
        spinnerSoLuongSP.setAdapter(spinerAdapter);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        imgChiTietSP = findViewById(R.id.img_ChiTietSP);
        txtTen = findViewById(R.id.txtTen_ChiTietSP);
        txtGia = findViewById(R.id.txtGia_ChiTietSP);
        txtMota = findViewById(R.id.txtMoTa_ChiTietSP);
        btnThemGioHang = findViewById(R.id.btn_ThemVaoGH);
        spinnerSoLuongSP = findViewById(R.id.spinner_SoLuongSP);
        toolbarChiTiet = findViewById(R.id.toolbarChiTietSP);
        badgeSoLuongSP = findViewById(R.id.badge_SoLuongSP);
        frame_GioHang = findViewById(R.id.img_GioHang);
        if (Utils.arrGioHang != null) {
            int totalItem = 0;
            for (int i = 0; i < Utils.arrGioHang.size(); i++) {
                totalItem = totalItem + Utils.arrGioHang.get(i).getSoluong();

            }
            badgeSoLuongSP.setText(String.valueOf(totalItem));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.arrGioHang != null) {
            int totalItem = 0;
            for (int i = 0; i < Utils.arrGioHang.size(); i++) {
                totalItem = totalItem + Utils.arrGioHang.get(i).getSoluong();

            }
            badgeSoLuongSP.setText(String.valueOf(totalItem));
        }
    }
}