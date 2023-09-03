package com.example.shoestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoestore.R;
import com.example.shoestore.adapter.AdidasAdapter;
import com.example.shoestore.model.SanPhamMoi;
import com.example.shoestore.retrofit.APIShoeStore;
import com.example.shoestore.retrofit.RetrofitClient;
import com.example.shoestore.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbarSearch;
    RecyclerView recyclerViewSearch;
    AdidasAdapter adidasAdapter;
    List<SanPhamMoi> arrSanPhamMoi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIShoeStore apiShoeStore;
    EditText edtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        ActionBar();
        initControl();
    }

    private void initControl() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() ==  0){
                    arrSanPhamMoi.clear();
                    adidasAdapter = new AdidasAdapter(getApplicationContext(),arrSanPhamMoi);
                    recyclerViewSearch.setAdapter(adidasAdapter);
                }else{
                    getSPSearch(s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getSPSearch(String search) {
        arrSanPhamMoi.clear();
        compositeDisposable.add(apiShoeStore.timKiemSP(search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    sanPhamMoiModel -> {
                        if(sanPhamMoiModel.isSuccess()){
                            arrSanPhamMoi = sanPhamMoiModel.getResult();
                            adidasAdapter = new AdidasAdapter(getApplicationContext(),arrSanPhamMoi);
                            recyclerViewSearch.setAdapter(adidasAdapter);

                        }
                    },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                ));
    }

    private void initView() {
        arrSanPhamMoi = new ArrayList<>();
        edtSearch = findViewById(R.id.edtSearchSP);
        toolbarSearch = findViewById(R.id.toolbarSearch);
        recyclerViewSearch = findViewById(R.id.recycleViewSearch);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewSearch.setHasFixedSize(true);
        recyclerViewSearch.setLayoutManager(layoutManager);
        apiShoeStore = RetrofitClient.getInstance(Utils.BASE_URL).create(APIShoeStore.class);
    }

    private void ActionBar() {
        setSupportActionBar(toolbarSearch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSearch.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();

    }
}