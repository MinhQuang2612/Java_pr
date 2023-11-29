package gui;

import dao.CT_DonDatHang_DAO;
import dao.DonDatHang_DAO;
import dao.GioHang_DAO;
import dao.HoaDonBanHang_DAO;
import dao.KhachHang_DAO;
import entity.CT_DonDatHang;
import entity.HoaDonBanHang;
import entity.KhachHang;
import javax.swing.*;

import TableModel.CT_DonDatHang_tableModel;
import TableModel.KH_TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DS_KhachHang_Form extends JFrame {
    BanHang1_Form banhang;
    KhachHang kh;
    public DS_KhachHang_Form(){
        doShow();
    }
    public void doShow(){
        setSize(700,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Danh Sách Khách Hàng");
        Container cp =getContentPane();

        //pnNorth
        JPanel pnNorth = new JPanel();
        JLabel lbltieuDe = new JLabel("DANH SÁCH KHÁCH HÀNG");
        pnNorth.add(lbltieuDe);

        //pnCenter
        JPanel pnCenter = new JPanel();
        List<KhachHang> ls = new ArrayList<>();
        KhachHang_DAO khDao = new KhachHang_DAO();
        KH_TableModel model = new KH_TableModel(khDao.getLS());
        JTable table = new JTable();
        table.setModel(model);
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
        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(650,170));
        pnCenter.add(sc);

        //South
        JPanel pnSouth = new JPanel();
        JButton btnLapHD = new JButton("Lập Hóa Đơn");
        pnSouth.add(btnLapHD);
        DecimalFormat df = new DecimalFormat("#,###.00 VND");
        btnLapHD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                GioHang_DAO ghDao = new GioHang_DAO();
                DonDatHang_DAO ddhDao = new DonDatHang_DAO();
                if(r != -1){
                	if(ddhDao.TimTheoMaKH(table.getValueAt(r,0).toString()) == null) {
                		kh = khDao.TimKiemMa(table.getValueAt(r,0).toString());
                        banhang.kh = kh;
                        banhang.txtKhachHang.setText(kh.getTenKH());
                        banhang.txtSDT.setText(kh.getDienThoai());
                        banhang.txtDiaChi.setText(kh.getDiaChi());
                        HoaDonBanHang hd = new HoaDonBanHang("", "Thanh Toán Trực Tiếp", Date.valueOf(LocalDate.now()));
                        hd.setKhachHang(kh);
                        hd.setNhanVien(banhang.nv);
                        HoaDonBanHang_DAO hdDao = new HoaDonBanHang_DAO();
                        if (hdDao.addHoaDonBH(hd)) {
                            setVisible(false);
                        }
                	}else {
                		 KhachHang_DAO khDao = new KhachHang_DAO();
                         CT_DonDatHang_DAO ctdhDao = new CT_DonDatHang_DAO();
                         kh = khDao.TimKiemMa(table.getValueAt(r,0).toString());
                         banhang.kh = kh;
                         banhang.txtKhachHang.setText(kh.getTenKH());
                         banhang.txtSDT.setText(kh.getDienThoai());
                         banhang.txtDiaChi.setText(kh.getDiaChi());
                		JOptionPane.showMessageDialog(null, "Khách Hàng Đã Có Đơn Đặt Hàng");
                		List<CT_DonDatHang> ls = ctdhDao.getLS(ddhDao.TimTheoMaKH(table.getValueAt(r,0).toString()).getMaDDH());
                        for (CT_DonDatHang ct: ls) {
                            banhang.tongTien += ct.getThanhTien();
                            banhang.txttienThuoc.setText(String.valueOf(df.format(banhang.tongTien)));
                            banhang.txtThue.setText("4%");
                            banhang.txtTong.setText(String.valueOf(df.format(banhang.tongTien + banhang.tongTien*0.04)));
                        }
                        ddhDao.TimTheoMaKH(table.getValueAt(r,0).toString()).setTinhTrang("Hoàn Thành");
                        banhang.table1.setModel(new CT_DonDatHang_tableModel(ls));
                        HoaDonBanHang hd = new HoaDonBanHang("", "Thanh Toán Trực Tiếp", Date.valueOf(LocalDate.now()));
                        hd.setKhachHang(kh);
                        hd.setNhanVien(banhang.nv);
                        HoaDonBanHang_DAO hdDao = new HoaDonBanHang_DAO();
                        if (hdDao.addHoaDonBH(hd)) {
                            setVisible(false);
                        }
                	}
                }
            }
        });

        cp.setLayout(new BorderLayout());
        cp.add(pnNorth, BorderLayout.NORTH);
        cp.add(pnCenter, BorderLayout.CENTER);
        cp.add(pnSouth, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new DS_KhachHang_Form().setVisible(true);
    }
}
