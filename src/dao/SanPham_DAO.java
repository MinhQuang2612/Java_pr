package dao;

import connection.MyConnection;
import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPham_DAO {
    private Connection con;

    public SanPham_DAO() {
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
    public List<SanPham> getLS() {
        List<SanPham> ds = new ArrayList<>();
        LoaiSanPham_DAO loaiSanPham_dao;
        NhaSanXuat_DAO nhaSanXuat_dao;
        try {
            //PreparedStatement stmt = con.prepareStatement("select * from KhachHang");
            ResultSet rs = getResultSet("select_SP");
            while(rs.next()){
                SanPham sp = new SanPham(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                loaiSanPham_dao = new LoaiSanPham_DAO();
                nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiSanPham lsp = null; NhaSanXuat nsx = null;
                lsp = loaiSanPham_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                sp.setLoaiSanPham(lsp);sp.setNhaSanXuat(nsx);
                ds.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addSanPham(SanPham sp) {
    	
        try {
        	
        	System.out.println(sp.getTenSP());
            PreparedStatement spAdd = con.prepareStatement("INSERT INTO SanPham ([MALOAI],[MANHASX],[TENSP],[BAOHANH],[NGAYSANXUAT],[TINHTRANG],[DONVITINH],\n" +
                    "[DONGIA],[SOLUONG],[IMAGES]) VALUES(?,?,?,?,?,?,?,?,?,?)");
            spAdd.setString(1,sp.getLoaiSanPham().getMaLoai());
            spAdd.setString(2,sp.getNhaSanXuat().getMaNhaSX());
         
            spAdd.setString(3,sp.getTenSP());
            spAdd.setString(4,sp.getBaoHanh());
            spAdd.setDate(5,sp.getNgaySX());
            spAdd.setString(6,sp.getTinhTrang());
            spAdd.setString(7,sp.getDonViTinh());
            spAdd.setDouble(8,sp.getDonGia());
            spAdd.setInt(9,sp.getSoLuong());
            spAdd.setString(10,sp.getiMages());
            
            int n = spAdd.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteSP(String maSP) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from SANPHAM where MASP = ?");
            stmt.setString(1, maSP);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateSanPham(String maSP, SanPham sp) {
        String sql = "update SanPham set MALOAI = ? ,MANHASX = ?, "
                + "TENSP = ?,BAOHANH = ? ,NGAYSANXUAT = ?,TINHTRANG = ? ,DONVITINH = ? ,DONGIA = ?,SOLUONG = ? ,IMAGES = ? where MASP = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,sp.getLoaiSanPham().getMaLoai());
            stmt.setString(2,sp.getNhaSanXuat().getMaNhaSX());
            stmt.setString(3,sp.getTenSP());
            stmt.setString(4,sp.getBaoHanh());
            stmt.setDate(5,sp.getNgaySX());
            stmt.setString(6,sp.getTinhTrang());
            stmt.setString(7,sp.getDonViTinh());
            stmt.setDouble(8,sp.getDonGia());
            stmt.setInt(9,sp.getSoLuong());
            stmt.setString(10,sp.getiMages());
            stmt.setString(11, maSP);

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateSoLuong(String maSP, int soLuong) {
        String sql = "update SANPHAM set SOLUONG = ? where MASP = ?";
        try {
            System.out.println("OK1");
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,soLuong);
            stmt.setString(2,maSP);

            int n = stmt.executeUpdate();
            System.out.println("OK2");
            if(n > 0){
                System.out.println("OK3");
                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public SanPham TimKiemMa(String ma){
        SanPham sp = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from SanPham where MASP = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                sp = new SanPham(rs.getString(1),rs.getString(4), rs.getString(5), rs.getDate(6),rs.getString(7), rs.getString(8),
                rs.getDouble(9),rs.getInt(10), rs.getString(11));
                LoaiSanPham_DAO loaiSanPham_dao = new LoaiSanPham_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiSanPham lsp = null; NhaSanXuat nsx = null;
                lsp = loaiSanPham_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                sp.setLoaiSanPham(lsp); sp.setNhaSanXuat(nsx);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return sp;
    }
    
    public List<SanPham> TimKiemMa1(String ma){
    	List<SanPham> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from SanPham where MASP = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                SanPham sp = new SanPham(rs.getString(1),rs.getString(4), rs.getString(5), rs.getDate(6),rs.getString(7), rs.getString(8),
                rs.getDouble(9),rs.getInt(10), rs.getString(11));
                LoaiSanPham_DAO loaiSanPham_dao = new LoaiSanPham_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiSanPham lsp = null; NhaSanXuat nsx = null;
                lsp = loaiSanPham_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                sp.setLoaiSanPham(lsp); sp.setNhaSanXuat(nsx);
                ls.add(sp);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    
    public SanPham TimKiemTen(String ten){
        SanPham sp = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from SanPham where TENSP = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                sp = new SanPham(rs.getString(1),rs.getString(4), rs.getString(5), rs.getDate(6),rs.getString(7), rs.getString(8),
                        rs.getDouble(9),rs.getInt(10), rs.getString(11));
                LoaiSanPham_DAO loaiSanPham_dao = new LoaiSanPham_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiSanPham lsp = null; NhaSanXuat nsx = null;
                lsp = loaiSanPham_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                sp.setLoaiSanPham(lsp); sp.setNhaSanXuat(nsx);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return sp;
    }
    public List<SanPham> TimKiemTen1(String ten){
        List<SanPham> ls = new ArrayList<>();
        try{
        	 PreparedStatement stmt = con.prepareStatement("select * from SANPHAM where TENSP LIKE ?");
             stmt.setString(1, "%" + ten + "%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	SanPham sp = new SanPham(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                LoaiSanPham_DAO loaiSanPham_dao = new LoaiSanPham_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiSanPham lsp = null; NhaSanXuat nsx = null;
                lsp = loaiSanPham_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                sp.setLoaiSanPham(lsp);sp.setNhaSanXuat(nsx);
                ls.add(sp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public List<SanPham> TimKiemLoai(String maLoai){
        List<SanPham> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from SANPHAM where MALOAI = ?");
            stmt.setString(1,maLoai);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	SanPham sp =new SanPham(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                LoaiSanPham_DAO loaiSanPham_dao = new LoaiSanPham_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiSanPham lsp = null; NhaSanXuat nsx = null;
                lsp = loaiSanPham_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                sp.setLoaiSanPham(lsp);sp.setNhaSanXuat(nsx);
                ls.add(sp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public List<SanPham> TimKiemNSX(String maNSX){
        List<SanPham> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from SANPHAM where MANHASX = ?");
            stmt.setString(1,maNSX);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	SanPham sp =new SanPham(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                LoaiSanPham_DAO loaiSanPham_dao = new LoaiSanPham_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiSanPham lsp = null; NhaSanXuat nsx = null;
                lsp = loaiSanPham_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                sp.setLoaiSanPham(lsp);sp.setNhaSanXuat(nsx);
                ls.add(sp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public List<SanPham> TimKiemGia1(double gia1){
        List<SanPham> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from SANPHAM where DONGIA < ?");
            stmt.setDouble(1,gia1);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	SanPham sp =new SanPham(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                LoaiSanPham_DAO loaiSanPham_dao = new LoaiSanPham_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiSanPham lsp = null; NhaSanXuat nsx = null;
                lsp = loaiSanPham_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                sp.setLoaiSanPham(lsp);sp.setNhaSanXuat(nsx);
                ls.add(sp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public List<SanPham> TimKiemGia2(double gia1,double gia2){
        List<SanPham> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from SANPHAM where DONGIA >= ? AND DONGIA < ?");
            stmt.setDouble(1,gia1);
            stmt.setDouble(2,gia2);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	SanPham sp =new SanPham(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                LoaiSanPham_DAO loaiSanPham_dao = new LoaiSanPham_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiSanPham lsp = null; NhaSanXuat nsx = null;
                lsp = loaiSanPham_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                sp.setLoaiSanPham(lsp); sp.setNhaSanXuat(nsx);
                ls.add(sp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public List<SanPham> TimKiemGia3(double gia3){
        List<SanPham> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from SANPHAM where DONGIA > ?");
            stmt.setDouble(1,gia3);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	SanPham sp =new SanPham(rs.getString(1),rs.getString(4),rs.getString(5),rs.getDate(6),
                        rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10),
                        rs.getString(11));
                LoaiSanPham_DAO loaiSanPham_dao = new LoaiSanPham_DAO();
                NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                LoaiSanPham lsp = null; NhaSanXuat nsx = null;
                lsp = loaiSanPham_dao.TimKiemMa(rs.getString(2));
                nsx = nhaSanXuat_dao.TimKiemMa(rs.getString(3));
                sp.setLoaiSanPham(lsp);sp.setNhaSanXuat(nsx);
                ls.add(sp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
}
