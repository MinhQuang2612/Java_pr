package entity;

public class CT_HoaDonBanHang {
    private int soLuong;
    private HoaDonBanHang hoaDonBanHang;
    private SanPham sanPham;
    private double thanhTien;
    public CT_HoaDonBanHang() {
        // TODO Auto-generated constructor stub
    }

    public CT_HoaDonBanHang(int soLuong) {
        super();
        this.soLuong = soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public HoaDonBanHang getHoaDonBanHang() {
        return hoaDonBanHang;
    }

    public void setHoaDonBanHang(HoaDonBanHang hoaDonBanHang) {
        this.hoaDonBanHang = hoaDonBanHang;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }
    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }


    @Override
    public String toString() {
        return "CT_HoaDonBanHang [soLuong=" + soLuong + ", hoaDonBanHang=" + hoaDonBanHang + ", sanPham=" + sanPham
                + "]";
    }


}
