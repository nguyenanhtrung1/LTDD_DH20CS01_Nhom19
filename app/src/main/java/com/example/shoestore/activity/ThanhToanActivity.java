package com.example.shoestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoestore.R;
import com.example.shoestore.retrofit.APIShoeStore;
import com.example.shoestore.retrofit.RetrofitClient;
import com.example.shoestore.utils.Utils;
import com.google.gson.Gson;

import java.text.DecimalFormat;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThanhToanActivity extends AppCompatActivity {
    Toolbar toolbarDatHang;
    TextView txtTaiKhoanDH,txtSDTDH,txtTongTienDH;
    EditText edtDiaChiDH;
    AppCompatButton btnDatHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIShoeStore apiShoeStore;
    long tongtien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        initView();
        initConTrol();
    }

    private void initConTrol() {
        setSupportActionBar(toolbarDatHang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDatHang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien = getIntent().getLongExtra("tongtien",0);
        txtTongTienDH.setText(decimalFormat.format(tongtien));
        txtTaiKhoanDH.setText(Utils.user_current.getTaikhoan());
        txtSDTDH.setText(Utils.user_current.getSodienthoai());
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String diachi = edtDiaChiDH.getText().toString().trim();
                if(TextUtils.isEmpty(diachi)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập địa chỉ giao hàng!!!", Toast.LENGTH_SHORT).show();
                }else{
                    String soDienThoai = Utils.user_current.getSodienthoai();
                    int idUser = Utils.user_current.getId();

                    int soluong = 0;
                    for (int i = 0; i < Utils.arrGioHang.size(); i++) {
                        soluong = soluong + Utils.arrGioHang.get(i).getSoluong();

                    }
                    //Log.d("test",new Gson().toJson(Utils.arrGioHang));
                    compositeDisposable.add(apiShoeStore.taoDonHang(idUser,soDienThoai,diachi,soluong,String.valueOf(tongtien),new Gson().toJson(Utils.arrGioHang))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                 userModel -> {
                                     Toast.makeText(getApplicationContext(), "Thanh Toán Thành Công", Toast.LENGTH_SHORT).show();
                                     Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                     startActivity(intent);
                                     finish();
                                 },
                                    throwable -> {
                                        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                            )
                    );
                }
            }
        });
    }
    private void initView() {
        apiShoeStore = RetrofitClient.getInstance(Utils.BASE_URL).create(APIShoeStore.class);
        toolbarDatHang = findViewById(R.id.toolbarThanhToan);
        txtTongTienDH = findViewById(R.id.txtTongTienDatHang);
        txtTaiKhoanDH = findViewById(R.id.txtTaiKhoanDatHang);
        txtSDTDH = findViewById(R.id.txtSDTDatHang);
        btnDatHang = findViewById(R.id.btnDatHang);
        edtDiaChiDH = findViewById(R.id.edtDiaChiGiaoHang);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}