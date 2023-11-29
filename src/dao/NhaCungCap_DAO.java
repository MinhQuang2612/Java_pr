package dao;

import connection.MyConnection;
import entity.NhaCungCap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCap_DAO {
    private Connection con;

    public NhaCungCap_DAO() {
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
    public List<NhaCungCap> getLS() {
        List<NhaCungCap> ds = new ArrayList<>();
        try {
            //PreparedStatement stmt = con.prepareStatement("select * from KhachHang");
            ResultSet rs = getResultSet("select_NCC");
            while(rs.next()){
                NhaCungCap ncc = new NhaCungCap(rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(3),rs.getString(6));
                ds.add(ncc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addNhaCungCap(NhaCungCap ncc) {
        try {
            PreparedStatement nccAdd = con.prepareStatement("INSERT INTO NhaCungCap ([TENNHACC],[DIACHI],[SODT]," +
                    "[EMAIL],[IMAGES]) VALUES(?,?,?,?,?)");
            nccAdd.setString(1,ncc.getTenNhaCC());
            nccAdd.setString(2,ncc.getDiaChi());
            nccAdd.setString(3,ncc.getSoDT());
            nccAdd.setString(4,ncc.geteMail());
            nccAdd.setString(5,ncc.getiMages());

            int n = nccAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteNCC(String maNCC) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from NhaCungCap where MANHACC = ?");
            stmt.setString(1, maNCC);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateNhaCC(String maNCC, NhaCungCap ncc) {
        String sql = "update NhaCungCap set TENNHACC = ?, "
                + "DIACHI = ?,SODT = ? ,EMAIL = ?,IMAGES = ? where MANHACC = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,ncc.getTenNhaCC());
            stmt.setString(2,ncc.getDiaChi());
            stmt.setString(3,ncc.getSoDT());
            stmt.setString(4,ncc.geteMail());
            stmt.setString(5,ncc.getiMages());
            stmt.setString(6, maNCC);

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public NhaCungCap TimKiemMa(String ma){
        NhaCungCap ncc = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NhaCungCap where MANHACC = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                ncc = new NhaCungCap(rs.getString(1),rs.getString(2), rs.getString(3),
                       rs.getString(4), rs.getString(5),
                        rs.getString(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ncc;
    }
    
    public NhaCungCap TimKiemSDT(String sdt){
        NhaCungCap ncc = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NhaCungCap where SODT = ?");
            stmt.setString(1, sdt);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                ncc = new NhaCungCap(rs.getString(1),rs.getString(2), rs.getString(3),
                       rs.getString(4), rs.getString(5),
                        rs.getString(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ncc;
    }

    public NhaCungCap TimKiemTen(String ten){
        NhaCungCap ncc = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHACUNGCAP where TENNHACC = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                ncc = new NhaCungCap(rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(3),rs.getString(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ncc;
    }

    public List<NhaCungCap> TimKiemTen1(String ten){
        List<NhaCungCap> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHACUNGCAP where TENNHACC LIKE ?");
            stmt.setString(1, "%" + ten + "%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                NhaCungCap ncc = new NhaCungCap(rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(3),rs.getString(6));
                ls.add(ncc);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    
    public List<NhaCungCap> TimKiemSDT1(String soDT){
    	List<NhaCungCap> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NhaCungCap where SODT = ?");
            stmt.setString(1, soDT);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                NhaCungCap ncc = new NhaCungCap(rs.getString(1),rs.getString(2), rs.getString(3),
                       rs.getString(4), rs.getString(5),
                        rs.getString(6));
                ls.add(ncc);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public List<NhaCungCap> TimKiemDC2(String diaChi){
        String dc = "%".concat(diaChi).concat("%");
        System.out.println(dc);
        List<NhaCungCap> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHACUNGCAP where DIACHI LIKE ?");
//            stmt.setString(1,"N");
            stmt.setString(1,dc);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                NhaCungCap ncc = new NhaCungCap(rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5),rs.getString(3),rs.getString(6));
                ls.add(ncc);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
}
