package gui;

import TableModel.KH_TableModel;
import com.toedter.calendar.JDateChooser;
import dao.KhachHang_DAO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TimKiemKH_Form  extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6706955420429243501L;
	JPanel pnNorth,pnCenter1,pnWest1,pnCenter,pnSouth;
    JLabel lbImage,lblText,lblTen,lbldiaChi,lblLuaChon,lblGioiTinh,lblTuoi,lblTieuDe;
    JTextField txtdiaChi,txtTen;
    JButton btnThem,btnXoa,btnSua,btnLuu,btnThoat,btnTimKiem;
    JCheckBox cbTen,cbGtinh,cbDiaChi,cbTuoi;
    ImageIcon icon;
    JComboBox<String> cbcGT,cbcTuoi;
    JDateChooser namSinh;
    private KhachHang_DAO KhDao;
    private KH_TableModel tableModel;
    public TimKiemKH_Form(){
        doShow();
    }
    public void doShow(){
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        lblTieuDe = new JLabel("TÌM KIẾM KHÁCH HÀNG");
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

        //File
        final JFileChooser fileDialog = new JFileChooser();
        JFrame cha = new JFrame();
        icon = new ImageIcon("E:\\Nhom01_QuanLyMuaBanLinhKien_PTUD\\src\\images\\user11.png");
        lbImage.setPreferredSize(new Dimension(130,130));
        //lbImage.setIcon(new ImageIcon(getClass().getResource("/images/user11.png")));
        lbImage.setIcon(icon);
        pnWest1.add(lbImage,BorderLayout.NORTH);
        //pnCenter
        pnCenter1 = new JPanel();
        Box b,b1,b2,b3,b4;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840,190));

        b.add(Box.createVerticalStrut(20));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblLuaChon = new JLabel("Tìm Kiếm Khách Hàng Theo: "));
        b1.add(Box.createHorizontalStrut(30));
        b1.add(cbTen = new JCheckBox("Tên Khách Hàng"));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(cbGtinh = new JCheckBox("Giới Tính"));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(cbDiaChi = new JCheckBox("Địa Chỉ"));
        b1.add(Box.createHorizontalStrut(10));
        b1.add(cbTuoi = new JCheckBox("Độ Tuổi"));
        b.add(Box.createVerticalStrut(20));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblTen = new JLabel("Nhập Tên KH Cần Tìm: "));
        b2.add(txtTen = new JTextField());
        b2.add(Box.createHorizontalStrut(30));
        b2.add(lblGioiTinh = new JLabel("Chọn Giới Tính Cần Tìm:   "));
        cbcGT = new JComboBox<>();
        cbcGT.addItem("Nam");
        cbcGT.addItem("Nữ");
        cbcGT.setPreferredSize(new Dimension(250,20));
        b2.add(cbcGT);
        b.add(Box.createVerticalStrut(20));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lbldiaChi = new JLabel("Nhập Địa Chỉ Cần Tìm: "));
        b3.add(txtdiaChi = new JTextField());
        b3.add(Box.createHorizontalStrut(30));
        b3.add(lblTuoi = new JLabel("Chọn Độ Tuổi Cần Tìm: "));
        cbcTuoi = new JComboBox<>();
        cbcTuoi.addItem("Dưới 18 Tuổi");
		cbcTuoi.addItem("18 - 23 Tuổi");
		cbcTuoi.addItem("24 - 30 Tuổi");
        cbcTuoi.setPreferredSize(new Dimension(250,20));
        b3.add(cbcTuoi);
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(btnTimKiem = new JButton("Tìm Khách Hàng"));
        btnTimKiem.setIcon(new ImageIcon(getClass().getResource("/images_menu/datmua.png")));
        btnTimKiem.setEnabled(true);

        txtTen.setEnabled(false);
        txtdiaChi.setEnabled(false);
        cbcGT.setEnabled(false);
        cbcTuoi.setEnabled(false);

        lblTen.setPreferredSize(lblGioiTinh.getPreferredSize());
        lbldiaChi.setPreferredSize(lblGioiTinh.getPreferredSize());
        lblTuoi.setPreferredSize(lblGioiTinh.getPreferredSize());

        cbTen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbTen.isSelected())
                    txtTen.setEnabled(true);
                else
                    txtTen.setEnabled(false);
            }
        });
        cbGtinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbGtinh.isSelected())
                    cbcGT.setEnabled(true);
                else
                    cbcGT.setEnabled(false);
            }
        });
        cbDiaChi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbDiaChi.isSelected())
                    txtdiaChi.setEnabled(true);
                else
                    txtdiaChi.setEnabled(false);
            }
        });
        cbTuoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbTuoi.isSelected())
                    cbcTuoi.setEnabled(true);
                else
                    cbcTuoi.setEnabled(false);
            }
        });
        pnCenter1.add(b);
        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);

        //Center
        TitledBorder tileDanhSach = new TitledBorder("Danh sách khách hàng");

        //pnSouth.setPreferredSize(new Dimension(1030,60));
        pnCenter = new JPanel();

        KhDao = new KhachHang_DAO();
        tableModel = new KH_TableModel(KhDao.getLS());
        JTable table = new JTable(tableModel);

        //Sự kiện Table
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {
                    if(table.getValueAt(r,8).toString().length() < 20)
                        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(table.getValueAt(r,8).toString())));
                    else
                        lbImage.setIcon(new javax.swing.ImageIcon((table.getValueAt(r,8).toString())));
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
        sc.setPreferredSize(new Dimension(930,220));
        pnCenter.add(sc);

        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);


        pnSouth = new JPanel();
        pnCenter.setBorder(tileDanhSach);
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
        KhachHang_DAO khDao = new KhachHang_DAO();
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbTen.isSelected()){
                    table.setModel(new KH_TableModel(khDao.TimKiemTen1(txtTen.getText().trim())));
                }else if(cbGtinh.isSelected()){
                    table.setModel(new KH_TableModel(khDao.TimKiemGT(cbcGT.getSelectedItem().toString())));
                }else if(cbDiaChi.isSelected()){
//                    table.setModel(new NV_TableModel(nvDao.TimKiemTenDC1(txtdiaChi.getText().trim())));
                    table.setModel(new KH_TableModel(khDao.TimKiemDC2(txtdiaChi.getText().trim())));
                }else if(cbTuoi.isSelected()){
                	if (cbcTuoi.getSelectedIndex() == 0) {
						table.setModel(new KH_TableModel(khDao.TimKiemTuoi1(18)));
					} else if (cbcTuoi.getSelectedIndex() == 1) {
						table.setModel(new KH_TableModel(khDao.TimKiemTuoi2(18, 23)));
					} else if (cbcTuoi.getSelectedIndex() == 2) {
						table.setModel(new KH_TableModel(khDao.TimKiemTuoi2(24, 30)));
					} 
                }
            }
        });
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/images_menu/thoat.png")));
        Box bSouth = Box.createHorizontalBox();
        bSouth.add(Box.createHorizontalStrut(900));
        btnThoat.setBackground(Color.red);
        btnThoat.setForeground(Color.WHITE);
        bSouth.add(btnThoat);
        pnSouth.add(bSouth);

        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
}
