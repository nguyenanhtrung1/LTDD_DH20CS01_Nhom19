package com.example.shoestore.adapter;

import static com.example.shoestore.activity.GioHangActivity.totalMoney;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoestore.Interface.ImageClickListener;
import com.example.shoestore.R;
import com.example.shoestore.activity.GioHangActivity;
import com.example.shoestore.model.GioHang;
import com.example.shoestore.utils.Utils;

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
        holder.checkBoxSP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Utils.arrMuaHang.add(gioHang);
                    totalMoney();
                }else {
                    for (int i = 0; i < Utils.arrMuaHang.size(); i++){
                        if(Utils.arrMuaHang.get(i).getMasanpham() == gioHang.getMasanpham()){
                            Utils.arrMuaHang.remove(i);
                            totalMoney();
                        }
                    }
                }
            }
        });
        holder.setImageClickListener(new ImageClickListener() {
            @Override
            public void onImageClickListener(View view, int pos, int giatri) {
                if(giatri == 1){
                     if(arrGioHang.get(pos).getSoluong() > 1){
                         int soLuongMoi = arrGioHang.get(pos).getSoluong()-1;
                         arrGioHang.get(pos).setSoluong(soLuongMoi);

                         holder.txtSoLuong.setText(arrGioHang.get(pos).getSoluong()+" ");
                         long gia = arrGioHang.get(pos).getSoluong() * arrGioHang.get(pos).getGiasanpham();
                         holder.txtTongGia.setText(decimalFormat.format(gia));

                     }else if (arrGioHang.get(pos).getSoluong() == 1){
                         AlertDialog.Builder AlertXoaSP = new AlertDialog.Builder(view.getRootView().getContext());
                         AlertXoaSP.setTitle("Thông báo !!");
                         AlertXoaSP.setMessage("Bạn có muốn xoá sản phẩm nay khỏi giỏ hàng? ");
                         AlertXoaSP.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 Utils.arrGioHang.remove(pos);
                                 notifyDataSetChanged();
                                 totalMoney();
                             }
                         });
                         AlertXoaSP.setNegativeButton("Huỷ!!", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 dialog.dismiss();
                             }
                         });
                         AlertXoaSP.show();

                     }
                }
                else if(giatri == 2){
                    if(arrGioHang.get(pos).getSoluong() < 11){
                        int soLuongMoi = arrGioHang.get(pos).getSoluong()+1;
                        arrGioHang.get(pos).setSoluong(soLuongMoi);
                    }
                    holder.txtSoLuong.setText(arrGioHang.get(pos).getSoluong()+" ");
                    long gia = arrGioHang.get(pos).getSoluong() * arrGioHang.get(pos).getGiasanpham();
                    holder.txtTongGia.setText(decimalFormat.format(gia));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrGioHang.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgItemGioHang,imgTruSanPham,imgCongSanPham;
        TextView txtTenSp, txtGiaSp, txtSoLuong, txtTongGia;
        ImageClickListener imageClickListener;
        CheckBox checkBoxSP;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItemGioHang = itemView.findViewById(R.id.img_ItemGioHang);
            txtTenSp = itemView.findViewById(R.id.txtTen_itemGioHang);
            txtGiaSp= itemView.findViewById(R.id.txtGia_itemGioHang);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong_GioHang);
            txtTongGia = itemView.findViewById(R.id.txtGia_SPGioHang);
            imgCongSanPham = itemView.findViewById(R.id.img_CongSPGioHang);
            imgTruSanPham = itemView.findViewById(R.id.img_TruSPGioHang);
            checkBoxSP = itemView.findViewById(R.id.checkboxSP);

            imgTruSanPham.setOnClickListener(this);
            imgCongSanPham.setOnClickListener(this);

        }

        public void setImageClickListener(ImageClickListener imageClickListener) {
            this.imageClickListener = imageClickListener;
        }

        @Override
        public void onClick(View v) {
            if(v == imgTruSanPham){
                imageClickListener.onImageClickListener(v, getAdapterPosition(), 1);
                totalMoney();
            }
            else if(v == imgCongSanPham){
                imageClickListener.onImageClickListener(v,getAdapterPosition(), 2);
                totalMoney();
            }
        }
    }
}
