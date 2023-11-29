package entity;

public class CT_DonDatHang {
    private DonDatHang donDatHang;
    private SanPham sanPham;
    private int soLuong;
    private double thanhTien;

    public CT_DonDatHang() {
        // TODO Auto-generated constructor stub
    }

    public DonDatHang getDonDatHang() {
        return donDatHang;
    }

    public void setDonDatHang(DonDatHang donDatHang) {
        this.donDatHang = donDatHang;
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
        return "CT_DonDatHang [donDatHang=" + donDatHang + ", sanPham=" + sanPham + ", soLuong=" + soLuong
                + ", thanhTien=" + thanhTien + "]";
    }
}
