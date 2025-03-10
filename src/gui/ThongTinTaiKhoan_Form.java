package gui;

import TableModel.KH_TableModel;
import com.toedter.calendar.JDateChooser;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.KhachHang;
import entity.NhanVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ThongTinTaiKhoan_Form extends JPanel {
    JPanel pnNorth, pnCenter1, pnWest1, pnCenter, pnSouth;
    JLabel lbImage, lblMa, lblKhachHang, lblNamSinh, lblGioiTinh, lbldiaChi, lblEmail, lblSDT, lblCM, lblText, lblNgayL, lblChucV;
    JTextField txtMa, txtKhachHang, txtNamSinh, txtNgayVaoL, txtdiaChi, txtEmail, txtSDT, txtCM, txtChucV, txtGT;
    JButton btnSua, btnLuu, btnThoat, btnSuaAnh;
    ImageIcon icon;
    JComboBox cbcGT;
    
    JDateChooser namSinh, ngayVaoL;
    KhachHang kh;
    NhanVien nv;
    private KhachHang_DAO KhDao;
    private NhanVien_DAO nvDao;
    private KH_TableModel tableModel;

    public ThongTinTaiKhoan_Form() {

    }

    public KhachHang thongtin(){
        return kh;
    }
    public void doshow() {
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("THÔNG TIN TÀI KHOẢN");
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
        icon = new ImageIcon("D:\\Nhom13_QuanLyMuaBanHangQuanAo\\src\\images\\user11.png");
        lbImage.setPreferredSize(new Dimension(130, 130));
        //lbImage.setIcon(new ImageIcon(getClass().getResource("/images/user11.png")));
        lbImage.setIcon(icon);
        pnWest1.add(lbImage, BorderLayout.NORTH);
        pnWest1.add(btnSuaAnh);
        //pnCenter
        pnCenter1 = new JPanel();
        Box b, b1, b2, b3, b4, b5;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840, 160));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Nhân Viên: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblKhachHang = new JLabel("Tên Nhân Viên:    "));
        b1.add(txtKhachHang = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblGioiTinh = new JLabel("Giới Tính: "));
        b2.add(txtGT = new JTextField());
//        cbcGT = new JComboBox<>();
//        cbcGT.addItem("Nam");
//        cbcGT.addItem("Nữ");
//        cbcGT.setPreferredSize(new Dimension(300, 20));
//        b2.add(cbcGT);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblNamSinh = new JLabel("Ngày Sinh:    "));
        b2.add(txtNamSinh = new JTextField());
//        namSinh = new JDateChooser();
//        namSinh.setSize(new Dimension(45, 20));
//        namSinh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
//        namSinh.setDateFormatString("yyyy-MM-dd");
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date date = Date.valueOf(LocalDate.now());
//            System.out.println("Date: " + date);
//            namSinh.setDate(date);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        b2.add(namSinh);
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
       
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lbldiaChi = new JLabel("Địa Chỉ:    "));
        b4.add(txtdiaChi = new JTextField(30));
        b.add(Box.createVerticalStrut(10));
        
        b.add(b5 = Box.createHorizontalBox());
        b5.add(lblChucV = new JLabel("Chức Vụ: "));
        b5.add(txtChucV = new JTextField());
      
        b5.add(Box.createHorizontalStrut(20));
        
        b5.add(lblNgayL = new JLabel("Ngày Vào Làm: "));
        b5.add(txtNgayVaoL = new JTextField());
//        ngayVaoL = new JDateChooser();
//        ngayVaoL.setSize(new Dimension(45, 20));
//        ngayVaoL.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
//        ngayVaoL.setDateFormatString("yyyy-MM-dd");
//        
//        try {
//            Date date = Date.valueOf(LocalDate.now());
//            System.out.println("Date: " + date);
//            ngayVaoL.setDate(date);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        b5.add(ngayVaoL);
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblKhachHang.getPreferredSize());
        lblEmail.setPreferredSize(lblKhachHang.getPreferredSize());
        lblCM.setPreferredSize(lblKhachHang.getPreferredSize());
        lblGioiTinh.setPreferredSize(lblKhachHang.getPreferredSize());
        lblNamSinh.setPreferredSize(lblKhachHang.getPreferredSize());
        lblNgayL.setPreferredSize(lblKhachHang.getPreferredSize());
        lblSDT.setPreferredSize(lblKhachHang.getPreferredSize());
        lbldiaChi.setPreferredSize(lblKhachHang.getPreferredSize());
        lblChucV.setPreferredSize(lblKhachHang.getPreferredSize());

        if (nv != null) {
            txtMa.setText(nv.getMaNV());
            txtKhachHang.setText(nv.getTenNV());
            txtCM.setText(String.valueOf(nv.getCCCD()));
            txtGT.setText(nv.getGioiTinh());
            txtNamSinh.setText(String.valueOf(nv.getNgaySinh()));
            //ngayVaoL.setDate(nv.getNgayVaoLam());
            txtEmail.setText(nv.getEmail());
            txtSDT.setText(nv.getDienThoai());
            //namSinh.setDate(nv.getNgaySinh());
            txtNgayVaoL.setText(String.valueOf(nv.getNgayVaoLam()));
            txtChucV.setText(nv.getChucVu().getTenChucVu());
            txtdiaChi.setText(nv.getDiaChi());
            icon = new ImageIcon(nv.getiMages());
            lbImage.setIcon(icon);
        } else {
            JOptionPane.showMessageDialog(null, "Không có thông tin tài khoản");
        }

        pnCenter1.add(b);
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        pnCenter.add(pnWest1, BorderLayout.WEST);
        pnCenter.add(pnCenter1, BorderLayout.CENTER);


        pnSouth = new JPanel();
        //pnSouth.add(btnSua = new JButton("Cập nhật thông tin"));
        //btnSua.setIcon(new ImageIcon(getClass().getResource("/images_menu/sua.png")));
        //pnSouth.add(btnLuu = new JButton("Lưu thông tin"));
        //btnLuu.setIcon(new ImageIcon(getClass().getResource("/images_menu/luu.png")));
        pnSouth.add(btnThoat = new JButton("Quay lại"));
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/images_menu/thoat.png")));

        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                TrangChu_Form tc = new TrangChu_Form();
                add(tc);
                validate();
            }
        });
        
//        btnLuu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String dateTime = (String) formatter.format(namSinh.getDate());
//                String ngayVaol = (String) formatter.format(ngayVaoL.getDate());
//                btnSuaAnh.setEnabled(true);
//                txtMa.setEditable(false);
//                    btnSuaAnh.setText("Chỉnh sửa ảnh");
//                    btnSuaAnh.setVisible(true);
//                    String maS = txtMa.getText().trim();
//                    System.out.println(maS);
//                    NhanVien nvSua = new NhanVien(maS, txtKhachHang.getText(), cbcGT.getSelectedItem().toString(), Date.valueOf(dateTime),
//                            txtEmail.getText(), txtSDT.getText(), txtCM.getText(), txtdiaChi.getText(), Date.valueOf(ngayVaol), lbImage.getIcon().toString());
//                    System.out.println(nvSua);
//                    if (nvDao.updateNhanVien(maS, nvSua)) {
//                        JOptionPane.showMessageDialog(null,"Cập nhật thông tin thành công!");
//                    }
//                }
//        });

        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
}