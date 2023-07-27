package com.example.shoestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoestore.R;
import com.example.shoestore.adapter.GioHangAdapter;
import com.example.shoestore.utils.Utils;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    ImageView imgGioHangTrong;
    static TextView txtTongTien;
    Toolbar toolbarGioHang;
    RecyclerView recyclerViewGioHang;
    Button btnMuaHang;
    GioHangAdapter gioHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        initView();
        initControl();
        totalMoney();
    }

    public static void totalMoney() {
        long tongtiensp = 0;
        for(int i = 0; i < Utils.arrGioHang.size(); i++){
            tongtiensp = tongtiensp +  (Utils.arrGioHang.get(i).getGiasanpham()* Utils.arrGioHang.get(i).getSoluong());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongtiensp));
    }

    private void initControl() {
        setSupportActionBar(toolbarGioHang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGioHang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerViewGioHang.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewGioHang.setLayoutManager(layoutManager);
        if(Utils.arrGioHang.size() == 0){
            imgGioHangTrong.setVisibility(View.VISIBLE);
        }else{
            gioHangAdapter = new GioHangAdapter( getApplicationContext(), Utils.arrGioHang);
            recyclerViewGioHang.setAdapter(gioHangAdapter);
        }
    }
    private void initView() {
        txtTongTien = findViewById(R.id.txtTongTien);
        imgGioHangTrong = findViewById(R.id.img_GioHangRong);
        toolbarGioHang = findViewById(R.id.toolbarGioHang);
        recyclerViewGioHang = findViewById(R.id.recycleViewGioHang);
        btnMuaHang = findViewById(R.id.btnMuaHang);
    }
}