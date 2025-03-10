package gui;

import TableModel.KH_TableModel;
import TableModel.NV_TableModel;
import com.toedter.calendar.JDateChooser;
import dao.ChucVu_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.ChucVu;
import entity.KhachHang;
import entity.NhanVien;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TT_NhanVien_Form extends JPanel {
    JPanel pnNorth,pnCenter1,pnWest1,pnCenter,pnSouth;
    JLabel lbImage,lblMa,lblTen,lblNamSinh,lblGioiTinh,lbldiaChi,lblEmail,lblSDT,lblCM,lblText,lblChucVu,lblNgayVao;
    JTextField txtMa,txtTen,txtNamSinh,txtdiaChi,txtEmail,txtSDT,txtCM, txtTim;
    JButton btnThem,btnXoa,btnSua,btnLuu,btnThoat,btnSuaAnh, btnTim;
    ImageIcon icon;
    JComboBox cbcGT,cbcChucVu;
    JDateChooser namSinh,NgayVao;
    NhanVien_DAO NvDao;
    NhanVien nv;
    NV_TableModel tableModel;
    List<NhanVien> lnv;
    public TT_NhanVien_Form(){
        doshow();
    }

    private void doshow() {
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
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
        icon = new ImageIcon("D:\\Nhom13_QuanLyBanHangQuanAo\\src\\images\\user11.png");
        lbImage.setPreferredSize(new Dimension(145,145));
        //lbImage.setIcon(new ImageIcon(getClass().getResource("/images/user11.png")));
        lbImage.setIcon(icon);
        pnWest1.add(lbImage,BorderLayout.NORTH);
        pnWest1.add(btnSuaAnh);
        //pnCenter
        pnCenter1 = new JPanel();
        Box b,b1,b2,b3,b4,b5;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840,170));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Nhân Viên: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên Nhân Viên:    "));
        b1.add(txtTen = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblGioiTinh = new JLabel("Giới Tính: "));
        cbcGT = new JComboBox<>();
        cbcGT.addItem("Nam");
        cbcGT.addItem("Nữ");
        cbcGT.setPreferredSize(new Dimension(313,20));
        b2.add(cbcGT);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblNamSinh = new JLabel("Ngày Sinh:    "));
        namSinh = new JDateChooser();
        namSinh.setSize(new Dimension(30,20));
        namSinh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        namSinh.setDateFormatString("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
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

        b.add(b5 = Box.createHorizontalBox());
        b5.add(lblChucVu = new JLabel("Chức Vụ: "));
        cbcChucVu = new JComboBox<>();
        ChucVu_DAO CvDao = new ChucVu_DAO();
        cbcChucVu.addItem("Nhân Viên Kinh Doanh");
        cbcChucVu.addItem("Nhân Viên Kế Toán");
        cbcChucVu.addItem("Quản Lý");
        cbcChucVu.addItem("Nhân Viên Thu Ngân");
        cbcChucVu.setPreferredSize(new Dimension(313,20));
        b5.add(cbcChucVu);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(lblNgayVao = new JLabel("Ngày Vào Làm:    "));
        NgayVao = new JDateChooser();
        lblNgayVao.setSize(new Dimension(30,20));
        lblNgayVao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        NgayVao.setDateFormatString("yyyy-MM-dd");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            NgayVao.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b5.add(NgayVao);
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblTen.getPreferredSize());
        lblEmail.setPreferredSize(lblTen.getPreferredSize());
        lblCM.setPreferredSize(lblTen.getPreferredSize());
        lblGioiTinh.setPreferredSize(lblTen.getPreferredSize());
        lblNamSinh.setPreferredSize(lblTen.getPreferredSize());
        lblSDT.setPreferredSize(lblTen.getPreferredSize());
        lbldiaChi.setPreferredSize(lblTen.getPreferredSize());
        lblChucVu.setPreferredSize(lblTen.getPreferredSize());
        lblNgayVao.setPreferredSize(lblTen.getPreferredSize());


        pnCenter1.add(b);
        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);

        //Center
        TitledBorder tileDanhSach = new TitledBorder("Danh sách nhân viên");

        //pnSouth.setPreferredSize(new Dimension(1030,60));
        pnCenter = new JPanel();
        pnCenter.setBorder(tileDanhSach);
//        NvDao = new NhanVien_DAO();
//        tableModel = new NV_TableModel(NvDao.getLS());
//        JTable table = new JTable(tableModel);

        List<NhanVien> ls = new ArrayList<>();
        NV_TableModel model = new NV_TableModel(ls);
        JTable table = new JTable();
        table.setModel(model);
        
        //Sự kiện Table
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1){
                    txtMa.setEditable(false);
                    icon = new ImageIcon("D:\\Nhom13_QuanLyBanHangQuanAo\\src\\images\\user11.png");
                    txtMa.setText(table.getValueAt(r,0).toString());
                    txtTen.setText(table.getValueAt(r,1).toString());
                    if(table.getValueAt(r,2).toString().equalsIgnoreCase("Nam"))
                        cbcGT.setSelectedItem("Nam");
                    else
                        cbcGT.setSelectedItem("Nữ");
//                    Date date = null;
//                    try {
//                        date = formatter.parse(table.getValueAt(r,3).toString());
//                    } catch (ParseException parseException) {
//                        parseException.printStackTrace();
//                    }
                    namSinh.setDate(java.sql.Date.valueOf(table.getValueAt(r,3).toString()));
                    txtSDT.setText(table.getValueAt(r,4).toString());
                    txtEmail.setText(table.getValueAt(r,5).toString());
                    txtCM.setText(table.getValueAt(r,6).toString());
                    txtdiaChi.setText(table.getValueAt(r,7).toString());

                    cbcChucVu.setSelectedItem(table.getValueAt(r,8).toString());
                    NgayVao.setDate(java.sql.Date.valueOf(table.getValueAt(r,9).toString()));
                    System.out.println(table.getValueAt(r,9).toString());

                    btnSua.setEnabled(true);
                    //btnXoa.setEnabled(true);

                    if(table.getValueAt(r,10).toString().length() < 20)
                        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(table.getValueAt(r,10).toString())));
                    else
                        lbImage.setIcon(new javax.swing.ImageIcon((table.getValueAt(r,10).toString())));

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
        pnCenter.add(sc);
        //South
        TitledBorder tileTacvu = new TitledBorder("Tác vụ");
        pnSouth = new JPanel();
        pnSouth.setBorder(tileTacvu);
        pnSouth.setPreferredSize(new Dimension(1030,70));
        
        txtTim = new JTextField(10);
        btnTim = new JButton("Tìm Nhân Viên Theo Mã");
        //Su kien tim
        btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NvDao = new NhanVien_DAO();
				lnv = NvDao.TimKiemMa1(txtTim.getText());
				System.out.println(lnv);
				if (lnv.size() == 0) {
					JOptionPane.showMessageDialog(null, "Nhân viên không tồn tại!");
				}
				else {
					table.setModel(new NV_TableModel(lnv));
				}
				
			}
		});
        
        btnThem = new JButton("Thêm Nhân Viên");
        btnThem.setIcon(new ImageIcon(getClass().getResource("/images_menu/them.png")));
        //Su kien Them
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSuaAnh.setText("Chỉnh sửa ảnh");
                btnSuaAnh.setEnabled(true);
                txtMa.setEnabled(true);
                txtMa.setText("");
                txtTen.setText("");
                txtdiaChi.setText("");
                txtCM.setText("");
                txtEmail.setText("");
                txtSDT.setText("");
                txtMa.setEditable(false);
                txtTen.requestFocus();
                btnSua.setEnabled(false);
                //btnXoa.setEnabled(false);
            }
        });
        //btnXoa = new JButton("Xóa Nhân Viên");
        //btnXoa.setIcon(new ImageIcon(getClass().getResource("/images_menu/xoa.png")));
        //Sự kiện xóa
//        btnXoa.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int r = table.getSelectedRow();
//                if(r != -1) {
//                    int tb = JOptionPane.showConfirmDialog(null,"Bạn chắc chắn muốn xóa dòng này?","Delete",
//                            JOptionPane.YES_NO_OPTION);
//                    if(tb == JOptionPane.YES_OPTION) {
//                        String maX = txtMa.getText().trim();
//                        System.out.println(maX);
//                        if (NvDao.deleteNV(maX)) {
//                            try {
//                                table.setModel(new NV_TableModel(NvDao.getLS()));
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//                        }
//                        clearTextField();
//                    }
//                }else{
//                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần xóa!");
//                }
//            }
//        });
        btnSua = new JButton("Sửa TT Nhân Viên");
        btnSua.setIcon(new ImageIcon(getClass().getResource("/images_menu/sua.png")));

        //Su Kien Sua
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	NhanVien_DAO nvDao = new NhanVien_DAO();
                String dateTime = (String) formatter.format(namSinh.getDate());
                String ngayVao1 = (String) formatter.format(NgayVao.getDate());
                btnSuaAnh.setEnabled(true);
                int r = table.getSelectedRow();
                System.out.println(table.getRowCount());
                txtMa.setEnabled(false);
                if (r != -1){
                    btnSuaAnh.setText("Chỉnh sửa ảnh");
                    btnSuaAnh.setVisible(true);
                    String maS = txtMa.getText().trim();
                    NhanVien nvSua = new NhanVien(maS, txtTen.getText(), cbcGT.getSelectedItem().toString(), java.sql.Date.valueOf(dateTime),
                            txtEmail.getText(), txtSDT.getText(),txtCM.getText(),txtdiaChi.getText(),
                            java.sql.Date.valueOf(ngayVao1),lbImage.getIcon().toString());
                    ChucVu_DAO cvDao = new ChucVu_DAO();
                    ChucVu cv = cvDao.TimKiemTen(cbcChucVu.getSelectedItem().toString());
                    nvSua.setChucVu(cv);
                    System.out.println(nvSua);
                    int lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin không ?","option",JOptionPane.YES_NO_OPTION);
                    if (lc == JOptionPane.YES_OPTION) {
                    	if (nvDao.updateNhanVien(maS,nvSua)) {
//                            try {
//                                table.setModel(new NV_TableModel(nvDao.getLS()));
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
                    		JOptionPane.showMessageDialog(null, "Đã sửa thông tin nhân viên");
                        }
                    }
            
                    clearTextField();

                }else {
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần sửa!");
                }
            }
        });

        btnLuu = new JButton("Lưu Thông Tin");
        btnLuu.setIcon(new ImageIcon(getClass().getResource("/images_menu/luu.png")));

        //Su kien luu
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVien_DAO nvDao = new NhanVien_DAO();
                if(txtTen.getText().trim().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null,"Tên nhân viên không được rỗng");
                }else {
                    if (txtSDT.getText().trim().matches("\\d{10}")) {
                        if (txtCM.getText().trim().equalsIgnoreCase("")) {
                            JOptionPane.showMessageDialog(null, "Chưa nhập số CCCD");
                        } else {
                            if (nvDao.TimKiemCM(txtCM.getText().trim()) != null) {
                                JOptionPane.showMessageDialog(null, "Nhân viên đã tồn tại (Trùng số CCCD)");
                                txtCM.requestFocus();
                            } else {
                                btnSuaAnh.setEnabled(false);
                                System.out.println("NamSinhNV" + namSinh.getDate());
                                String dateTime = (String) formatter.format(namSinh.getDate());
                                String ngayVao1 = (String) formatter.format(NgayVao.getDate());
                                NhanVien nv = new NhanVien(txtMa.getText().trim(), txtTen.getText().trim(),
                                        cbcGT.getSelectedItem().toString(),
                                        java.sql.Date.valueOf(dateTime),
                                        txtEmail.getText().trim(),
                                        txtSDT.getText().trim(),
                                        txtCM.getText().trim(),
                                        txtdiaChi.getText().trim(),
                                        java.sql.Date.valueOf(ngayVao1),
                                        lbImage.getIcon().toString());
                                ChucVu_DAO cvDao = new ChucVu_DAO();
                                ChucVu cv;
                                if (cvDao.TimKiemTen(cbcChucVu.getSelectedItem().toString()) != null) {
                                    cv = cvDao.TimKiemTen(cbcChucVu.getSelectedItem().toString());
                                    nv.setChucVu(cv);
                                    if (nvDao.addNhanVien(nv)) {
//                                        try {
//                                            table.setModel(new NV_TableModel(nvDao.getLS()));
//                                        } catch (Exception ex) {
//                                            ex.printStackTrace();
//                                        }
                                    	JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
                                    } else
                                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin!");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Lỗi!");
                                }
                                clearTextField();
                                //table.setModel(new NV_TableModel(nvDao.getLS()));
                                //System.out.println(table.getRowCount());

                                // tableModel.fireTableDataChanged();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa chữ cái, số đt gồm 10 chữ số!");
                    }
                }

            }
        });
        btnThoat = new JButton("Thoát ");
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/images_menu/thoat.png")));
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
    public void clearTextField(){
        txtMa.setText("");
        txtTen.setText("");
        txtCM.setText("");
        txtdiaChi.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        cbcChucVu.setSelectedItem("Nhân Viên Kinh Doanh");
        cbcGT.setSelectedItem("Nam");
        txtTen.requestFocus();
    }
}
