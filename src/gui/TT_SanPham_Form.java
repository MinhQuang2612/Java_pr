package gui;

import TableModel.CT_GioHang_TableModel;
import TableModel.SP_TableModel;
import dao.CT_GioHang_DAO;
import dao.GioHang_DAO;
import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import entity.CT_GioHang;
import entity.GioHang;
import entity.KhachHang;
import entity.SanPham;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TT_SanPham_Form extends JPanel {
    JPanel pnNorth,pnCenter,pnWest1,pnCenter1,pnSouth;
    JLabel lbImage,lblMa,lblTen,lblBaoHanh,lblNgaySX,lblTinhTrang,lblLoai,lblDonViTinh,lblGia,lblSoLuong,lblNhaSX;
    JButton btnCapNhatSL,btnXoa,btnDatHang,btnCapNhat,btnQuayLai,btnChonHang,btnGioHang;
    ImageIcon icon;
    JTextField txtMa,txtTen,txtGia,txtSoLuong,txtNgaySanXuat,txtBaoHanh,txtTinhTrang,txtLoai,txtDonVi,txtNhaSX;
    SanPham_DAO spDao;
    SP_TableModel tableModel;
    KhachHang kh;

    public TT_SanPham_Form(){

    }
    public void doShow(){
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("THÔNG TIN SẢN PHẨM");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 18));
        lblTieuDe.setForeground(Color.BLUE);
//        pnNorth1.add(lblTieuDe);
        pnNorth.add(pnNorth1,BorderLayout.NORTH);


        //pnWest
        pnWest1 = new JPanel();
        JPanel pn1 = new JPanel();
        JPanel pn2 = new JPanel();
        pn1.setLayout(new BorderLayout());
        pn2.setLayout(new BorderLayout());
        pnWest1.setLayout(new BorderLayout());
        JButton btn1 = new JButton("<");
        pn1.add(btn1,BorderLayout.SOUTH);

        JButton btn2 = new JButton(">");
        pn2.add(btn2,BorderLayout.SOUTH);
        pnWest1.setLayout(new BorderLayout());
        pnWest1.setPreferredSize(new Dimension(185,150));
        //pnWest1.setBorder(BorderFactory.createLineBorder(Color.red));
        lbImage = new JLabel();

        //File
        final JFileChooser  fileDialog = new JFileChooser();
        JFrame cha = new JFrame();
        icon = new ImageIcon("E:\\Nhom01_QuanLyMuaBanLinhKien_PTUD\\src\\images_LinhKien\\linhkien11.png");
        lbImage.setPreferredSize(new Dimension(110,145));
        //lbImage.setIcon(new ImageIcon(getClass().getResource("/images/user11.png")));
        lbImage.setIcon(icon);
        pnWest1.add(lbImage,BorderLayout.CENTER);
        pnWest1.add(pn1,BorderLayout.WEST);
        pnWest1.add(pn2,BorderLayout.EAST);
        //pnCenter
        pnCenter1 = new JPanel();
        Box b,b1,b2,b3,b4,b5;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(820,160));

        b.add(b1 = Box.createHorizontalBox());
//        b1.add(lblMa = new JLabel("Mã Linh Kiện: "));
//        b1.add(txtMa = new JTextField());
        b1.add(lblTen = new JLabel("Tên Linh Kiện:    "));
        b1.add(txtTen = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblLoai = new JLabel("Loại Linh Kiện: "));
        b1.add(txtLoai = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblBaoHanh = new JLabel("Bảo Hành: "));
        b2.add(txtBaoHanh = new JTextField(30));
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblNgaySX = new JLabel("Ngày Sản Xuất:    "));

        b2.add(txtNgaySanXuat = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblTinhTrang = new JLabel("Tình Trạng: "));
        b3.add(txtTinhTrang = new JTextField(30));
        b3.add(Box.createHorizontalStrut(20));
//        b3.add(lblLoai = new JLabel("Loại Linh Kiện: "));
//        b3.add(txtLoai = new JTextField());
        b3.add(lblNhaSX = new JLabel("Nhà Sản Xuất:    "));
        b3.add(txtNhaSX = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
//        b4.add(lblDonViTinh = new JLabel("Đơn Vị Tính: "));
//        b4.add(txtDonVi = new JTextField());
        b4.add(lblSoLuong = new JLabel("Số Lượng:    "));
        b4.add(txtSoLuong = new JTextField(30));
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblGia = new JLabel("Đơn Giá:    "));
        b4.add(txtGia = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b5 = Box.createHorizontalBox());
        b5.add(btnChonHang = new JButton("Đặt Mua"));
        btnChonHang.setIcon(new ImageIcon(getClass().getResource("/images_menu/datmua.png")));
        btnChonHang.setEnabled(false);

        pnCenter1.add(b);
        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);

        txtBaoHanh.setEditable(false);
        txtGia.setEditable(false);
        txtLoai.setEditable(false);
        txtNgaySanXuat.setEditable(false);
        txtNhaSX.setEditable(false);
        txtTinhTrang.setEditable(false);
        txtTen.setEditable(false);

        lblBaoHanh.setPreferredSize(lblNgaySX.getPreferredSize());
        lblGia.setPreferredSize(lblNgaySX.getPreferredSize());
        lblTen.setPreferredSize(lblNgaySX.getPreferredSize());
        lblLoai.setPreferredSize(lblNgaySX.getPreferredSize());
        lblNhaSX.setPreferredSize(lblNgaySX.getPreferredSize());
        lblSoLuong.setPreferredSize(lblNgaySX.getPreferredSize());
        lblTinhTrang.setPreferredSize(lblNgaySX.getPreferredSize());

        //Center
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
                    txtTen.setText(table.getValueAt(r,1).toString());
                    String[] p = table.getValueAt(r,10).toString().trim().split("-");
                    ImageIcon icon;
                    if(table.getValueAt(r,10).toString().trim().contains("C:\\")){
                        System.out.println("Chuoi: "+table.getValueAt(r,10).toString().trim());
                        icon = new ImageIcon(table.getValueAt(r,10).toString().trim());
                    }else{
                        System.out.println("Chuoiq"+p[0]);
                        icon = new javax.swing.ImageIcon(getClass().getResource(p[0]));
                    }
                    lbImage.setIcon(icon);
                    System.out.println(p.length);
                    txtLoai.setText(table.getValueAt(r,8).toString());
                    txtBaoHanh.setText(table.getValueAt(r,2).toString());
                    txtNgaySanXuat.setText(table.getValueAt(r,3).toString());
                    txtTinhTrang.setText(table.getValueAt(r,4).toString());
                    txtNhaSX.setText(table.getValueAt(r,9).toString());
                    txtSoLuong.setText(table.getValueAt(r,5).toString());
                    txtGia.setText(table.getValueAt(r,7).toString());


                    btn1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for(int i = p.length-1; i >= 0; i--){
                                if(icon.toString().contains(p[i]))
                                    if(i == 0) {
                                        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(p[p.length-1])));
                                    }else {
                                        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(p[i - 1])));
                                    }
                            }
                            icon.setDescription(lbImage.getIcon().toString());
                        }
                    });
                    btn2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for(int i = 0; i < p.length; i++){
                                if(icon.toString().contains(p[i]))
                                    if(i != (p.length - 1)) {
                                        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(p[i + 1])));

                                    }else {
                                        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(p[0])));
                                    }
                            }
                            icon.setDescription(lbImage.getIcon().toString());
                        }
                    });

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
        sc.setPreferredSize(new Dimension(930,230));

        pnCenter_N.setBorder(tileDanhSach);
        pnCenter_N.add(sc);pnCenter.add(pnCenter_N,BorderLayout.NORTH);

        //pnSouth

        pnSouth = new JPanel();
//        btnDatHang = new JButton("Thanh Toán");
        btnGioHang = new JButton("Đi Đến Giỏ Hàng");
        btnQuayLai = new JButton("Quay Lại");
        btnQuayLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                TrangChu_Form tc = new TrangChu_Form();
                add(tc);
                validate();
            }
        });
//        btnDatHang.setIcon(new ImageIcon(getClass().getResource("/images_menu/thanhtoan.png")));
        btnQuayLai.setIcon(new ImageIcon(getClass().getResource("/images_menu/quaylai.png")));
        btnGioHang.setIcon(new ImageIcon(getClass().getResource("/images_menu/ditoigiohang.png")));
        pnSouth.setBorder(new TitledBorder("Tác Vụ"));

        Box d = Box.createHorizontalBox();
        d.add(btnGioHang);
        d.add(Box.createHorizontalStrut(150));
        d.add(btnQuayLai);


        pnSouth.add(d);


        //Sự kiện chọn mua
        btnChonHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(kh);
                btnChonHang.setEnabled(false);
                table.clearSelection();
                GioHang_DAO ghDao = new GioHang_DAO();
                spDao = new SanPham_DAO();
                CT_GioHang_DAO ctGioHangDao = new CT_GioHang_DAO();
                SanPham sp = null; GioHang gh = null;
                System.out.println("bắt đầu");
                System.out.println("KTGH: "+ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH()).size());
                if(Integer.parseInt(txtSoLuong.getText()) > spDao.TimKiemTen(txtTen.getText()).getSoLuong()){
                    JOptionPane.showMessageDialog(null,"Không đủ số lượng linh kiện");
                }
                else{
                    if(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH()).size() != 0){
                        for (CT_GioHang ct: ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())) {
                            System.out.println(ct);
                            if(!ct.getSanPham().getMaSP().equalsIgnoreCase(spDao.TimKiemTen(txtTen.getText().trim()).getMaSP())){
                                gh = ghDao.TimKiemMaKH(kh.getMaKH());
                                System.out.println("GioHang-----:"+gh);
                                System.out.println(gh);
                                sp = spDao.TimKiemTen(txtTen.getText().trim());
                                System.out.println(sp);
                                CT_GioHang ctgh = new CT_GioHang(Integer.parseInt(txtSoLuong.getText()));

                                ctgh.setSanPham(sp);ctgh.setGioHang(gh);ctgh.setThanhTien(sp.getDonGia()* ctgh.getSoLuong());

                                KhachHang_DAO khDao = new KhachHang_DAO();
                                String ma = kh.getMaKH();
                                if(ctGioHangDao.addCTGioHang(ctgh)){
                                    try {
//                                        table1.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                                        table.setModel(new SP_TableModel(spDao.getLS()));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                            else {
                                ctGioHangDao.updateCTGH(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH(),spDao.TimKiemTen(txtTen.getText()).getMaSP(),Integer.parseInt(txtSoLuong.getText())+ct.getSoLuong());
//                                table1.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                                int sl = spDao.TimKiemTen(txtTen.getText()).getSoLuong();
                                spDao.updateSoLuong(spDao.TimKiemTen(txtTen.getText()).getMaSP(),sl - Integer.parseInt(txtSoLuong.getText().trim()));
                                table.setModel(new SP_TableModel(spDao.getLS()));
                            }

                        }
                    }else{
                        System.out.println("null");
                        gh = ghDao.TimKiemMaKH(kh.getMaKH());
                        System.out.println("GioHang-----:"+gh);
                        System.out.println(gh);
                        sp = spDao.TimKiemTen(txtTen.getText().trim());
                        System.out.println(sp);
                        CT_GioHang ctgh = new CT_GioHang(Integer.parseInt(txtSoLuong.getText()));

                        ctgh.setSanPham(sp);ctgh.setGioHang(gh);ctgh.setThanhTien(sp.getDonGia()* ctgh.getSoLuong());

                        KhachHang_DAO khDao = new KhachHang_DAO();
                        String ma = kh.getMaKH();
                        if(ctGioHangDao.addCTGioHang(ctgh)){
                            try {
//                                table1.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                                table.setModel(new SP_TableModel(spDao.getLS()));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        //Sự kiện
        btnGioHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                GioHang_Form gh = new GioHang_Form();
                gh.kh = kh;
                gh.doShow();
                add(gh);
                validate();
            }
        });

        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
}
