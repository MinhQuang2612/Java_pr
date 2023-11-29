package gui;

import TableModel.CT_DonDatHang_tableModel;
import TableModel.CT_GioHang_TableModel;
import TableModel.DDH_TableModel;
import dao.*;
import entity.CT_DonDatHang;
import entity.CT_GioHang;
import entity.KhachHang;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class DonHang_Form extends JPanel {
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblMaDDH,lblMaKH,lblNgayDat,lblPT,lblTinhTrang,lblTongTien;
    JTextField txtMaDDH,txtMaKH,txtNgayDat,txtPT,txtTinhTrang,txtTongTien;
    JButton btnHuyDatHang,btnQuayLai;
    KhachHang kh;
    List<CT_GioHang> ls;
    public DonHang_Form(){

    }
    public void doShow(){
        this.setLayout(new BorderLayout());
        //pn_North - Tiêu Đề
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        JPanel pnNorth2 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ ĐƠN HÀNG");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.BLUE);
        pnNorth1.add(lblTieuDe);
        Box bx,bx1,bx2,bx3;
        bx = Box.createVerticalBox();
        bx.setPreferredSize(new Dimension(800,120));
        bx.add(bx1 = Box.createHorizontalBox());
        bx1.add(lblMaDDH = new JLabel("Mã Đơn Hàng: "));
        bx1.add(txtMaDDH = new JTextField());
        bx1.add(Box.createHorizontalStrut(30));
        bx1.add(lblMaKH = new JLabel("Mã Khách Hàng: "));
        bx1.add(txtMaKH = new JTextField());
        bx.add(Box.createVerticalStrut(15));
        bx.add(bx2 = Box.createHorizontalBox());
        bx2.add(lblNgayDat = new JLabel("Ngày Đặt Hàng: "));
        bx2.add(txtNgayDat = new JTextField());
        bx2.add(Box.createHorizontalStrut(30));
        bx2.add(lblPT = new JLabel("Phương Thức Thanh Toán: "));
        bx2.add(txtPT = new JTextField());
        bx.add(Box.createVerticalStrut(15));
        bx.add(bx3 = Box.createHorizontalBox());
        bx3.add(lblTinhTrang = new JLabel("Tình Trạng: "));
        bx3.add(txtTinhTrang = new JTextField());
        bx3.add(Box.createHorizontalStrut(30));
        bx3.add(lblTongTien = new JLabel("Tổng Tiền: "));
        bx3.add(txtTongTien = new JTextField());
        bx.add(Box.createVerticalStrut(15));
        pnNorth2.add(bx);
        pnNorth.add(pnNorth1,BorderLayout.NORTH);
        pnNorth.add(pnNorth2,BorderLayout.CENTER);
        lblMaDDH.setPreferredSize(lblPT.getPreferredSize());
        lblMaKH.setPreferredSize(lblPT.getPreferredSize());
        lblNgayDat.setPreferredSize(lblPT.getPreferredSize());
        lblTinhTrang.setPreferredSize(lblPT.getPreferredSize());
        lblTongTien.setPreferredSize(lblPT.getPreferredSize());


        //pnCenter - Danh sách đơn hàng
        pnCenter = new JPanel();
        JPanel pnCenter_N = new JPanel();
        JPanel pnCenter_C = new JPanel();
        pnCenter_N.setBorder(new TitledBorder("Danh Sách Đơn Hàng"));
        pnCenter_C.setBorder(new TitledBorder("Danh Sách Sản Phẩm"));
        pnCenter.setLayout(new BorderLayout());
        DonDatHang_DAO ddhDao = new DonDatHang_DAO();
        GioHang_DAO ghDao = new GioHang_DAO();
        DDH_TableModel tableModel = new DDH_TableModel(ddhDao.getLS(kh.getMaKH()));
        JTable table = new JTable(tableModel);

        //Sự kiện Table
        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(800,100));
//        CT_GioHang_DAO ctghDao = new CT_GioHang_DAO();
        ls = new ArrayList<>();
        CT_GioHang_TableModel tableModel1 = new CT_GioHang_TableModel(ls);
        JTable table1 = new JTable(tableModel1);

        //Sự kiện Table
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    txtMaDDH.setText(table.getValueAt(r,0).toString());
                    txtMaKH.setText(table.getValueAt(r,1).toString());
                    txtNgayDat.setText(table.getValueAt(r,2).toString());
                    txtPT.setText(table.getValueAt(r,3).toString());
                    txtTinhTrang.setText(table.getValueAt(r,4).toString());
                    txtTongTien.setText(table.getValueAt(r,5).toString());
                    CT_DonDatHang_DAO ctdhDao = new CT_DonDatHang_DAO();
                    GioHang_DAO ghDao = new GioHang_DAO();
                    table1.setModel(new CT_DonDatHang_tableModel(ctdhDao.getLS(txtMaDDH.getText().trim())));
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
        sc1.setPreferredSize(new Dimension(800,100));
        pnCenter_N.add(sc);pnCenter.add(pnCenter_N,BorderLayout.NORTH);
        pnCenter_C.add(sc1);pnCenter.add(pnCenter_C,BorderLayout.CENTER);

        //pnSouth - Tác vụ
        pnSouth = new JPanel();
        pnSouth.setBorder(new TitledBorder("Tác Vụ"));
        btnHuyDatHang = new JButton("Hủy Đơn Hàng");
        btnHuyDatHang.setIcon(new ImageIcon(getClass().getResource("/images_menu/huydathang.png")));
        btnQuayLai = new JButton("Quay Lại");
        btnQuayLai.setIcon(new ImageIcon(getClass().getResource("/images_menu/quaylai.png")));

        Box d = Box.createHorizontalBox();
        d.add(btnHuyDatHang);
        d.add(Box.createHorizontalStrut(150));
        btnHuyDatHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                CT_DonDatHang_DAO ctdhDao = new CT_DonDatHang_DAO();
                List<CT_DonDatHang> ls  =new ArrayList<>();
                SanPham_DAO lkDao = new SanPham_DAO();
                int lc = JOptionPane.showConfirmDialog(null,"Bạn Có Chắc Chắn Muốn Xóa Dòng Này Không!","Delete",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION)
                        if(r != -1){
                            if(ddhDao.deleteDH_TheoMa(table.getValueAt(r,0).toString())){
                                table1.setModel(new CT_DonDatHang_tableModel(ls));
                                table.setModel(new DDH_TableModel(ddhDao.getLS(kh.getMaKH())));

                            }
                }
            }
        });
        d.add(btnQuayLai);
        pnSouth.add(d);
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
}
