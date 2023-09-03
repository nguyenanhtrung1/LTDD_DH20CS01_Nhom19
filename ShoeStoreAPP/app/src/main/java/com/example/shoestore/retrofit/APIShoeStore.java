package com.example.shoestore.retrofit;

import com.example.shoestore.model.DonHangModel;
import com.example.shoestore.model.LoaiSpModel;
import com.example.shoestore.model.SanPhamMoiModel;
import com.example.shoestore.model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIShoeStore {
    @GET("getloaisp.php")
    Observable<LoaiSpModel> getLoaiSp();

    @GET("getsanphammoi.php")
    Observable<SanPhamMoiModel> getSPMoi();

    @GET("getadidas.php")
    Observable<SanPhamMoiModel> getAdidas();

    @GET("getnike.php")
    Observable<SanPhamMoiModel> getNike();

    @POST("getchichietsp.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> getSanPham(
      @Field("page") int page,
      @Field("loai") int loai
    );

    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> dangKi(
            @Field("taikhoan") String taikhoan,
            @Field("matkhau") String matkhau,
            @Field("tennguoidung") String tennguoidung,
            @Field("sodienthoai") String sodienthoai
    );
    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangNhap(
            @Field("taikhoan") String taikhoan,
            @Field("matkhau") String matkhau
    );

    @POST("donhang.php")
    @FormUrlEncoded
    Observable<UserModel> taoDonHang(
            @Field("iduser") int iduser,
            @Field("sodienthoai") String sodienthoai,
            @Field("diachi") String diachi,
            @Field("soluong") int soluong,
            @Field("tongtien") String tongtien,
            @Field("chitiet") String chitiet
    );

    @POST("xemdonhang.php")
    @FormUrlEncoded
    Observable<DonHangModel> xemDonHang(
            @Field("iduser") int id
    );
    @POST("searchsp.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> timKiemSP(
            @Field("search") String search
    );


    @GET("xemdonhang.php")
    Observable<DonHangModel> xemdonhang();
}
