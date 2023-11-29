package entity;

public class CT_GioHang {
	private GioHang gioHang;
	private SanPham sanPham;
	private int soLuong;
	private double thanhTien;

	public CT_GioHang() {
		// TODO Auto-generated constructor stub

	}

	public CT_GioHang(int soLuong) {
		super();
		this.soLuong = soLuong;
	}

	public GioHang getGioHang() {
		return gioHang;
	}

	public void setGioHang(GioHang gioHang) {
		this.gioHang = gioHang;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	@Override
	public String toString() {
		return "CT_GioHang [gioHang=" + gioHang + ", sanPham=" + sanPham + ", soLuong=" + soLuong + ", thanhTien="
				+ thanhTien + "]";
	}



}

