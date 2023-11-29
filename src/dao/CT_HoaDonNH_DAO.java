package dao;

import connection.MyConnection;
import entity.DonDatHang;
import entity.SanPham;
import entity.CT_HoaDonNhapHang;
import entity.HoaDonNhapHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CT_HoaDonNH_DAO {
    private Connection con;

    public CT_HoaDonNH_DAO() {
        con = MyConnection.getInstance().getConnection();
    }
    public boolean addCTHoaDonNH(CT_HoaDonNhapHang ctnh) {
        try {
            PreparedStatement ghAdd = con.prepareStatement("INSERT INTO [dbo].[CT_HOADONNHAPHANG]([MAHDNH],[MASP],[SOLUONG],[DONGIA]) VALUES(?,?,?,?)");
            ghAdd.setString(1,ctnh.getDonNhapHang().getMaHDNH());
            ghAdd.setString(2,ctnh.getSanPham().getMaSP());
            ghAdd.setInt(3,ctnh.getSoLuong());
            ghAdd.setDouble(4,ctnh.getDonGia());

            int n = ghAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<CT_HoaDonNhapHang> getLS(String maHDNH){
        HoaDonNhapHang_DAO hdnhDao = new HoaDonNhapHang_DAO();
        HoaDonNhapHang hdnh;
        SanPham_DAO lkDao;
        List<CT_HoaDonNhapHang> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CT_HOADONNHAPHANG where MAHDNH = ?");
            stmt.setString(1,maHDNH);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CT_HoaDonNhapHang cthdnh = new CT_HoaDonNhapHang(rs.getInt(3),rs.getDouble(4));
                lkDao = new SanPham_DAO();
                SanPham sp = null; DonDatHang ddh = null;
                hdnh = hdnhDao.TimKiemMa(rs.getString(1));
                System.out.println(ddh);
                sp = lkDao.TimKiemMa(rs.getString(2));
                System.out.println(sp);
                cthdnh.setSanPham(sp);cthdnh.setDonNhapHang(hdnh);
                cthdnh.setThanhTien(sp.getDonGia()* rs.getInt(3));
                ls.add(cthdnh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public boolean deleteCTHDNH() {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM CT_HOADONNHAPHANG");
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
