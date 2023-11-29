package dao;

import connection.MyConnection;
import entity.DonDatHang;
import entity.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonDatHang_DAO {
    private Connection con;

    public DonDatHang_DAO() {
        con = MyConnection.getInstance().getConnection();
    }
    public DonDatHang TimKiemMa(String ma){
        DonDatHang dh = null;
        KhachHang_DAO khDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from DonDatHang where MADDH = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                dh = new DonDatHang(rs.getString(1),rs.getString(3),rs.getString(4),rs.getDate(5));
                khDao = new KhachHang_DAO();
                KhachHang kh = (khDao.TimKiemMa(rs.getString(2)));
                dh.setKhachHang(kh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dh;
    }
    public List<DonDatHang> getLS(String maKH){
        List<DonDatHang> ls = new ArrayList<>();
        KhachHang_DAO khDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from DonDatHang where MAKH = ?");
            stmt.setString(1,maKH);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                DonDatHang dh = new DonDatHang(rs.getString(1),rs.getString(3),rs.getString(4),rs.getDate(5));
                khDao = new KhachHang_DAO();
                KhachHang kh = (khDao.TimKiemMa(rs.getString(2)));
                dh.setKhachHang(kh);
                ls.add(dh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }

    public boolean addDonDatHang(DonDatHang dh) {
        try {
                PreparedStatement dhAdd = con.prepareStatement("INSERT INTO DONDATHANG([MAKH],[PTTT],[TINHTRANG],[NGAYDATHANG]) VALUES(?,?,?,?)");
            dhAdd.setString(1,dh.getKhachHang().getMaKH());
            dhAdd.setString(2,dh.getPTTT());
            dhAdd.setString(3,dh.getTinhTrang());
            dhAdd.setDate(4,dh.getNgayDatHang());

            int n = dhAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteDH_TheoMa(String maDH) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from DonDatHang where MADDH = ?");
            stmt.setString(1, maDH);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public DonDatHang TimTheoMaKH(String maKH){
        DonDatHang dh = null;
        KhachHang_DAO khDao;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from DonDatHang where MAKH = ?");
            stmt.setString(1,maKH);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                dh = new DonDatHang(rs.getString(1),rs.getString(3),rs.getString(4),rs.getDate(5));
                khDao = new KhachHang_DAO();
                KhachHang kh = (khDao.TimKiemMa(rs.getString(2)));
                dh.setKhachHang(kh);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dh;
    }

}
