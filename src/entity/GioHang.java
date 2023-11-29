package entity;

public class GioHang {
	private String maGH;
	private KhachHang khachHang;
	public GioHang() {
		super();
	}
	public GioHang(String maGH) {
		super();
		this.maGH = maGH;
	}
	public String getMaGH() {
		return maGH;
	}
	public void setMaGH(String maGH) {
		this.maGH = maGH;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	@Override
	public String toString() {
		return "GioHang [maGH=" + maGH + ", khachHang=" + khachHang + "]";
	}

}
