package com.example.shoestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoestore.R;
import com.example.shoestore.adapter.DonHangAdapter;
import com.example.shoestore.adapter.LoaiSpAdapter;
import com.example.shoestore.retrofit.APIShoeStore;
import com.example.shoestore.retrofit.RetrofitClient;
import com.example.shoestore.utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LichSuDonHangActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIShoeStore apiShoeStore;
    RecyclerView recyclerViewLichSuDH;
    Toolbar toolbarLichSuDH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_don_hang);

        initView();
        actionToolBar();
        xemDonHang();

    }

    private void xemDonHang() {
        compositeDisposable.add(apiShoeStore.xemDonHang(Utils.user_current.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        donHangModel -> {
                            DonHangAdapter donHangAdapter = new DonHangAdapter(getApplicationContext(),donHangModel.getResult());
                            recyclerViewLichSuDH.setAdapter(donHangAdapter);

                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                )
        );
    }

    private void actionToolBar() {
        setSupportActionBar(toolbarLichSuDH);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLichSuDH.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarLichSuDH.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        apiShoeStore = RetrofitClient.getInstance(Utils.BASE_URL).create(APIShoeStore.class);
        toolbarLichSuDH = findViewById(R.id.toolbarChiTietDH);
        recyclerViewLichSuDH = findViewById(R.id.recycleViewLichSuDH);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLichSuDH.setLayoutManager(layoutManager);
    }


}
