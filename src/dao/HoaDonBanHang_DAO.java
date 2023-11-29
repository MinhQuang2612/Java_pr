package dao;

import connection.MyConnection;
import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonBanHang_DAO {
    private Connection con;

    public HoaDonBanHang_DAO() {
        con = MyConnection.getInstance().getConnection();
    }
    public HoaDonBanHang TimKiemMa(String ma){
        HoaDonBanHang hdbh = null;
        KhachHang_DAO khDao;
        NhanVien_DAO nvDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from HoaDonBanHang where MAHDBH = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                hdbh = new HoaDonBanHang(rs.getString(1),rs.getString(4),rs.getDate(5));
                khDao = new KhachHang_DAO();
                KhachHang kh = (khDao.TimKiemMa(rs.getString(2)));
                hdbh.setKhachHang(kh);
                nvDao = new NhanVien_DAO();
                NhanVien nv = (nvDao.TimKiemMa(rs.getString(3)));
                hdbh.setNhanVien(nv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hdbh;
    }
    public List<HoaDonBanHang> getLS(){
    	List<HoaDonBanHang> ls = new ArrayList<HoaDonBanHang>();
        KhachHang_DAO khDao;
        NhanVien_DAO nvDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from HoaDonBanHang");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	HoaDonBanHang hdbh = new HoaDonBanHang(rs.getString(1),rs.getString(4),rs.getDate(5));
                khDao = new KhachHang_DAO();
                KhachHang kh = (khDao.TimKiemMa(rs.getString(2)));
                hdbh.setKhachHang(kh);
                nvDao = new NhanVien_DAO();
                NhanVien nv = (nvDao.TimKiemMa(rs.getString(3)));
                hdbh.setNhanVien(nv);
                ls.add(hdbh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public boolean addHoaDonBH(HoaDonBanHang hdbh) {
        try {
            PreparedStatement dhAdd = con.prepareStatement("INSERT INTO [dbo].[HoaDonBanHang]([MAKH],[MANV],[PTTT],[NGAYLAPHD]) VALUES(?,?,?,?)");
            dhAdd.setString(1,hdbh.getKhachHang().getMaKH());
            dhAdd.setString(2,hdbh.getNhanVien().getMaNV());
            dhAdd.setString(3,hdbh.getPTTT());
            dhAdd.setDate(4,hdbh.getNgayLapHD());

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
            stmt = con.prepareStatement("SELECT MAX([MAHDBH]) FROM [dbo].[HoaDonBanHang]");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            ma = rs.getString(1);
        }
        return ma;
    }
    public boolean deleteHDBH() {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM HOADONBANHANG");
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
