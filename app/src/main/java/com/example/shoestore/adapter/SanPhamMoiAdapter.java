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
import com.example.shoestore.model.SanPhamMoi;

import java.text.DecimalFormat;
import java.util.List;

public class SanPhamMoiAdapter extends RecyclerView.Adapter<SanPhamMoiAdapter.MyViewHolder> {
    Context context;
    List<SanPhamMoi> arraySPMoi;

    public SanPhamMoiAdapter(Context context, List<SanPhamMoi> arraySPMoi) {
        this.context = context;
        this.arraySPMoi = arraySPMoi;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanphammoi, parent, false);

        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SanPhamMoi sanPhamMoi = arraySPMoi.get(position);
        holder.txtTenSpmoi.setText(sanPhamMoi.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiaSpmoi.setText("Giá : " + decimalFormat.format(Double.parseDouble(sanPhamMoi.getGiasanpham())) + "Đ");
        Glide.with(context).load(sanPhamMoi.getHinhanh()).into(holder.imgSanPhamMoi);
    }

    @Override
    public int getItemCount() {
        return arraySPMoi.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView txtGiaSpmoi, txtTenSpmoi;
        ImageView imgSanPhamMoi;
        public MyViewHolder(@NonNull View itemView){
            super((itemView));
            txtGiaSpmoi = itemView.findViewById(R.id.item_giaspmoi);
            txtTenSpmoi = itemView.findViewById(R.id.item_tenspmoi);
            imgSanPhamMoi = itemView.findViewById(R.id.item_imgspmoi);
        }

    }
}
