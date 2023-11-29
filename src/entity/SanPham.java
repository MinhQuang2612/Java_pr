package entity;
import java.sql.Date;

public class SanPham {
    private String maSP;
    private String tenSP;
    private String baoHanh;
    private Date ngaySX;
    private String tinhTrang;
    private String donViTinh;
    private double donGia;
    private int soLuong;
    private String iMages;

    private LoaiSanPham loaiSanPham;
    private NhaSanXuat nhaSanXuat;

    public SanPham() {
        // TODO Auto-generated constructor stub
    }

    public SanPham(String maSP, String tenSP, String baoHanh, Date ngaySX, String tinhTrang, String donViTinh,
                    double donGia, int soLuong, String iMages) {
        super();
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.baoHanh = baoHanh;
        this.ngaySX = ngaySX;
        this.tinhTrang = tinhTrang;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.iMages = iMages;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getBaoHanh() {
        return baoHanh;
    }

    public void setBaoHanh(String baoHanh) {
        this.baoHanh = baoHanh;
    }

    public Date getNgaySX() {
        return ngaySX;
    }

    public void setNgaySX(Date ngaySX) {
        this.ngaySX = ngaySX;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getiMages() {
        return iMages;
    }

    public void setiMages(String iMages) {
        this.iMages = iMages;
    }

    public LoaiSanPham getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public NhaSanXuat getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(NhaSanXuat nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    @Override
    public String toString() {
        return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", baoHanh=" + baoHanh + ", ngaySX=" + ngaySX
                + ", tinhTrang=" + tinhTrang + ", donViTinh=" + donViTinh + ", donGia=" + donGia + ", soLuong="
                + soLuong + ", iMages=" + iMages + ", loaiSanPham=" + loaiSanPham + ", nhaSanXuat=" + nhaSanXuat
                + "]";
    }
}
