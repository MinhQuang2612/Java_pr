package entity;

public class NhaCungCap {
    private String maNhaCC;
    private String tenNhaCC;
    private String soDT;
    private String eMail;
    private String diaChi;
    private String iMages;


    public NhaCungCap() {
        // TODO Auto-generated constructor stub
    }


    public NhaCungCap(String maNhaCC, String tenNhaCC, String diaChi, String soDT, String eMail, String iMages) {
        super();
        this.maNhaCC = maNhaCC;
        this.tenNhaCC = tenNhaCC;
        this.soDT = soDT;
        this.eMail = eMail;
        this.diaChi = diaChi;
        this.iMages = iMages;
    }


    public String getMaNhaCC() {
        return maNhaCC;
    }


    public void setMaNhaCC(String maNhaCC) {
        this.maNhaCC = maNhaCC;
    }


    public String getTenNhaCC() {
        return tenNhaCC;
    }


    public void setTenNhaCC(String tenNhaCC) {
        this.tenNhaCC = tenNhaCC;
    }


    public String getSoDT() {
        return soDT;
    }


    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }


    public String geteMail() {
        return eMail;
    }


    public void seteMail(String eMail) {
        this.eMail = eMail;
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
        return "NhaCungCap [maNhaCC=" + maNhaCC + ", tenNhaCC=" + tenNhaCC + ", soDT=" + soDT + ", eMail=" + eMail
                + ", diaChi=" + diaChi + ", iMages=" + iMages + "]";
    }


}