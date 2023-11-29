package entity;

import java.sql.Date;

public class NhanVien {
    private String maNV;
    private String tenNV;
    private String gioiTinh;
    private Date ngaySinh;
    private String email;
    private String dienThoai;
    private String CCCD;
    private String diaChi;
    private ChucVu chucVu;
    private Date ngayVaoLam;
    private String iMages;

    public NhanVien() {
        // TODO Auto-generated constructor stub
    }

    public NhanVien(String maNV, String tenNV, String gioiTinh, Date ngaySinh, String email, String dienThoai,
                    String cCCD, String diaChi, Date ngayVaoLam, String iMages) {
        super();
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.dienThoai = dienThoai;
        CCCD = cCCD;
        this.diaChi = diaChi;
        this.ngayVaoLam = ngayVaoLam;
        this.iMages = iMages;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
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

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getiMages() {
        return iMages;
    }

    public void setiMages(String iMages) {
        this.iMages = iMages;
    }

    @Override
    public String toString() {
        return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh
                + ", email=" + email + ", dienThoai=" + dienThoai + ", CCCD=" + CCCD + ", diaChi=" + diaChi
                + ", chucVu=" + chucVu + ", ngayVaoLam=" + ngayVaoLam + ", iMages=" + iMages + "]";
    }
}
