package entity;

public class TaiKhoan {
    private String tenDN;
    private String matKhau;
    private String ma;
    private String ten;
    private String quyen;

    public TaiKhoan() {
        // TODO Auto-generated constructor stub
    }

    public TaiKhoan(String tenDN, String matKhau, String ma, String ten, String quyen) {
        super();
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.ma = ma;
        this.ten = ten;
        this.quyen = quyen;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    @Override
    public String toString() {
        return "TaiKhoan [tenDN=" + tenDN + ", matKhau=" + matKhau + ", ma=" + ma + ", ten=" + ten + ", quyen=" + quyen
                + "]";
    }

}
