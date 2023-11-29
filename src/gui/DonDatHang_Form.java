package gui;

import TableModel.CT_GioHang_TableModel;
import TableModel.DDH_TableModel;
import dao.*;
import entity.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonDatHang_Form extends JPanel {
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblMaDDH,lblMaKH,lblNgayDat,lblPT,lblTinhTrang,lblTenKH;
    JTextField txtMaDDH,txtMaKH,txtNgayDat,txtPT,txtTinhTrang,txtTenKH;
    JButton btnLuuDatHang,btnQuayLai;
    KhachHang kh;
    public DonDatHang_Form(){

    }
    public void doShow(){
        this.setLayout(new BorderLayout());
        //pn_North - Tiêu Đề
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        JPanel pnNorth2 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("ĐƠN ĐẶT HÀNG");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.BLUE);
        pnNorth1.add(lblTieuDe);
        Box bx,bx1,bx2,bx3;
        bx = Box.createVerticalBox();
        bx.setPreferredSize(new Dimension(800,120));
        bx.add(bx1 = Box.createHorizontalBox());
        bx1.add(lblMaDDH = new JLabel("Mã Đơn Đặt Hàng: "));
        bx1.add(txtMaDDH = new JTextField(30));
        bx1.add(Box.createHorizontalStrut(10));
        bx1.add(lblTenKH = new JLabel("Tên Khách Hàng "));
        bx1.add(txtTenKH = new JTextField(30));

        bx.add(Box.createVerticalStrut(15));
        bx.add(bx2 = Box.createHorizontalBox());
        bx2.add(lblMaKH = new JLabel("Mã Khách Hàng: "));
        bx2.add(txtMaKH = new JTextField(30));
        bx2.add(Box.createHorizontalStrut(10));
        bx2.add(lblNgayDat = new JLabel("Ngày Đặt Hàng: "));
        bx2.add(txtNgayDat = new JTextField(30));

        bx.add(Box.createVerticalStrut(15));
        bx.add(bx3 = Box.createHorizontalBox());
        bx3.add(lblTinhTrang = new JLabel("Tình Trạng: "));
        bx3.add(txtTinhTrang = new JTextField(20));
        bx3.add(Box.createHorizontalStrut(10));
        bx3.add(lblPT = new JLabel("Phương Thức Thanh Toán: "));
        ButtonGroup btnGroup = new ButtonGroup();
        JRadioButton rbTT,rbOL;
        bx3.add(rbTT = new JRadioButton("Thanh Toán Trực Tiếp"));
        bx3.add(rbOL = new JRadioButton("Thanh Toán Online"));
        btnGroup.add(rbTT);
        btnGroup.add(rbOL);
        bx.add(Box.createVerticalStrut(15));
        pnNorth2.add(bx);
        pnNorth.add(pnNorth1,BorderLayout.NORTH);
        pnNorth.add(pnNorth2,BorderLayout.CENTER);
        lblMaKH.setPreferredSize(lblMaDDH.getPreferredSize());
        lblTenKH.setPreferredSize(lblMaDDH.getPreferredSize());
        lblNgayDat.setPreferredSize(lblMaDDH.getPreferredSize());
        lblTinhTrang.setPreferredSize(lblMaDDH.getPreferredSize());

        txtMaDDH.setEditable(false);
        txtMaKH.setText(kh.getMaKH());
        txtMaKH.setEditable(false);
        GD_Chinh gd = new GD_Chinh();
        KhachHang_DAO khDao = new KhachHang_DAO();
        txtTenKH.setText(khDao.TimKiemMa(txtMaKH.getText().trim()).getTenKH());
        txtTenKH.setEditable(false);
        Date date = Date.valueOf(LocalDate.now());
        txtNgayDat.setText(date.toString());
        txtNgayDat.setEditable(false);
        txtTinhTrang.setText("Đang Xử Lý");
        txtTinhTrang.setEditable(false);


        //pnCenter - Danh sách đơn hàng
        pnCenter = new JPanel();
        JPanel pnTB1 = new JPanel();
        JPanel pnTB2 = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        pnTB1.setBorder(new TitledBorder("Danh Sách Sản Phẩm Mua"));
        pnTB2.setBorder(new TitledBorder("Danh Sách Đơn Đặt Hàng"));

        CT_GioHang_DAO ctghDao = new CT_GioHang_DAO();
        GioHang_DAO ghDao = new GioHang_DAO();
        System.out.println(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH());
        System.out.println(ctghDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH()));
        CT_GioHang_TableModel tableModel1 = new CT_GioHang_TableModel(ctghDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH()));
        JTable table1 = new JTable(tableModel1);
        JScrollPane sc1 = new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc1.setPreferredSize(new Dimension(930,100));

        pnTB1.add(sc1,BorderLayout.NORTH);


        DonDatHang_DAO ddhDao = new DonDatHang_DAO();
        List<DonDatHang> ls = new ArrayList<>();
        DDH_TableModel tableModel = new DDH_TableModel(ls);
        JTable table = new JTable(tableModel);

        //Sự kiện Table
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
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
        sc.setPreferredSize(new Dimension(930,100));
        pnTB2.add(sc,BorderLayout.SOUTH);

        pnCenter.add(pnTB1,BorderLayout.NORTH);
        pnCenter.add(pnTB2,BorderLayout.SOUTH);

        //pnSouth - Tác vụ
        pnSouth = new JPanel();
        pnSouth.setBorder(new TitledBorder("Tác Vụ"));
        btnLuuDatHang = new JButton("Lưu Đơn Hàng");
        btnLuuDatHang.setIcon(new ImageIcon(getClass().getResource("/images_menu/huydathang.png")));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        btnLuuDatHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Date.valueOf(txtNgayDat.getText()));
                CT_GioHang_DAO ctghDao = new CT_GioHang_DAO();
                if(ctghDao.getLS().size() != 0){
                    String pt = "";
                    if (rbTT.isSelected())
                        pt = rbTT.getText();
                    else
                        pt = rbOL.getText();
                    DonDatHang dh = new DonDatHang(txtMaDDH.getText().trim(), pt, txtTinhTrang.getText(), Date.valueOf(txtNgayDat.getText()));
                    KhachHang_DAO khDao = new KhachHang_DAO();
                    KhachHang kh = khDao.TimKiemMa(txtMaKH.getText().trim());
                    dh.setKhachHang(kh);
                    System.out.println(dh);
                    if(ddhDao.addDonDatHang(dh)){
                        ctghDao = new CT_GioHang_DAO();
                        CT_DonDatHang_DAO ctddhDao = new CT_DonDatHang_DAO();
                        SanPham_DAO lkDao = new SanPham_DAO();
                        GioHang_DAO ghDao = new GioHang_DAO();
                        for (CT_GioHang ctgh: ctghDao.getLS(ghDao.TimKiemMaKH(txtMaKH.getText().trim()).getMaGH())) {
                            System.out.println(ctgh);
                            CT_DonDatHang ctdh = new CT_DonDatHang();
                            ctdh.setSoLuong(ctgh.getSoLuong());
                            ctdh.setThanhTien(ctgh.getThanhTien());
                            ctdh.setDonDatHang(ddhDao.TimTheoMaKH(txtMaKH.getText().trim()));
                            ctdh.setSanPham(ctgh.getSanPham());
                            System.out.println(ctdh);
                            ctddhDao.addCTDonDatHang(ctdh);
                            ctghDao.deleteCTGH_TheoMa(ghDao.TimKiemMaKH(txtMaKH.getText()).getMaGH(),ctgh.getSanPham().getMaSP());
                        }
                        System.out.println(dh.getMaDDH());
                        System.out.println((ctddhDao.getLS(ddhDao.TimTheoMaKH(txtMaKH.getText()).getMaDDH())));
                        table.setModel(new DDH_TableModel(ddhDao.getLS(txtMaKH.getText())));
                        table1.setModel(new CT_GioHang_TableModel(ctghDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Không còn sản phẩm trong giỏ hàng");
                }

            }
        });
        btnQuayLai = new JButton("Quay Lại");
        btnQuayLai.setIcon(new ImageIcon(getClass().getResource("/images_menu/quaylai.png")));

        Box d = Box.createHorizontalBox();
        d.add(btnLuuDatHang);
        d.add(Box.createHorizontalStrut(150));
        d.add(btnQuayLai);
        pnSouth.add(d);
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
}
