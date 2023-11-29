package gui;

import TableModel.KH_TableModel;
import TableModel.SP_TableModel;
import TableModel.NV_TableModel;
import com.toedter.calendar.JDateChooser;
import dao.*;
import entity.ChucVu;
import entity.LoaiSanPham;
import entity.NhaSanXuat;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TimKiemSP_Form extends JPanel {
    JPanel pnNorth,pnCenter1,pnWest1,pnCenter,pnSouth;
    JLabel lbImage,lblText,lblTen,lblLuaChon,lblTieuDe,lblLoai,lblNSX,lblGia;
    JTextField txtTen;
    JButton btnThem,btnXoa,btnSua,btnLuu,btnThoat,btnTimKiem;
    JCheckBox cbTen,cbLoai,cbNSX,cbGia;
    ImageIcon icon;
    JComboBox cbcLoai,cbcNSX,cbcGia;
    JDateChooser namSinh;
    private KhachHang_DAO KhDao;
    private SP_TableModel tableModel;
    public TimKiemSP_Form(){
        doShow();
    }
    public void doShow(){
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        lblTieuDe = new JLabel("TÌM KIẾM SẢN PHẨM");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.BLUE);
        pnNorth1.add(lblTieuDe);
        pnNorth.add(pnNorth1,BorderLayout.NORTH);


        //pnWest
        pnWest1 = new JPanel();
        pnWest1.setLayout(new BorderLayout());
        JPanel pn1 = new JPanel();
        JPanel pn2 = new JPanel();
        pn1.setLayout(new BorderLayout());
        pn2.setLayout(new BorderLayout());
        pnWest1.setPreferredSize(new Dimension(180,180));
        //pnWest1.setBorder(BorderFactory.createLineBorder(Color.red));
        JButton btn1 = new JButton("<");
        pn1.add(btn1,BorderLayout.SOUTH);

        JButton btn2 = new JButton(">");
        pn2.add(btn2,BorderLayout.SOUTH);
        lbImage = new JLabel();

        //File
        final JFileChooser  fileDialog = new JFileChooser();
        JFrame cha = new JFrame();
        icon = new ImageIcon("D:\\Nhom13_QuanLyBanHangQuanAo\\src\\images_SanPham\\sanpham11.png");
        lbImage.setPreferredSize(new Dimension(110,110));
        //lbImage.setIcon(new ImageIcon(getClass().getResource("/images/user11.png")));
        lbImage.setIcon(icon);
        pnWest1.add(lbImage,BorderLayout.CENTER);
        pnWest1.add(pn1,BorderLayout.WEST);
        pnWest1.add(pn2,BorderLayout.EAST);
        //pnCenter
        pnCenter1 = new JPanel();
        Box b,b1,b2,b3,b4;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(760,190));

        b.add(Box.createVerticalStrut(20));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblLuaChon = new JLabel("Tìm Kiếm Sản Phẩm Theo: "));
        b1.add(Box.createHorizontalStrut(30));
        b1.add(cbTen = new JCheckBox("Tên Sản Phẩm"));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(cbLoai = new JCheckBox("Loại Sản Phẩm"));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(cbNSX = new JCheckBox("Nhà Sản Xuất"));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(cbGia = new JCheckBox("Giá Cả"));
        b.add(Box.createVerticalStrut(20));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblTen = new JLabel("Nhập Tên SP Cần Tìm: "));
        b2.add(txtTen = new JTextField());
        b2.add(Box.createHorizontalStrut(30));
        b2.add(lblLoai = new JLabel("Chọn Loại SP Cần Tìm:   "));
        cbcLoai = new JComboBox<>();
        cbcLoai.setPreferredSize(new Dimension(225,15));
        LoaiSanPham_DAO llkDao = new LoaiSanPham_DAO();
        for (LoaiSanPham llk: llkDao.getLS()) {
            cbcLoai.addItem(llk.getTenLoai());
        }
        b2.add(cbcLoai);
        b.add(Box.createVerticalStrut(20));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblNSX = new JLabel("Chọn Nhà Sản Xuất Cần Tìm: "));
        cbcNSX = new JComboBox<>();
        NhaSanXuat_DAO nsxDao = new NhaSanXuat_DAO();
        for (NhaSanXuat nsx: nsxDao.getLS()) {
            cbcNSX.addItem(nsx.getTenNhaSX());
        }
        b3.add(cbcNSX);
        b3.add(Box.createHorizontalStrut(30));
        b3.add(lblGia = new JLabel("Chọn Mức Giá Cần Tìm: "));

        cbcGia = new JComboBox<>();
        cbcGia.addItem("Dưới 1 triệu");
        cbcGia.addItem("1 triệu - 2 triệu");
        cbcGia.addItem("2 triệu - 3 triệu");
        cbcGia.addItem("Trên 3 triệu");
   
        cbcGia.setPreferredSize(new Dimension(110,20));
        b3.add(cbcGia);
        b.add(Box.createVerticalStrut(20));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(btnTimKiem = new JButton("Tìm Sản Phẩm"));
        btnTimKiem.setIcon(new ImageIcon(getClass().getResource("/images_menu/datmua.png")));
        btnTimKiem.setEnabled(true);

        txtTen.setEnabled(false);
        cbcGia.setEnabled(false);
        cbcLoai.setEnabled(false);
        cbcNSX.setEnabled(false);

        lblTen.setPreferredSize(lblNSX.getPreferredSize());
        lblLoai.setPreferredSize(lblNSX.getPreferredSize());
        lblGia.setPreferredSize(lblNSX.getPreferredSize());

        cbTen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbTen.isSelected())
                    txtTen.setEnabled(true);
                else
                    txtTen.setEnabled(false);
            }
        });
        cbLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbLoai.isSelected())
                    cbcLoai.setEnabled(true);
                else
                    cbcLoai.setEnabled(false);
            }
        });
        cbNSX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbNSX.isSelected())
                    cbcNSX.setEnabled(true);
                else
                    cbcNSX.setEnabled(false);
            }
        });
        cbGia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbGia.isSelected())
                    cbcGia.setEnabled(true);
                else
                    cbcGia.setEnabled(false);
            }
        });
        pnCenter1.add(b);
        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);

        //Center
        TitledBorder tileDanhSach = new TitledBorder("Danh sách sản phẩm");

        //pnSouth.setPreferredSize(new Dimension(1030,60));
        pnCenter = new JPanel();

        SanPham_DAO lkDao = new SanPham_DAO();
        tableModel = new SP_TableModel(lkDao.getLS());
        JTable table = new JTable(tableModel);

        //Sự kiện Table
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {
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
                    btn1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(icon.getDescription());
                            for(int i = p.length-1; i >= 0; i--){
                                if(lbImage.getIcon().toString().contains(p[i])) {
                                    if (i == 0) {
                                        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(p[p.length - 1])));
                                    } else {
                                        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(p[i - 1])));
                                    }
                                    break;
                                }
                            }
                            icon.setDescription(lbImage.getIcon().toString());
//                            System.out.println(lbImage.getIcon().toString());
                        }
                    });
                    btn2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(icon.toString());
                            for(int i = 0; i < p.length; i++){
                                if(lbImage.getIcon().toString().contains(p[i])) {
                                    if (i != (p.length - 1)) {
                                        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(p[i + 1])));
//                                        break;
                                    } else {
                                        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(p[0])));
//                                        break;
                                    }
                                    break;
                                }
                            }
                            icon.setDescription(lbImage.getIcon().toString());
//                            System.out.println(lbImage.getIcon().toString());
                        }
                    });
                    icon.setDescription(lbImage.getIcon().toString());
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
        sc.setPreferredSize(new Dimension(930,220));
        pnCenter.add(sc);

        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);


        pnSouth = new JPanel();
        pnCenter.setBorder(tileDanhSach);
        btnThoat = new JButton("Thoát ");
        //Sự kiện thoát
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                TrangChu_Form tc = new TrangChu_Form();
                add(tc);
                validate();
            }
        });


        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbTen.isSelected()){
                    table.setModel(new SP_TableModel(lkDao.TimKiemTen1(txtTen.getText().trim())));
                }else if(cbLoai.isSelected()){
                    table.setModel(new SP_TableModel(lkDao.TimKiemLoai(llkDao.TimKiemTen(cbcLoai.getSelectedItem().toString()).getMaLoai())));
                }else if(cbNSX.isSelected()){
//                    table.setModel(new NV_TableModel(nvDao.TimKiemTenDC1(txtdiaChi.getText().trim())));
                    table.setModel(new SP_TableModel(lkDao.TimKiemNSX(nsxDao.TimKiemTen(cbcNSX.getSelectedItem().toString()).getMaNhaSX())));
                }else if(cbGia.isSelected()){
//                    table.setModel(new NV_TableModel(nvDao.TimKiemCV(cvDao.TimKiemTen(cbcChucVu.getSelectedItem().toString().trim()).getMaChucVu())));
                    if(cbcGia.getSelectedIndex() == 0){
                        table.setModel(new SP_TableModel(lkDao.TimKiemGia1(1000000)));
                    }else if(cbcGia.getSelectedIndex() == 4){
                        table.setModel(new SP_TableModel(lkDao.TimKiemGia3(3000000)));
                    }else if(cbcGia.getSelectedIndex() == 1){
                        table.setModel(new SP_TableModel(lkDao.TimKiemGia2(1000000,2000000)));
                    }else if(cbcGia.getSelectedIndex() == 2){
                        table.setModel(new SP_TableModel(lkDao.TimKiemGia2(2000000,3000000)));
                    }
                }
            }
        });
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/images_menu/thoat.png")));
        Box bSouth = Box.createHorizontalBox();
        bSouth.add(Box.createHorizontalStrut(900));
        btnThoat.setBackground(Color.red);
        btnThoat.setForeground(Color.WHITE);
        bSouth.add(btnThoat);
        pnSouth.add(bSouth);

        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
}


