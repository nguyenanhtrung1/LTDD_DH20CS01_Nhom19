package com.example.shoestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoestore.R;
import com.example.shoestore.model.DonHang;

import java.util.List;

import io.paperdb.Paper;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.MyViewHolder> {
    public RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Context context;
    List<DonHang> arrDonHang;

    public DonHangAdapter(Context context, List<DonHang> arrDonHang) {
        this.context = context;
        this.arrDonHang = arrDonHang;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DonHang donHang = arrDonHang.get(position);
        holder.txtDonHang.setText("Đơn Hàng : " + donHang.getMadonhang());
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.recycleChiTietDH.getContext(),
                LinearLayoutManager.VERTICAL,
                false );
        layoutManager.setInitialPrefetchItemCount(donHang.getItem().size());

        //Adapter Chi Tiet
        ChiTietDhAdapter chiTietDhAdapter = new ChiTietDhAdapter(donHang.getItem(), context);
        holder.recycleChiTietDH.setLayoutManager(layoutManager);
        holder.recycleChiTietDH.setAdapter(chiTietDhAdapter);
        holder.recycleChiTietDH.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return arrDonHang.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView txtDonHang;
        RecyclerView recycleChiTietDH;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDonHang = itemView.findViewById(R.id.txtMaDonHang);
            recycleChiTietDH = itemView.findViewById(R.id.recycleViewChiTietDH);
        }
    }
}
