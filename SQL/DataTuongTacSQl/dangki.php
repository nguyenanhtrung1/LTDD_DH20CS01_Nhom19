<?php
include "connect.php";

$taikhoan = $_POST['taikhoan'];
$matkhau = $_POST['matkhau'];
$tennguoidung = $_POST['tennguoidung'];
$sodienthoai = $_POST['sodienthoai'];

//Kiểm tra đã tồn tại tài khoản chưa
$query = 'SELECT * FROM `user` WHERE `taikhoan` = "'.$taikhoan.'"';
$data = mysqli_query($conn, $query);
$numrow = mysqli_num_rows($data);
if($numrow > 0){
	$arr = [
		'success' => false,
		'message' => "Tai khoan da ton tai"
	];
}
else{
	$query = 'INSERT INTO `user`( `taikhoan`, `matkhau`, `tennguoidung`, `sodienthoai`) VALUES ("'.$taikhoan.'","'.$matkhau.'","'.$tennguoidung.'","'.$sodienthoai.'")';
	$data = mysqli_query($conn, $query);

	if($data == true){

		$arr = [
			'success' => true,
			'message' => "thanh cong"
		];
	}else{
		$arr = [
			'success' => false,
			'message' => "khong thanh cong"
		];
	}
}

print_r(json_encode($arr));

?>