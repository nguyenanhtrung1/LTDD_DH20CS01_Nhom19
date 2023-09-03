-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 29, 2023 lúc 02:40 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `datashoestore`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `machitietdonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `gia` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`machitietdonhang`, `masanpham`, `soluong`, `gia`) VALUES
(45, 23, 1, '1690000'),
(45, 24, 1, '4109000'),
(46, 23, 1, '1690000'),
(46, 24, 1, '4109000'),
(47, 23, 1, '1690000'),
(47, 24, 1, '4109000'),
(48, 21, 1, '1590000'),
(48, 22, 1, '1690000'),
(48, 19, 1, '1490000'),
(48, 13, 1, '1390000'),
(49, 21, 1, '1590000'),
(49, 4, 3, '2090000'),
(49, 6, 1, '1690000'),
(50, 22, 1, '1690000'),
(50, 19, 1, '1490000'),
(50, 16, 1, '2090000'),
(51, 23, 3, '1690000'),
(51, 24, 1, '4109000'),
(52, 23, 2, '1690000'),
(52, 21, 8, '1590000'),
(53, 14, 1, '1590000'),
(54, 23, 1, '1690000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `madonhang` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `sodienthoai` varchar(11) NOT NULL,
  `diachi` text NOT NULL,
  `soluong` int(11) NOT NULL,
  `tongtien` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`madonhang`, `iduser`, `sodienthoai`, `diachi`, `soluong`, `tongtien`) VALUES
(45, 9, '03525861101', '464 Nguyễn Văn Công', 2, '5799000'),
(46, 9, '03525861101', '194 Nguyễn Lương Bằng', 2, '5799000'),
(47, 9, '03525861101', 'a', 2, '5799000'),
(48, 9, '03525861101', 'a', 4, '6160000'),
(49, 9, '03525861101', 'a', 5, '9550000'),
(50, 12, '0844027271', '465 nguyễn văn công', 3, '5270000'),
(51, 9, '03525861101', 'nguyen van cong', 4, '9179000'),
(52, 14, '01238417927', 'nguyễn kiệm', 10, '16100000'),
(53, 14, '01238417927', 'gò vấp', 1, '1590000'),
(54, 7, '0898357214', '465 Nguyễn Văn Công', 1, '1690000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(100) NOT NULL,
  `hinhanh` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`masanpham`, `tensanpham`, `hinhanh`) VALUES
(1, 'Trang Chủ', 'https://ngochieu.name.vn/img/home.png'),
(2, 'Giày Adidas', 'https://myshoes.vn/image/data/thuong/logo-adidas.jpg'),
(3, 'Giày Nike', 'https://runningshoes.vn/wp-content/uploads/2021/05/Nike-just-do-it.jpeg'),
(4, 'Thông tin', 'https://ngochieu.name.vn/img/info.png'),
(5, 'Liên hệ', 'https://ngochieu.name.vn/img/contact.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanphammoi`
--

CREATE TABLE `sanphammoi` (
  `masanphammoi` int(11) NOT NULL,
  `tensanpham` varchar(255) NOT NULL,
  `giasanpham` varchar(100) NOT NULL,
  `hinhanh` text NOT NULL,
  `mota` text NOT NULL,
  `loaisanpham` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanphammoi`
--

INSERT INTO `sanphammoi` (`masanphammoi`, `tensanpham`, `giasanpham`, `hinhanh`, `mota`, `loaisanpham`) VALUES
(1, 'GIÀY ADIDAS VULC RAID3R NAM - ĐEN TRẮNG', '1390000', 'https://myshoes.vn/image/cache/catalog/2023/adidas/ad6/giay-adidas-vulc-raid3r-nam-den-03-800x800.jpg', 'Giày Adidas Vulc Raid3r có thiết kế cổ điển dạng slip on thuận tiện cho việc đi lại, chất liệu cao cấp, có thể sử dụng phù hợp trong mọi hoàn cảnh.\r\n\r\nChất liệu sản xuất mẫu giày Adidas Vulc Raid3r đều sử dụng vật liệu bền vững thân thiện với môi trường.', 1),
(2, 'GIÀY ADIDAS GALAXY STAR NAM - ĐEN TRẮNG', '1590000', 'https://myshoes.vn/image/cache/catalog/2023/adidas/ad6/giay-adidas-galaxy-star-nam-den-trang-01-800x800.jpg', 'Giày adidas Galaxy Star có thiết kế thể thao khỏe khoắn, đây là mẫu giày đa dụng cho mọi hoạt động hàng ngày. Adidas Galaxy Star có nhiều cải tiến so với adidas Galaxy 6 giúp đôi giày ngày càng hoàn hảo.\r\n\r\nCông nghệ đế CloudFoam của Adidas chưa bao giờ làm Fan hâm mộ của họ thất vọng. Với cảm giác trải nghiệm giống như đi trên \'\'Mây\'\' đấy là những gì được người dùng chia sẻ lại. Form dáng thiết kế trẻ trung, khỏe khoắn nên đây sẽ là mẫu giày không thể thiếu cho những hoạt động vui chơi, thể thao.', 1),
(3, 'GIÀY NIKE AIR MAX SC NAM - TRẮNG XANH LÁ', '2190000', 'https://myshoes.vn/image/cache/catalog/2023/nike/nk011/giay-nike-air-max-sc-nam-trang-xanh-la-01-800x800.jpg', 'Giày Nike Air Max SC mang nét huyền thoại của Nike, với bộ đệm Air Max trứ danh đây là mẫu giày có thể kết hợp với bất cứ trang phục nào mà bạn vẫn hoàn toàn tự tin trong mọi hoàn cảnh.', 2),
(4, 'GIÀY NIKE COURT VISION LOW NAM - TRẮNG XANH', '2090000', 'https://myshoes.vn/image/cache/catalog/2023/nike/nk011/giay-nike-court-vision-low-nam-trang-xanh-01-800x800.jpg', 'Giày Nike Court Vision Low là mẫu giày với thiết kế lấy cảm hứng từ thập niên 80 với những nét cổ điển mang phong cách đường phố đặc trưng.\r\n\r\nVới chất liệu da cao cấp và đến cao su nguyên chất khiến đôi giày bền bỉ với thời gian.', 2),
(5, 'GIÀY NIKE AIR MAX EXCEE NAM - TRẮNG NÂU', '2390000', 'https://myshoes.vn/image/cache/catalog/2023/nike/nk011/giay-nike-air-max-excee-nam-trang-nau-01-800x800.jpg', 'Giày Nike Air Max Excee mẫu giày thời trang năng động, trẻ trung Air Max Excee đã cập bến tại Myshoes.vn. Đây hứa hẹn sẽ là một mẫu giày chất chơi mà bạn không thể bỏ qua.', 2),
(6, 'GIÀY ADIDAS RUNFALCON 3.0 NAM - ĐEN XANH', '1690000', 'https://myshoes.vn/image/cache/catalog/2023/adidas/ad5/giay-adidas-runfalcon-3-nam-den-xanh-01-800x800.jpg', 'Giày Adidas RunFalcon 3.0 là mẫu giày thể thao mới nhất của adidas. Với thiết kế trẻ trung, khỏe khoắn, ôm sát bàn chân. Đế giày cloud siêu nhẹ và êm ái giúp bạn di chuyển cả ngày mà không mệt mỏi.\r\n\r\nNgoài ra, Adidas RunFalcon 3.0 lại có mức giá rất hợp lý dành cho tất cả mọi người.', 1),
(7, 'GIÀY ADIDAS GALAXY 6 NAM - TRẮNG ĐỎ', '1490000', 'https://myshoes.vn/image/cache/catalog/2023/adidas/ad5/giay-adidas-runfalcon-3-nam-trang-do-01-800x800.jpg', 'Giày adidas Galaxy 6  có thiết kế thể thao đẹp mắt, đây là mẫu giày có thể sử dụng trong mọi hoạt động hàng ngày. adidas Galaxy 6 có nhiều cải tiến so với adidas Galaxy 5 giúp đôi giày ngày càng hoàn hảo.\r\n\r\nCông nghệ đế CloudFoam của Adidas chưa bao giờ làm Fan hâm mộ của họ thất vọng. Với cảm giác trải nghiệm giống như đi trên \'\'Mây\'\' đấy là những gì được người dùng chia sẻ lại. Form dáng thiết kế trẻ trung, khỏe khoắn nên đây sẽ là mẫu giày không thể thiếu cho những hoạt động vui chơi, thể thao. Ngoài ra, adidas Galaxy 6 sử dụng hơn 50% vật liệu tái chế thân thiện với môi trường.', 1),
(8, 'GIÀY ADIDAS ADVANCOURT BASE NAM NỮ - TRẮNG TRẮNG', '1490000', 'https://myshoes.vn/image/cache/catalog/2023/adidas/adi4/giay-adidas-advantage-base-nam-nu-trang-navy-01-800x800.jpg', 'Giày adidas Advancourt Base được thừa hưởng lối thiết kế của đàn anh Giày adidas Advantage song vẫn có thêm nhiều điểm mới cải thiện. Với thiết kế đơn giản nhưng lại vô cùng thanh lịch.\r\n\r\nPhần Upper của giày được các nhà thiết kế của Adidas sử dụng bằng da bạn tăng thêm độ lịch lãm khi mang. Với các đường kim mũi chỉ hoàn hảo đến từng chi tiết giúp bạn tự tin sải bước. Đây quả thật là một lựa chọn mà các phải mạnh không nên bỏ lỡ. ', 1),
(9, 'GIÀY ADIDAS GRAND COURT BASE 2.0 NAM NỮ - TRẮNG ĐEN', '1590000', 'https://myshoes.vn/image/cache/catalog/2023/adidas/ad6/giay-adidas-grand-court-base-2-nam-trang-den-01-800x800.jpg', 'Giày adidas Grand Court Base 2.0 phiên bản nâng cấp rất được ưu chuộng của dòng Grand Court Base .Với những cải tiến mới khiến cho mẫu giày này bền đẹp và năng động hơn khá nhiều.\r\n\r\nGiày adidas Grand Court Base 2.0 chắc chắn sẽ là một mẫu giày thời trang không thể nào bỏ qua được trong năm nay.', 1),
(10, 'GIÀY NIKE REVOLUTION 6 NEXT NATURE NAM - ĐEN', '1690000', 'https://myshoes.vn/image/cache/catalog/2023/nike/nk12/giay-nike-revolution-6-den-den-01-800x800.jpg', 'Giày Nike Revolution 6 là mẫu giày chạy bộ, tập thể thao nhẹ nhàng và có mức giá rất tốt tại Myshoes.vn- Giày Chính Hãng. Nike Revolution 6 sẽ là lựa chọn hoàn hảo, hợp lý,chính xác cho bạn.\r\n\r\nVới công nghệ được thiết kế để vừa chạy vừa có thể tập thể thao nhưng lại không quá đắt như Zoom hay React. Nike Revolution 6 sẽ là mẫu giày mà các tín đồ yêu thích thể thao nên có cho mình một đôi. Đồng thời với lối thiết kế trẻ trung, hiện đại thì bạn có thể dùng chính đôi giày này để kết hợp với các Outfit thường ngày.', 2),
(11, 'GIÀY NIKE REVOLUTION 6 NEXT NATURE NAM - TRẮNG XANH', '1690000', 'https://myshoes.vn/image/cache/catalog/2023/nike/nk12/giay-nike-revolution-6-trang-xam-01-800x800.jpg', 'Giày Nike Revolution 6 là mẫu giày chạy bộ, tập thể thao nhẹ nhàng và có mức giá rất tốt tại Myshoes.vn- Giày Chính Hãng. Nike Revolution 6 sẽ là lựa chọn hoàn hảo, hợp lý,chính xác cho bạn.\r\n\r\nVới công nghệ được thiết kế để vừa chạy vừa có thể tập thể thao nhưng lại không quá đắt như Zoom hay React. Nike Revolution 6 sẽ là mẫu giày mà các tín đồ yêu thích thể thao nên có cho mình một đôi. Đồng thời với lối thiết kế trẻ trung, hiện đại thì bạn có thể dùng chính đôi giày này để kết hợp với các Outfit thường ngày.', 2),
(12, 'GIÀY NIKE AIR ZOOM PEGASUS 40 PREMIUM NAM - TRẮNG', '4109000', 'https://myshoes.vn/image/cache/catalog/2023/nike/nk10/giay-nike-air-zoom-pegasus-40-premium-nam-trang-01-800x800.jpg', 'Giày Nike Air Zoom Pegasus 40 Premium là siêu phẩm giày thể thao được mong chờ nhất năm 2023 này. Đây là phiên bản thứ 40 của dòng giày huyền thoại Nike Pegasus và thật tuyệt vời, Nike Air Zoom Pegasus 40 đã có mặt sớm nhất tại Myshoes.vn rồi.\r\n\r\nGiày Nike Air Zoom Pegasus 40 chắc chắn sẽ làm thoả mãn những người yêu thương hiệu thương hiệu Nike trên toàn thế giới, với những cải tiến vượt trội so với phiên bản trước Pegasus 39, Nike Pegasus 40 xứng đáng được các fan ngày đêm săn đón.', 2),
(13, 'GIÀY ADIDAS VULC RAID3R NAM - ĐEN TRẮNG', '1390000', 'https://myshoes.vn/image/cache/catalog/2023/adidas/ad6/giay-adidas-vulc-raid3r-nam-den-03-800x800.jpg', 'Giày Adidas Vulc Raid3r có thiết kế cổ điển dạng slip on thuận tiện cho việc đi lại, chất liệu cao cấp, có thể sử dụng phù hợp trong mọi hoàn cảnh.\r\n\r\nChất liệu sản xuất mẫu giày Adidas Vulc Raid3r đều sử dụng vật liệu bền vững thân thiện với môi trường.', 1),
(14, 'GIÀY ADIDAS GALAXY STAR NAM - ĐEN TRẮNG', '1590000', 'https://myshoes.vn/image/cache/catalog/2023/adidas/ad6/giay-adidas-galaxy-star-nam-den-trang-01-800x800.jpg', 'Giày adidas Galaxy Star có thiết kế thể thao khỏe khoắn, đây là mẫu giày đa dụng cho mọi hoạt động hàng ngày. Adidas Galaxy Star có nhiều cải tiến so với adidas Galaxy 6 giúp đôi giày ngày càng hoàn hảo.\r\n\r\nCông nghệ đế CloudFoam của Adidas chưa bao giờ làm Fan hâm mộ của họ thất vọng. Với cảm giác trải nghiệm giống như đi trên \'\'Mây\'\' đấy là những gì được người dùng chia sẻ lại. Form dáng thiết kế trẻ trung, khỏe khoắn nên đây sẽ là mẫu giày không thể thiếu cho những hoạt động vui chơi, thể thao.', 1),
(15, 'GIÀY NIKE AIR MAX SC NAM - TRẮNG XANH LÁ', '2190000', 'https://myshoes.vn/image/cache/catalog/2023/nike/nk011/giay-nike-air-max-sc-nam-trang-xanh-la-01-800x800.jpg', 'Giày Nike Air Max SC mang nét huyền thoại của Nike, với bộ đệm Air Max trứ danh đây là mẫu giày có thể kết hợp với bất cứ trang phục nào mà bạn vẫn hoàn toàn tự tin trong mọi hoàn cảnh.', 2),
(16, 'GIÀY NIKE COURT VISION LOW NAM - TRẮNG XANH', '2090000', 'https://myshoes.vn/image/cache/catalog/2023/nike/nk011/giay-nike-court-vision-low-nam-trang-xanh-01-800x800.jpg', 'Giày Nike Court Vision Low là mẫu giày với thiết kế lấy cảm hứng từ thập niên 80 với những nét cổ điển mang phong cách đường phố đặc trưng.\r\n\r\nVới chất liệu da cao cấp và đến cao su nguyên chất khiến đôi giày bền bỉ với thời gian.', 2),
(17, 'GIÀY NIKE AIR MAX EXCEE NAM - TRẮNG NÂU', '2390000', 'https://myshoes.vn/image/cache/catalog/2023/nike/nk011/giay-nike-air-max-excee-nam-trang-nau-01-800x800.jpg', 'Giày Nike Air Max Excee mẫu giày thời trang năng động, trẻ trung Air Max Excee đã cập bến tại Myshoes.vn. Đây hứa hẹn sẽ là một mẫu giày chất chơi mà bạn không thể bỏ qua.', 2),
(18, 'GIÀY ADIDAS RUNFALCON 3.0 NAM - ĐEN XANH', '1690000', 'https://myshoes.vn/image/cache/catalog/2023/adidas/ad5/giay-adidas-runfalcon-3-nam-den-xanh-01-800x800.jpg', 'Giày Adidas RunFalcon 3.0 là mẫu giày thể thao mới nhất của adidas. Với thiết kế trẻ trung, khỏe khoắn, ôm sát bàn chân. Đế giày cloud siêu nhẹ và êm ái giúp bạn di chuyển cả ngày mà không mệt mỏi.\r\n\r\nNgoài ra, Adidas RunFalcon 3.0 lại có mức giá rất hợp lý dành cho tất cả mọi người.', 1),
(19, 'GIÀY ADIDAS GALAXY 6 NAM - TRẮNG ĐỎ', '1490000', 'https://myshoes.vn/image/cache/catalog/2023/adidas/ad5/giay-adidas-runfalcon-3-nam-trang-do-01-800x800.jpg', 'Giày adidas Galaxy 6  có thiết kế thể thao đẹp mắt, đây là mẫu giày có thể sử dụng trong mọi hoạt động hàng ngày. adidas Galaxy 6 có nhiều cải tiến so với adidas Galaxy 5 giúp đôi giày ngày càng hoàn hảo.\r\n\r\nCông nghệ đế CloudFoam của Adidas chưa bao giờ làm Fan hâm mộ của họ thất vọng. Với cảm giác trải nghiệm giống như đi trên \'\'Mây\'\' đấy là những gì được người dùng chia sẻ lại. Form dáng thiết kế trẻ trung, khỏe khoắn nên đây sẽ là mẫu giày không thể thiếu cho những hoạt động vui chơi, thể thao. Ngoài ra, adidas Galaxy 6 sử dụng hơn 50% vật liệu tái chế thân thiện với môi trường.', 1),
(20, 'GIÀY ADIDAS ADVANCOURT BASE NAM NỮ - TRẮNG TRẮNG', '1490000', 'https://myshoes.vn/image/cache/catalog/2023/adidas/adi4/giay-adidas-advantage-base-nam-nu-trang-navy-01-800x800.jpg', 'Giày adidas Advancourt Base được thừa hưởng lối thiết kế của đàn anh Giày adidas Advantage song vẫn có thêm nhiều điểm mới cải thiện. Với thiết kế đơn giản nhưng lại vô cùng thanh lịch.\r\n\r\nPhần Upper của giày được các nhà thiết kế của Adidas sử dụng bằng da bạn tăng thêm độ lịch lãm khi mang. Với các đường kim mũi chỉ hoàn hảo đến từng chi tiết giúp bạn tự tin sải bước. Đây quả thật là một lựa chọn mà các phải mạnh không nên bỏ lỡ. ', 1),
(21, 'GIÀY ADIDAS GRAND COURT BASE 2.0 NAM NỮ - TRẮNG ĐEN', '1590000', 'https://myshoes.vn/image/cache/catalog/2023/adidas/ad6/giay-adidas-grand-court-base-2-nam-trang-den-01-800x800.jpg', 'Giày adidas Grand Court Base 2.0 phiên bản nâng cấp rất được ưu chuộng của dòng Grand Court Base .Với những cải tiến mới khiến cho mẫu giày này bền đẹp và năng động hơn khá nhiều.\r\n\r\nGiày adidas Grand Court Base 2.0 chắc chắn sẽ là một mẫu giày thời trang không thể nào bỏ qua được trong năm nay.', 1),
(22, 'GIÀY NIKE REVOLUTION 6 NEXT NATURE NAM - ĐEN', '1690000', 'https://myshoes.vn/image/cache/catalog/2023/nike/nk12/giay-nike-revolution-6-den-den-01-800x800.jpg', 'Giày Nike Revolution 6 là mẫu giày chạy bộ, tập thể thao nhẹ nhàng và có mức giá rất tốt tại Myshoes.vn- Giày Chính Hãng. Nike Revolution 6 sẽ là lựa chọn hoàn hảo, hợp lý,chính xác cho bạn.\r\n\r\nVới công nghệ được thiết kế để vừa chạy vừa có thể tập thể thao nhưng lại không quá đắt như Zoom hay React. Nike Revolution 6 sẽ là mẫu giày mà các tín đồ yêu thích thể thao nên có cho mình một đôi. Đồng thời với lối thiết kế trẻ trung, hiện đại thì bạn có thể dùng chính đôi giày này để kết hợp với các Outfit thường ngày.', 2),
(23, 'GIÀY NIKE REVOLUTION 6 NEXT NATURE NAM - TRẮNG XANH', '1690000', 'https://myshoes.vn/image/cache/catalog/2023/nike/nk12/giay-nike-revolution-6-trang-xam-01-800x800.jpg', 'Giày Nike Revolution 6 là mẫu giày chạy bộ, tập thể thao nhẹ nhàng và có mức giá rất tốt tại Myshoes.vn- Giày Chính Hãng. Nike Revolution 6 sẽ là lựa chọn hoàn hảo, hợp lý,chính xác cho bạn.\r\n\r\nVới công nghệ được thiết kế để vừa chạy vừa có thể tập thể thao nhưng lại không quá đắt như Zoom hay React. Nike Revolution 6 sẽ là mẫu giày mà các tín đồ yêu thích thể thao nên có cho mình một đôi. Đồng thời với lối thiết kế trẻ trung, hiện đại thì bạn có thể dùng chính đôi giày này để kết hợp với các Outfit thường ngày.', 2),
(24, 'GIÀY NIKE AIR ZOOM PEGASUS 40 PREMIUM NAM - TRẮNG', '4109000', 'https://myshoes.vn/image/cache/catalog/2023/nike/nk10/giay-nike-air-zoom-pegasus-40-premium-nam-trang-01-800x800.jpg', 'Giày Nike Air Zoom Pegasus 40 Premium là siêu phẩm giày thể thao được mong chờ nhất năm 2023 này. Đây là phiên bản thứ 40 của dòng giày huyền thoại Nike Pegasus và thật tuyệt vời, Nike Air Zoom Pegasus 40 đã có mặt sớm nhất tại Myshoes.vn rồi.\r\n\r\nGiày Nike Air Zoom Pegasus 40 chắc chắn sẽ làm thoả mãn những người yêu thương hiệu thương hiệu Nike trên toàn thế giới, với những cải tiến vượt trội so với phiên bản trước Pegasus 39, Nike Pegasus 40 xứng đáng được các fan ngày đêm săn đón.', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `taikhoan` varchar(255) NOT NULL,
  `matkhau` varchar(255) NOT NULL,
  `tennguoidung` varchar(100) NOT NULL,
  `sodienthoai` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `taikhoan`, `matkhau`, `tennguoidung`, `sodienthoai`) VALUES
(5, 'daitrago10', 'trungml12345', 'trung', '0123456789'),
(6, 'bonghihuu', 'haobmt123', 'bao', '01238417927'),
(7, 'admin', '123', 'trung', '0898357214'),
(9, 'trung', '1', 'trung', '03525861101'),
(12, 'tai', '1', 'tài', '0844027271'),
(13, 'tu', '1', 'tudola', '64654'),
(14, 'tlun508', '1612000lyly', 'Bảo', '01238417927'),
(15, 'a', '1', 'a', '1');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`madonhang`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`masanpham`);

--
-- Chỉ mục cho bảng `sanphammoi`
--
ALTER TABLE `sanphammoi`
  ADD PRIMARY KEY (`masanphammoi`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `madonhang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `masanpham` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `sanphammoi`
--
ALTER TABLE `sanphammoi`
  MODIFY `masanphammoi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
