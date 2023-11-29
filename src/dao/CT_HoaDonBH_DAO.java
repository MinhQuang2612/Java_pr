package dao;

import connection.MyConnection;
import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CT_HoaDonBH_DAO {
    private Connection con;

    public CT_HoaDonBH_DAO() {
        con = MyConnection.getInstance().getConnection();
    }
    public boolean addCTHoaDonBH(CT_HoaDonBanHang ctbh) {
        try {
            PreparedStatement ghAdd = con.prepareStatement("INSERT INTO [dbo].[CT_HOADONBANHANG]([MAHDBH],[MASP],[SOLUONG]) VALUES(?,?,?)");
            ghAdd.setString(1,ctbh.getHoaDonBanHang().getMaHDBH());
            ghAdd.setString(2,ctbh.getSanPham().getMaSP());
            ghAdd.setInt(3,ctbh.getSoLuong());

            int n = ghAdd.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<CT_HoaDonBanHang> getLS(String maHDBH){
        HoaDonBanHang_DAO hdbhDao = new HoaDonBanHang_DAO();
        HoaDonBanHang hdbh;
        SanPham_DAO spDao;
        List<CT_HoaDonBanHang> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CT_HOADONBANHANG where MAHDBH = ?");
            stmt.setString(1,maHDBH);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CT_HoaDonBanHang cthdbh = new CT_HoaDonBanHang(rs.getInt(3));
                spDao = new SanPham_DAO();
                SanPham lk = null; DonDatHang ddh = null;
                hdbh = hdbhDao.TimKiemMa(rs.getString(1));
                System.out.println(ddh);
                lk = spDao.TimKiemMa(rs.getString(2));
                System.out.println(lk);
                cthdbh.setSanPham(lk);cthdbh.setHoaDonBanHang(hdbh);
                cthdbh.setThanhTien(lk.getDonGia()* rs.getInt(3));
                ls.add(cthdbh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public boolean deleteCTHDBH() {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM CT_HOADONBANHANG");
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
