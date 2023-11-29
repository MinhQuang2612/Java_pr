package dao;

import connection.MyConnection;
import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CT_DonDatHang_DAO {
    private Connection con;

    public CT_DonDatHang_DAO() {

        con = MyConnection.getInstance().getConnection();
    }
    public ResultSet getResultSet(String StoreName)throws Exception {
        ResultSet rs = null;
        try {
            String callStore;
            callStore = "{Call " + StoreName +"}";
            CallableStatement cs = this.con.prepareCall(callStore);
            cs.executeQuery();
            rs = cs.getResultSet();
        } catch (Exception e) {
            throw new Exception("Error get Store " + e.getMessage());
        }
        return rs;
    }
    public List<CT_DonDatHang> getLS() {
        DonDatHang_DAO ddhDao;
        SanPham_DAO spDao;
        List<CT_DonDatHang> ls = new ArrayList<>();
        try {
            //PreparedStatement stmt = con.prepareStatement("select * from KhachHang");
            ResultSet rs = getResultSet("select_CTDDH");
            while(rs.next()){
                CT_DonDatHang ctddh = new CT_DonDatHang();
                ddhDao = new DonDatHang_DAO();
                spDao = new SanPham_DAO();
                SanPham sp = null; DonDatHang ddh = null;
                ddh = ddhDao.TimKiemMa(rs.getString(1));
                System.out.println(ddh);
                sp = spDao.TimKiemMa(rs.getString(2));
                System.out.println(sp);
                ctddh.setSanPham(sp);ctddh.setDonDatHang(ddh);
                ls.add(ctddh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    public boolean addCTDonDatHang(CT_DonDatHang ctddh) {
        try {
            PreparedStatement ghAdd = con.prepareStatement("INSERT INTO [dbo].[CT_DonDatHang]([MADDH],[MASP],[SOLUONG]) VALUES(?,?,?)");
            ghAdd.setString(1,ctddh.getDonDatHang().getMaDDH());
            ghAdd.setString(2,ctddh.getSanPham().getMaSP());
            ghAdd.setInt(3,ctddh.getSoLuong());

            int n = ghAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
   
    public boolean deleteCTDH_TheoMa(String maDHH,String maSP) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from CT_DONDATHANG where MADDH = ? AND MASP = ?");
            stmt.setString(1, maDHH);
            stmt.setString(2, maSP);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
 
    public CT_DonDatHang TimTheoMaLK(String maSP){
        DonDatHang_DAO ddhDao;
        SanPham_DAO spDao;
        CT_DonDatHang ctdh = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CT_DONDATHANG where MASP = ?");
            stmt.setString(1,maSP);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                ctdh = new CT_DonDatHang();
                ddhDao = new DonDatHang_DAO();
                spDao = new SanPham_DAO();
                SanPham sp = null; DonDatHang ddh = null;
                ddh = ddhDao.TimKiemMa(rs.getString(1));
                System.out.println(ddh);
                sp = spDao.TimKiemMa(rs.getString(2));
                System.out.println(sp);
                ctdh.setSanPham(sp);ctdh.setDonDatHang(ddh);
                ctdh.setThanhTien(sp.getDonGia()* rs.getInt(3));
                ctdh.setSoLuong(rs.getInt(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ctdh;
    }
    public List<CT_DonDatHang> getLS(String maDDH){
        DonDatHang_DAO ddhDao;
        SanPham_DAO spDao;
        List<CT_DonDatHang> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CT_DONDATHANG where MADDH = ?");
            stmt.setString(1,maDDH);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CT_DonDatHang ctddh = new CT_DonDatHang();
                ddhDao = new DonDatHang_DAO();
                spDao = new SanPham_DAO();
                SanPham sp = null; DonDatHang ddh = null;
                ddh = ddhDao.TimKiemMa(rs.getString(1));
                System.out.println(ddh);
                sp = spDao.TimKiemMa(rs.getString(2));
                System.out.println(sp);
                ctddh.setSanPham(sp);ctddh.setDonDatHang(ddh);
                ctddh.setThanhTien(sp.getDonGia()* rs.getInt(3));
                ctddh.setSoLuong(rs.getInt(3));
                ls.add(ctddh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
}
