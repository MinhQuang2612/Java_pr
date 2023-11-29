package gui;

import TableModel.KH_TableModel;
import TableModel.NCC_TableModel;
import TableModel.NV_TableModel;
import com.toedter.calendar.JDateChooser;
import dao.KhachHang_DAO;
import dao.NhaCungCap_DAO;
import dao.NhanVien_DAO;
import entity.NhaCungCap;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TimKiemNCC_Form extends JPanel {
    JPanel pnNorth,pnCenter1,pnWest1,pnCenter,pnSouth;
    JLabel lbImage,lblText,lblTen,lbldiaChi,lblLuaChon,lblTieuDe,lblDienThoai;
    JTextField txtdiaChi,txtTen,txtDienThoai;
    JButton btnThem,btnXoa,btnSua,btnLuu,btnThoat,btnTimKiem;
    JCheckBox cbTen,cbDiaChi,cbSDT;
    ImageIcon icon;
    JComboBox cbcGT,cbcChucVu;
    JDateChooser namSinh;
    private KhachHang_DAO KhDao;
    private NCC_TableModel tableModel;
    public TimKiemNCC_Form(){
        doShow();
    }
    public void doShow(){
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        lblTieuDe = new JLabel("TÌM KIẾM NHÀ CUNG CẤP");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.BLUE);
        pnNorth1.add(lblTieuDe);
        pnNorth.add(pnNorth1,BorderLayout.NORTH);


        //pnWest
        pnWest1 = new JPanel();
        pnWest1.setLayout(new BorderLayout());
        pnWest1.setPreferredSize(new Dimension(150,150));
        //pnWest1.setBorder(BorderFactory.createLineBorder(Color.red));
        lbImage = new JLabel();

        //File
        final JFileChooser  fileDialog = new JFileChooser();
        JFrame cha = new JFrame();
        icon = new ImageIcon("E:\\Nhom01_QuanLyMuaBanLinhKien_PTUD\\src\\images\\user11.png");
        lbImage.setPreferredSize(new Dimension(130,130));
        //lbImage.setIcon(new ImageIcon(getClass().getResource("/images/user11.png")));
        lbImage.setIcon(icon);
        pnWest1.add(lbImage,BorderLayout.NORTH);
        //pnCenter
        pnCenter1 = new JPanel();
        Box b,b1,b2,b3,b4;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840,190));

        b.add(Box.createVerticalStrut(20));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblLuaChon = new JLabel("Tìm Kiếm Nhà Cung Cấp Theo: "));
        b1.add(Box.createHorizontalStrut(30));
        b1.add(cbTen = new JCheckBox("Tên Nhà Cung Cấp: "));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(cbSDT = new JCheckBox("Số Điện Thoại"));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(cbDiaChi = new JCheckBox("Địa Chỉ"));
        b.add(Box.createVerticalStrut(20));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblTen = new JLabel("Nhập Tên NCC Cần Tìm: "));
        b2.add(txtTen = new JTextField());
        b2.add(Box.createHorizontalStrut(30));
        b2.add(lblDienThoai = new JLabel("Nhập Số Điện Thoại Cần Tìm:   "));
        b2.add(txtDienThoai = new JTextField());
        b.add(Box.createVerticalStrut(20));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lbldiaChi = new JLabel("Nhập Địa Chỉ Cần Tìm: "));
        b3.add(txtdiaChi = new JTextField());
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(btnTimKiem = new JButton("Tìm Nhà Cung Cấp"));
        btnTimKiem.setIcon(new ImageIcon(getClass().getResource("/images_menu/datmua.png")));
        btnTimKiem.setEnabled(true);

        txtTen.setEnabled(false);
        txtdiaChi.setEnabled(false);
        txtDienThoai.setEnabled(false);

        lblTen.setPreferredSize(lblDienThoai.getPreferredSize());
        lbldiaChi.setPreferredSize(lblDienThoai.getPreferredSize());

        cbTen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbTen.isSelected())
                    txtTen.setEnabled(true);
                else
                    txtTen.setEnabled(false);
            }
        });
        cbSDT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbSDT.isSelected())
                    txtDienThoai.setEnabled(true);
                else
                    txtDienThoai.setEnabled(false);
            }
        });
        cbDiaChi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbDiaChi.isSelected())
                    txtdiaChi.setEnabled(true);
                else
                    txtdiaChi.setEnabled(false);
            }
        });
        pnCenter1.add(b);
        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);

        //Center
        TitledBorder tileDanhSach = new TitledBorder("Danh sách nhà cung cấp");

        //pnSouth.setPreferredSize(new Dimension(1030,60));
        pnCenter = new JPanel();

        NhaCungCap_DAO nccDao = new NhaCungCap_DAO();
        tableModel = new NCC_TableModel(nccDao.getLS());
        JTable table = new JTable(tableModel);

        //Sự kiện Table
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {
                    if(table.getValueAt(r,5).toString().length() < 20)
                        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(table.getValueAt(r,5).toString())));
                    else
                        lbImage.setIcon(new javax.swing.ImageIcon((table.getValueAt(r,5).toString())));
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
                    table.setModel(new NCC_TableModel(nccDao.TimKiemTen1(txtTen.getText().trim())));
                }else if(cbSDT.isSelected()){
                    table.setModel(new NCC_TableModel(nccDao.TimKiemSDT1(txtDienThoai.getText().trim())));
                }else if(cbDiaChi.isSelected()){
//                    table.setModel(new NV_TableModel(nvDao.TimKiemTenDC1(txtdiaChi.getText().trim())));
                    table.setModel(new NCC_TableModel(nccDao.TimKiemDC2(txtdiaChi.getText().trim())));
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


