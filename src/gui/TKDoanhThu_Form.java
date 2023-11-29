package gui;

import TableModel.CT_HoaDonBH_tableModel;
import TableModel.SP_TableModel;
import dao.CT_HoaDonBH_DAO;
import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import entity.CT_HoaDonBanHang;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TKDoanhThu_Form extends JPanel {
    JPanel pnNorth,pnCenter1,pnWest1,pnCenter,pnSouth;
    JLabel lbImage,lblText,lblTen,lblLuaChon,lblTieuDe,lblLoai,lblNSX,lblGia;
    JTextField txtTen;
    JButton btnIn,btnThoat;
    JCheckBox cbTen,cbLoai,cbNSX,cbGia;
    ImageIcon icon;
    JComboBox cbcTK;
    private KhachHang_DAO KhDao;
    private CT_HoaDonBH_tableModel tableModel;
    public TKDoanhThu_Form(){
        doShow();
    }
    public void doShow(){
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        lblTieuDe = new JLabel("THỐNG KÊ DOANH THU");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.BLUE);
        pnNorth1.add(lblTieuDe);
        pnNorth.add(pnNorth1,BorderLayout.NORTH);


        //pnWest
        pnWest1 = new JPanel();
        pnWest1.setLayout(new BorderLayout());
        JPanel pn1 = new JPanel();
        JPanel pn2 = new JPanel();
        pn1.setLayout(new BorderLayout());
        pn2.setLayout(new BorderLayout());

        //pnCenter
        pnCenter1 = new JPanel();
        Box b,b1,b2,b3,b4;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(500,50));

        b.add(Box.createVerticalStrut(20));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblLuaChon = new JLabel("Thống kê doanh thu theo: "));
        b1.add(Box.createHorizontalStrut(30));
        b1.add(cbcTK = new JComboBox());
//        cbcTK.addItem("Ngày");
        cbcTK.addItem("Tháng");
       
        pnCenter1.add(b);
        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);

        //Center
        TitledBorder tileDanhSach = new TitledBorder("Danh sách linh kiện");

        //pnSouth.setPreferredSize(new Dimension(1030,60));
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());

       
        //Sự kiện Table
        String data[][] = {};
        String [] column1 = {"Tháng","Doanh Thu"};

        DefaultTableModel model1 = new DefaultTableModel(data,column1);
      
        JTable table1 = new JTable(model1);
        
        JScrollPane sc = new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(350,150));
        
        String data2[][] = {};
        String [] column2 = {"STT","Mã HDBH","Ngày Lập HD","Tổng Tiền"};

        DefaultTableModel model2 = new DefaultTableModel(data2,column2);
      
        JTable table2 = new JTable(model2);
        
        JScrollPane sc2 = new JScrollPane(table2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc2.setPreferredSize(new Dimension(350,150));
        BieuDo bd = new BieuDo("School Vs Years" ,
                "Doanh Thu Theo Từng Tháng");
        bd.setVisible(true);
        BieuDo1 bd1 = new BieuDo1("School Vs Years" ,
                "Doanh Thu Theo Từng Tháng");
        bd1.setVisible(true);
        
        JPanel pnwc = new JPanel();
        pnwc.setLayout(new BorderLayout());
        
        pnwc.add(sc,BorderLayout.NORTH);
        pnwc.add(new JLabel(""),BorderLayout.CENTER);
        pnwc.add(sc2,BorderLayout.SOUTH);
        pnCenter.add(pnwc,BorderLayout.WEST);
        pnCenter.add(bd,BorderLayout.EAST);
        
        cbcTK.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(cbcTK.getSelectedItem().toString().trim().equalsIgnoreCase("Ngày")) {
		        	pnwc.add(sc,BorderLayout.NORTH);
		            pnwc.add(new JLabel(""),BorderLayout.CENTER);
		            pnwc.add(sc2,BorderLayout.SOUTH);
		            pnCenter.add(pnwc,BorderLayout.WEST);
		            pnCenter.add(bd1,BorderLayout.EAST);
		           
		        }else {
		        	pnwc.add(sc,BorderLayout.NORTH);
		            pnwc.add(new JLabel(""),BorderLayout.CENTER);
		            pnwc.add(sc2,BorderLayout.SOUTH);
		            pnCenter.add(pnwc,BorderLayout.WEST);
		            pnCenter.add(bd,BorderLayout.EAST);
		           
		        }
			}
		});
		

        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);


        pnSouth = new JPanel();
//        pnCenter.setBorder(tileDanhSach);
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
        Box bSouth = Box.createHorizontalBox();
        bSouth.add(Box.createHorizontalStrut(400));
        btnThoat.setBackground(Color.red);
        btnThoat.setForeground(Color.WHITE);
        bSouth.add(btnIn = new JButton("Xuất Báo Cáo"));
        btnIn.setIcon(new ImageIcon(getClass().getResource("/images_menu/printer.png")));
        bSouth.add(Box.createHorizontalStrut(350));
        bSouth.add(btnThoat);
        pnSouth.add(bSouth);

        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
}
