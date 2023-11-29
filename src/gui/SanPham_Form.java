package gui;

import TableModel.NV_TableModel;
import TableModel.SP_TableModel;
import com.toedter.calendar.JDateChooser;
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

public class SanPham_Form extends JPanel {
    JPanel pnNorth,pnCenter1,pnWest1,pnCenter,pnSouth;
    JLabel lblMa,lblTen,lblNgaySX,lblBaoHanh,lblTinhTrang,lblDonViTinh,lblGia,lblLoai,lblMoTa,lblNhaSX,lbImage,lblSoLuong;
    JTextField txtMa,txtTen,txtNhaSX,txtDonViTinh,txtGia,txtMoTa,txtSoLuong,txtTinhTrang, txtTim;
    JButton btnThem,btnXoa,btnSua,btnLuu,btnThoat,btnSuaAnh, btnTim;
    ImageIcon icon;
    ImageIcon lk [];
    JComboBox cbcGT,cbcChucVu,cbcBaoHanh,cbcLoai,cbcNhaSX,cbcDonVi;
    JDateChooser ngaySanXuat;
    SanPham_DAO spDao;
    SanPham sp;
    List<SanPham> lsp;
    SP_TableModel tableModel;
    public SanPham_Form(){
        doshow();
    }

    private void doshow() {
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ SẢN PHẨM");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.BLUE);
        pnNorth1.add(lblTieuDe);
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
        btnSuaAnh = new JButton("Chỉnh sửa ảnh");
        btnSuaAnh.setFont(new Font("Arial",Font.ITALIC,13));
        btnSuaAnh.setIcon(new ImageIcon(getClass().getResource("/images_menu/suaAnh.png")));
        btnSuaAnh.setPreferredSize(new Dimension(70,30));
        btnSuaAnh.setVisible(true);

        //File
        final JFileChooser  fileDialog = new JFileChooser();
        JFrame cha = new JFrame();
        btnSuaAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileDialog.showOpenDialog(cha);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileDialog.getSelectedFile();
//                    statusLabel.setText("File Selected :"
//                            + file.getParent());
                    lbImage.setIcon(new ImageIcon(file.getPath()));
                    System.out.println(file.getPath());
                }
                else{
                }
            }
        });
        icon = new ImageIcon("D:\\Nhom13_QuanLyBanHangQuanAo\\src\\images_SanPham\\sanpham11.png");
        lbImage.setPreferredSize(new Dimension(110,145));
        //lbImage.setIcon(new ImageIcon(getClass().getResource("/images/user11.png")));
        lbImage.setIcon(icon);
        pnWest1.add(lbImage,BorderLayout.CENTER);
        pnWest1.add(btnSuaAnh,BorderLayout.SOUTH);
        pnWest1.add(pn1,BorderLayout.WEST);
        pnWest1.add(pn2,BorderLayout.EAST);
        //pnCenter
        pnCenter1 = new JPanel();
        Box b,b1,b2,b3,b4,b5;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(860,180));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Sản Phẩm: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên Sản Phẩm:    "));
        b1.add(txtTen = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblBaoHanh = new JLabel("Bảo Hành: "));
        cbcBaoHanh = new JComboBox<>();
        cbcBaoHanh.addItem("2 tháng");
        cbcBaoHanh.addItem("3 tháng");
        cbcBaoHanh.addItem("5 tháng");
        cbcBaoHanh.addItem("6 tháng");
        cbcBaoHanh.addItem("Không có");
        cbcBaoHanh.setPreferredSize(new Dimension(322,20));
        b2.add(cbcBaoHanh);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblNgaySX = new JLabel("Ngày Sản Xuất:    "));
        ngaySanXuat = new JDateChooser();
        //ngaySanXuat.setSize(new Dimension(30,20));
        ngaySanXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ngaySanXuat.setDateFormatString("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            ngaySanXuat.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b2.add(ngaySanXuat);
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblTinhTrang = new JLabel("Tình Trạng: "));
        b3.add(txtTinhTrang = new JTextField(30));
//        txtTinhTrang.setText("Mới - FullBox 100%");
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblLoai = new JLabel("Loại Sản Phẩm: "));
        cbcLoai = new JComboBox<>();
        cbcLoai.addItem("Quần Áo");
        cbcLoai.addItem("Giày Dép");
        cbcLoai.addItem("Phụ Kiện");
        cbcLoai.setPreferredSize(new Dimension(325,20));
        b3.add(cbcLoai);
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblDonViTinh = new JLabel("Đơn Vị Tính: "));
        b4.add(txtDonViTinh = new JTextField(30));
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblGia = new JLabel("Đơn Giá:    "));
        b4.add(txtGia = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b5 = Box.createHorizontalBox());
        b5.add(lblSoLuong = new JLabel("Số Lượng:    "));
        b5.add(txtSoLuong = new JTextField(30));
        b5.add(Box.createHorizontalStrut(20));
        b5.add(lblNhaSX = new JLabel("Nhà Sản Xuất:    "));
        cbcNhaSX = new JComboBox<>();
        cbcNhaSX.addItem("BOBUI");
        cbcNhaSX.addItem("Dirty Coins");
        cbcNhaSX.addItem("TSUN");
        cbcNhaSX.addItem("SWE");
        cbcNhaSX.addItem("Teelab");
        cbcNhaSX.addItem("Odin");
        cbcNhaSX.addItem("Hades");
        cbcNhaSX.addItem("Ananas");
        cbcNhaSX.addItem("Juno");
        cbcNhaSX.addItem("Bitis");
        cbcNhaSX.addItem("Laforce");
        cbcNhaSX.addItem("MWC");
        cbcNhaSX.addItem("Pauvie Jewelry - U");
        cbcNhaSX.addItem("Docs");
        cbcNhaSX.addItem("Hiphop Pallas");
        cbcNhaSX.addItem("Rouge");
        cbcNhaSX.setPreferredSize(new Dimension(322,20));
        b5.add(cbcNhaSX);



        pnCenter1.add(b);
        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);

        lblBaoHanh.setPreferredSize(lblNgaySX.getPreferredSize());
        lblDonViTinh.setPreferredSize(lblNgaySX.getPreferredSize());
        lblGia.setPreferredSize(lblNgaySX.getPreferredSize());
        lblMa.setPreferredSize(lblNgaySX.getPreferredSize());
        lblTen.setPreferredSize(lblNgaySX.getPreferredSize());
        lblLoai.setPreferredSize(lblNgaySX.getPreferredSize());
        lblNhaSX.setPreferredSize(lblNgaySX.getPreferredSize());
        lblSoLuong.setPreferredSize(lblNgaySX.getPreferredSize());
        lblTinhTrang.setPreferredSize(lblNgaySX.getPreferredSize());

        //Center
        TitledBorder tileDanhSach = new TitledBorder("Danh sách sản phẩm");

        //pnSouth.setPreferredSize(new Dimension(1030,60));
        pnCenter = new JPanel();
        pnCenter.setBorder(tileDanhSach);
        //spDao = new SanPham_DAO();
//        tableModel = new SP_TableModel(spDao.getLS());
//        JTable table = new JTable(tableModel);
        List<SanPham> ls = new ArrayList<>();
        SP_TableModel model = new SP_TableModel(ls);
        JTable table = new JTable();
        table.setModel(model);

        //Sự kiện Table
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    ImageIcon icon;
                    txtMa.setText(table.getValueAt(r,0).toString());
                    txtTen.setText(table.getValueAt(r,1).toString());
                    cbcBaoHanh.setSelectedItem(table.getValueAt(r,2).toString());
                    ngaySanXuat.setDate(Date.valueOf(table.getValueAt(r,3).toString()));
                    txtTinhTrang.setText(table.getValueAt(r,4).toString());
                    txtTinhTrang.setEditable(false);
                    cbcLoai.setSelectedItem(table.getValueAt(r,8).toString());
                    txtDonViTinh.setText(table.getValueAt(r,6).toString());
                    txtGia.setText(table.getValueAt(r,7).toString());
                    txtSoLuong.setText(table.getValueAt(r,5).toString());
                    cbcNhaSX.setSelectedItem(table.getValueAt(r,9).toString());
                    String [] p = table.getValueAt(r,10).toString().trim().split("-");
                    System.out.println(p[0]);
                    System.out.println(p[1]);
                    System.out.println(p[2]);
                    System.out.println(p.length);
//                    System.out.println(p[3]);

                    if(table.getValueAt(r,10).toString().trim().contains("C:\\")){
//                        System.out.println("Chuoi: "+table.getValueAt(r,10).toString().trim());
                         icon = new ImageIcon(table.getValueAt(r,10).toString().trim());
                    }else{
//                        System.out.println("Chuoiq"+p[0]);
                         icon = new javax.swing.ImageIcon(getClass().getResource(p[0]));
                    }

                    lbImage.setIcon(icon);
//                    System.out.println(p.length);

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
        sc.setPreferredSize(new Dimension(980,210));
        pnCenter.add(sc);
        //South
        TitledBorder tileTacvu = new TitledBorder("Tác vụ");
        pnSouth = new JPanel();
        pnSouth.setBorder(tileTacvu);
        pnSouth.setPreferredSize(new Dimension(1030,70));
        
        txtTim = new JTextField(10);
        btnTim = new JButton("Tìm Sản Phẩm");
        
        btnTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spDao = new SanPham_DAO();
				lsp = spDao.TimKiemMa1(txtTim.getText());
				System.out.println(lsp);
				if (lsp.size() == 0) {
					JOptionPane.showMessageDialog(null, "Sản phẩm không tồn tại!");
				}
				else {
					table.setModel(new SP_TableModel(lsp));
				}
				
			}
		});
        btnThem = new JButton("Thêm Sản Phẩm");
        btnThem.setIcon(new ImageIcon(getClass().getResource("/images_menu/them.png")));
        //Su kien Them
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbImage.setIcon(new ImageIcon("D:\\Nhom13_QuanLyBanHangQuanAo\\src\\images_SanPham\\sanpham11.png"));
                btnSuaAnh.setText("Chỉnh sửa ảnh");
                btnSuaAnh.setEnabled(true);
                txtMa.setEditable(false);
                txtMa.setText("");
                txtTen.setText("");
                clearText();
                txtTinhTrang.setText("Mới");
                txtTinhTrang.setEditable(false);
                txtTen.requestFocus();
            }
        });
//        btnXoa = new JButton("Xóa Sản Phẩm");
//        btnXoa.setIcon(new ImageIcon(getClass().getResource("/images_menu/xoa.png")));
        //Sự kiện xóa
//        btnXoa.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int r = table.getSelectedRow();
//                if(r != -1) {
//                    int tb = JOptionPane.showConfirmDialog(null,"Bạn chắc chắn muốn xóa sản phẩm này?","Delete",
//                            JOptionPane.YES_NO_OPTION);
//                    if(tb == JOptionPane.YES_OPTION) {
//                        String maX = tableModel.getValueAt(r, 0).toString();
//                        System.out.println(maX);
//                        if (spDao.deleteSP(maX)) {
//                            try {
//                                clearText();
//                                table.setModel(new SP_TableModel(spDao.getLS()));
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//                        }
////                        clearTextField();
//                    }
//                }else{
//                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn sản phẩm cần xóa!");
//                }
//            }
//        });
        btnSua = new JButton("Sửa TT Sản Phẩm");
        btnSua.setIcon(new ImageIcon(getClass().getResource("/images_menu/sua.png")));

        //Su Kien Sua
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateTime = (String) formatter.format(ngaySanXuat.getDate());
                btnSuaAnh.setEnabled(true);
                int r = table.getSelectedRow();
                txtMa.setEnabled(false);
                if(r != -1){
                    btnSuaAnh.setText("Chỉnh sửa ảnh");
                    btnSuaAnh.setVisible(true);String maS = tableModel.getValueAt(r, 0).toString();
                    SanPham spSua = new SanPham(maS, txtTen.getText(), cbcBaoHanh.getSelectedItem().toString(), java.sql.Date.valueOf(dateTime),
                            txtTinhTrang.getText(), txtDonViTinh.getText(),Double.parseDouble(txtGia.getText()),Integer.parseInt(txtSoLuong.getText()),
                            icon.getDescription());
                    LoaiSanPham_DAO loaiLinhKien_dao = new LoaiSanPham_DAO();
                    NhaSanXuat_DAO nhaSanXuat_dao = new NhaSanXuat_DAO();
                    LoaiSanPham lsp = loaiLinhKien_dao.TimKiemTen(cbcLoai.getSelectedItem().toString());
                    NhaSanXuat nsx = nhaSanXuat_dao.TimKiemTen(cbcNhaSX.getSelectedItem().toString());
                    spSua.setLoaiSanPham(lsp);spSua.setNhaSanXuat(nsx);
                    System.out.println(spSua);
                    if (spDao.updateSanPham(maS, spSua)) {
                        try {
                            clearText();
                            table.setModel(new SP_TableModel(spDao.getLS()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng nào!");
                }
            }
        });

        btnLuu = new JButton("Lưu Thông Tin");
        btnLuu.setIcon(new ImageIcon(getClass().getResource("/images_menu/luu.png")));

        //Su kien luu
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSuaAnh.setEnabled(false);
                String dateTime = (String) formatter.format(ngaySanXuat.getDate());
                SanPham sp = new SanPham(txtMa.getText().trim(), txtTen.getText().trim(),
                        cbcBaoHanh.getSelectedItem().toString(),
                        java.sql.Date.valueOf(dateTime),
                        txtTinhTrang.getText().trim(),
                        txtDonViTinh.getText().trim(),
                        Double.valueOf(txtGia.getText()),
                        Integer.valueOf(txtSoLuong.getText()),
                        icon.getDescription());
                LoaiSanPham_DAO lspDao = new LoaiSanPham_DAO();
                LoaiSanPham lsp;
                NhaSanXuat_DAO nsxDao = new NhaSanXuat_DAO();
                NhaSanXuat nsx;
                if(lspDao.TimKiemTen(cbcLoai.getSelectedItem().toString()) != null) {
                    lsp = lspDao.TimKiemTen(cbcLoai.getSelectedItem().toString());
                    sp.setLoaiSanPham(lsp);
                    nsx = nsxDao.TimKiemTen(cbcNhaSX.getSelectedItem().toString());
                    sp.setNhaSanXuat(nsx);
                    if(spDao.addSanPham(sp)) {
                        try {
                            table.setModel(new SP_TableModel(spDao.getLS()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    else
                        JOptionPane.showMessageDialog(null,"Bạn chưa nhập thông tin !");
                }else{
                    JOptionPane.showMessageDialog(null,"Lỗi!");
                }
//                clearTextField();
                clearText();
                table.setModel(new SP_TableModel(spDao.getLS()));
                System.out.println(table.getRowCount());

                // tableModel.fireTableDataChanged();

            }
        });
        btnThoat = new JButton("Thoát ");
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                TrangChu_Form tc = new TrangChu_Form();
                add(tc);
                validate();
            }
        });
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/images_menu/thoat.png")));
        pnSouth.add(txtTim);
        pnSouth.add(btnTim);
        pnSouth.add(btnThem);
        //pnSouth.add(btnXoa);
        pnSouth.add(btnSua);
        pnSouth.add(btnLuu);
        pnSouth.add(btnThoat);

        this.add(pnNorth,BorderLayout.NORTH);

        this.add(pnCenter,BorderLayout.CENTER);

        this.add(pnSouth,BorderLayout.SOUTH);
    }
    public void clearText(){
        txtMa.setText("");
        txtTen.setText("");
        txtGia.setText("");
        txtDonViTinh.setText("");
        txtSoLuong.setText("");
        txtSoLuong.setText("");
    }
}
