package entity;


import java.sql.Date;

public class HoaDonBanHang {
    private String maHDBH;
    private String PTTT;
    private Date ngayLapHD;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private double tongTien;

    public HoaDonBanHang() {
        // TODO Auto-generated constructor stub
    }

    public HoaDonBanHang(String maHDBH, String pTTT, Date ngayLapHD) {
        super();
        this.maHDBH = maHDBH;
        PTTT = pTTT;
        this.ngayLapHD = ngayLapHD;
    }

    public String getMaHDBH() {
        return maHDBH;
    }

    public void setMaHDBH(String maHDBH) {
        this.maHDBH = maHDBH;
    }

    public String getPTTT() {
        return PTTT;
    }

    public void setPTTT(String pTTT) {
        PTTT = pTTT;
    }

    public Date getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(Date ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    @Override
    public String toString() {
        return "HoaDonBanHang [maHDBH=" + maHDBH + ", PTTT=" + PTTT + ", ngayLapHD=" + ngayLapHD + ", nhanVien="
                + nhanVien + ", khachHang=" + khachHang + "]";
    }


}
