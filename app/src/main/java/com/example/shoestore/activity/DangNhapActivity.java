package com.example.shoestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoestore.R;
import com.example.shoestore.retrofit.APIShoeStore;
import com.example.shoestore.retrofit.RetrofitClient;
import com.example.shoestore.utils.Utils;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangNhapActivity extends AppCompatActivity {
    TextView txtDangKi, txtQuenMatKhau;
    EditText edtTaiKhoan, edtMatKhau;
    AppCompatButton btnDangNhap;
    APIShoeStore apiShoeStore;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        initView();
        initControl();
    }

    private void initControl() {
        txtDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKiActivity.class);
                startActivity(intent);

            }
        });
        txtQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, ResetMatKhauActivity.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taiKhoan = edtTaiKhoan.getText().toString().trim();
                String matKhau = edtMatKhau.getText().toString().trim();

                if (TextUtils.isEmpty(taiKhoan)) {
                    Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập Tài Khoản!!!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(matKhau)) {
                    Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập Mật Khẩu!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Paper.book().write("taikhoan",taiKhoan);
                    Paper.book().write("matkhau",matKhau);
                    compositeDisposable.add(apiShoeStore.dangNhap(taiKhoan, matKhau)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userModel -> {
                                        if (userModel.isSuccess()){
                                            Utils.user_current = userModel.getResult().get(0);
                                            Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
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
        Paper.init(this);
        txtDangKi = findViewById(R.id.txtClick_DangKi);
        edtMatKhau = findViewById(R.id.edtMatKhauDN);
        edtTaiKhoan = findViewById(R.id.edtTaiKhoanDN);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        txtQuenMatKhau = findViewById(R.id.txtClick_QuenMatKhau);
        apiShoeStore = RetrofitClient.getInstance(Utils.BASE_URL).create(APIShoeStore.class);

        //Read data
        if(Paper.book().read("taikhoan") != null && Paper.book().read("matkhau") != null){
            edtTaiKhoan.setText(Paper.book().read("taikhoan"));
            edtMatKhau.setText(Paper.book().read("MatKhau"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.user_current.getTaikhoan() != null && Utils.user_current.getMatkhau() != null) {
            edtTaiKhoan.setText(Utils.user_current.getTaikhoan());
            edtMatKhau.setText(Utils.user_current.getMatkhau());
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}