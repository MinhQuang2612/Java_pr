USE [master]
GO
/****** Object:  Database [QuanLyBanHangQuanAo]    Script Date: 11/5/2023 1:35:46 PM ******/
CREATE DATABASE [QuanLyBanHangQuanAo]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QLBH_DATA', FILENAME = N'D:\Nhom13_QuanLyBanHangQuanAo\QLBH_DATA.MDF' , SIZE = 10240KB , MAXSIZE = 102400KB , FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QLBH_LOG', FILENAME = N'D:\Nhom13_QuanLyBanHangQuanAo\QLBH_LOG.LDF' , SIZE = 10240KB , MAXSIZE = 102400KB , FILEGROWTH = 1024KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyBanHangQuanAo].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET QUERY_STORE = OFF
GO
USE [QuanLyBanHangQuanAo]
GO
/****** Object:  FullTextCatalog [myFullText]    Script Date: 11/5/2023 1:35:46 PM ******/
CREATE FULLTEXT CATALOG [myFullText] 
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDBH]    Script Date: 11/5/2023 1:35:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDBH]()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MABH) FROM PHIEUBAOHANH) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MABH, 3)) FROM PHIEUBAOHANH
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'BH00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'BH0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDCV]    Script Date: 11/5/2023 1:35:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDCV]()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MACV) FROM CHUCVU) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MACV, 3)) FROM CHUCVU
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'CV00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'CV0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDDDH]    Script Date: 11/5/2023 1:35:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDDDH]()
RETURNS VARCHAR(12) -- Thay đổi chiều dài của chuỗi trả về thành 12 ký tự
AS
BEGIN
    DECLARE @ID VARCHAR(12)
    DECLARE @Stt INT
    SET @Stt = 1
    
    -- Lấy số thứ tự tăng dần cuối cùng
    IF (SELECT COUNT(MADDH) FROM DONDATHANG) > 0
    BEGIN
        SET @Stt = (SELECT MAX(CONVERT(INT, RIGHT(MADDH, 4))) + 1 FROM DONDATHANG)
    END

    -- Lấy ngày, tháng, 2 số cuối của năm
    DECLARE @NgayThang CHAR(6)
    SET @NgayThang = CONVERT(CHAR(6), GETDATE(), 12)

    -- Tạo mã đơn đặt hàng
    SET @ID = 'HD' + @NgayThang + RIGHT('000' + CAST(@Stt AS VARCHAR(4)), 4)
    RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDGH]    Script Date: 11/5/2023 1:35:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDGH]()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MAGH) FROM GIOHANG) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MAGH, 3)) FROM GIOHANG
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'GH00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'GH0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDHDBH]    Script Date: 11/5/2023 1:35:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDHDBH]()
RETURNS VARCHAR(7)
AS
BEGIN
	DECLARE @ID VARCHAR(7)
	IF (SELECT COUNT(MAHDBH) FROM HOADONBANHANG) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MAHDBH, 3)) FROM HOADONBANHANG
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'HDBH00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'HDBH0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDHDNH]    Script Date: 11/5/2023 1:35:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDHDNH]()
RETURNS VARCHAR(7)
AS
BEGIN
	DECLARE @ID VARCHAR(7)
	IF (SELECT COUNT(MAHDNH) FROM HOADONNHAPHANG) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MAHDNH, 3)) FROM HOADONNHAPHANG
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'HDNH00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'HDNH0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDKH]    Script Date: 11/5/2023 1:35:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDKH]()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MAKH) FROM KHACHHANG) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MAKH, 3)) FROM KHACHHANG
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'KH00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'KH0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDLSP]    Script Date: 11/5/2023 1:35:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDLSP]()
RETURNS VARCHAR(6)
AS
BEGIN
	DECLARE @ID VARCHAR(6)
	IF (SELECT COUNT(MALOAI) FROM LOAISANPHAM) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MALOAI, 3)) FROM LOAISANPHAM
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'LSP00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'LSP0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDNCC]    Script Date: 11/5/2023 1:35:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDNCC]()
RETURNS VARCHAR(6)
AS
BEGIN
	DECLARE @ID VARCHAR(6)
	IF (SELECT COUNT(MANHACC) FROM NHACUNGCAP) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MANHACC, 3)) FROM NHACUNGCAP
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'NCC00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'NCC0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDNSX]    Script Date: 11/5/2023 1:35:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDNSX]()
RETURNS VARCHAR(6)
AS
BEGIN
	DECLARE @ID VARCHAR(6)
	IF (SELECT COUNT(MANHASX) FROM NHASANXUAT) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MANHASX, 3)) FROM NHASANXUAT
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'NSX00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'NSX0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDNV]    Script Date: 11/5/2023 1:35:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDNV]()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MANV) FROM NHANVIEN) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MANV, 3)) FROM NHANVIEN
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'NV00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'NV0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  UserDefinedFunction [dbo].[AUTO_IDSP]    Script Date: 11/5/2023 1:35:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AUTO_IDSP]()
RETURNS VARCHAR(5)
AS
BEGIN
	DECLARE @ID VARCHAR(5)
	IF (SELECT COUNT(MASP) FROM SANPHAM) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(MASP, 3)) FROM SANPHAM
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'SP00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'SP0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 11/5/2023 1:35:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[MACV] [char](5) NOT NULL,
	[TENCV] [nvarchar](30) NOT NULL,
	[HESOLUONG] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[MACV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_DonDatHang]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_DonDatHang](
	[MADDH] [char](12) NOT NULL,
	[MASP] [char](5) NOT NULL,
	[SOLUONG] [int] NULL,
 CONSTRAINT [pk_CTDDHang] PRIMARY KEY CLUSTERED 
(
	[MADDH] ASC,
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_GioHang]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_GioHang](
	[MAGH] [char](5) NOT NULL,
	[MASP] [char](5) NOT NULL,
	[SOLUONG] [int] NULL,
 CONSTRAINT [pk_CTGioHang] PRIMARY KEY CLUSTERED 
(
	[MAGH] ASC,
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_HoaDonBanHang]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_HoaDonBanHang](
	[MaHDBH] [char](7) NOT NULL,
	[MASP] [char](5) NOT NULL,
	[SOLUONG] [int] NULL,
 CONSTRAINT [pk_CTHDBH] PRIMARY KEY CLUSTERED 
(
	[MaHDBH] ASC,
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_HoaDonNhapHang]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_HoaDonNhapHang](
	[MaHDNH] [char](7) NOT NULL,
	[MASP] [char](5) NOT NULL,
	[SOLUONG] [int] NULL,
	[DONGIA] [float] NULL,
 CONSTRAINT [pk_CTHDNH] PRIMARY KEY CLUSTERED 
(
	[MaHDNH] ASC,
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_PhieuBaoHanh]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_PhieuBaoHanh](
	[MaBH] [char](5) NOT NULL,
	[MASP] [char](5) NOT NULL,
	[SOLUONG] [int] NULL,
 CONSTRAINT [pk_CTPBH] PRIMARY KEY CLUSTERED 
(
	[MaBH] ASC,
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DonDatHang]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DonDatHang](
	[MaDDH] [char](12) NOT NULL,
	[MAKH] [char](5) NOT NULL,
	[PTTT] [nvarchar](30) NOT NULL,
	[TINHTRANG] [nvarchar](10) NOT NULL,
	[NGAYDATHANG] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaDDH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GioHang]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GioHang](
	[MAGH] [char](5) NOT NULL,
	[MAKH] [char](5) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MAGH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonBanHang]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonBanHang](
	[MaHDBH] [char](7) NOT NULL,
	[MAKH] [char](5) NOT NULL,
	[MANV] [char](5) NOT NULL,
	[PTTT] [nvarchar](30) NOT NULL,
	[NGAYLAPHD] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHDBH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonNhapHang]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonNhapHang](
	[MaHDNH] [char](7) NOT NULL,
	[MANHACC] [char](6) NOT NULL,
	[MANV] [char](5) NOT NULL,
	[NGAYLAPHD] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHDNH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MAKH] [char](5) NOT NULL,
	[TENKH] [nvarchar](30) NOT NULL,
	[GIOITINH] [nvarchar](5) NOT NULL,
	[NGAYSINH] [date] NULL,
	[EMAIL] [char](30) NULL,
	[DIENTHOAI] [char](12) NULL,
	[CCCD] [char](12) NULL,
	[DIACHI] [nvarchar](40) NULL,
	[IMAGES] [char](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[MAKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiSanPham]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiSanPham](
	[MALOAI] [char](6) NOT NULL,
	[TENLOAI] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MALOAI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[MANHACC] [char](6) NOT NULL,
	[TENNHACC] [nvarchar](30) NOT NULL,
	[DIACHI] [nvarchar](50) NOT NULL,
	[SODT] [char](30) NOT NULL,
	[EMAIL] [char](30) NOT NULL,
	[IMAGES] [char](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[MANHACC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MANV] [char](5) NOT NULL,
	[MACV] [char](5) NOT NULL,
	[TENNV] [nvarchar](30) NOT NULL,
	[GIOITINH] [nvarchar](5) NOT NULL,
	[NGAYSINH] [date] NULL,
	[EMAIL] [char](30) NULL,
	[DIENTHOAI] [char](12) NULL,
	[CCCD] [char](12) NULL,
	[DIACHI] [nvarchar](40) NULL,
	[NGAYVAOLAM] [date] NULL,
	[IMAGES] [char](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[MANV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaSanXuat]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaSanXuat](
	[MANHASX] [char](6) NOT NULL,
	[TENNHASX] [nvarchar](30) NOT NULL,
	[DIACHI] [nvarchar](40) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MANHASX] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuBaoHanh]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuBaoHanh](
	[MaBH] [char](5) NOT NULL,
	[MAHDBH] [char](7) NOT NULL,
	[NGAYLAPPHIEU] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaBH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[MASP] [char](5) NOT NULL,
	[MALOAI] [char](6) NOT NULL,
	[MANHASX] [char](6) NOT NULL,
	[TENSP] [nvarchar](30) NOT NULL,
	[BAOHANH] [nvarchar](10) NOT NULL,
	[NGAYSANXUAT] [date] NULL,
	[TINHTRANG] [nvarchar](30) NULL,
	[DONVITINH] [nvarchar](10) NULL,
	[DONGIA] [float] NULL,
	[SOLUONG] [int] NULL,
	[IMAGES] [char](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[TENDN] [char](15) NOT NULL,
	[MATKHAU] [char](30) NOT NULL,
	[MANV] [char](5) NULL,
	[MAKH] [char](5) NULL,
	[TEN] [nvarchar](40) NOT NULL,
	[QUYEN] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TENDN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ChucVu] ([MACV], [TENCV], [HESOLUONG]) VALUES (N'CV001', N'Nhân Viên Thu Ngân', 1.5)
INSERT [dbo].[ChucVu] ([MACV], [TENCV], [HESOLUONG]) VALUES (N'CV002', N'Nhân Viên Kế Toán', 2)
INSERT [dbo].[ChucVu] ([MACV], [TENCV], [HESOLUONG]) VALUES (N'CV003', N'Quản Lý', 1.5)
INSERT [dbo].[ChucVu] ([MACV], [TENCV], [HESOLUONG]) VALUES (N'CV004', N'Nhân Viên Kinh Doanh', 1.5)
GO
INSERT [dbo].[GioHang] ([MAGH], [MAKH]) VALUES (N'GH001', N'KH001')
INSERT [dbo].[GioHang] ([MAGH], [MAKH]) VALUES (N'GH002', N'KH002')
INSERT [dbo].[GioHang] ([MAGH], [MAKH]) VALUES (N'GH003', N'KH003')
INSERT [dbo].[GioHang] ([MAGH], [MAKH]) VALUES (N'GH004', N'KH004')
INSERT [dbo].[GioHang] ([MAGH], [MAKH]) VALUES (N'GH005', N'KH005')
INSERT [dbo].[GioHang] ([MAGH], [MAKH]) VALUES (N'GH006', N'KH006')
INSERT [dbo].[GioHang] ([MAGH], [MAKH]) VALUES (N'GH007', N'KH007')
INSERT [dbo].[GioHang] ([MAGH], [MAKH]) VALUES (N'GH008', N'KH008')
INSERT [dbo].[GioHang] ([MAGH], [MAKH]) VALUES (N'GH009', N'KH009')
INSERT [dbo].[GioHang] ([MAGH], [MAKH]) VALUES (N'GH010', N'KH010')
GO
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [IMAGES]) VALUES (N'KH001', N'Nguyễn Thị Mộng Bình', N'Nữ', CAST(N'2003-12-17' AS Date), N'binh1712@gmail.com            ', N'0854926311  ', N'83202010671 ', N'Gò vấp, Tp.HCM', N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\userKH1.png                                                ')
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [IMAGES]) VALUES (N'KH002', N'Nguyễn Văn Hoàng', N'Nam', CAST(N'2002-06-28' AS Date), N'hoang2806@gmail.com           ', N'0964387243  ', N'83203056712 ', N'Tân Bình, Tp.HCM', N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [IMAGES]) VALUES (N'KH003', N'Lê Đức Quân', N'Nam', CAST(N'2000-07-22' AS Date), N'Quan2207@gmail.com            ', N'0829674345  ', N'83203045171 ', N'Tân Phú, Tp.HCM', N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [IMAGES]) VALUES (N'KH004', N'Lê Thu Hiền', N'Nữ', CAST(N'2001-05-10' AS Date), N'Hien10005@gmail.com           ', N'0734925463  ', N'83202075112 ', N'Thủ Đức, Tp.HCM', N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [IMAGES]) VALUES (N'KH005', N'Lê Hoài Như', N'Nữ', CAST(N'2001-11-12' AS Date), N'Nhu1211@gmail.com             ', N'0946285732  ', N'83202415781 ', N'Quận 1, Tp.HCM', N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [IMAGES]) VALUES (N'KH006', N'Lê Mạnh Hùng', N'Nam', CAST(N'2000-08-10' AS Date), N'manhung1008@gmail.com         ', N'0914576695  ', N'83203884713 ', N'Quận 2, Tp.HCM', N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [IMAGES]) VALUES (N'KH007', N'Nguyễn Quang Dũng', N'Nam', CAST(N'2000-05-23' AS Date), N'dunghy2232@gmail.com          ', N'0945723542  ', N'83203041632 ', N'Quận Bình Thạnh, Tp.HCM', N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [IMAGES]) VALUES (N'KH008', N'Ngô Hiếu Ngọc', N'Nữ', CAST(N'2000-04-24' AS Date), N'ngocngoc244@gmail.com         ', N'096346257   ', N'83202000174 ', N'Quận 12, Tp.HCM', N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [IMAGES]) VALUES (N'KH009', N'Lê Thị Minh Hằng', N'Nữ', CAST(N'2000-07-14' AS Date), N'hangdgh2321@gmail.com         ', N'0734534323  ', N'83203054871 ', N'Quận 3, Tp.HCM', N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
INSERT [dbo].[KhachHang] ([MAKH], [TENKH], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [IMAGES]) VALUES (N'KH010', N'Lê Nguyên Long', N'Nam', CAST(N'2000-09-28' AS Date), N'longnguyen123@gmail.com       ', N'0734523341  ', N'83203005981 ', N'Quận 10, Tp.HCM', N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
GO
INSERT [dbo].[LoaiSanPham] ([MALOAI], [TENLOAI]) VALUES (N'LSP001', N'Quần áo')
INSERT [dbo].[LoaiSanPham] ([MALOAI], [TENLOAI]) VALUES (N'LSP002', N'Giày dép')
INSERT [dbo].[LoaiSanPham] ([MALOAI], [TENLOAI]) VALUES (N'LSP003', N'Phụ kiện')
GO
INSERT [dbo].[NhaCungCap] ([MANHACC], [TENNHACC], [DIACHI], [SODT], [EMAIL], [IMAGES]) VALUES (N'NCC001', N'Qu?n áo Vi?t Nam', N'Tân Bình, TP.Hồ Chí Minh', N'0987699999                    ', N'quanaoVN@gmail.com            ', N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
INSERT [dbo].[NhaCungCap] ([MANHACC], [TENNHACC], [DIACHI], [SODT], [EMAIL], [IMAGES]) VALUES (N'NCC002', N'Giày dép Vi?t Nam', N'Tân Bình, TP.Hồ Chí Minh', N'09876888888                   ', N'giaydepVN@gmail.com           ', N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
INSERT [dbo].[NhaCungCap] ([MANHACC], [TENNHACC], [DIACHI], [SODT], [EMAIL], [IMAGES]) VALUES (N'NCC003', N'Phụ kiện Việt Nam', N'Gò Vấp, TP.Hồ Chí Minh', N'0987699779                    ', N'phukienVN@gmail.com           ', N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
GO
INSERT [dbo].[NhanVien] ([MANV], [MACV], [TENNV], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [NGAYVAOLAM], [IMAGES]) VALUES (N'NV001', N'CV001', N'Nguyễn Thị Như Ý', N'Nữ', CAST(N'2003-12-21' AS Date), N'nhuy2112@gmail.com            ', N'0972972842  ', N'89303014812 ', N'Thủ Đức, Tp.HCM', CAST(N'2020-10-10' AS Date), N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
INSERT [dbo].[NhanVien] ([MANV], [MACV], [TENNV], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [NGAYVAOLAM], [IMAGES]) VALUES (N'NV002', N'CV002', N'Nguyễn Thanh Quyền', N'Nam', CAST(N'2002-06-01' AS Date), N'quyenngoo@gmail.com           ', N'095642843   ', N'82202014381 ', N'Quận Bình Thạnh, Tp.HCM', CAST(N'2020-10-10' AS Date), N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
INSERT [dbo].[NhanVien] ([MANV], [MACV], [TENNV], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [NGAYVAOLAM], [IMAGES]) VALUES (N'NV003', N'CV003', N'Trần Minh Quang', N'Nam', CAST(N'2000-07-28' AS Date), N'trungngu123@gmail.com         ', N'0987536999  ', N'84203981661 ', N'Tân Phú, Tp.HCM', CAST(N'2020-10-10' AS Date), N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
INSERT [dbo].[NhanVien] ([MANV], [MACV], [TENNV], [GIOITINH], [NGAYSINH], [EMAIL], [DIENTHOAI], [CCCD], [DIACHI], [NGAYVAOLAM], [IMAGES]) VALUES (N'NV004', N'CV004', N'Đỗ Văn Việt', N'Nam', CAST(N'2000-03-10' AS Date), N'locngu122@gmail.com           ', N'0914393090  ', N'89302551241 ', N'Gò vấp, Tp.HCM', CAST(N'2020-10-10' AS Date), N'D:\Nhom13_QuanLyBanHangQuanAo\src\images\user11.png                                                 ')
GO
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX001', N'BOBUI', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX002', N'Dirty Coins', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX003', N'TSUN', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX004', N'SWE', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX005', N'Teelab', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX006', N'Odin', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX007', N'Hades', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX008', N'Ananas', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX009', N'Juno', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX010', N'Bitis', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX011', N'Laforce', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX012', N'MWC', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX013', N'Pauvie Jewelry', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX014', N'Docs', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX015', N'Hiphop Pallas', N'Việt Nam')
INSERT [dbo].[NhaSanXuat] ([MANHASX], [TENNHASX], [DIACHI]) VALUES (N'NSX016', N'Rouge', N'Việt Nam')
GO
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP001', N'LSP001', N'NSX001', N'Áo polo Teelab tay lỡ', N'2 tháng', CAST(N'2020-04-15' AS Date), N'Còn hàng', N'Cái', 160000, 100, N'/images_SanPham/teelab1.png-/images_SanPham/teelab2.png-/images_SanPham/teelab3.png-/images_SanPham/teelab4.png                                                                                         ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP002', N'LSP001', N'NSX008', N'Áo Sơ Mi Oxford Angel', N'3 tháng', CAST(N'2020-04-15' AS Date), N'Còn hàng', N'Cái', 500000, 100, N'/images_SanPham/oxford1.png-/images_SanPham/oxford2.png-/images_SanPham/oxford3.png                                                                                                                     ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP003', N'LSP001', N'NSX003', N'Áo Khoác DirtyCoins Cardigan', N'5 tháng', CAST(N'2020-04-15' AS Date), N'Còn hàng', N'Cái', 320000, 100, N'/images_SanPham/dirtycoins1.png-/images_SanPham/dirtycoins2.png-/images_SanPham/dirtycoins3.png                                                                                                         ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP004', N'LSP001', N'NSX005', N'Áo thun Outerity tay cộc', N'2 tháng', CAST(N'2020-04-15' AS Date), N'Còn hàng', N'Cái', 140000, 100, N'/images_SanPham/outerity1.png-/images_SanPham/outerity2.png-/images_SanPham/outerity3.png                                                                                                               ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP005', N'LSP001', N'NSX006', N'Áo hoodie Odin', N'5 tháng', CAST(N'2020-04-15' AS Date), N'Còn hàng', N'Cái', 180000, 100, N'/images_SanPham/odin1.png-/images_SanPham/odin2.png-/images_SanPham/odin3.png                                                                                                                           ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP006', N'LSP001', N'NSX003', N'Baby Tee Commonly Hades', N'3 tháng', CAST(N'2020-04-15' AS Date), N'Còn hàng', N'Bộ', 316000, 100, N'/images_SanPham/hades1.png-/images_SanPham/hades2.png-/images_SanPham/hades3.png                                                                                                                        ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP007', N'LSP002', N'NSX009', N'Giày thể thao nam Bitis Hunter', N'3 tháng', CAST(N'2023-10-15' AS Date), N'Còn hàng', N'Cái', 814000, 50, N'/images_SanPham/DSMH062001.png-/images_SanPham/DSMH062002.png-/images_SanPham/DSMH062003.png-/images_SanPham/DSMH062004.png                                                                             ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP008', N'LSP002', N'NSX001', N'Sandal Thể Thao Eva Phun Nam', N'3 tháng', CAST(N'2023-08-20' AS Date), N'Còn hàng', N'Cái', 460000, 200, N'/images_SanPham/HEM0007001.png-/images_SanPham/HEM0007002.png-/images_SanPham/HEM0007003.png-/images_SanPham/HEM0007004.png                                                                             ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP009', N'LSP002', N'NSX016', N' Giày cao gót Pump họa tiết nơ', N'6 tháng', CAST(N'2023-09-02' AS Date), N'Còn hàng', N'Cái', 539000, 100, N'/images_SanPham/giaypump1.png-/images_SanPham/giaypump2.png-/images_SanPham/giaypump3.png-/images_SanPham/giaypump4.png                                                                                 ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP010', N'LSP002', N'NSX001', N'Giày Búp Bê Ballerina Họa Tiết', N'6 tháng', CAST(N'2023-05-20' AS Date), N'Còn hàng', N'Cái', 650000, 150, N'/images_SanPham/giayballerina1.png-/images_SanPham/giayballerina2.png-/images_SanPham/giayballerina3.png-/images_SanPham/giayballerina4.png                                                             ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP011', N'LSP002', N'NSX013', N'Vintas Nauda EXT-High Top', N'6 tháng', CAST(N'2023-04-30' AS Date), N'Còn hàng', N'Cái', 720000, 80, N'/images_SanPham/giayvintas1.png-/images_SanPham/giayvintas2.png-/images_SanPham/giayvintas3.png-/images_SanPham/giayvintas4.png                                                                         ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP012', N'LSP003', N'NSX008', N'Nhẫn INFIRIS', N'Không có', CAST(N'2020-04-15' AS Date), N'Còn hàng', N'Chiếc', 4500000, 100, N'/images_SanPham/Hình ?nh ph? ki?n_00001.png-/images_SanPham/Hình ?nh ph? ki?n_00002.png-/images_SanPham/Hình ?nh ph? ki?n_00004.png                                                                     ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP013', N'LSP003', N'NSX004', N'Nhẫn SECURE RING', N'Không có', CAST(N'2020-04-15' AS Date), N'Còn hàng', N'Chiếc', 3990000, 95, N'/images_SanPham/Hình ?nh ph? ki?n_00005.png-/images_SanPham/Hình ?nh ph? ki?n_00006.png-/images_SanPham/Hình ?nh ph? ki?n_00007.png                                                                     ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP014', N'LSP003', N'NSX013', N'Combo set nhẫn titan UNISEX', N'2Không có', CAST(N'2020-04-15' AS Date), N'Hết hàng', N'Set', 1550000, 0, N'/images_SanPham/Hình ?nh ph? ki?n_00008.png-/images_SanPham/Hình ?nh ph? ki?n_00009.png-/images_SanPham/Hình ?nh ph? ki?n_00010.png                                                                     ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP015', N'LSP003', N'NSX004', N'"GREEN GOBLER" FANNY BELTED', N'6 tháng', CAST(N'2020-04-15' AS Date), N'Còn hàng', N'Chiếc', 1650000, 100, N'/images_SanPham/Hình ?nh ph? ki?n_00011.png-/images_SanPham/Hình ?nh ph? ki?n_00012.png-/images_SanPham/Hình ?nh ph? ki?n_00013.png                                                                     ')
INSERT [dbo].[SanPham] ([MASP], [MALOAI], [MANHASX], [TENSP], [BAOHANH], [NGAYSANXUAT], [TINHTRANG], [DONVITINH], [DONGIA], [SOLUONG], [IMAGES]) VALUES (N'SP016', N'LSP003', N'NSX008', N'BERRY BLACK', N'6 tháng', CAST(N'2020-04-15' AS Date), N'Hết hàng', N'Cái', 1000000, 0, N'/images_SanPham/Hình ?nh ph? ki?n_00014.png-/images_SanPham/Hình ?nh ph? ki?n_00015.png-/images_SanPham/Hình ?nh ph? ki?n_00017.png                                                                     ')
GO
INSERT [dbo].[TaiKhoan] ([TENDN], [MATKHAU], [MANV], [MAKH], [TEN], [QUYEN]) VALUES (N'bequyen        ', N'12345                         ', N'NV002', NULL, N'Nguyễn Thanh Quyền', N'Nhân Viên Kế Toán')
INSERT [dbo].[TaiKhoan] ([TENDN], [MATKHAU], [MANV], [MAKH], [TEN], [QUYEN]) VALUES (N'minhquang      ', N'12345                         ', N'NV003', NULL, N'Trần Minh Quang', N'Quản lý')
INSERT [dbo].[TaiKhoan] ([TENDN], [MATKHAU], [MANV], [MAKH], [TEN], [QUYEN]) VALUES (N'viet           ', N'12345                         ', N'NV004', NULL, N'Ðỗ Văn Việt', N'Khách hàng')
INSERT [dbo].[TaiKhoan] ([TENDN], [MATKHAU], [MANV], [MAKH], [TEN], [QUYEN]) VALUES (N'yelsa          ', N'12345                         ', N'NV001', NULL, N'Nguyễn Thị Như Ý', N'Nhân Viên Thu Ngân')
GO
ALTER TABLE [dbo].[ChucVu] ADD  CONSTRAINT [IDCV]  DEFAULT ([DBO].[AUTO_IDCV]()) FOR [MACV]
GO
ALTER TABLE [dbo].[DonDatHang] ADD  CONSTRAINT [IDDDH]  DEFAULT ([DBO].[AUTO_IDDDH]()) FOR [MaDDH]
GO
ALTER TABLE [dbo].[GioHang] ADD  CONSTRAINT [IDGH]  DEFAULT ([DBO].[AUTO_IDGH]()) FOR [MAGH]
GO
ALTER TABLE [dbo].[HoaDonBanHang] ADD  CONSTRAINT [IDHDBH]  DEFAULT ([DBO].[AUTO_IDHDBH]()) FOR [MaHDBH]
GO
ALTER TABLE [dbo].[HoaDonNhapHang] ADD  CONSTRAINT [IDHDNH]  DEFAULT ([DBO].[AUTO_IDHDNH]()) FOR [MaHDNH]
GO
ALTER TABLE [dbo].[KhachHang] ADD  CONSTRAINT [IDKH]  DEFAULT ([DBO].[AUTO_IDKH]()) FOR [MAKH]
GO
ALTER TABLE [dbo].[LoaiSanPham] ADD  CONSTRAINT [IDLSP]  DEFAULT ([DBO].[AUTO_IDLSP]()) FOR [MALOAI]
GO
ALTER TABLE [dbo].[NhaCungCap] ADD  CONSTRAINT [IDNCC]  DEFAULT ([DBO].[AUTO_IDNCC]()) FOR [MANHACC]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [IDNV]  DEFAULT ([DBO].[AUTO_IDNV]()) FOR [MANV]
GO
ALTER TABLE [dbo].[NhaSanXuat] ADD  CONSTRAINT [IDNSX]  DEFAULT ([DBO].[AUTO_IDNSX]()) FOR [MANHASX]
GO
ALTER TABLE [dbo].[PhieuBaoHanh] ADD  CONSTRAINT [IDBH]  DEFAULT ([DBO].[AUTO_IDBH]()) FOR [MaBH]
GO
ALTER TABLE [dbo].[SanPham] ADD  CONSTRAINT [IDSP]  DEFAULT ([DBO].[AUTO_IDSP]()) FOR [MASP]
GO
ALTER TABLE [dbo].[CT_DonDatHang]  WITH CHECK ADD FOREIGN KEY([MASP])
REFERENCES [dbo].[SanPham] ([MASP])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_DonDatHang]  WITH CHECK ADD  CONSTRAINT [fk_CTDDHang_DDH] FOREIGN KEY([MADDH])
REFERENCES [dbo].[DonDatHang] ([MaDDH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_DonDatHang] CHECK CONSTRAINT [fk_CTDDHang_DDH]
GO
ALTER TABLE [dbo].[CT_GioHang]  WITH CHECK ADD  CONSTRAINT [fk_CTGH_SP] FOREIGN KEY([MASP])
REFERENCES [dbo].[SanPham] ([MASP])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_GioHang] CHECK CONSTRAINT [fk_CTGH_SP]
GO
ALTER TABLE [dbo].[CT_GioHang]  WITH CHECK ADD  CONSTRAINT [fk_CTGioHang_GH] FOREIGN KEY([MAGH])
REFERENCES [dbo].[GioHang] ([MAGH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_GioHang] CHECK CONSTRAINT [fk_CTGioHang_GH]
GO
ALTER TABLE [dbo].[CT_HoaDonBanHang]  WITH CHECK ADD  CONSTRAINT [fk_MaHDBH] FOREIGN KEY([MaHDBH])
REFERENCES [dbo].[HoaDonBanHang] ([MaHDBH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_HoaDonBanHang] CHECK CONSTRAINT [fk_MaHDBH]
GO
ALTER TABLE [dbo].[CT_HoaDonBanHang]  WITH CHECK ADD  CONSTRAINT [fk_MASPB] FOREIGN KEY([MASP])
REFERENCES [dbo].[SanPham] ([MASP])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_HoaDonBanHang] CHECK CONSTRAINT [fk_MASPB]
GO
ALTER TABLE [dbo].[CT_HoaDonNhapHang]  WITH CHECK ADD  CONSTRAINT [fk_MaHDNH] FOREIGN KEY([MaHDNH])
REFERENCES [dbo].[HoaDonNhapHang] ([MaHDNH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_HoaDonNhapHang] CHECK CONSTRAINT [fk_MaHDNH]
GO
ALTER TABLE [dbo].[CT_HoaDonNhapHang]  WITH CHECK ADD  CONSTRAINT [fk_MASPN] FOREIGN KEY([MASP])
REFERENCES [dbo].[SanPham] ([MASP])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_HoaDonNhapHang] CHECK CONSTRAINT [fk_MASPN]
GO
ALTER TABLE [dbo].[CT_PhieuBaoHanh]  WITH CHECK ADD  CONSTRAINT [fk_MaBHP] FOREIGN KEY([MaBH])
REFERENCES [dbo].[PhieuBaoHanh] ([MaBH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_PhieuBaoHanh] CHECK CONSTRAINT [fk_MaBHP]
GO
ALTER TABLE [dbo].[CT_PhieuBaoHanh]  WITH CHECK ADD  CONSTRAINT [fk_MASPP] FOREIGN KEY([MASP])
REFERENCES [dbo].[SanPham] ([MASP])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CT_PhieuBaoHanh] CHECK CONSTRAINT [fk_MASPP]
GO
ALTER TABLE [dbo].[DonDatHang]  WITH CHECK ADD  CONSTRAINT [fk_MaKHDH] FOREIGN KEY([MAKH])
REFERENCES [dbo].[KhachHang] ([MAKH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DonDatHang] CHECK CONSTRAINT [fk_MaKHDH]
GO
ALTER TABLE [dbo].[GioHang]  WITH CHECK ADD  CONSTRAINT [fk_MaKH] FOREIGN KEY([MAKH])
REFERENCES [dbo].[KhachHang] ([MAKH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[GioHang] CHECK CONSTRAINT [fk_MaKH]
GO
ALTER TABLE [dbo].[HoaDonBanHang]  WITH CHECK ADD  CONSTRAINT [fk_MaKHMH] FOREIGN KEY([MAKH])
REFERENCES [dbo].[KhachHang] ([MAKH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDonBanHang] CHECK CONSTRAINT [fk_MaKHMH]
GO
ALTER TABLE [dbo].[HoaDonBanHang]  WITH CHECK ADD  CONSTRAINT [fk_MaNV] FOREIGN KEY([MANV])
REFERENCES [dbo].[NhanVien] ([MANV])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDonBanHang] CHECK CONSTRAINT [fk_MaNV]
GO
ALTER TABLE [dbo].[HoaDonNhapHang]  WITH CHECK ADD  CONSTRAINT [fk_MaNCCSP] FOREIGN KEY([MANHACC])
REFERENCES [dbo].[NhaCungCap] ([MANHACC])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDonNhapHang] CHECK CONSTRAINT [fk_MaNCCSP]
GO
ALTER TABLE [dbo].[HoaDonNhapHang]  WITH CHECK ADD  CONSTRAINT [fk_MaNVNH] FOREIGN KEY([MANV])
REFERENCES [dbo].[NhanVien] ([MANV])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDonNhapHang] CHECK CONSTRAINT [fk_MaNVNH]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [fk_MaChucVu] FOREIGN KEY([MACV])
REFERENCES [dbo].[ChucVu] ([MACV])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [fk_MaChucVu]
GO
ALTER TABLE [dbo].[PhieuBaoHanh]  WITH CHECK ADD  CONSTRAINT [fk_MaHDP] FOREIGN KEY([MAHDBH])
REFERENCES [dbo].[HoaDonBanHang] ([MaHDBH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PhieuBaoHanh] CHECK CONSTRAINT [fk_MaHDP]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [fk_MaLoai] FOREIGN KEY([MALOAI])
REFERENCES [dbo].[LoaiSanPham] ([MALOAI])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [fk_MaLoai]
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [fk_MaNhaSX] FOREIGN KEY([MANHASX])
REFERENCES [dbo].[NhaSanXuat] ([MANHASX])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [fk_MaNhaSX]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [fk_MaKHPK] FOREIGN KEY([MAKH])
REFERENCES [dbo].[KhachHang] ([MAKH])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [fk_MaKHPK]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [fk_MaNVPK] FOREIGN KEY([MANV])
REFERENCES [dbo].[NhanVien] ([MANV])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [fk_MaNVPK]
GO
/****** Object:  StoredProcedure [dbo].[select_CTDDH]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_CTDDH]
as
	SELECT * FROM [dbo].[CT_DonDatHang]
GO
/****** Object:  StoredProcedure [dbo].[select_CTGH]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_CTGH]
as
	SELECT * FROM [dbo].[CT_GioHang]
GO
/****** Object:  StoredProcedure [dbo].[select_CV]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_CV]
as
	SELECT * FROM [dbo].[ChucVu]
GO
/****** Object:  StoredProcedure [dbo].[select_KH]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_KH]
as
	SELECT * FROM [dbo].[KhachHang]
GO
/****** Object:  StoredProcedure [dbo].[select_LSP]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_LSP]
as
	SELECT * FROM [dbo].[LoaiSanPham]
GO
/****** Object:  StoredProcedure [dbo].[select_NCC]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_NCC]  
as
	SELECT * FROM [dbo].[NhaCungCap]
GO
/****** Object:  StoredProcedure [dbo].[select_NSX]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_NSX]
as
	SELECT * FROM [dbo].[NhaSanXuat]
GO
/****** Object:  StoredProcedure [dbo].[select_NV]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_NV]
as
	SELECT * FROM [dbo].[NhanVien]
GO
/****** Object:  StoredProcedure [dbo].[select_SP]    Script Date: 11/5/2023 1:35:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[select_SP]
as
	SELECT * FROM [dbo].[SanPham]
GO
USE [master]
GO
ALTER DATABASE [QuanLyBanHangQuanAo] SET  READ_WRITE 
GO

