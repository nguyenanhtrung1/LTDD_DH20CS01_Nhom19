package com.example.shoestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.shoestore.R;
import com.example.shoestore.adapter.LoaiSpAdapter;
import com.example.shoestore.adapter.SanPhamMoiAdapter;
import com.example.shoestore.model.LoaiSp;
import com.example.shoestore.model.LoaiSpModel;
import com.example.shoestore.model.SanPhamMoi;
import com.example.shoestore.retrofit.APIShoeStore;
import com.example.shoestore.retrofit.RetrofitClient;
import com.example.shoestore.utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewTrangChu;
    NavigationView navigationView;
    ListView listViewTrangChu;
    DrawerLayout drawerLayout;
    List<LoaiSp> arrLoaiSp;
    LoaiSpAdapter loaiSpAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIShoeStore apiShoeStore;
    List<SanPhamMoi> arrSanPhamMoi;
    SanPhamMoiAdapter sanPhamMoiAdapter;
    NotificationBadge badge;
    FrameLayout frameGioHang;
    ImageView imgSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiShoeStore = RetrofitClient.getInstance(Utils.BASE_URL).create(APIShoeStore.class);

        AnhXa();
        ActionBar();

        if(isConnected(this)){
            ActionViewFlipper();
            getLoaiSanPham();
            getSPMoi();
            getEventClick();


        }else{
            Toast.makeText(getApplicationContext(), "Không có internet", Toast.LENGTH_LONG).show();
        }
    }
    private void getEventClick() {
        listViewTrangChu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent trangchu = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(trangchu);
                        break;
                    case 1:
                        Intent giayadidas = new Intent(getApplicationContext(), AdidasActivity.class);
                        startActivity(giayadidas);
                        break;
                    case 2:
                        Intent giaynike = new Intent(getApplicationContext(), NikeActivity.class);
                        startActivity(giaynike);
                        break;
                    case 3:
                        Intent donhang = new Intent(getApplicationContext(), LichSuDonHangActivity.class);
                        startActivity(donhang);
                        break;
                }

            }
        });
        frameGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getSPMoi() {
        compositeDisposable.add(apiShoeStore.getSPMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    sanPhamMoiModel -> {
                        if(sanPhamMoiModel.isSuccess()){
                            arrSanPhamMoi = sanPhamMoiModel.getResult();
                            sanPhamMoiAdapter = new SanPhamMoiAdapter(getApplicationContext(), arrSanPhamMoi);
                            recyclerViewTrangChu.setAdapter(sanPhamMoiAdapter);
                        }

                    },
                        throwable -> {
                        Toast.makeText(getApplicationContext(), "Không thể kết nối được Server", Toast.LENGTH_LONG).show();
                        }
                )
        );
    }

    private void getLoaiSanPham() {
        compositeDisposable.add(apiShoeStore.getLoaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel -> {
                            if(loaiSpModel.isSuccess()){
                                arrLoaiSp =loaiSpModel.getResult();
                                loaiSpAdapter = new LoaiSpAdapter(getApplicationContext(), arrLoaiSp);
                                listViewTrangChu.setAdapter(loaiSpAdapter);
                            }
                        }
                )
            );
    }

    private void ActionViewFlipper() {
        List<String> arrQuanCao = new ArrayList<>();
        arrQuanCao.add("https://intphcm.com/data/upload/poster-giay.jpg");
        arrQuanCao.add("https://tmdt.web5phut.com/wp-content/uploads/2022/03/slider_2.jpg");
        arrQuanCao.add("https://intphcm.com/data/upload/poster-giay-adidas.jpg");
        for (int i = 0; i < arrQuanCao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(arrQuanCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(30000);
        viewFlipper.setAutoStart(true);
        Animation Slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation Slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(Slide_in);
        viewFlipper.setOutAnimation(Slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void AnhXa() {
        imgSearch = findViewById(R.id.img_SearchSP);
        frameGioHang = findViewById(R.id.frame_GioHang);
        badge = findViewById(R.id.badge_SoLuongSP);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbarTrangChu);
        viewFlipper = findViewById(R.id.viewLipper);
        recyclerViewTrangChu = findViewById(R.id.recycleViewTrangChu);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewTrangChu.setLayoutManager(layoutManager);
        recyclerViewTrangChu.setHasFixedSize(true);
        navigationView = findViewById(R.id.navigationView);
        listViewTrangChu = findViewById(R.id.listviewTrangChu);
        arrLoaiSp = new ArrayList<>();
        arrSanPhamMoi = new ArrayList<>();
        if(Utils.arrGioHang == null){
            Utils.arrGioHang = new ArrayList<>();
        }else{
            int totalItem = 0;
            for (int i = 0; i < Utils.arrGioHang.size(); i++) {
                totalItem = totalItem + Utils.arrGioHang.get(i).getSoluong();

            }
            badge.setText(String.valueOf(totalItem));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int totalItem = 0;
        for (int i = 0; i < Utils.arrGioHang.size(); i++) {
            totalItem = totalItem + Utils.arrGioHang.get(i).getSoluong();

        }
        badge.setText(String.valueOf(totalItem));
    }

    private boolean isConnected(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}