package com.example.shoestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewTrangChu;
    NavigationView navigationView;
    ListView listViewTrangChu;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        ActionBar();
        ActionViewFlipper();
    }
    private void ActionViewFlipper() {
        List<String> arrQuanCao = new ArrayList<>();
        arrQuanCao.add("https://intphcm.com/data/upload/poster-giay.jpg");
        arrQuanCao.add("https://tmdt.web5phut.com/wp-content/uploads/2022/03/slider_2.jpg");
        arrQuanCao.add("https://intphcm.com/data/upload/poster-giay-adidas.jpg");
        for(int i = 0 ; i < arrQuanCao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(arrQuanCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(2000);
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
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbarTrangChu);
        viewFlipper = findViewById(R.id.viewLipper);
        recyclerViewTrangChu = findViewById(R.id.recycleViewTrangChu);
        navigationView = findViewById(R.id.navigationView);
        listViewTrangChu = findViewById(R.id.listviewTrangChu);
    }
}