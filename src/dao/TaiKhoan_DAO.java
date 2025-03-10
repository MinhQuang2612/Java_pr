package dao;

import connection.MyConnection;

import entity.TaiKhoan;

import java.sql.*;


public class TaiKhoan_DAO {
    private Connection con;

    public TaiKhoan_DAO() {
        con = MyConnection.getInstance().getConnection();
    }
    public TaiKhoan TimKiemTen(String ten){
        TaiKhoan tk = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from TaiKhoan where TENDN = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tk;
    }
    
    public TaiKhoan TimKiemTenKH(String ten){
        TaiKhoan tk = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from TaiKhoan where TENDN = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(4),rs.getString(5), rs.getString(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tk;
    }
    
    public TaiKhoan TimKiemMa(String ma){
        TaiKhoan tk = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from TaiKhoan where MANV = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tk;
    }
    public boolean updateMK(String ma,String mk) {
        String sql = "update TAIKHOAN set MATKHAU = ? where MANV = ?";
        try {
            System.out.println("OK1");
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,mk);
            stmt.setString(2,ma);

            int n = stmt.executeUpdate();
            if(n > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
