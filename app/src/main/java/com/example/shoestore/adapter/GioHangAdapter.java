package com.example.shoestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoestore.R;
import com.example.shoestore.model.GioHang;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHolder> {
    Context context;
    List<GioHang> arrGioHang;

    public GioHangAdapter(Context context, List<GioHang> arrGioHang) {
        this.context = context;
        this.arrGioHang = arrGioHang;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GioHang gioHang = arrGioHang.get(position);
        holder.txtTenSp.setText(gioHang.getTensanpham());
        holder.txtSoLuong.setText(gioHang.getSoluong()+" ");
        Glide.with(context).load(gioHang.getHinhanh()).into(holder.imgItemGioHang);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiaSp.setText( decimalFormat.format((gioHang.getGiasanpham())) + "Đ");
        long gia = gioHang.getSoluong() * gioHang.getGiasanpham();
        holder.txtTongGia.setText(decimalFormat.format(gia));

    }

    @Override
    public int getItemCount() {
        return arrGioHang.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        ImageView imgItemGioHang;
        TextView txtTenSp, txtGiaSp, txtSoLuong, txtTongGia;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItemGioHang = itemView.findViewById(R.id.img_ItemGioHang);
            txtTenSp = itemView.findViewById(R.id.txtTen_itemGioHang);
            txtGiaSp= itemView.findViewById(R.id.txtGia_itemGioHang);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong_GioHang);
            txtTongGia = itemView.findViewById(R.id.txtGia_SPGioHang);
        }

    }
}
