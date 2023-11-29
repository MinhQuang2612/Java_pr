package entity;

public class NhaSanXuat {
    private String maNhaSX;
    private String tenNhaSX;
    private String diaChi;
    public NhaSanXuat() {
        // TODO Auto-generated constructor stub
    }
    public NhaSanXuat(String maNhaSX, String tenNhaSX, String diaChi) {
        super();
        this.maNhaSX = maNhaSX;
        this.tenNhaSX = tenNhaSX;
        this.diaChi = diaChi;
    }
    public String getMaNhaSX() {
        return maNhaSX;
    }
    public void setMaNhaSX(String maNhaSX) {
        this.maNhaSX = maNhaSX;
    }
    public String getTenNhaSX() {
        return tenNhaSX;
    }
    public void setTenNhaSX(String tenNhaSX) {
        this.tenNhaSX = tenNhaSX;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    @Override
    public String toString() {
        return "NhaSanXuat [maNhaSX=" + maNhaSX + ", tenNhaSX=" + tenNhaSX + ", diaChi=" + diaChi + "]";
    }

}
