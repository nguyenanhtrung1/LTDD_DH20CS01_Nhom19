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

public class AdidasAdapter extends RecyclerView.Adapter<AdidasAdapter.Myviewholder> {
    Context context;
    List<SanPhamMoi> arrSPAdidas;


    public AdidasAdapter(Context context, List<SanPhamMoi> arrSPAdidas) {
        this.context = context;
        this.arrSPAdidas = arrSPAdidas;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adidas, parent, false);

        return new Myviewholder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        SanPhamMoi spAdidas = arrSPAdidas.get(position);
        holder.txtTensp.setText(spAdidas.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiasp.setText("Giá : " + decimalFormat.format(Double.parseDouble(spAdidas.getGiasanpham())) + " Đ ");

        holder.txtMota.setText(spAdidas.getMota());
        Glide.with(context).load(spAdidas.getHinhanh()).into(holder.imgAdidas);
    }

    @Override
    public int getItemCount() {
        return arrSPAdidas.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView txtTensp, txtGiasp, txtMota;
        ImageView imgAdidas;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            txtTensp = itemView.findViewById(R.id.item_TenAdidas);
            txtGiasp = itemView.findViewById(R.id.item_GiaAdidas);
            txtMota = itemView.findViewById(R.id.item_motaAdidas);
            imgAdidas = itemView.findViewById(R.id.item_imgAdidas);
        }
    }
}
