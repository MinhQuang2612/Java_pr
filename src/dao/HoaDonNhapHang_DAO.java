package dao;

import connection.MyConnection;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.HoaDonNhapHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HoaDonNhapHang_DAO {
    private Connection con;

    public HoaDonNhapHang_DAO() {
        con = MyConnection.getInstance().getConnection();
    }
    public HoaDonNhapHang TimKiemMa(String ma){
        HoaDonNhapHang hdnh = null;
        NhaCungCap_DAO nccDao;
        NhanVien_DAO nvDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from HoaDonNhapHang where MAHDNH = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                hdnh = new HoaDonNhapHang(rs.getString(1),rs.getDate(4));
                nccDao = new NhaCungCap_DAO();
                NhaCungCap ncc = (nccDao.TimKiemMa(rs.getString(2)));
                hdnh.setNhaCungCap(ncc);
                nvDao = new NhanVien_DAO();
                NhanVien nv = (nvDao.TimKiemMa(rs.getString(3)));
                hdnh.setNhanVien(nv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hdnh;
    }
    public boolean addHoaDonNH(HoaDonNhapHang hdnh) {
        try {
            PreparedStatement dhAdd = con.prepareStatement("INSERT INTO [dbo].[HoaDonNhapHang]([MANHACC],[MANV],[NGAYLAPHD]) VALUES(?,?,?)");
            dhAdd.setString(1,hdnh.getNhaCungCap().getMaNhaCC());
            dhAdd.setString(2,hdnh.getNhanVien().getMaNV());
            dhAdd.setDate(3,hdnh.getNgayLapHD());

            int n = dhAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public String getMa() throws SQLException {
        String ma = "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("SELECT MAX([MAHDNH]) FROM [dbo].[HoaDonNhapHang]");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            ma = rs.getString(1);
        }
        return ma;
    }
    public boolean deleteHDNH() {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM HOADONNHAPHANG");
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
