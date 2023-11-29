package gui;

import dao.ChucVu_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.ChucVu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DangNhap_Form extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1866457517774971813L;
	JLabel lblTen,lblMK,lblQuyen,lblQuenMK,lbTaoTK;
    JTextField txtTen,txtMK;
    JPasswordField pwText;
    JComboBox<String> cboQuyen;
    JCheckBox cbNhoMK;
    JButton btnDangNhap,btnThoat,btnTaoTK;
    static GD_Chinh gd;
    public DangNhap_Form(){
        doShow();
    }
    public void doShow(){
        gd = new GD_Chinh();
        setSize(600,330);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Đăng nhập hệ thống");
        Container cp = getContentPane();
        cp.setBackground(Color.GRAY);
        cp.setLayout(new BorderLayout());

        JPanel pnNorth = new JPanel();
        JLabel lblTitle = new JLabel("Đăng Nhập Hệ Thống");
        lblTitle.setIcon(new ImageIcon(getClass().getResource("/images_menu/dangnhapHT.png")));
        lblTitle.setFont(new Font("Arial",Font.BOLD,18));
        lblTitle.setForeground(Color.RED);
        pnNorth.add(lblTitle);

        JPanel pnCenter = new JPanel();
        Box b,b1,b2,b3,b4;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(450,180));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblTen = new JLabel("Tên Đăng Nhập: "));
        b1.add(Box.createHorizontalStrut(40));
        b1.add(txtTen = new JTextField("minhquang"));
        b.add(Box.createVerticalStrut(10));
        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblMK = new JLabel("Mật Khẩu: "));
        b2.add(Box.createHorizontalStrut(40));
        b2.add(pwText = new JPasswordField("12345"));
        pwText.setEchoChar('*');
        b.add(Box.createVerticalStrut(10));
        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblQuyen = new JLabel("Quyền Truy Cập: "));
        b3.add(Box.createHorizontalStrut(40));
        b3.add(cboQuyen = new JComboBox<String>());
       
       
        ChucVu_DAO cvDao = new ChucVu_DAO();
        for (ChucVu cv: cvDao.getLS()) {
            cboQuyen.addItem(cv.getTenChucVu());
        }
        b.add(Box.createVerticalStrut(10));
        b.add(b4 = Box.createHorizontalBox());
        b4.add(Box.createHorizontalStrut(130));
        b4.add(cbNhoMK = new JCheckBox("Nhớ mật khẩu"));
        b4.add(Box.createHorizontalStrut(120));
        b4.add(lblQuenMK = new JLabel("Quên mật khẩu?"));
        lblQuenMK.setFont(new Font("Arial",Font.ITALIC,12));
        b.add(Box.createVerticalStrut(10));
        b.add(b4 = Box.createHorizontalBox());
        b4.add(btnDangNhap = new JButton("Đăng Nhập "));
        btnDangNhap.setIcon(new ImageIcon(getClass().getResource("/images_menu/login.png")));
        btnDangNhap.setFont(new Font("Arial",Font.BOLD,17));
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setBackground(Color.GREEN);
        b4.add(Box.createHorizontalStrut(100));
        b4.add(btnThoat = new JButton("Thoát "));
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/images_menu/thoat.png")));
        btnThoat.setFont(new Font("Arial",Font.BOLD,17));
        btnThoat.setForeground(Color.WHITE);
        btnThoat.setBackground(Color.RED);

        lblMK.setPreferredSize(lblQuyen.getPreferredSize());
        lblTen.setPreferredSize(lblQuyen.getPreferredSize());

        pnCenter.add(b);

        JPanel pnSouth = new JPanel();
        pnSouth.add(lbTaoTK = new JLabel("Chưa có tài khoản đăng nhập  ---->"));
        pnSouth.add(btnTaoTK = new JButton("Tạo tài khoản"));
        btnTaoTK.setFont(new Font("Arial",Font.BOLD,14));
        btnTaoTK.setForeground(Color.WHITE);
        btnTaoTK.setBackground(Color.GRAY);
        pnSouth.setPreferredSize(new Dimension(0,40));

        cp.add(pnNorth,BorderLayout.NORTH);
        cp.add(pnCenter,BorderLayout.CENTER);
        cp.add(pnSouth,BorderLayout.SOUTH);

        TaiKhoan_DAO tkDao  = new TaiKhoan_DAO();
        btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(txtTen.getText());
//                System.out.println(pwText.getPassword());
                String mk = String.valueOf(pwText.getPassword());
//                System.out.println(mk);
//                System.out.println("mk: "+tkDao.TimKiemTen(txtTen.getText().trim()).getMatKhau().trim());
//                System.out.println(cboQuyen.getSelectedItem());
                if(tkDao.TimKiemTen(txtTen.getText().trim()) != null){
                    if(tkDao.TimKiemTen(txtTen.getText().trim()).getMatKhau().trim().equalsIgnoreCase(mk)){
                        String ma = tkDao.TimKiemTen(txtTen.getText().trim()).getMa();
                        String maKH = tkDao.TimKiemTenKH(txtTen.getText().trim()).getMa();
                        if(ma != null && ma.contains("NV")){
                            NhanVien_DAO nvDao = new NhanVien_DAO();
                            String quyenDN = nvDao.TimKiemMa(ma).getChucVu().getTenChucVu();
                            if(quyenDN.equalsIgnoreCase(cboQuyen.getSelectedItem().toString())){
                                JOptionPane.showMessageDialog(null, "Đăng nhập hệ thống thành công");
                                setVisible(false);
                                gd.setVisible(true);
                                gd.nv = nvDao.TimKiemMa(ma);
                                gd.Account.setText(gd.nv.getTenNV());
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Bạn chọn sai quyền đăng nhập !");
                            }
                        }else if(maKH != null && maKH.contains("KH")){
                            if(cboQuyen.getSelectedItem().toString().equalsIgnoreCase("Khách Hàng")){
                                JOptionPane.showMessageDialog(null, "Đăng nhập hệ thống thành công");
                                setVisible(false);
                                gd.setVisible(true);
                                KhachHang_DAO khDao = new KhachHang_DAO();
                                gd.kh = khDao.TimKiemMa(maKH);
                                gd.Account.setText(gd.kh.getTenKH());
                            }else{
                                JOptionPane.showMessageDialog(null,"Bạn chọn sai quyền đăng nhập !");
                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Mật khẩu không chính xác!");
                    }
                }
            }
        });

        txtTen.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(tkDao.TimKiemTen(txtTen.getText().trim()) != null){
                        pwText.requestFocus();
                    }else{
                        JOptionPane.showMessageDialog(null,"Tên đăng nhập sai!");
                    }

                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        btnDangNhap.isSelected();

        btnDangNhap.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println(txtTen.getText());
                    System.out.println(pwText.getPassword());
                    String mk = String.valueOf(pwText.getPassword());
                    System.out.println(mk);
                    System.out.println("mk: " + tkDao.TimKiemTen(txtTen.getText().trim()).getMatKhau().trim());
                    System.out.println(cboQuyen.getSelectedItem());
                    if (tkDao.TimKiemTen(txtTen.getText().trim()) != null) {
                        if (tkDao.TimKiemTen(txtTen.getText().trim()).getMatKhau().trim().equalsIgnoreCase(mk)) {
                            String ma = tkDao.TimKiemTen(txtTen.getText().trim()).getMa();
                            if (ma.contains("NV")) {
                                NhanVien_DAO nvDao = new NhanVien_DAO();
                                String quyenDN = nvDao.TimKiemMa(ma).getChucVu().getTenChucVu();
                                if (quyenDN.equalsIgnoreCase(cboQuyen.getSelectedItem().toString())) {
                                    JOptionPane.showMessageDialog(null, "Đăng nhập hệ thống thành công");
                                    setVisible(false);
                                    gd.setVisible(true);
                                    gd.nv = nvDao.TimKiemMa(ma);
                                    gd.Account.setText(gd.nv.getTenNV());
                                } else {
                                    JOptionPane.showMessageDialog(null, "Bạn chọn sai quyền đăng nhập !");
                                }
                            } else if (ma.contains("KH")) {
                                if (cboQuyen.getSelectedItem().toString().equalsIgnoreCase("Khách Hàng")) {
                                    JOptionPane.showMessageDialog(null, "Đăng nhập hệ thống thành công");
                                    setVisible(false);
                                    gd.setVisible(true);
                                    KhachHang_DAO khDao = new KhachHang_DAO();
                                    gd.kh = khDao.TimKiemMa(ma);
                                    gd.Account.setText(gd.kh.getTenKH());
                                } else {
                                    JOptionPane.showMessageDialog(null, "Bạn chọn sai quyền đăng nhập !");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Mật khẩu không chính xác!");
                        }
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
    public static void main(String[] args) {
        new DangNhap_Form().setVisible(true);
        gd.iterator();
    }
}
