package com.example.shoestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoestore.R;
import com.example.shoestore.retrofit.APIShoeStore;
import com.example.shoestore.retrofit.RetrofitClient;
import com.example.shoestore.utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangKiActivity extends AppCompatActivity {
    EditText edtTaiKhoan, edtMatKhau, edtReMatKhau, edtSoDienThoai,edtTenNguoiDung;
    AppCompatButton btnDangKi;
    APIShoeStore apiShoeStore;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        initView();
        initControl();
    }
    private void initControl() {
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKi();
            }
        });
    }
    private void DangKi() {
        String taiKhoan = edtTaiKhoan.getText().toString().trim();
        String tenNguoiDung = edtTenNguoiDung.getText().toString().trim();
        String matKhau = edtMatKhau.getText().toString().trim();
        String reMatKhau = edtReMatKhau.getText().toString().trim();
        String sodienthoai = edtSoDienThoai.getText().toString().trim();

        if(TextUtils.isEmpty(taiKhoan)){
            Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập Tài Khoản!!!", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(tenNguoiDung)){
            Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập Tên Người Dùng!!!", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(matKhau)){
            Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập Mật Khẩu!!!", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(reMatKhau)){
            Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập Lại Mật Khẩu!!!", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(sodienthoai)){
            Toast.makeText(getApplicationContext(), "Bạn chưa điền số điện thoại!!!", Toast.LENGTH_SHORT).show();
        }else{
            if(matKhau.equals(reMatKhau)){
                compositeDisposable.add(apiShoeStore.dangKi(taiKhoan, matKhau, tenNguoiDung, sodienthoai)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            userModel -> {
                                if(userModel.isSuccess()){
                                    Utils.user_current.setTaikhoan(taiKhoan);
                                    Utils.user_current.setMatkhau(matKhau);
                                    Intent intent = new Intent(DangKiActivity.this, DangNhapActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Success!!", Toast.LENGTH_SHORT).show();
                                }
                            },
                            throwable -> {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        )
                );
            }else{
                Toast.makeText(getApplicationContext(), "Mật khẩu điền lại chưa khớp!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initView() {
        apiShoeStore = RetrofitClient.getInstance(Utils.BASE_URL).create(APIShoeStore.class);
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtReMatKhau = findViewById(R.id.edtReMatKhau);
        btnDangKi = findViewById(R.id.btnDangKi);
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai);
        edtTenNguoiDung = findViewById(R.id.edtTenNguoiDung);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}