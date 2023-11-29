package dao;

import connection.MyConnection;
import entity.LoaiSanPham;
import entity.NhaSanXuat;
import entity.SanPham;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaiSanPham_DAO {
    private Connection con;

    public LoaiSanPham_DAO() {
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
    public List<LoaiSanPham> getLS() {
        List<LoaiSanPham> ds = new ArrayList<>();
        try {
            //PreparedStatement stmt = con.prepareStatement("select * from KhachHang");
            ResultSet rs = getResultSet("select_LSP");
            while(rs.next()){
                LoaiSanPham lsp = new LoaiSanPham(rs.getString(1),rs.getString(2));
                ds.add(lsp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public LoaiSanPham TimKiemTen(String ten){
        LoaiSanPham lsp = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LoaiSanPham where TENLOAI = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                lsp = new LoaiSanPham(rs.getString(1), rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lsp;
    }
    public LoaiSanPham TimKiemMa(String ma){
        LoaiSanPham lsp = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LoaiSanPham where MALOAI = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                lsp = new LoaiSanPham(rs.getString(1), rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lsp;
    }
    
    public List<LoaiSanPham> TimKiemMa1(String ma){
    	List<LoaiSanPham> ls = new ArrayList<>();
    	try{
            PreparedStatement stmt = con.prepareStatement("select * from LoaiSanPham where MALOAI = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham(rs.getString(1), rs.getString(2));
                ls.add(lsp);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    
    public boolean addLoaiSanPham(LoaiSanPham lsp) {
        try {
            PreparedStatement lspAdd = con.prepareStatement("INSERT INTO LoaiSanPham ([TENLOAI]) VALUES(?)");
            lspAdd.setString(1,lsp.getTenLoai());

            int n = lspAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteLSP(String maLSP) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from LoaiSanPham where MALOAI = ?");
            stmt.setString(1, maLSP);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateLoaiSP(String maLOAI, LoaiSanPham lsp) {
        String sql = "update LoaiSanPham set TENLOAI = ? where MALOAI = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,lsp.getTenLoai());
            stmt.setString(2, maLOAI);

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


}
