package gui;

import TableModel.CT_GioHang_TableModel;
import dao.CT_GioHang_DAO;
import dao.GioHang_DAO;
import dao.SanPham_DAO;
import entity.KhachHang;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GioHang_Form extends JPanel {
    private JPanel pnNorth,pnCenter,pnThongTin,pnTable,pnTacVu,pnSouth,pnWest1,pnCenter1;
    JButton btnDatHang,btnCapNhat,btnQuayLai,btnCapNhatSL,btnXoa;
    JLabel lbImage,lblTen,lblLoai,lblHangSX,lblSoLuong,lblDonGia,lblBaoHanh;
    JTextField txtTen,txtLoai,txtHangSX,txtSoLuong,txtDonGia,txtBaoHanh;
    ImageIcon icon;
    KhachHang kh;

    public GioHang_Form(){


    }
    public KhachHang thongTin(){
        return kh;
    }
    public void doShow(){
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("GIỎ HÀNG");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.BLUE);
        pnNorth1.add(lblTieuDe);
        pnNorth.add(pnNorth1,BorderLayout.NORTH);


        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        pnThongTin = new JPanel();
        pnTable = new JPanel();
        pnTacVu = new JPanel();


        //pnWest1
        pnWest1 = new JPanel();
        pnWest1.setLayout(new BorderLayout());
        pnWest1.setPreferredSize(new Dimension(150,150));
        lbImage = new JLabel();

        icon = new ImageIcon(getClass().getResource("/images_LinhKien/gigabyte1.png"));
        lbImage.setPreferredSize(new Dimension(130,130));
        //lbImage.setIcon(new ImageIcon(getClass().getResource("/images/user11.png")));
        lbImage.setIcon(icon);
        pnWest1.add(lbImage,BorderLayout.NORTH);
        //pnCenter
        pnCenter1 = new JPanel();
        Box b,b1,b2,b3,b4;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(750,100));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblTen = new JLabel("Tên Linh Kiện: "));
        b1.add(txtTen = new JTextField());
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblLoai = new JLabel("Loại Linh Kiện:    "));
        b1.add(txtLoai = new JTextField());
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblHangSX = new JLabel("Hãng Sản Xuất: "));
        b2.add(txtHangSX = new JTextField());
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblSoLuong = new JLabel("Số Lượng:    "));
        b2.add(txtSoLuong = new JTextField());
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblDonGia = new JLabel("Đơn Giá: "));
        b3.add(txtDonGia = new JTextField());
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblBaoHanh = new JLabel("Bảo Hành:    "));
        b3.add(txtBaoHanh = new JTextField());
        b.add(Box.createVerticalStrut(10));

        pnCenter1.add(b);
        pnThongTin.add(pnWest1,BorderLayout.WEST);
        pnThongTin.add(pnCenter1,BorderLayout.CENTER);

        lblTen.setPreferredSize(lblLoai.getPreferredSize());
        lblBaoHanh.setPreferredSize(lblLoai.getPreferredSize());
        lblDonGia.setPreferredSize(lblLoai.getPreferredSize());
        lblHangSX.setPreferredSize(lblLoai.getPreferredSize());
        lblSoLuong.setPreferredSize(lblLoai.getPreferredSize());

        System.out.println();
        CT_GioHang_DAO ctghDao = new CT_GioHang_DAO();
        GioHang_DAO ghDao = new GioHang_DAO();
        System.out.println(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH());
        System.out.println(ctghDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH()));
        CT_GioHang_TableModel tableModel = new CT_GioHang_TableModel(ctghDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH()));
        JTable table = new JTable(tableModel);

        //Sự kiện Table
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    btnCapNhatSL.setEnabled(true);
                    btnXoa.setEnabled(true);
                    txtTen.setText(table.getValueAt(r,1).toString());
                    txtTen.setEditable(false);
                    txtLoai.setText(table.getValueAt(r,2).toString());
                    txtLoai.setEditable(false);
                    txtHangSX.setText(table.getValueAt(r,3).toString());
                    txtHangSX.setEditable(false);
                    txtSoLuong.setText(table.getValueAt(r,4).toString());
                    txtDonGia.setText(table.getValueAt(r,5).toString());
                    txtDonGia.setEditable(false);
                    txtBaoHanh.setText(table.getValueAt(r,6).toString());
                    txtBaoHanh.setEditable(false);
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
        sc.setPreferredSize(new Dimension(930,180));
        pnTable.add(sc);

        btnCapNhatSL = new JButton("Cập Nhật Số Lượng");
        btnXoa = new JButton("Xóa Sản Phẩm");
        btnXoa.setIcon(new ImageIcon(getClass().getResource("/images_menu/xoa.png")));
        btnCapNhatSL.setIcon(new ImageIcon(getClass().getResource("/images_menu/sua.png")));

        Box c = Box.createHorizontalBox();
        c.add(btnCapNhatSL);
        btnCapNhatSL.setEnabled(false);
        btnXoa.setEnabled(false);
        c.add(Box.createHorizontalStrut(150));
        c.add(btnXoa);

        //Sự kiện cập nhật
        btnCapNhatSL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                btnCapNhatSL.setEnabled(false);
                btnXoa.setEnabled(false);
                if(r != -1) {
                    CT_GioHang_DAO ctGioHangDao = new CT_GioHang_DAO();
                    GioHang_DAO ghDao = new GioHang_DAO();
                    SanPham_DAO linhKien_dao = new SanPham_DAO();
                    if (ctGioHangDao.updateCTGH(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH(),linhKien_dao.TimKiemTen(table.getValueAt(r,1).toString()).getMaSP(),Integer.parseInt(txtSoLuong.getText().trim()))){
                        try {
                            System.out.println("ok");
                            table.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                table.clearSelection();
            }
        });
        //Sự kiện xóa sản phẩm
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                btnXoa.setEnabled(false);
                btnCapNhatSL.setEnabled(false);
                if(r != -1) {
                    CT_GioHang_DAO ctGioHangDao = new CT_GioHang_DAO();
                    GioHang_DAO ghDao = new GioHang_DAO();
                    SanPham_DAO sanPham_dao = new SanPham_DAO();
                    System.out.println(sanPham_dao.TimKiemTen(table.getValueAt(r,1).toString()).getMaSP());
                    String ma = sanPham_dao.TimKiemTen(table.getValueAt(r,1).toString()).getMaSP();
                    int slg = sanPham_dao.TimKiemTen(table.getValueAt(r,1).toString()).getSoLuong();
                    int sl = ctghDao.TimTheoMaGH(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH()).getSoLuong();
                    if (ctGioHangDao.deleteCTGH_TheoMa(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH(),sanPham_dao.TimKiemTen(table.getValueAt(r,1).toString()).getMaSP())){
                        try {
                            sanPham_dao.updateSoLuong(ma,sl+slg);
                            table.setModel(new CT_GioHang_TableModel(ctGioHangDao.getLS(ghDao.TimKiemMaKH(kh.getMaKH()).getMaGH())));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });


        pnTacVu.add(c);


        pnCenter.add(pnThongTin,BorderLayout.NORTH);
        pnCenter.add(pnTable,BorderLayout.CENTER);
        pnCenter.add(pnTacVu,BorderLayout.SOUTH);
        pnTable.setBorder(new TitledBorder("Danh Sách Sản Phẩm"));

        //pnSouth
        pnSouth = new JPanel();
        btnDatHang = new JButton("Đặt Hàng");
        btnCapNhat = new JButton("Cập Nhật Giỏ Hàng");
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
        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                TT_SanPham_Form tt = new TT_SanPham_Form();
                tt.kh = kh;
                tt.doShow();
                add(tt);
                validate();
            }
        });
        btnDatHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                DonDatHang_Form tt = new DonDatHang_Form();
                tt.kh = kh;
                tt.doShow();
                add(tt);
                validate();
            }
        });
        btnCapNhat.setIcon(new ImageIcon(getClass().getResource("/images_menu/capnhatgiohang.png")));
        btnDatHang.setIcon(new ImageIcon(getClass().getResource("/images_menu/thanhtoan.png")));
        btnQuayLai.setIcon(new ImageIcon(getClass().getResource("/images_menu/quaylai.png")));
        pnSouth.setBorder(new TitledBorder("Tác Vụ"));
        pnSouth.add(btnDatHang);
        pnSouth.add(btnCapNhat);
        pnSouth.add(btnQuayLai);

        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
}
