package dao;

import connection.MyConnection;
import entity.GioHang;
import entity.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GioHang_DAO {
    private Connection con;

    public GioHang_DAO() {
        con = MyConnection.getInstance().getConnection();
    }
    public GioHang TimKiemMa(String ma){
        GioHang gh = null;
        KhachHang_DAO khDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from GioHang where MAGH = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                gh = new GioHang(rs.getString(1));
                khDao = new KhachHang_DAO();
                KhachHang kh = (khDao.TimKiemMa(rs.getString(2)));
                gh.setKhachHang(kh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return gh;
    }
    public GioHang TimKiemMaKH(String maKH){
        GioHang gh = null;
        KhachHang_DAO khDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from GioHang where MAKH = ?");
            stmt.setString(1,maKH);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                gh = new GioHang(rs.getString(1));
                khDao = new KhachHang_DAO();
                KhachHang kh = (khDao.TimKiemMa(rs.getString(2)));
                gh.setKhachHang(kh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return gh;
    }
}
