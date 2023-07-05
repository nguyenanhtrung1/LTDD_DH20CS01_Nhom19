package com.example.shoestore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
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
    AdidasAdapter AdidasAdapter;
    List<SanPhamMoi> arrAdidas;
    LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler();
    boolean isLoading = false;
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
        addEventLoad();
    }

    private void addEventLoad() {
        recyclerViewAdidas.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading == false) {
                    if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == arrAdidas.size() - 1) {
                        isLoading = true;
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                arrAdidas.add(null);
                AdidasAdapter.notifyItemInserted(arrAdidas.size() - 1);

            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                arrAdidas.remove(arrAdidas.size() - 1);
                AdidasAdapter.notifyItemRemoved(arrAdidas.size());
                getData();
                AdidasAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

    private void getData() {
        compositeDisposable.add(apiShoeStore.getAdidas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()) {
                                if (AdidasAdapter == null) {
                                    arrAdidas = sanPhamMoiModel.getResult();
                                    AdidasAdapter = new AdidasAdapter(getApplicationContext(), arrAdidas);
                                    recyclerViewAdidas.setAdapter(AdidasAdapter);
                                } else {
                                    int viTri = arrAdidas.size() - 1;
                                    int soLuongThem = sanPhamMoiModel.getResult().size();
                                    for (int i = 0; i < soLuongThem; i++) {
                                        arrAdidas.add(sanPhamMoiModel.getResult().get(i));
                                    }
                                    AdidasAdapter.notifyItemRangeInserted(viTri, soLuongThem);
                                }
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
        linearLayoutManager = new LinearLayoutManager(this, linearLayoutManager.VERTICAL, false);
        recyclerViewAdidas.setHasFixedSize(true);
        recyclerViewAdidas.setLayoutManager(linearLayoutManager);
    }
}