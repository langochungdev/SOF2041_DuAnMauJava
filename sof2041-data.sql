CREATE DATABASE SOF2041
GO

USE SOF2041
GO

CREATE TABLE NhanVien (
    MaNV nvarchar(50) NOT NULL PRIMARY KEY,
    MatKhau nvarchar(50) NOT NULL,
    HoTen nvarchar(50) NOT NULL,
    VaiTro bit NOT NULL DEFAULT 0
)

CREATE TABLE ChuyenDe (
    MaCD nchar(5) NOT NULL PRIMARY KEY,
    TenCD nvarchar(50) NOT NULL UNIQUE,
    HocPhi float NOT NULL DEFAULT 0 CHECK (HocPhi >= 0),
    ThoiLuong int NOT NULL DEFAULT 30 CHECK (ThoiLuong > 0),
    Hinh nvarchar(50) NOT NULL DEFAULT 'chuyen-de.png',
    MoTa nvarchar(255) NOT NULL
)

CREATE TABLE NguoiHoc (
    MaNH nchar(7) NOT NULL PRIMARY KEY,
    HoTen nvarchar(50) NOT NULL,
    NgaySinh date NOT NULL,
    GioiTinh bit NOT NULL DEFAULT 0,
    DienThoai nvarchar(50) NOT NULL,
    Email nvarchar(50) NOT NULL,
    GhiChu nvarchar(max) NULL,
    MaNV nvarchar(50) NOT NULL,
    NgayDK date NOT NULL DEFAULT GETDATE(),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) ON UPDATE CASCADE
)

CREATE TABLE KhoaHoc (
    MaKH int IDENTITY(1,1) NOT NULL PRIMARY KEY,
    MaCD nchar(5) NOT NULL,
    HocPhi float NOT NULL DEFAULT 0 CHECK (HocPhi >= 0),
    ThoiLuong int NOT NULL DEFAULT 0 CHECK (ThoiLuong > 0),
    NgayKG date NOT NULL,
    GhiChu nvarchar(50) NULL,
    MaNV nvarchar(50) NOT NULL,
    NgayTao date NOT NULL DEFAULT GETDATE(),
    FOREIGN KEY (MaCD) REFERENCES ChuyenDe(MaCD) ON UPDATE CASCADE,
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) ON UPDATE CASCADE
)

CREATE TABLE HocVien (
    MaHV int IDENTITY(1,1) NOT NULL PRIMARY KEY,
    MaKH int NOT NULL,
    MaNH nchar(7) NOT NULL,
    Diem float NOT NULL,
    UNIQUE (MaKH, MaNH),
    FOREIGN KEY (MaKH) REFERENCES KhoaHoc(MaKH) ON DELETE CASCADE,
    FOREIGN KEY (MaNH) REFERENCES NguoiHoc(MaNH) ON UPDATE CASCADE
)
GO

-- PROCEDURE
CREATE PROC sp_BangDiem(@MaKH INT)
AS BEGIN
	SELECT 
		nh.MaNH,
		nh.HoTen,
		hv.Diem
	FROM HocVien hv 
	JOIN NguoiHoc nh ON nh.MaNH = hv.MaNH
	WHERE hv.MaKH = @MaKH
	ORDER BY hv.Diem DESC
END

GO

CREATE PROC sp_DiemChuyenDe
AS BEGIN
	SELECT
		TenCD ChuyenDe,
		COUNT(MaHV) SoHV,
		MIN(Diem) ThapNhat,
		MAX(Diem) CaoNhat,
		AVG(Diem) TrungBinh
	FROM KhoaHoc kh
	JOIN HocVien hv ON kh.MaKH = hv.MaKH
	JOIN ChuyenDe cd ON cd.MaCD = kh.MaCD
	GROUP BY TenCD
END

GO

CREATE PROC sp_DoanhThu(@Year INT)
AS BEGIN
	SELECT
		TenCD ChuyenDe,
		COUNT(DISTINCT kh.MaKH) SoKH,
		COUNT(hv.MaHV) SoHV,
		SUM(kh.HocPhi) DoanhThu,
		MIN(kh.HocPhi) ThapNhat,
		MAX(kh.HocPhi) CaoNhat,
		AVG(Kh.HocPhi) TrungBinh
	FROM KhoaHoc kh
	JOIN HocVien hv ON kh.MaKH = hv.MaKH
	JOIN ChuyenDe cd ON cd.MaCD = kh.MaCD
	WHERE YEAR(NgayKG) = @Year
	GROUP BY TenCD
END

GO

CREATE PROC sp_LuongNguoiHoc
AS BEGIN
	SELECT
		YEAR(NgayDK) Nam,
		COUNT(*) SoLuong,
		MIN(NgayDK) DauTien,
		MAX(NgayDK) CuoiCung
	FROM NguoiHoc
	GROUP BY YEAR(NgayDK)
END

GO 

-- DỮ LIỆU MẪU
INSERT INTO NhanVien VALUES
('hungln', '123', 'La Ngoc Hung 1', 1),
('hunglnf', '123', 'La Ngoc Hung 2', 0)

INSERT INTO ChuyenDe VALUES
(N'JAV01', N'Lập trình Java cơ bản', 2500, 90, N'GAME.png', N'JAV01 - Lập trình Java cơ bản'),
(N'JAV02', N'Lập trình Java nâng cao', 3000, 90, N'JSPR.png', N'JAV02 - Lập trình Java nâng cao'),
(N'WEB01', N'Thiết kế web với HTML và CSS', 2000, 70, N'WEBU.jpg', N'WEB01 - Thiết kế web với HTML và CSS'),
(N'PRO01', N'Dự án với Swing & JDBC', 3000, 90, N'VBPR.png', N'PRO01 - Dự án với Swing & JDBC')

INSERT INTO NguoiHoc VALUES
(N'PY00060', N'La Ngoc Hung 3', '2005-10-12', 0, N'0388928274', N'hung3@fpt.edu.vn', N'0388928274 - La Ngoc Hung 3', N'hungln', '2024-02-20'),
(N'PY00061', N'La Ngoc Hung 4', '2005-08-15', 1, N'0388928197', N'hung4@fpt.edu.vn', N'0388928197 - La Ngoc Hung 4', N'mainh', '2024-03-05'),
(N'PY00062', N'La Ngoc Hung 5', '2005-11-25', 1,  N'0388928311', N'hung5@fpt.edu.vn', N'0388928311 - La Ngoc Hung 5', N'hungln', '2024-04-10')

INSERT INTO KhoaHoc VALUES
(N'JAV01', 2500, 90, '2024-09-05', N'Lập trình Java cơ bản', N'hungln', '2024-09-06'),
(N'JAV02', 3000, 90, '2024-11-06', N'Lập trình Java nâng cao', N'hungln', '2024-11-16')

INSERT INTO HocVien VALUES
(1, N'PY00060', 8),
(2, N'PY00061', 8)
