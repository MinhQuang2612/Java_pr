package entity;

public class CT_HoaDonNhapHang {
    private HoaDonNhapHang donNhapHang;
    private SanPham sanPham;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public CT_HoaDonNhapHang() {
        // TODO Auto-generated constructor stub
    }

    public CT_HoaDonNhapHang(int soLuong, double donGia) {
        super();
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }

    public HoaDonNhapHang getDonNhapHang() {
        return donNhapHang;
    }

    public void setDonNhapHang(HoaDonNhapHang donNhapHang) {
        this.donNhapHang = donNhapHang;
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

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "CT_HoaDonNhapHang [donNhapHang=" + donNhapHang + ", sanPham=" + sanPham + ", soLuong=" + soLuong
                + ", donGia=" + donGia + ", thanhTien=" + thanhTien + "]";
    }






}

