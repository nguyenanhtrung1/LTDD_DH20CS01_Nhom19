<?php
include "connect.php";
$iduser = $_POST['iduser'];
$sodienthoai = $_POST['sodienthoai'];
$tongtien = $_POST['tongtien'];
$diachi = $_POST['diachi'];
$soluong = $_POST['soluong'];
$chitiet = $_POST['chitiet'];


$query = 'INSERT INTO `donhang`( `iduser`, `sodienthoai`, `diachi`, `soluong`, `tongtien`) VALUES 
('.$iduser.',"'.$sodienthoai.'","'.$diachi.'",'.$soluong.',"'.$tongtien.'")';

$data = mysqli_query($conn, $query);

if ($data == true) {
	$query = 'SELECT madonhang FROM  `donhang` WHERE `iduser`='.$iduser.' ORDER BY madonhang DESC LIMIT 1';
	$data = mysqli_query($conn, $query);
	
	while($row = mysqli_fetch_assoc($data)){
		$madonhang = ($row);
	}
	if(!empty($madonhang)){
		//da co don hang
		$chitiet = json_decode($chitiet,true);
		foreach ($chitiet as $key => $value) {
			$querychitiet = 'INSERT INTO `chitietdonhang`(`machitietdonhang`, `masanpham`, `soluong`, `gia`) VALUES ('.$madonhang["madonhang"].','.$value["masanpham"].','.$value["soluong"].',"'.$value["giasanpham"].'")';

			$data = mysqli_query($conn, $querychitiet);
		}
		if($data == true){
			$arr = [
			'success' => true,
			'message' => "thanh cong"
			];
		}
		else{
			$arr = [
			'success' => false,
			'message' => "khong thanh cong"
			];
		}
		print_r(json_encode($arr));
	}	
			
}
else
	{
		$arr = [
			'success' => false,
			'message' => "khong thanh cong"
		];
		print_r(json_encode($arr));
	}
?>