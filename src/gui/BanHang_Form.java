package gui;

import TableModel.CT_DonDatHang_tableModel;
import TableModel.CT_GioHang_TableModel;
import TableModel.CT_HoaDonBH_tableModel;
import TableModel.SP_TableModel;
import com.toedter.calendar.JDateChooser;
import dao.*;
import entity.*;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JRDesignQuery;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class BanHang_Form extends JPanel {
    JPanel pnNorth,pnCenter,pnNorth2,pnSouth;
    JLabel lblMaHD,lblNgayXuat,lblMaNV,lblTenNV,lblMaKH,lblTenKH,lblSDT,lblMail,lblDiaChi,lblPT;
    JDateChooser NgayXuatHD;
    JTextField txtMahD,txtMaNV,txtTenNV,txtMaKH,txtTenKH,txtSDT,txtMail,txtDiaChi,txtTongTien;
    JButton btnCapNhatSL,btnXoa,btnDatHang,btnQuayLai,btnChonHang;
    JRadioButton rbTT,rbOL;
    ButtonGroup btnGroup;
    SanPham_DAO spDao;
    SP_TableModel tableModel;
    NhanVien nv;
    KhachHang_DAO khDao;
    KhachHang kh;
    String maHDBH = "";
    double tongTien = 0;
    double tienKhach = 0;

    public BanHang_Form(){

    }
    public void doShow(){
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        //pnNorth1 - Tiêu Đề
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("HÓA ĐƠN BÁN HÀNG");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 18));
        lblTieuDe.setForeground(Color.BLUE);
        pnNorth1.add(lblTieuDe);
        pnNorth.add(pnNorth1,BorderLayout.NORTH);
        //pnNorth2 - Thông Tin
        pnNorth2 = new JPanel();
        Box bx,bxHoaDon,bxNhanVien,bxKhachHang,b1,b2,b3;
        bx = Box.createVerticalBox();
        bx.setPreferredSize(new Dimension(900,160));
        bx.add(bxHoaDon = Box.createHorizontalBox());
        bxHoaDon.add(lblMaHD = new JLabel("Mã Hóa Đơn: "));
        bxHoaDon.add(txtMahD = new JTextField());
        txtMahD.setPreferredSize(new Dimension(70,20));
        bxHoaDon.add(Box.createHorizontalStrut(30));
        bxHoaDon.add(lblNgayXuat = new JLabel("Ngày Xuất Hóa Đơn:  "));
        NgayXuatHD = new JDateChooser();
        NgayXuatHD.setSize(new Dimension(30,20));
        NgayXuatHD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        NgayXuatHD.setDateFormatString("dd/MM/yyyy");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = java.sql.Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            NgayXuatHD.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bxHoaDon.add(NgayXuatHD);
        bx.add(Box.createVerticalStrut(10));
        bx.add(bxNhanVien = Box.createHorizontalBox());
        bxNhanVien.add(lblMaNV = new JLabel("Mã Nhân Viên: "));
        bxNhanVien.add(txtMaNV = new JTextField(30));
        bxNhanVien.add(Box.createHorizontalStrut(30));
        bxNhanVien.add(lblTenNV = new JLabel("Tên Nhân Viên: "));
        bxNhanVien.add(txtTenNV = new JTextField(30));
        bx.add(Box.createVerticalStrut(10));
        bx.add(bxKhachHang = Box.createVerticalBox());
        bxKhachHang.add(b1 = Box.createHorizontalBox());
        b1.add(lblMaKH = new JLabel("Mã Khách Hàng: "));
        b1.add(txtMaKH = new JTextField(30));
        b1.add(Box.createHorizontalStrut(30));
        b1.add(lblTenKH = new JLabel("Tên Khách Hàng: "));
        b1.add(txtTenKH = new JTextField(30));

        bxKhachHang.add(Box.createVerticalStrut(10));
        bxKhachHang.add(b2 = Box.createHorizontalBox());
        b2.add(lblSDT = new JLabel("Số Điện Thoại: "));
        b2.add(txtSDT = new JTextField(30));
        b2.add(Box.createHorizontalStrut(30));
        b2.add(lblMail = new JLabel("Email: "));
        b2.add(txtMail = new JTextField(30));
        bxKhachHang.add(Box.createVerticalStrut(10));
        bxKhachHang.add(b3 = Box.createHorizontalBox());
        b3.add(lblDiaChi = new JLabel("Địa Chỉ: "));
        b3.add(txtDiaChi = new JTextField(30));
        b3.add(Box.createHorizontalStrut(30));
        b3.add(lblPT = new JLabel("Phương Thức Thanh Toán: "));
        btnGroup = new ButtonGroup();
        b3.add(rbTT = new JRadioButton("Thanh Toán Trực Tiếp"));
        b3.add(rbOL = new JRadioButton("Thanh Toán Online"));
        btnGroup.add(rbTT);
        btnGroup.add(rbOL);

//        bxKhachHang.setBorder(new TitledBorder("Thông Tin Khách Hàng"));
        pnNorth2.add(bx);
        pnNorth.add(pnNorth2,BorderLayout.CENTER);

        lblMaHD.setPreferredSize(lblNgayXuat.getPreferredSize());
        lblMaNV.setPreferredSize(lblNgayXuat.getPreferredSize());
        lblMaKH.setPreferredSize(lblNgayXuat.getPreferredSize());
        lblMail.setPreferredSize(lblNgayXuat.getPreferredSize());
        lblDiaChi.setPreferredSize(lblNgayXuat.getPreferredSize());
        lblSDT.setPreferredSize(lblNgayXuat.getPreferredSize());
        lblTenKH.setPreferredSize(lblNgayXuat.getPreferredSize());
        lblTenNV.setPreferredSize(lblNgayXuat.getPreferredSize());

        txtMahD.setEditable(false);


        if(nv != null){
            txtMaNV.setText(nv.getMaNV());
            txtTenNV.setText(nv.getTenNV());
            txtMaNV.setEditable(false);
            txtTenNV.setEditable(false);
        }


        //pnCenter
        TitledBorder tileDanhSach = new TitledBorder("Danh sách linh kiện");
        TitledBorder tileDanhSachSP = new TitledBorder("Danh sách sản phẩm");

        //pnSouth.setPreferredSize(new Dimension(1030,60));
        pnCenter = new JPanel();
        JPanel pnCenter_N = new JPanel();
        JPanel pnCenter_C = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        spDao = new SanPham_DAO();
        tableModel = new SP_TableModel(spDao.getLS());
        JTable table = new JTable(tableModel);

        //Sự kiện Table
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    btnChonHang.setEnabled(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(900,100));

        CT_GioHang_DAO ctghDao = new CT_GioHang_DAO();
        List<CT_GioHang> ds = new ArrayList<>();
        CT_GioHang_TableModel tableModel1 = new CT_GioHang_TableModel(ds);
        JTable table1 = new JTable(tableModel1);
        //Sự kiện Table
        table1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table1.getSelectedRow();
                if(r != -1){
                    btnCapNhatSL.setEnabled(true);
                    btnXoa.setEnabled(true);

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JScrollPane sc1 = new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc1.setPreferredSize(new Dimension(900,80));

        JPanel pnTable = new JPanel();

        txtTenKH.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                khDao = new KhachHang_DAO();
                CT_DonDatHang_DAO ctdhDao = new CT_DonDatHang_DAO();
                DonDatHang_DAO ddhDao = new DonDatHang_DAO();
                kh = khDao.TimKiemTen(txtTenKH.getText().trim());
                if(khDao.TimKiemTen(txtTenKH.getText().trim()) != null){
                    txtMaKH.setText(kh.getMaKH());
                    txtMaKH.setEditable(false);
                    txtSDT.setText(kh.getDienThoai());
                    txtDiaChi.setText(kh.getDiaChi());
                    txtMail.setText(kh.getEmail());
                    if(ddhDao.TimTheoMaKH(txtMaKH.getText().trim()) != null && ddhDao.TimTheoMaKH(txtMaKH.getText().trim()).getTinhTrang().equalsIgnoreCase("Đang Xử Lý")){
                        JOptionPane.showMessageDialog(null,"Khách hàng đã có đơn đặt hàng!");
                        List<CT_DonDatHang> ls = ctdhDao.getLS(ddhDao.TimTheoMaKH(txtMaKH.getText().trim()).getMaDDH());
                        for (CT_DonDatHang ct: ls) {
                            tongTien += ct.getThanhTien();
                        }
                        ddhDao.TimTheoMaKH(txtMaKH.getText().trim()).setTinhTrang("Hoàn Thành");
                        table1.setModel(new CT_DonDatHang_tableModel(ls));
                    }
                }else{
                    Object [] options = {"Thêm khách hàng","Thoát"};
                    int n = JOptionPane.showOptionDialog(null,"Chưa có thông tin khách hàng, thêm khách hàng mới??",
                            "Thông báo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
                    if(n == 0){
                        removeAll();
                        TT_KhachHang_Form khf = new TT_KhachHang_Form();
                        add(khf);
                        validate();
                    }
                }

            }
        });

        btnCapNhatSL = new JButton("Cập Nhật Số Lượng");
        btnXoa = new JButton("Xóa Sản Phẩm");
        btnXoa.setIcon(new ImageIcon(getClass().getResource("/images_menu/xoa.png")));
        btnCapNhatSL.setIcon(new ImageIcon(getClass().getResource("/images_menu/sua.png")));


        pnCenter.setPreferredSize(new Dimension(900,270));
        pnCenter_N.setBorder(tileDanhSach);
        pnCenter_C.setBorder(tileDanhSachSP);
        pnCenter_C.setLayout(new BorderLayout());
        pnCenter_N.add(sc);pnCenter.add(pnCenter_N,BorderLayout.NORTH);
        pnCenter_C.add(pnTable,BorderLayout.CENTER);
        pnCenter.add(pnCenter_C,BorderLayout.CENTER);
        pnTable.add(sc1);

        //pnSouth

        pnSouth = new JPanel();
        btnDatHang = new JButton("Xuất Hóa Đơn");
        btnQuayLai = new JButton("Quay Lại");
        JButton btnIn = new JButton("In");
        btnQuayLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                TrangChu_Form tc = new TrangChu_Form();
                add(tc);
                validate();
            }
        });
        btnDatHang.setIcon(new ImageIcon(getClass().getResource("/images_menu/thanhtoan.png")));
        btnQuayLai.setIcon(new ImageIcon(getClass().getResource("/images_menu/quaylai.png")));
        btnIn.setIcon(new ImageIcon(getClass().getResource("/images_menu/printer.png")));
        pnSouth.setBorder(new TitledBorder("Tác Vụ"));

        Box d = Box.createHorizontalBox();
        d.add(btnDatHang);
        d.add(Box.createHorizontalStrut(20));
        d.add(btnIn);
        d.add(Box.createHorizontalStrut(50));
        d.add(btnCapNhatSL);
        d.add(Box.createHorizontalStrut(50));
        btnCapNhatSL.setEnabled(false);
        btnChonHang = new JButton("Chọn Mua");
        btnChonHang.setIcon(new ImageIcon(getClass().getResource("/images_menu/datmua.png")));
        btnChonHang.setEnabled(false);
        d.add(btnChonHang);
        btnXoa.setEnabled(false);
        d.add(Box.createHorizontalStrut(50));
        d.add(btnXoa);
        d.add(Box.createHorizontalStrut(50));
        d.add(btnQuayLai);
        pnSouth.add(d);

        System.out.println(kh);
        //Sự kiện chọn mua
        btnChonHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(kh != null){
                    if(r != -1){
                        int sl = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm muốn mua: ",
                                "Nhập số lượng", JOptionPane.INFORMATION_MESSAGE));
                        System.out.println(kh);
                        btnChonHang.setEnabled(false);
                        table.clearSelection();
                        GioHang_DAO ghDao = new GioHang_DAO();
                        spDao = new SanPham_DAO();
                        CT_GioHang_DAO ctGioHangDao = new CT_GioHang_DAO();
                        SanPham sp; GioHang gh;
                        System.out.println("bắt đầu");
                        System.out.println("KTGH: "+ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH()).size());
                        if(sl > spDao.TimKiemTen(table.getValueAt(r,1).toString()).getSoLuong()){
                            JOptionPane.showMessageDialog(null,"Không đủ số lượng linh kiện");
                        }
                        else{
                            if(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH()).size() != 0){
                                for (CT_GioHang ct: ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())) {
                                    System.out.println(ct);
                                    if(!ct.getSanPham().getMaSP().equalsIgnoreCase(spDao.TimKiemTen(table.getValueAt(r,1).toString()).getMaSP())){
                                        gh = ghDao.TimKiemMaKH(kh.getMaKH());
                                        System.out.println("GioHang-----:"+gh);
                                        System.out.println(gh);
                                        sp = spDao.TimKiemTen(table.getValueAt(r,1).toString());
                                        System.out.println(sp);
                                        CT_GioHang ctgh = new CT_GioHang(sl);
                                        ctgh.setSanPham(sp);ctgh.setGioHang(gh);ctgh.setThanhTien(sp.getDonGia()* ctgh.getSoLuong());

                                        if(ctGioHangDao.addCTGioHang(ctgh)){
                                            try {
                                                table1.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                                                table.setModel(new SP_TableModel(spDao.getLS()));
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                            }
                                        }
                                    }
                                    else {
                                        ctghDao.updateCTGH(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH(),spDao.TimKiemTen(table.getValueAt(r,1).toString()).getMaSP(),sl+ct.getSoLuong());
                                        table1.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                                        int sl1 = spDao.TimKiemTen(table.getValueAt(r,1).toString()).getSoLuong();
                                        spDao.updateSoLuong(spDao.TimKiemTen(table.getValueAt(r,1).toString()).getMaSP(),sl1 - sl);
                                        table.setModel(new SP_TableModel(spDao.getLS()));
                                    }

                                }
                            }else{
                                System.out.println("null");
                                gh = ghDao.TimKiemMaKH(kh.getMaKH());
                                System.out.println("GioHang-----:"+gh);
                                System.out.println(gh);
                                sp = spDao.TimKiemTen(table.getValueAt(r,1).toString());
                                System.out.println(sp);
                                CT_GioHang ctgh = new CT_GioHang(sl);

                                ctgh.setSanPham(sp);ctgh.setGioHang(gh);ctgh.setThanhTien(sp.getDonGia()* ctgh.getSoLuong());

                                if(ctGioHangDao.addCTGioHang(ctgh)){
                                    try {
                                        table1.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                                        table.setModel(new SP_TableModel(spDao.getLS()));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Chưa nhập thông tin khách hàng!");
                    txtTenKH.requestFocus();
                }
            }
        });
        
        //Sự kiện cập nhật
        btnCapNhatSL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table1.getSelectedRow();
                btnCapNhatSL.setEnabled(false);
                btnXoa.setEnabled(false);
                if(r != -1) {
                    int sl = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số lượng cập nhật: ",
                            "Nhập số lượng", JOptionPane.INFORMATION_MESSAGE));
                    CT_GioHang_DAO ctGioHangDao = new CT_GioHang_DAO();
                    GioHang_DAO ghDao = new GioHang_DAO();
                    SanPham sp = spDao.TimKiemTen(table1.getValueAt(r,1).toString());
                    CT_GioHang ct = ctGioHangDao.TimTheoMaGH(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH());
                    if (ctGioHangDao.updateCTGH(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH(),spDao.TimKiemTen(table1.getValueAt(r,1).toString()).getMaSP(),sl)){
                        try {
                            System.out.println("ok");
                            int sl1 = sp.getSoLuong()+ct.getSoLuong() - sl;
                            System.out.println(sl1);
                            spDao.updateSoLuong(sp.getMaSP(),sl1);
                            table1.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                            table.setModel(new SP_TableModel(spDao.getLS()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                table1.clearSelection();
            }
        });
        //Sự kiện xóa sản phẩm
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table1.getSelectedRow();
                btnXoa.setEnabled(false);
                btnCapNhatSL.setEnabled(false);
                if(r != -1) {
                    CT_GioHang_DAO ctGioHangDao = new CT_GioHang_DAO();
                    GioHang_DAO ghDao = new GioHang_DAO();
                    SanPham_DAO sanPham_dao = new SanPham_DAO();
                    int soluong = ctGioHangDao.TimTheoMaGH(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH()).getSoLuong();
                    String masp = sanPham_dao.TimKiemTen(table1.getValueAt(r,1).toString()).getMaSP();
                    if (ctGioHangDao.deleteCTGH_TheoMa(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH(),sanPham_dao.TimKiemTen(table1.getValueAt(r,1).toString()).getMaSP())){
                        try {
                            table1.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                            if(spDao.updateSoLuong(masp,soluong+spDao.TimKiemMa(masp).getSoLuong())){
                                table.setModel(new SP_TableModel(spDao.getLS()));
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat nf = new DecimalFormat("#,###.00 VND");
        //Sự kiện
        btnDatHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DonDatHang_DAO ddhDao = new DonDatHang_DAO();
                if(ddhDao.TimTheoMaKH(txtMaKH.getText().trim()) == null){
                    GioHang_DAO ghDao = new GioHang_DAO();
                    double tong1 = 0;
                    for (CT_GioHang ctgh: ctghDao.getLS(ghDao.TimKiemMaKH(txtMaKH.getText().trim()).getMaGH())) {
                        System.out.println(ctgh);
                        tong1 += ctgh.getThanhTien();
                    }
                    String maHD = "";
                    JOptionPane.showMessageDialog(null,"Tổng Tiền Hóa Đơn Là: "+nf.format(tong1));
                    HoaDonBanHang_DAO hdbhDao = new HoaDonBanHang_DAO();
                    tienKhach = Double.parseDouble(JOptionPane.showInputDialog(null, "Nhập tiền khách trả : ",
                            "Nhập số tiền", JOptionPane.INFORMATION_MESSAGE));
                    System.out.println(NgayXuatHD.getDate());
                    CT_GioHang_DAO ctghDao = new CT_GioHang_DAO();
                    String dateTime = formatter.format(NgayXuatHD.getDate());
                    if(ctghDao.getLS().size() != 0){
                        String pt = "";
                        if (rbTT.isSelected())
                            pt = rbTT.getText();
                        else
                            pt = rbOL.getText();
                        HoaDonBanHang hdbh = new HoaDonBanHang(txtMahD.getText().trim(), pt, java.sql.Date.valueOf(dateTime));
                        KhachHang_DAO khDao = new KhachHang_DAO();
                        KhachHang kh = khDao.TimKiemMa(txtMaKH.getText().trim());
                        hdbh.setKhachHang(kh);
                        NhanVien_DAO nvDao = new NhanVien_DAO();
                        NhanVien nv = nvDao.TimKiemMa(txtMaNV.getText().trim());
                        hdbh.setNhanVien(nv);
                        System.out.println(hdbh);
                        double tong = 0;

                        try {
                            System.out.println(hdbhDao.getMa());
                            maHDBH = hdbhDao.getMa();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        if(hdbhDao.addHoaDonBH(hdbh)){
                            ctghDao = new CT_GioHang_DAO();
                            try {
                                maHD = hdbhDao.getMa();
                                maHDBH = maHD;
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            for (CT_GioHang ctgh: ctghDao.getLS(ghDao.TimKiemMaKH(txtMaKH.getText().trim()).getMaGH())) {
                                System.out.println(ctgh);
                                tong += ctgh.getThanhTien();
                                CT_HoaDonBanHang cthdbhh = new CT_HoaDonBanHang();
                                CT_HoaDonBH_DAO cthdbhDao = new CT_HoaDonBH_DAO();
                                cthdbhh.setSoLuong(ctgh.getSoLuong());
                                cthdbhh.setThanhTien(ctgh.getThanhTien());
                                cthdbhh.setHoaDonBanHang(hdbhDao.TimKiemMa(maHD));
                                cthdbhh.setSanPham(ctgh.getSanPham());
                                System.out.println(cthdbhh);
                                cthdbhDao.addCTHoaDonBH(cthdbhh);
//                            ctghDao.deleteCTGH_TheoMa(ghDao.TimKiemMaKH(txtMaKH.getText()).getMaGH(),ctgh.getLinhKien().getMaLK());
                            }
                            tongTien = tong;
//                        System.out.println((ctddhDao.getLS(ddhDao.TimTheoMaKH(txtMaKH.getText()).getMaDDH())));
                            table1.setModel(new CT_GioHang_TableModel(ctghDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Không còn sản phẩm trong giỏ hàng");
                    }
                    if(tongTien < tienKhach){
                        ctghDao.deleteCTGH();
                        table1.setModel(new CT_GioHang_TableModel(ctghDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                        int tongt = (int)tongTien;
                        String tienC = String.valueOf(tongt);
                        System.out.println(maHDBH);
                        HoaDonBanHang_Form hd = new HoaDonBanHang_Form();
                        hd.maHD = maHDBH;
                        hd.tienChu = numberToString(new BigDecimal(tienC));
                        hd.tong = tongTien;
                        hd.tienKhach = tienKhach;
                        hd.doShow();
//                        setVisible(false);
                        hd.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"Tiền khách trả nhỏ hơn tổng tiền!");
//                        CT_HoaDonBH_DAO cthdbhDao = new CT_HoaDonBH_DAO();
//                        cthdbhDao.deleteCTHDBH();
                    }
                }else{
                    String maHD = "";
                    JOptionPane.showMessageDialog(null,"Tổng Tiền Hóa Đơn Là: "+nf.format(tongTien));
                    HoaDonBanHang_DAO hdbhDao = new HoaDonBanHang_DAO();
                    tienKhach = Double.parseDouble(JOptionPane.showInputDialog(null, "Nhập tiền khách trả : ",
                            "Nhập số tiền", JOptionPane.INFORMATION_MESSAGE));
                    System.out.println(NgayXuatHD.getDate());
                    CT_DonDatHang_DAO ctdhDao = new CT_DonDatHang_DAO();
                    DonDatHang_DAO ddd = new DonDatHang_DAO();
                    String dateTime = formatter.format(NgayXuatHD.getDate());
                    GioHang_DAO ghDao = new GioHang_DAO();
                    if(ctdhDao.getLS(ddd.TimTheoMaKH(txtMaKH.getText().trim()).getMaDDH()).size() != 0){
                        String pt = "";
                        if (rbTT.isSelected())
                            pt = rbTT.getText();
                        else
                            pt = rbOL.getText();
                        HoaDonBanHang hdbh = new HoaDonBanHang(txtMahD.getText().trim(), pt, java.sql.Date.valueOf(dateTime));
                        KhachHang_DAO khDao = new KhachHang_DAO();
                        KhachHang kh = khDao.TimKiemMa(txtMaKH.getText().trim());
                        hdbh.setKhachHang(kh);
                        NhanVien_DAO nvDao = new NhanVien_DAO();
                        NhanVien nv = nvDao.TimKiemMa(txtMaNV.getText().trim());
                        hdbh.setNhanVien(nv);
                        System.out.println(hdbh);
                        double tong = 0;

                        try {
                            System.out.println(hdbhDao.getMa());
                            maHDBH = hdbhDao.getMa();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        if(hdbhDao.addHoaDonBH(hdbh)){
                            ctdhDao = new CT_DonDatHang_DAO();
                            DonDatHang_DAO dhDao = new DonDatHang_DAO();
                            try {
                                maHD = hdbhDao.getMa();
                                maHDBH = maHD;
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            for (CT_DonDatHang ct: ctdhDao.getLS(dhDao.TimTheoMaKH(txtMaKH.getText().trim()).getMaDDH())) {
                                System.out.println(ct);
                                tong += ct.getThanhTien();
                                CT_HoaDonBanHang cthdbhh = new CT_HoaDonBanHang();
                                CT_HoaDonBH_DAO cthdbhDao = new CT_HoaDonBH_DAO();
                                cthdbhh.setSoLuong(ct.getSoLuong());
                                cthdbhh.setThanhTien(ct.getThanhTien());
                                cthdbhh.setHoaDonBanHang(hdbhDao.TimKiemMa(maHD));
                                cthdbhh.setSanPham(ct.getSanPham());
                                System.out.println(cthdbhh);
                                cthdbhDao.addCTHoaDonBH(cthdbhh);
//                            ctghDao.deleteCTGH_TheoMa(ghDao.TimKiemMaKH(txtMaKH.getText()).getMaGH(),ctgh.getLinhKien().getMaLK());
                            }
                            tongTien = tong;
//                        System.out.println((ctddhDao.getLS(ddhDao.TimTheoMaKH(txtMaKH.getText()).getMaDDH())));
                            table1.setModel(new CT_DonDatHang_tableModel(ctdhDao.getLS(dhDao.TimTheoMaKH(kh.getMaKH()).getMaDDH())));
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Không còn sản phẩm");
                    }
                    if(tongTien < tienKhach){
//                        table1.setModel(new CT_GioHang_TableModel(ctghDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                        int tongt = (int)tongTien;
                        String tienC = String.valueOf(tongt);
                        System.out.println(maHDBH);
                        HoaDonBanHang_Form hd = new HoaDonBanHang_Form();
                        hd.maHD = maHDBH;
                        hd.tienChu = numberToString(new BigDecimal(tienC));
                        hd.tong = tongTien;
                        hd.tienKhach = tienKhach;
                        hd.doShow();
//                        setVisible(false);
                        hd.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"Tiền khách trả nhỏ hơn tổng tiền!");
//                        CT_HoaDonBH_DAO cthdbhDao = new CT_HoaDonBH_DAO();
//                        cthdbhDao.deleteCTHDBH();
                    }
                }
            }
        });
//        btnIn.addActionListener(new ActionListener() {
//        	private JasperPrint jprint;
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				try {
//					Class.forName("net.sourceforge.jtds.jdbc.Driver");
//					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyCuaHangLinhKien;instance=SQLEXPRESS;user=sa;password=hung");
////					Connection con = MyConnection.getInstance().getConnection();
//					String sql = "SELECT hd.MAHDBH,hd.NGAYLAPHD,nv.TENNV,kh.TENKH,kh.DIENTHOAI,kh.DIACHI ,lk.TenLK,llk.TENLOAI,nsx.TENNHASX,\r\n"
//							+ "cthd.SOLUONG,lk.DonGia,lk.BAOHANH,cthd.SOLUONG * lk.DONGIA AS ThanhTien\r\n"
//							+ "FROM [dbo].[CT_HoaDonBanHang] cthd JOIN [dbo].[HoaDonBanHang] hd ON cthd.MAHDBH = hd.MAHDBH\r\n"
//							+ "JOIN [dbo].[NhanVien] NV ON HD.MANV = NV.MANV\r\n"
//							+ "JOIN [dbo].[KhachHang] kh ON hd.MAKH =kh.MAKH\r\n"
//							+ "JOIN [dbo].[LinhKien] lk ON cthd.MaLK = lk.MALK\r\n"
//							+ "JOIN [dbo].[LoaiLinhKien] llk ON llk.MALOAI = lk.MALOAI\r\n"
//							+ "JOIN [dbo].[NhaSanXuat] nsx ON nsx.MANHASX = lk.MANHASX\r\n"
//							+ "WHERE hd.MAHDBH = HDBH001";
//					JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\vanhu\\Documents\\Nhom01_QuanLyMuaBanLinhKien_PTUD\\src\\BaoCao_Jasper\\BaoCaoBanHang.jrxml");
//					JRDesignQuery updateQuery = new JRDesignQuery();
//					updateQuery.setText(sql);
//					jdesign.setQuery(updateQuery);
//
//					Map<String, Object> parameters = new HashMap<String, Object>();
//					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
//					JasperPrint jpasperPrint = JasperFillManager.fillReport(jreport, parameters,con);
//
//					JasperViewer.viewReport(jpasperPrint,false);
//				}catch (Exception e2) {
//					// TODO: handle exception
//					e2.printStackTrace();
//					JOptionPane.showMessageDialog(null, e2);
//				}
//
//			}
//		});
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
    public static String formatNumberForRead(double number) {
        NumberFormat nf = NumberFormat.getInstance();
        String temp = nf.format(number);
        String strReturn = "";
        int slen = temp.length();
        for (int i = 0; i < slen; i++) {
            if (String.valueOf(temp.charAt(i)).equals("."))
                break;
            else if (Character.isDigit(temp.charAt(i))) {
                strReturn += String.valueOf(temp.charAt(i));
            }
        }
        return strReturn;

    }

//    public static void main(String[] args) {
//        System.out.println("Read: 10.000.000 - " +numberToString(new BigDecimal( "10000000")));
//        System.out.println("Read: 5.123.894.500 - " +numberToString(new BigDecimal( "5123894500")));
//        System.out.println("Read: 1.235.123.894.500 - " +numberToString(new BigDecimal( "12355123894500")));
//    }

    public static String numberToString(BigDecimal number) {
        String sNumber = number.toString();
        // Tao mot bien tra ve
        String sReturn = "";
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Lat nguoc chuoi nay lai
        String sNumber1 = "";
        for (int i = iLen - 1; i >= 0; i--) {
            sNumber1 += sNumber.charAt(i);
        }
        // Tao mot vong lap de doc so
        // Tao mot bien nho vi tri cua sNumber
        int iRe = 0;
        do {
            // Tao mot bien cat tam
            String sCut = "";
            if (iLen > 3) {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + 3);
                sReturn = Read(sCut, iRe) + sReturn;
                iRe++;
                iLen -= 3;
            } else {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + iLen);
                sReturn = Read(sCut, iRe) + sReturn;
                break;
            }
        } while (true);
        if(sReturn.length() > 1){
            sReturn = sReturn.substring(0,1).toUpperCase() + sReturn.substring(1);
        }
        sReturn = sReturn + "đồng";

        // xu ly lan cuoi voi 220 000 tỷ 000 000 000 000 000 HUTTV ADDED 3 OCT
        if(sNumber.length()>12)
        {
            // tren gia tri can xu ly, kiem tra xem don vi TY co = 000 khong
            int begin = sNumber.length()-9;
            String donviTy = sNumber.substring(begin-3, (begin-3)+3);
            if(donviTy.equals("000"))
            {
                sReturn = sReturn.replaceFirst("ngàn", "ngàn tỷ");
            }
        }


        return sReturn;
    }

    // Khoi tao ham Read
    public static String Read(String sNumber, int iPo) {
        // Tao mot bien tra ve
        String sReturn = "";
        // Tao mot bien so
        String sPo[] = { "", "ngàn" + " ",
                "triệu" + " ", "tỷ" + " ",  "ngàn" + " "};
        String sSo[] = { "không" + " ", "một" + " ",
                "hai" + " ", "ba" + " ",
                "bốn" + " ", "năm" + " ",
                "sáu" + " ", "bảy" + " ",
                "tám" + " ", "chín" + " " };
        String sDonvi[] = { "", "mươi" + " ",
                "trăm" + " " };
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Tao mot bien nho vi tri doc
        int iRe = 0;
        // Tao mot vong lap de doc so
        for (int i = 0; i < iLen; i++) {
            String sTemp = "" + sNumber.charAt(i);
            int iTemp = Integer.parseInt(sTemp);
            // Tao mot bien ket qua
            String sRead = "";
            // Kiem tra xem so nhan vao co = 0 hay ko
            if (iTemp == 0) {
                switch (iRe) {
                    case 0:
                        break;// Khong lam gi ca
                    case 1: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0) {
                            sRead = "lẻ" + " ";
                        }
                        break;
                    }
                    case 2: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0
                                && Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "không trăm" + " ";
                        }
                        break;
                    }
                }
            } else if (iTemp == 1) {
                switch (iRe) {
                    case 1:
                        sRead = "mười" + " ";
                        break;
                    default:
                        sRead = "một" + " " + sDonvi[iRe];
                        break;
                }
            } else if (iTemp == 5) {
                switch (iRe) {
                    case 0: {
                        if(sNumber.length() <= 1){
                            sRead = "năm" + " ";
                        }
                        else if (Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "lăm" + " ";
                        } else
                            sRead = "năm" + " ";
                        break;
                    }
                    default:
                        sRead = sSo[iTemp] + sDonvi[iRe];
                }
            } else {
                sRead = sSo[iTemp] + sDonvi[iRe];
            }

            sReturn = sRead + sReturn;
            iRe++;
        }
        if (sReturn.length() > 0) {
            sReturn += sPo[iPo];
        }
        // xu ly lan cuoi voi 220 000 tỷ 000 000 000 000 000
        if(sNumber.length()>12)
        {
            // tren gia tri can xu ly, kiem tra xem don vi TY co = 000 khong
            System.out.println(sNumber.substring(11, 8));
        }
        return sReturn;
    }

    public static String getRomanNumerals(int Int) {
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        String res = "";
        for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
            int matches = Int/entry.getValue();
            res += repeat(entry.getKey(), matches);
            Int = Int % entry.getValue();
        }
        return res;
    }
    public static String repeat(String s, int n) {
        if(s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
