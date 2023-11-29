package entity;
import java.sql.Date;

public class DonDatHang {
    private String maDDH;
    private String PTTT;
    private String tinhTrang;
    private Date ngayDatHang;
    private KhachHang khachHang;

    public DonDatHang() {
        // TODO Auto-generated constructor stub
    }

    public DonDatHang(String maDDH, String pTTT, String tinhTrang, Date ngayDatHang) {
        super();
        this.maDDH = maDDH;
        PTTT = pTTT;
        this.tinhTrang = tinhTrang;
        this.ngayDatHang = ngayDatHang;
    }

    public String getMaDDH() {
        return maDDH;
    }

    public void setMaDDH(String maDDH) {
        this.maDDH = maDDH;
    }

    public String getPTTT() {
        return PTTT;
    }

    public void setPTTT(String pTTT) {
        PTTT = pTTT;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Date getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(Date ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    @Override
    public String toString() {
        return "DonDatHang [maDDH=" + maDDH + ", PTTT=" + PTTT + ", tinhTrang=" + tinhTrang + ", ngayDatHang="
                + ngayDatHang + ", khachHang=" + khachHang + "]";
    }

}