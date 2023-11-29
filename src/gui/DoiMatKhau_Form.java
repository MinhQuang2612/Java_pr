package gui;

import TableModel.NCC_TableModel;
import dao.TaiKhoan_DAO;
import entity.KhachHang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class DoiMatKhau_Form extends JPanel {
    JPanel pnNorth, pnCenter1, pnWest1, pnCenter, pnSouth;
    JLabel lblMKCu,lblMKMoi,lblNLMK,lblCap,lblMa,lblTB1,lblTB2,lblTB3,lblTB4;
    JTextField txtMKcu,txtCap;
    JPasswordField txtMKmoi,txtNLMK;
    JButton btnThay,btnThoat,btnRep;
    KhachHang kh;
    String ran = "";

    public DoiMatKhau_Form() {

    }

    public void doshow() {
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("THAY ĐỔI MẬT KHẨU");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 18));
        lblTieuDe.setForeground(Color.RED);
        pnNorth1.add(lblTieuDe);
        pnNorth.add(pnNorth1, BorderLayout.NORTH);
//        pnNorth.setBorder(BorderFactory.createLoweredBevelBorder());
        //Center
        pnCenter = new JPanel();
        Box b,b1,b2,b3,b4,b5;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(600,260));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMKCu = new JLabel("Mật khẩu cũ:"));
        b1.add(Box.createHorizontalStrut(30));
        b1.add(txtMKcu = new JTextField());
        b.add(Box.createVerticalStrut(5));
        b.add(lblTB1 = new JLabel(" "));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblMKMoi = new JLabel("Mật khẩu mới:"));
        b2.add(Box.createHorizontalStrut(30));
        b2.add(txtMKmoi = new JPasswordField());
        b.add(Box.createVerticalStrut(5));
        b.add(lblTB2 = new JLabel(" "));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblNLMK = new JLabel("Nhập lại mật khẩu:"));
        b3.add(Box.createHorizontalStrut(30));
        b3.add(txtNLMK = new JPasswordField());
        b.add(Box.createVerticalStrut(5));
        b.add(lblTB3 = new JLabel(" "));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblCap = new JLabel("Nhập mã captcha:"));
        b4.add(Box.createHorizontalStrut(30));
        b4.add(txtCap = new JTextField());
        b4.add(Box.createHorizontalStrut(30));
        b4.add(lblMa = new JLabel());
        b4.add(btnRep = new JButton());
        b.add(Box.createVerticalStrut(5));
        b.add(lblTB4 = new JLabel(" "));
        b.add(Box.createVerticalStrut(10));
        lblMa.setForeground(Color.RED);
        btnRep.setBackground(Color.WHITE);
//        b4.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        lblMa.setText(ran);

        lblTB1.setEnabled(false);
        lblTB2.setEnabled(false);
        lblTB3.setEnabled(false);
        lblTB4.setEnabled(false);

        Font ft = new Font("arial",Font.ITALIC,14);
        lblTB1.setFont(ft); lblTB2.setFont(ft); lblTB3.setFont(ft); lblTB4.setFont(ft);
        lblTB1.setForeground(Color.RED);lblTB2.setForeground(Color.RED);lblTB3.setForeground(Color.RED);lblTB4.setForeground(Color.RED);

        lblMKCu.setPreferredSize(lblNLMK.getPreferredSize());
        lblMKMoi.setPreferredSize(lblNLMK.getPreferredSize());
        lblMa.setPreferredSize(lblNLMK.getPreferredSize());
        pnCenter.add(b);

        //South
        pnSouth  =new JPanel();
        Box c = Box.createHorizontalBox();
        c.add(btnThay = new JButton("Thay đổi"));
        c.add(Box.createHorizontalStrut(100));
        c.add(btnThoat = new JButton("Thoát"));
        pnSouth.add(c);

        lblMa.setFont(new Font("arial",Font.BOLD,15));
        btnRep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblMa.setText(new DoiMatKhau_Form().getSaltString());
            }
        });

        btnRep.setIcon(new ImageIcon(getClass().getResource("/images_menu/replay.png")));
        btnThay.setIcon(new ImageIcon(getClass().getResource("/images_menu/sua.png")));
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/images_menu/thoat.png")));
        btnThoat.setBackground(Color.RED);
        btnThoat.setForeground(Color.WHITE);

        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);

        txtMKcu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
                TaiKhoan_DAO tkDao = new TaiKhoan_DAO();
                System.out.println(tkDao.TimKiemMa(kh.getMaKH()).getMatKhau());

                if(txtMKcu.getText().trim().equalsIgnoreCase(tkDao.TimKiemMa(kh.getMaKH()).getMatKhau().trim())){
                    lblTB1.setText(" ");
                    lblTB1.setEnabled(false);
                    txtMKmoi.requestFocus();
                }else{
                    lblTB1.setText("Mật khẩu cũ không chính xác!");
                    lblTB1.setEnabled(true);
                    txtMKcu.requestFocus();
                }
            }
        });
        txtNLMK.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
                String mk1 = String.valueOf(txtMKmoi.getPassword());
                String mk2 = String.valueOf(txtNLMK.getPassword());
                if(mk1.equalsIgnoreCase(mk2)){
                    lblTB3.setText(" ");
                    lblTB3.setEnabled(false);
                    txtCap.requestFocus();
                }else{
                    lblTB3.setText("Mật khẩu mới không khớp!");
                    lblTB3.setEnabled(true);
                    txtNLMK.requestFocus();
                }
            }
        });
        txtCap.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
                if(txtCap.getText().trim().equals(lblMa.getText().trim())){
                    lblTB4.setText(" ");
                    lblTB4.setEnabled(false);
                }else{
                    lblTB4.setText("Mã Captcha không chính xác!");
                    lblTB4.setEnabled(true);
                    lblMa.setText(new DoiMatKhau_Form().getSaltString());
                    txtCap.requestFocus();
                }
            }
        });
        btnThay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
	            	TaiKhoan_DAO tkDao = new TaiKhoan_DAO();
	            	if(txtMKcu.getText().trim().equalsIgnoreCase(tkDao.TimKiemMa(kh.getMaKH()).getMatKhau().trim())
	            			&& txtCap.getText().trim().equals(lblMa.getText().trim())) {
		                String mkm = String.valueOf(txtMKmoi.getPassword());
		                if(tkDao.updateMK(kh.getMaKH().trim(),mkm)){
		                    JOptionPane.showMessageDialog(null,"Thay đổi mật khẩu thành công!");
		                    txtMKcu.setText("");
		                    txtMKmoi.setText("");
		                    txtNLMK.setText("");
		                    txtCap.setText("");
		                }else{
		                    JOptionPane.showMessageDialog(null,"lỗi!");
		                }
	            }else {
	            	JOptionPane.showMessageDialog(null, "Lỗi!");
	            }
            }
        });
    }
    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
