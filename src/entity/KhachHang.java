package entity;

import java.sql.Date;

public class KhachHang {
    private String maKH;
    private String tenKH;
    private String gioiTinh;
    private Date ngaySinh;
    private String email;
    private String dienThoai;
    private String CCCD;
    private String diaChi;
    private String iMages;

    public KhachHang() {
        // TODO Auto-generated constructor stub
    }

    public KhachHang(String maKH, String tenKH, String gioiTinh, Date ngaySinh, String email, String dienThoai,
                     String cCCD, String diaChi, String iMages) {
        super();
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.dienThoai = dienThoai;
        CCCD = cCCD;
        this.diaChi = diaChi;
        this.iMages = iMages;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String cCCD) {
        CCCD = cCCD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getiMages() {
        return iMages;
    }

    public void setiMages(String iMages) {
        this.iMages = iMages;
    }

    @Override
    public String toString() {
        return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh
                + ", email=" + email + ", dienThoai=" + dienThoai + ", CCCD=" + CCCD + ", diaChi=" + diaChi
                + ", iMages=" + iMages + "]";
    }



}
