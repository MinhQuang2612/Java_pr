package dao;

import connection.MyConnection;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhaSanXuat;
import entity.SanPham;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHang_DAO {
    private Connection con;

    public KhachHang_DAO() {
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
    public List<KhachHang> getLS() {
        List<KhachHang> ds = new ArrayList<>();
        try {
            //PreparedStatement stmt = con.prepareStatement("select * from KhachHang");
            ResultSet rs = getResultSet("select_KH");
            while(rs.next()){
                KhachHang kh =new KhachHang(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                ds.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addKhachHang(KhachHang kh) {
        try {
            PreparedStatement khAdd = con.prepareStatement("INSERT INTO KhachHang ([TENKH],[GIOITINH],[NGAYSINH]," +
                    "[EMAIL],[DIENTHOAI],[CCCD],[DIACHI],[IMAGES]) VALUES(?,?,?,?,?,?,?,?)");
            khAdd.setString(1,kh.getTenKH());
            khAdd.setString(2,kh.getGioiTinh());
            khAdd.setDate(3,kh.getNgaySinh());
            khAdd.setString(4,kh.getEmail());
            khAdd.setString(5,kh.getDienThoai());
            khAdd.setString(6,kh.getCCCD());
            khAdd.setString(7,kh.getDiaChi());
            khAdd.setString(8,kh.getiMages());

            int n = khAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteKH(String maKH) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from KhachHang where MAKH = ?");
            stmt.setString(1, maKH);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateKhachHang(String maKH, KhachHang kh) {
        String sql = "update KhachHang set TENKH = ?, "
                + "GIOITINH = ?,NGAYSINH = ? ,EMAIL = ?,DIENTHOAI = ? ,CCCD = ? ,DIACHI = ?,IMAGES = ? where MAKH = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, kh.getTenKH());
            stmt.setString(2, kh.getGioiTinh());
            stmt.setDate(3, kh.getNgaySinh());
            stmt.setString(4,kh.getEmail());
            stmt.setString(5,kh.getDienThoai());
            stmt.setString(6,kh.getCCCD());
            stmt.setString(7,kh.getDiaChi());
            stmt.setString(8,kh.getiMages());
            stmt.setString(9, maKH);

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public KhachHang TimKiemMa(String ma){
        KhachHang kh = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from KhachHang where MAKH = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                kh = new KhachHang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getDate(4),rs.getString(5), rs.getString(6),
                        rs.getString(7),rs.getString(8), rs.getString(9));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return kh;
    }
    
    public KhachHang TimKiemSDT(String sdt){
        KhachHang kh = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from KhachHang where DIENTHOAI = ?");
            stmt.setString(1, sdt);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                kh = new KhachHang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getDate(4),rs.getString(5), rs.getString(6),
                        rs.getString(7),rs.getString(8), rs.getString(9));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return kh;
    }
    
    public List<KhachHang> TimKiemSDT1(String sdt){
    	List<KhachHang> ls = new ArrayList<>();
    	try{
            PreparedStatement stmt = con.prepareStatement("select * from KhachHang where DIENTHOAI = ?");
            stmt.setString(1, sdt);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getDate(4),rs.getString(5), rs.getString(6),
                        rs.getString(7),rs.getString(8), rs.getString(9));
                ls.add(kh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    
    public KhachHang TimKiemTen(String ten){
        KhachHang kh = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from KhachHang where TENKH = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                kh = new KhachHang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getDate(4),rs.getString(5), rs.getString(6),
                        rs.getString(7),rs.getString(8), rs.getString(9));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return kh;
    }
    public KhachHang TimKiemCM(String cm){
        KhachHang kh = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from KhachHang where CCCD = ?");
            stmt.setString(1,cm);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                kh = new KhachHang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getDate(4),rs.getString(5), rs.getString(6),
                        rs.getString(7),rs.getString(8), rs.getString(9));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return kh;
    }
    public List<KhachHang> TimKiemTen1(String ten){
        List<KhachHang> ls = new ArrayList<>();
        try{
        	PreparedStatement stmt = con.prepareStatement("select * from KHACHHANG where TENKH LIKE ?");
            stmt.setString(1, "%" + ten + "%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getDate(4),rs.getString(5), rs.getString(6),
                        rs.getString(7),rs.getString(8), rs.getString(9));
                ls.add(kh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
//    public List<NhanVien> TimKiemTenDC1(String diaChi){
//        List<NhanVien> ls = new ArrayList<>();
//        ChucVu_DAO cvDao;
//        try{
//            PreparedStatement stmt = con.prepareStatement("select * from NHANVIEN where CONTAINS(DIACHI, ?)");
//            stmt.setString(1,diaChi);
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next()) {
//                NhanVien nv = new NhanVien(rs.getString(1),rs.getString(3),rs.getString(4),rs.getDate(5),
//                        rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),
//                        rs.getDate(10),rs.getString(11));
//                cvDao = new ChucVu_DAO();
//                System.out.println(rs.getString(2));
//                ChucVu cv = null;
//                cv = cvDao.TimKiemMa(rs.getString(2));
//                System.out.println(cv);
//                nv.setChucVu(cv);
//                ls.add(nv);
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return ls;
//    }
    public List<KhachHang> TimKiemDC2(String diaChi){
        String dc = "%".concat(diaChi).concat("%");
        System.out.println(dc);
        List<KhachHang> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from KHACHHANG where DIACHI LIKE ?");
//            stmt.setString(1,"N");
            stmt.setString(1,dc);
            ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    KhachHang kh = new KhachHang(rs.getString(1),rs.getString(2), rs.getString(3),
                            rs.getDate(4),rs.getString(5), rs.getString(6),
                            rs.getString(7),rs.getString(8), rs.getString(9));
                    ls.add(kh);
                }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public List<KhachHang> TimKiemGT(String gTinh){
        List<KhachHang> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from KHACHHANG where GIOITINH = ?");
            stmt.setString(1,gTinh);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getDate(4),rs.getString(5), rs.getString(6),
                        rs.getString(7),rs.getString(8), rs.getString(9));
                ls.add(kh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
//    SELECT * FROM [dbo].[KhachHang] WHERE YEAR(GETDATE()) - YEAR(NGAYSINH) BETWEEN 18 and 23
    public List<KhachHang> TimKiemTuoi1(int tuoi1){
        List<KhachHang> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM [dbo].[KhachHang] WHERE YEAR(GETDATE()) - YEAR(NGAYSINH) < ?");
            stmt.setInt(1,tuoi1);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getDate(4),rs.getString(5), rs.getString(6),
                        rs.getString(7),rs.getString(8), rs.getString(9));
                ls.add(kh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    public List<KhachHang> TimKiemTuoi2(int tuoi1,int tuoi2){
        List<KhachHang> ls = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM [dbo].[KhachHang] WHERE YEAR(GETDATE()) - YEAR(NGAYSINH) BETWEEN ? and ?");
            stmt.setInt(1,tuoi1);
            stmt.setInt(2,tuoi2);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getDate(4),rs.getString(5), rs.getString(6),
                        rs.getString(7),rs.getString(8), rs.getString(9));
                ls.add(kh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
}
