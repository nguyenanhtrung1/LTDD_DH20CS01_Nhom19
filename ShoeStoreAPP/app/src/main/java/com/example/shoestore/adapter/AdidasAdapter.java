package com.example.shoestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoestore.Interface.ItemClickListener;
import com.example.shoestore.R;
import com.example.shoestore.activity.ChiTietSPActivity;
import com.example.shoestore.model.SanPhamMoi;

import java.text.DecimalFormat;
import java.util.List;

public class AdidasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<SanPhamMoi> arrSPAdidas;
    private static final int VIEW_TYPE_DATA = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    public AdidasAdapter(Context context, List<SanPhamMoi> arrSPAdidas) {
        this.context = context;
        this.arrSPAdidas = arrSPAdidas;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_DATA){
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adidas, parent, false);
            return new Myviewholder(item);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewholder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return arrSPAdidas.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_DATA;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof Myviewholder){
            Myviewholder myviewholder = (Myviewholder) holder;
            SanPhamMoi spAdidas = arrSPAdidas.get(position);
            myviewholder.txtTensp.setText(spAdidas.getTensanpham());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            myviewholder.txtGiasp.setText("Giá : " + decimalFormat.format(Double.parseDouble(spAdidas.getGiasanpham())) + " Đ ");
            myviewholder.txtMota.setText(spAdidas.getMota());
            Glide.with(context).load(spAdidas.getHinhanh()).into(myviewholder.imgAdidas);
            myviewholder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int pos, boolean isLongClick) {
                    if(!isLongClick){
                        Intent intent = new Intent(context, ChiTietSPActivity.class);
                        intent.putExtra("chitiet", spAdidas);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }else{
            LoadingViewholder loadingViewholder = (LoadingViewholder) holder;
            loadingViewholder.progressBarLoading.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return arrSPAdidas.size();
    }

    public class LoadingViewholder extends RecyclerView.ViewHolder{
        ProgressBar progressBarLoading;
        public LoadingViewholder(@NonNull View itemView) {
            super(itemView);
            progressBarLoading = itemView.findViewById(R.id.item_progressbarLoading);
        }
    }

    public class Myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTensp, txtGiasp, txtMota;
        ImageView imgAdidas;
        private ItemClickListener itemClickListener;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            txtTensp = itemView.findViewById(R.id.item_TenAdidas);
            txtGiasp = itemView.findViewById(R.id.item_GiaAdidas);
            txtMota = itemView.findViewById(R.id.item_motaAdidas);
            imgAdidas = itemView.findViewById(R.id.item_imgAdidas);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(),false);
        }
    }
}
