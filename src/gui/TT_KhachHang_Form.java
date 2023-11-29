package gui;

import TableModel.KH_TableModel;
import TableModel.SP_TableModel;

import com.toedter.calendar.JDateChooser;
import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import entity.KhachHang;
import entity.SanPham;

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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TT_KhachHang_Form extends JPanel {
    JPanel pnNorth, pnCenter1, pnWest1, pnCenter, pnSouth;
    JLabel lbImage, lblMa, lblKhachHang, lblNamSinh, lblGioiTinh, lbldiaChi, lblEmail, lblSDT, lblCM, lblText;
    JTextField txtMa, txtKhachHang, txtNamSinh, txtdiaChi, txtEmail, txtSDT, txtCM, txtTim;
    JButton btnThem, btnXoa, btnSua, btnLuu, btnThoat, btnSuaAnh, btnTim;
    ImageIcon icon;
    JComboBox cbcGT;
    JDateChooser namSinh;
    KhachHang kh;
    List<KhachHang> lkh;
    private KhachHang_DAO KhDao;
    private KH_TableModel tableModel;

    public TT_KhachHang_Form() {
        doshow();
    }

    private void doshow() {
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.BLUE);
        pnNorth1.add(lblTieuDe);
        pnNorth.add(pnNorth1, BorderLayout.NORTH);


        //pnWest
        pnWest1 = new JPanel();
        pnWest1.setLayout(new BorderLayout());
        pnWest1.setPreferredSize(new Dimension(150, 150));
        //pnWest1.setBorder(BorderFactory.createLineBorder(Color.red));
        lbImage = new JLabel();
        btnSuaAnh = new JButton("Chỉnh sửa ảnh");
        btnSuaAnh.setFont(new Font("Arial", Font.ITALIC, 13));
        btnSuaAnh.setIcon(new ImageIcon(getClass().getResource("/images_menu/suaAnh.png")));
        btnSuaAnh.setVisible(true);

        //File
        final JFileChooser fileDialog = new JFileChooser();
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
                } else {
                }
            }
        });
        icon = new ImageIcon("D:\\Nhom13_QuanLyBanHangQuanAo\\src\\images\\user11.png");
        lbImage.setPreferredSize(new Dimension(130, 130));
        //lbImage.setIcon(new ImageIcon(getClass().getResource("/images/user11.png")));
        lbImage.setIcon(icon);
        pnWest1.add(lbImage, BorderLayout.NORTH);
        pnWest1.add(btnSuaAnh);
        //pnCenter
        pnCenter1 = new JPanel();
        Box b, b1, b2, b3, b4;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840, 150));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Khách Hàng: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblKhachHang = new JLabel("Tên Khách Hàng:    "));
        b1.add(txtKhachHang = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblGioiTinh = new JLabel("Giới Tính: "));
        cbcGT = new JComboBox<>();
        cbcGT.addItem("Nam");
        cbcGT.addItem("Nữ");
        cbcGT.setPreferredSize(new Dimension(300, 20));
        b2.add(cbcGT);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblNamSinh = new JLabel("Ngày Sinh:    "));
        namSinh = new JDateChooser();
        namSinh.setSize(new Dimension(45, 20));
        namSinh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        namSinh.setDateFormatString("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            namSinh.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b2.add(namSinh);
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblEmail = new JLabel("Email: "));
        b3.add(txtEmail = new JTextField(30));
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblSDT = new JLabel("Điện Thoại:    "));
        b3.add(txtSDT = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblCM = new JLabel("SỐ CCCD: "));
        b4.add(txtCM = new JTextField(30));
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lbldiaChi = new JLabel("Địa Chỉ:    "));
        b4.add(txtdiaChi = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblKhachHang.getPreferredSize());
        lblEmail.setPreferredSize(lblKhachHang.getPreferredSize());
        lblCM.setPreferredSize(lblKhachHang.getPreferredSize());
        lblGioiTinh.setPreferredSize(lblKhachHang.getPreferredSize());
        lblNamSinh.setPreferredSize(lblKhachHang.getPreferredSize());
        lblSDT.setPreferredSize(lblKhachHang.getPreferredSize());
        lbldiaChi.setPreferredSize(lblKhachHang.getPreferredSize());


        pnCenter1.add(b);
        pnNorth.add(pnWest1, BorderLayout.WEST);
        pnNorth.add(pnCenter1, BorderLayout.CENTER);

        //Center
        TitledBorder tileDanhSach = new TitledBorder("Danh sách khách hàng");

        //pnSouth.setPreferredSize(new Dimension(1030,60));
        pnCenter = new JPanel();
        pnCenter.setBorder(tileDanhSach);
       
//        KhDao = new KhachHang_DAO();
//        tableModel = new KH_TableModel();
//        JTable table = new JTable(tableModel);
        
        List<KhachHang> ls = new ArrayList<>();
        KH_TableModel model = new KH_TableModel(ls);
        JTable table = new JTable();
        table.setModel(model);

        //Sự kiện Table
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r != -1) {
                    txtMa.setEditable(false);
                    icon = new ImageIcon("D:\\Nhom13_QuanLyBanHangQuanAo\\src\\images\\user11.png");
                    txtMa.setText(table.getValueAt(r, 0).toString());
                    txtKhachHang.setText(table.getValueAt(r, 1).toString());
                    if (table.getValueAt(r, 2).toString().equalsIgnoreCase("Nam"))
                        cbcGT.setSelectedItem("Nam");
                    else
                        cbcGT.setSelectedItem("Nữ");
                    namSinh.setDate(java.sql.Date.valueOf(table.getValueAt(r, 3).toString()));
                    txtSDT.setText(table.getValueAt(r, 4).toString());
                    txtEmail.setText(table.getValueAt(r, 5).toString());
                    txtCM.setText(table.getValueAt(r, 6).toString());
                    txtdiaChi.setText(table.getValueAt(r, 7).toString());
                    System.out.println(table.getValueAt(r, 8).toString());
                    if (table.getValueAt(r, 8).toString().length() < 20)
                        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(table.getValueAt(r, 8).toString())));
                    else
                        lbImage.setIcon(new javax.swing.ImageIcon((table.getValueAt(r, 8).toString())));
                    //btnXoa.setEnabled(true);
                    btnSua.setEnabled(true);
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
        JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(930, 230));
        pnCenter.add(sc);
        //South
        TitledBorder tileTacvu = new TitledBorder("Tác vụ");
        pnSouth = new JPanel();
        pnSouth.setBorder(tileTacvu);
        pnSouth.setPreferredSize(new Dimension(1030, 70));
        
        txtTim = new JTextField(10);
        btnTim = new JButton("Tìm Khách Hàng");
        //Su kien tim
        btnTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KhDao = new KhachHang_DAO();
				lkh = KhDao.TimKiemSDT1(txtTim.getText());
				System.out.println(lkh);
				if (lkh.size() == 0) {
					JOptionPane.showMessageDialog(null, "Khách hàng không tồn tại!");
				}
				else {
					table.setModel(new KH_TableModel(lkh));
				}
			}
		});
        
        btnThem = new JButton("Thêm Khách Hàng");
        btnThem.setIcon(new ImageIcon(getClass().getResource("/images_menu/them.png")));
        //Su kien Them
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSuaAnh.setText("Chỉnh sửa ảnh");
                btnSuaAnh.setEnabled(true);
                txtMa.setEnabled(true);
                txtMa.setText("");
                txtKhachHang.setText("");
                txtdiaChi.setText("");
                txtCM.setText("");
                txtEmail.setText("");
                txtSDT.setText("");
                txtMa.setEditable(false);
                txtKhachHang.requestFocus();
                //btnXoa.setEnabled(false);
                btnSua.setEnabled(false);
            }
        });
//        btnXoa = new JButton("Xóa Khách Hàng");
//        btnXoa.setIcon(new ImageIcon(getClass().getResource("/images_menu/xoa.png")));
        btnSua = new JButton("Sửa TT Khách Hàng");
        btnSua.setIcon(new ImageIcon(getClass().getResource("/images_menu/sua.png")));

        //Su Kien Sua
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateTime = (String) formatter.format(namSinh.getDate());
                btnSuaAnh.setEnabled(true);
                int r = table.getSelectedRow();
                txtMa.setEditable(false);
                if (r != -1) {
                    btnSuaAnh.setText("Chỉnh sửa ảnh");
                    btnSuaAnh.setVisible(true);
                    String maS = txtMa.getText().trim();
                    System.out.println(lbImage.getIcon().toString());
                    KhachHang khSua = new KhachHang(maS, txtKhachHang.getText(), cbcGT.getSelectedItem().toString(), Date.valueOf(dateTime),
                            txtEmail.getText(), txtSDT.getText(), txtCM.getText(), txtdiaChi.getText(), lbImage.getIcon().toString());
                    System.out.println(khSua);
                    int lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin không ?","option",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION) {
	                    if (KhDao.updateKhachHang(maS, khSua)) {
	                        try {
	                            table.setModel(new KH_TableModel(KhDao.getLS()));
		                        } catch (Exception ex) {
		                            ex.printStackTrace();
		                        }
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng nào!");
		                }
                	}
                	cleartext();           
                }
        });

        btnLuu = new JButton("Lưu Thông Tin");
        btnLuu.setIcon(new ImageIcon(getClass().getResource("/images_menu/luu.png")));

        //Su kien luu
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KhachHang_DAO khDao = new KhachHang_DAO();
                if(txtKhachHang.getText().trim().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null,"Tên khách hàng không được rỗng");
                }else {
                    if (txtSDT.getText().trim().matches("\\d{10}")) {
                        if (txtCM.getText().trim().equalsIgnoreCase("")) {
                            JOptionPane.showMessageDialog(null, "Chưa nhập số CCCD");
                        }else{
                            if (khDao.TimKiemCM(txtCM.getText().trim()) != null) {
                                JOptionPane.showMessageDialog(null, "Khách hàng đã tồn tại (Trùng số CCCD)");
                                txtCM.requestFocus();
                            } else {
                                btnSuaAnh.setEnabled(false);
                                System.out.println(namSinh.getDate());
                                String dateTime = (String) formatter.format(namSinh.getDate());
                                KhachHang kh = new KhachHang(txtMa.getText().trim(), txtKhachHang.getText().trim(),
                                        cbcGT.getSelectedItem().toString(),
                                        Date.valueOf(dateTime),
                                        txtEmail.getText().trim(),
                                        txtSDT.getText().trim(),
                                        txtCM.getText().trim(),
                                        txtdiaChi.getText().trim(),
                                        lbImage.getIcon().toString());
                                if (KhDao.addKhachHang(kh)) {
                                    try {
                                        table.setModel(new KH_TableModel(KhDao.getLS()));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
                                }
                                cleartext();
                                table.setModel(new KH_TableModel(KhDao.getLS()));
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa chữ cái, số đt gồm 10 chữ số!");
                    }
                }
            }
        });
        //Sự kiện xóa
//        btnXoa.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int r = table.getSelectedRow();
//                if (r != -1) {
//                    int tb = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa dòng này?", "Delete",
//                            JOptionPane.YES_NO_OPTION);
//                    if (tb == JOptionPane.YES_OPTION) {
//                        String maX = txtMa.getText().trim();
//                        System.out.println(maX);
//                        if (KhDao.deleteKH(maX)) {
//                            try {
//                                table.setModel(new KH_TableModel(KhDao.getLS()));
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//                        }
//                        cleartext();
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa!");
//                }
//            }
//        });
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
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/images_menu/thoat.png")));
        pnSouth.add(txtTim);
        pnSouth.add(btnTim);
        pnSouth.add(btnThem);
        //pnSouth.add(btnXoa);
        pnSouth.add(btnSua);
        pnSouth.add(btnLuu);
        pnSouth.add(btnThoat);

        this.add(pnNorth, BorderLayout.NORTH);

        this.add(pnCenter, BorderLayout.CENTER);

        this.add(pnSouth, BorderLayout.SOUTH);

    }
    public void  cleartext(){
        txtMa.setText("");
        txtKhachHang.setText("");
        txtCM.setText("");
        txtdiaChi.setText("");
        txtEmail.setText("");
    }
}