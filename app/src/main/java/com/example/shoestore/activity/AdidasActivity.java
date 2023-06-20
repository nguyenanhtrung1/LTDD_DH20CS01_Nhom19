package com.example.shoestore.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shoestore.R;
import com.example.shoestore.adapter.AdidasAdapter;
import com.example.shoestore.adapter.SanPhamMoiAdapter;
import com.example.shoestore.model.SanPhamMoi;
import com.example.shoestore.retrofit.APIShoeStore;
import com.example.shoestore.retrofit.RetrofitClient;
import com.example.shoestore.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AdidasActivity extends AppCompatActivity {
    Toolbar toolbarAdidas;
    RecyclerView recyclerViewAdidas;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIShoeStore apiShoeStore;
    AdidasAdapter adapter;
    List<SanPhamMoi> arrAdidas;
    int page = 1;
    int loai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adidas);
        apiShoeStore = RetrofitClient.getInstance(Utils.BASE_URL).create(APIShoeStore.class);
//        loai = getIntent().getIntExtra("loai",1);
        AnhXa();
        ActionToolBar();
        getData();
    }

    private void getData() {
        compositeDisposable.add(apiShoeStore.getAdidas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    sanPhamMoiModel -> {
                        if(sanPhamMoiModel.isSuccess()){
                            arrAdidas = sanPhamMoiModel.getResult();
                            adapter = new AdidasAdapter(getApplicationContext(), arrAdidas);
                            recyclerViewAdidas.setAdapter(adapter);
                        }
                    },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không kết nối được server", Toast.LENGTH_LONG).show();
                        }
                ));

    }
    private void ActionToolBar() {
        setSupportActionBar(toolbarAdidas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAdidas.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        arrAdidas = new ArrayList<>();
        toolbarAdidas = findViewById(R.id.toolbarAdidas);
        recyclerViewAdidas = findViewById(R.id.recycleViewAdidas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewAdidas.setHasFixedSize(true);
        recyclerViewAdidas.setLayoutManager(layoutManager);
    }
}