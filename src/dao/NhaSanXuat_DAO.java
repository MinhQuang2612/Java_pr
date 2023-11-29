package dao;

import connection.MyConnection;
import entity.LoaiSanPham;
import entity.NhaSanXuat;
import entity.SanPham;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaSanXuat_DAO {
    private Connection con;

    public NhaSanXuat_DAO() {
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
    public List<NhaSanXuat> getLS() {
        List<NhaSanXuat> ds = new ArrayList<>();
        try {
            //PreparedStatement stmt = con.prepareStatement("select * from KhachHang");
            ResultSet rs = getResultSet("select_NSX");
            while(rs.next()){
                NhaSanXuat nsx = new NhaSanXuat(rs.getString(1),rs.getString(2),rs.getString(3));
                ds.add(nsx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public NhaSanXuat TimKiemTen(String ten){
        NhaSanXuat nsx = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHASANXUAT where TENNHASX = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                nsx = new NhaSanXuat(rs.getString(1), rs.getString(2),rs.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nsx;
    }
    public NhaSanXuat TimKiemMa(String ma){
        NhaSanXuat nsx = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHASANXUAT where MANHASX = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                nsx = new NhaSanXuat(rs.getString(1), rs.getString(2),rs.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nsx;
    }
    
    public List<NhaSanXuat> TimKiemMa1(String ma){
    	List<NhaSanXuat> ls = new ArrayList<>();
        try{
        	PreparedStatement stmt = con.prepareStatement("select * from NHASANXUAT where MANHASX = ?");
            stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                NhaSanXuat nsx = new NhaSanXuat(rs.getString(1), rs.getString(2),rs.getString(3));
                ls.add(nsx);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    
    public boolean addNhaSanXuat(NhaSanXuat nsx) {
        try {
            PreparedStatement nsxAdd = con.prepareStatement("INSERT INTO NhaSanXuat ([TENNHASX],[DIACHI]) VALUES(?,?)");
            nsxAdd.setString(1,nsx.getTenNhaSX());
            nsxAdd.setString(2,nsx.getDiaChi());

            int n = nsxAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteNSX(String maNSX) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from NhaSanXuat where MANHASX = ?");
            stmt.setString(1, maNSX);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateNhaSX(String maNSX, NhaSanXuat nsx) {
        String sql = "update NhaSanXuat set TENNHASX = ?, DIACHI = ? where MANHASX = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nsx.getTenNhaSX());
            stmt.setString(2,nsx.getDiaChi());
            stmt.setString(3, maNSX);

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
