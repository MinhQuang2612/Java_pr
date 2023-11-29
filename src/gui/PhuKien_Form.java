package gui;

import dao.SanPham_DAO;
import entity.KhachHang;
import entity.SanPham;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.List;

public class PhuKien_Form extends JPanel {
    JPanel pnNorth,pnCenter;
    KhachHang kh;

    public PhuKien_Form(){

    }
    public void doShow(){
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("Phụ Kiện");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 18));
        lblTieuDe.setForeground(Color.RED);
        pnNorth1.add(lblTieuDe);
        pnNorth.add(pnNorth1,BorderLayout.NORTH);


        SanPham_DAO spDao = new SanPham_DAO();
        SanPham sp1,sp2,sp3,sp4,sp5;
        List<SanPham> ls = spDao.TimKiemLoai("LSP003");
        sp1 = ls.get(0);sp2 = ls.get(1);sp3 = ls.get(2);sp4 = ls.get(3);sp5 = ls.get(4);
        DecimalFormat df = new DecimalFormat("#,###.00 VND");

        //Thông tin
        //1
        JLabel lbImages1 = new JLabel();
        JLabel lbttct1 = new JLabel("Click để xem chi tiết");
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/images_SanPham/Hình ảnh phụ kiện_00001.png"));
//        lbImages1.setPreferredSize(new Dimension(110,110));
        lbImages1.setIcon(icon);
        JLabel ten1 = new JLabel(sp1.getTenSP());
        JLabel lbGia1 = new JLabel(df.format(sp1.getDonGia()));

        //2
        ImageIcon icon2;
        JLabel lbImages2 = new JLabel();
        JLabel lbttct2 = new JLabel("Click để xem chi tiết");
        
        icon2 = new ImageIcon(getClass().getResource("/images_SanPham/Hình ảnh phụ kiện_00005.png"));
//        lbImages2.setPreferredSize(new Dimension(110,110));
        lbImages2.setIcon(icon2);
        JLabel ten2 = new JLabel(sp2.getTenSP());
        JLabel lbGia2 = new JLabel(df.format(sp2.getDonGia()));

        //3
        ImageIcon icon3;
        JLabel lbImages3 = new JLabel();
        JLabel lbttct3 = new JLabel("Click để xem chi tiết");
        
        icon3 = new ImageIcon(getClass().getResource("/images_SanPham/Hình ảnh phụ kiện_00007.png"));
//        lbImages2.setPreferredSize(new Dimension(110,110));
        lbImages3.setIcon(icon3);
        JLabel ten3 = new JLabel(sp3.getTenSP());
        JLabel lbGia3 = new JLabel(df.format(sp3.getDonGia()));

        //4
        ImageIcon icon4;
        JLabel lbImages4 = new JLabel();
        JLabel lbttct4 = new JLabel("Click để xem chi tiết");
        
        icon4 = new ImageIcon(getClass().getResource("/images_SanPham/Hình ảnh phụ kiện_00009.png"));
//        lbImages2.setPreferredSize(new Dimension(110,110));
        lbImages4.setIcon(icon4);
        JLabel ten4 = new JLabel(sp4.getTenSP());
        JLabel lbGia4 = new JLabel(df.format(sp4.getDonGia()));




        //pnCenter

        pnCenter = new JPanel();
        pnCenter.setLayout(new GridLayout(1,2));
        Box b,b1,b2;
        b = Box.createVerticalBox();
        b.add(Box.createVerticalStrut(20));
        b.add(b1 = Box.createHorizontalBox());
        b.add(Box.createVerticalStrut(20));
        b.add(b2 = Box.createHorizontalBox());

        //1
        JPanel pn1 = new JPanel();
        pn1.setPreferredSize(new Dimension(230,190));
        pn1.setLayout(new BorderLayout());
        JPanel pnN1 = new JPanel();pnN1.setLayout(new BorderLayout());
        JPanel pnN11 = new JPanel();
        JPanel pnN111 = new JPanel();
        JPanel pnC1 = new JPanel();
        JPanel pnS1 = new JPanel();
        pnN11.add(lbttct1);
        
        pnN111.add(new JLabel("          "));
        
        lbttct1.setForeground(Color.WHITE);
        pnN11.setBackground(Color.GRAY);
        lbttct1.setFont(new Font("arial",Font.ITALIC,13));

        pnN1.add(new JLabel("                         "),BorderLayout.WEST);
        pnN1.add(new JLabel("      "),BorderLayout.EAST);
        pnN1.add(lbImages1,BorderLayout.CENTER);
        pnN1.setBackground(Color.WHITE);
//        pnN111.setBackground(Color.WHITE);
//        pnC1.setBackground(Color.WHITE);
//        pnS1.setBackground(Color.WHITE);
        pnN1.add(pnN11,BorderLayout.SOUTH);
        pn1.add(pnN1,BorderLayout.NORTH);
        pnC1.add(ten1);
        pnS1.add(lbGia1);
        lbGia1.setForeground(Color.RED);
        pn1.add(pnC1,BorderLayout.CENTER);
        pn1.add(pnS1,BorderLayout.SOUTH);

        pnN11.setVisible(false);
        pnN1.add(pnN111,BorderLayout.SOUTH);
        pn1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pnN11.setVisible(false);
                pnN111.setVisible(true);
                pnN1.add(pnN111,BorderLayout.SOUTH);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pn1.setBorder(BorderFactory.createLineBorder(Color.RED));
                pnN11.setVisible(true);
                pnN111.setVisible(false);
                pnN1.add(pnN11,BorderLayout.SOUTH);
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                pnN11.setVisible(false);
                pnN11.setVisible(false);
                pnN111.setVisible(true);
                pnN1.add(pnN111,BorderLayout.SOUTH);
                pn1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            }
        });

        //2

        JPanel pn2 = new JPanel();
        pn2.setPreferredSize(new Dimension(230,190));
        pn2.setLayout(new BorderLayout());
        JPanel pnN2 = new JPanel();pnN2.setLayout(new BorderLayout());
        JPanel pnN22 = new JPanel();
        JPanel pnN221 = new JPanel();
        JPanel pnC2 = new JPanel();
        JPanel pnS2 = new JPanel();
        pnN22.add(lbttct2);
        
        lbGia2.setForeground(Color.RED);
        JLabel l1 = new JLabel("          ");
        pnN221.add(l1);
        
        lbttct2.setForeground(Color.WHITE);
        pnN22.setBackground(Color.GRAY);
        lbttct2.setFont(new Font("arial",Font.ITALIC,13));

        pnN22.setVisible(false);
        pnN2.add(pnN221,BorderLayout.SOUTH);
        pn2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pnN22.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pnN22.setVisible(true);
                pnN221.setVisible(false);
                pnN2.add(pnN22,BorderLayout.SOUTH);
                pn2.setBorder(BorderFactory.createLineBorder(Color.RED));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pnN22.setVisible(false);
                pnN221.setVisible(true);
                pnN2.add(pnN221,BorderLayout.SOUTH);
                pn2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            }
        });
        pnN2.add(new JLabel("                         "),BorderLayout.WEST);
        pnN2.add(new JLabel("      "),BorderLayout.EAST);
        pnN2.add(lbImages2,BorderLayout.CENTER);
        pnN2.setBackground(Color.WHITE);
//        pnN2.add(pnN22,BorderLayout.SOUTH);
        pn2.add(pnN2,BorderLayout.NORTH);
        pnC2.add(ten2);
        pnS2.add(lbGia2);
        pn2.add(pnC2,BorderLayout.CENTER);
        pn2.add(pnS2,BorderLayout.SOUTH);

        //3
        JPanel pn3 = new JPanel();
        pn3.setPreferredSize(new Dimension(230,190));
        pn3.setLayout(new BorderLayout());
        JPanel pnN3 = new JPanel();pnN3.setLayout(new BorderLayout());
        JPanel pnN33 = new JPanel();
        JPanel pnN333 = new JPanel();
        JPanel pnC3 = new JPanel();
        JPanel pnS3 = new JPanel();
        pnN33.add(lbttct3);
        
        lbGia3.setForeground(Color.RED);
        pnN333.add(new JLabel("           "));
        
        lbttct3.setForeground(Color.WHITE);
        pnN33.setBackground(Color.GRAY);
        lbttct3.setFont(new Font("arial",Font.ITALIC,13));
        pnN33.setVisible(false);
        pnN3.add(pnN333,BorderLayout.SOUTH);
        pn3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pnN33.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pnN33.setVisible(true);
                pnN333.setVisible(false);
                pnN3.add(pnN33,BorderLayout.SOUTH);
                pn3.setBorder(BorderFactory.createLineBorder(Color.RED));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pnN33.setVisible(false);
                pnN333.setVisible(true);
                pnN3.add(pnN333,BorderLayout.SOUTH);
                pn3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            }
        });
        pnN3.add(new JLabel("                         "),BorderLayout.WEST);
        pnN3.add(new JLabel("      "),BorderLayout.EAST);
        pnN3.add(lbImages3,BorderLayout.CENTER);
        pnN3.setBackground(Color.WHITE);
//        pnN3.add(pnN33,BorderLayout.SOUTH);
        pn3.add(pnN3,BorderLayout.NORTH);
        pnC3.add(ten3);
        pnS3.add(lbGia3);
        pn3.add(pnC3,BorderLayout.CENTER);
        pn3.add(pnS3,BorderLayout.SOUTH);

        //4

        JPanel pn4 = new JPanel();
        pn4.setPreferredSize(new Dimension(230,190));
        pn4.setLayout(new BorderLayout());
        JPanel pnN4 = new JPanel();pnN4.setLayout(new BorderLayout());
        JPanel pnN44 = new JPanel();
        JPanel pnN444 = new JPanel();
        JPanel pnC4 = new JPanel();
        JPanel pnS4 = new JPanel();
        pnN44.add(lbttct4);
        
        lbGia4.setForeground(Color.RED);
        pnN444.add(new JLabel("          "));
        
        lbttct4.setForeground(Color.WHITE);
        pnN44.setBackground(Color.GRAY);
        lbttct4.setFont(new Font("arial",Font.ITALIC,13));

        pnN4.add(new JLabel("                         "),BorderLayout.WEST);
        pnN4.add(new JLabel("      "),BorderLayout.EAST);
        pnN4.add(lbImages4,BorderLayout.CENTER);
        pnN4.setBackground(Color.WHITE);
        pnN4.add(pnN44,BorderLayout.SOUTH);
        pn4.add(pnN4,BorderLayout.NORTH);
        pnC4.add(ten4);
        pnS4.add(lbGia4);
        pn4.add(pnC4,BorderLayout.CENTER);
        pn4.add(pnS4,BorderLayout.SOUTH);

        pnN44.setVisible(false);
        pnN4.add(pnN444,BorderLayout.SOUTH);
        pn4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pnN44.setVisible(false);
                pnN444.setVisible(true);
                pnN4.add(pnN444,BorderLayout.SOUTH);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pn4.setBorder(BorderFactory.createLineBorder(Color.RED));
                pnN44.setVisible(true);
                pnN444.setVisible(false);
                pnN4.add(pnN44,BorderLayout.SOUTH);
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                pnN11.setVisible(false);
                pnN44.setVisible(false);
                pnN444.setVisible(true);
                pnN4.add(pnN444,BorderLayout.SOUTH);
                pn4.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            }
        });

        //5
        JLabel lbImages5 = new JLabel();
        JLabel lbttct5 = new JLabel("Click để xem chi tiết");
       
        ImageIcon icon5 = new ImageIcon(getClass().getResource("/images_SanPham/Hình ảnh phụ kiện_00010.png"));
//        lbImages1.setPreferredSize(new Dimension(110,110));
        lbImages5.setIcon(icon5);
        JLabel ten5 = new JLabel(sp5.getTenSP());
        JLabel lbGia5 = new JLabel(df.format(sp5.getDonGia()));

        //2
        ImageIcon icon6;
        JLabel lbImages6 = new JLabel();
        JLabel lbttct6 = new JLabel("Click để xem chi tiết");
        
        icon6 = new ImageIcon(getClass().getResource("/images_SanPham/Hình ảnh phụ kiện_00012.png"));
//        lbImages2.setPreferredSize(new Dimension(110,110));
        lbImages6.setIcon(icon6);
        JLabel ten6 = new JLabel(sp2.getTenSP());
        JLabel lbGia6 = new JLabel(df.format(sp2.getDonGia()));

        //3
        ImageIcon icon7;
        JLabel lbImages7 = new JLabel();
        JLabel lbttct7 = new JLabel("Click để xem chi tiết");
        
        icon7 = new ImageIcon(getClass().getResource("/images_SanPham/Hình ảnh phụ kiện_00014.png"));
//        lbImages2.setPreferredSize(new Dimension(110,110));
        lbImages7.setIcon(icon7);
        JLabel ten7 = new JLabel(sp3.getTenSP());
        JLabel lbGia7 = new JLabel(df.format(sp3.getDonGia()));

        //4
        ImageIcon icon8;
        JLabel lbImages8 = new JLabel();
        JLabel lbttct8 = new JLabel("Click để xem chi tiết");
        
        icon8 = new ImageIcon(getClass().getResource("/images_SanPham/Hình ảnh phụ kiện_00017.png"));
//        lbImages2.setPreferredSize(new Dimension(110,110));
        lbImages8.setIcon(icon8);
        JLabel ten8 = new JLabel(sp4.getTenSP());
        JLabel lbGia8 = new JLabel(df.format(sp4.getDonGia()));


        //1
        JPanel pn5 = new JPanel();
        pn5.setPreferredSize(new Dimension(230,190));
        pn5.setLayout(new BorderLayout());
        JPanel pnN5 = new JPanel();pnN5.setLayout(new BorderLayout());
        JPanel pnN55 = new JPanel();
        JPanel pnN555 = new JPanel();
        JPanel pnC5 = new JPanel();
        JPanel pnS5 = new JPanel();
        pnN55.add(lbttct5);
        
        lbGia5.setForeground(Color.RED);
        pnN555.add(new JLabel("           "));
        
        lbttct5.setForeground(Color.WHITE);
        pnN55.setBackground(Color.GRAY);
        lbttct5.setFont(new Font("arial",Font.ITALIC,13));
        pnN55.setVisible(false);
        pnN5.add(pnN555,BorderLayout.SOUTH);
        pn5.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pnN55.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pn5.setBorder(BorderFactory.createLineBorder(Color.RED));
                pnN55.setVisible(true);
                pnN555.setVisible(false);
                pnN5.add(pnN55,BorderLayout.SOUTH);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pnN55.setVisible(false);
                pnN555.setVisible(true);
                pnN5.add(pnN555,BorderLayout.SOUTH);
                pn5.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            }
        });
        pnN5.add(new JLabel("                         "),BorderLayout.WEST);
        pnN5.add(new JLabel("      "),BorderLayout.EAST);
        pnN5.add(lbImages5,BorderLayout.CENTER);
        pnN5.setBackground(Color.WHITE);
//        pnN5.add(pnN55,BorderLayout.SOUTH);
        pn5.add(pnN5,BorderLayout.NORTH);
        pnC5.add(ten5);
        pnS5.add(lbGia5);
        pn5.add(pnC5,BorderLayout.CENTER);
        pn5.add(pnS5,BorderLayout.SOUTH);

        //2

        JPanel pn6 = new JPanel();
        pn6.setPreferredSize(new Dimension(230,190));
        pn6.setLayout(new BorderLayout());
        JPanel pnN6 = new JPanel();pnN6.setLayout(new BorderLayout());
        JPanel pnN66 = new JPanel();
        JPanel pnN666 = new JPanel();
        JPanel pnC6 = new JPanel();
        JPanel pnS6 = new JPanel();
        pnN66.add(lbttct6);
        
        lbGia6.setForeground(Color.RED);
        pnN666.add(new JLabel("           "));
        
        lbttct6.setForeground(Color.WHITE);
        pnN66.setBackground(Color.GRAY);
        lbttct6.setFont(new Font("arial",Font.ITALIC,13));
        pnN66.setVisible(false);
        pnN6.add(pnN666, BorderLayout.SOUTH);
        pn6.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pnN66.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pn6.setBorder(BorderFactory.createLineBorder(Color.RED));
                pnN66.setVisible(true);
                pnN666.setVisible(false);
                pnN6.add(pnN66,BorderLayout.SOUTH);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pnN66.setVisible(false);
                pnN666.setVisible(true);
                pnN6.add(pnN666,BorderLayout.SOUTH);
                pn6.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            }
        });
        pnN6.add(new JLabel("                         "),BorderLayout.WEST);
        pnN6.add(new JLabel("      "),BorderLayout.EAST);
        pnN6.add(lbImages6,BorderLayout.CENTER);
        pnN6.setBackground(Color.WHITE);
//        pnN6.add(pnN66,BorderLayout.SOUTH);
        pn6.add(pnN6,BorderLayout.NORTH);
        pnC6.add(ten6);
        pnS6.add(lbGia6);
        pn6.add(pnC6,BorderLayout.CENTER);
        pn6.add(pnS6,BorderLayout.SOUTH);

        //3
        JPanel pn7 = new JPanel();
        pn7.setPreferredSize(new Dimension(230,190));
        pn7.setLayout(new BorderLayout());
        JPanel pnN7 = new JPanel();pnN7.setLayout(new BorderLayout());
        JPanel pnN77 = new JPanel();
        JPanel pnN777 = new JPanel();
        JPanel pnC7 = new JPanel();
        JPanel pnS7 = new JPanel();
        pnN77.add(lbttct7);
        
        lbGia7.setForeground(Color.RED);
        pnN777.add(new JLabel("         "));
        
        lbttct7.setForeground(Color.WHITE);
        pnN77.setBackground(Color.GRAY);
        lbttct7.setFont(new Font("arial",Font.ITALIC,13));
        pnN77.setVisible(false);
        pnN7.add(pnN777,BorderLayout.SOUTH);
        pn7.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pnN77.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pn7.setBorder(BorderFactory.createLineBorder(Color.RED));
                pnN77.setVisible(true);
                pnN777.setVisible(false);
                pnN7.add(pnN77,BorderLayout.SOUTH);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pnN77.setVisible(false);
                pnN777.setVisible(true);
                pnN7.add(pnN777,BorderLayout.SOUTH);
                pn7.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            }
        });
        pnN7.add(new JLabel("                         "),BorderLayout.WEST);
        pnN7.add(new JLabel("      "),BorderLayout.EAST);
        pnN7.add(lbImages7,BorderLayout.CENTER);
        pnN7.setBackground(Color.WHITE);
//        pnN7.add(pnN77,BorderLayout.SOUTH);
        pn7.add(pnN7,BorderLayout.NORTH);
        pnC7.add(ten7);
        pnS7.add(lbGia7);
        pn7.add(pnC7,BorderLayout.CENTER);
        pn7.add(pnS7,BorderLayout.SOUTH);

        //4
        JPanel pn8 = new JPanel();
        pn8.setPreferredSize(new Dimension(230,190));
        pn8.setLayout(new BorderLayout());
        JPanel pnN8 = new JPanel();pnN8.setLayout(new BorderLayout());
        JPanel pnN88 = new JPanel();
        JPanel pnN888 = new JPanel();
        JPanel pnC8 = new JPanel();
        JPanel pnS8 = new JPanel();
        pnN88.add(lbttct8);
        
        lbGia8.setForeground(Color.RED);
        pnN888.add(new JLabel("          "));
       
        lbttct8.setForeground(Color.WHITE);
        pnN88.setBackground(Color.GRAY);
        lbttct8.setFont(new Font("arial",Font.ITALIC,13));
        pnN88.setVisible(false);
        pnN8.add(pnN888,BorderLayout.SOUTH);
        pn8.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pnN88.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pn8.setBorder(BorderFactory.createLineBorder(Color.RED));
                pnN88.setVisible(true);
                pnN888.setVisible(false);
                pnN8.add(pnN88,BorderLayout.SOUTH);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pnN88.setVisible(false);
                pnN888.setVisible(true);
                pnN8.add(pnN888,BorderLayout.SOUTH);
                pn8.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            }
        });
        pnN8.add(new JLabel("                         "),BorderLayout.WEST);
        pnN8.add(new JLabel("      "),BorderLayout.EAST);
        pnN8.add(lbImages8,BorderLayout.CENTER);
        pnN8.setBackground(Color.WHITE);
//        pnN8.add(pnN88,BorderLayout.SOUTH);
        pn8.add(pnN8,BorderLayout.NORTH);
        pnC8.add(ten8);
        pnS8.add(lbGia8);
        pn8.add(pnC8,BorderLayout.CENTER);
        pn8.add(pnS8,BorderLayout.SOUTH);


        pn1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        pn2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        pn3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        pn4.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        pn5.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        pn6.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        pn7.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        pn8.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        b1.add(pn1);
        b1.add(Box.createHorizontalStrut(15));
        b1.add(pn2);
        b1.add(Box.createHorizontalStrut(15));
        b1.add(pn3);
        b1.add(Box.createHorizontalStrut(15));
        b1.add(pn4);

        b.add(Box.createVerticalStrut(20));
        b2.add(pn5);
        b2.add(Box.createHorizontalStrut(15));
        b2.add(pn6);
        b2.add(Box.createHorizontalStrut(15));
        b2.add(pn7);
        b2.add(Box.createHorizontalStrut(15));
        b2.add(pn8);
        pnCenter.add(b);

        //pnSouth
        JPanel pnSouth = new JPanel();
        JButton btn1,btn2,btn3,btn4,btn10,btnqua;
        pnSouth.add(btn1 = new JButton("1"));
        btn1.setBackground(Color.ORANGE);
        pnSouth.add(btn2 = new JButton("2"));
        btn2.setBackground(Color.WHITE);
        pnSouth.add(btn3 = new JButton("3"));
        btn3.setBackground(Color.WHITE);
        pnSouth.add(btn4 = new JButton("..."));
        btn4.setBackground(Color.WHITE);
        pnSouth.add(btn10 = new JButton("10"));
        btn10.setBackground(Color.WHITE);
        pnSouth.add(btnqua = new JButton(">"));
        btnqua.setBackground(Color.WHITE);

        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
}

