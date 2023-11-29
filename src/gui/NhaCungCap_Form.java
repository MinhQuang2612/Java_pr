package gui;

import TableModel.KH_TableModel;
import TableModel.NCC_TableModel;
import TableModel.NV_TableModel;
import com.toedter.calendar.JDateChooser;

import dao.KhachHang_DAO;
import dao.NhaCungCap_DAO;
import entity.NhaCungCap;
import entity.NhaSanXuat;
import entity.NhanVien;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCap_Form extends JPanel {
    JPanel pnNorth,pnCenter1,pnWest1,pnCenter,pnSouth;
    JLabel lbImage,lblMa,lblKhachHang,lblNamSinh,lblGioiTinh,lbldiaChi,lblEmail,lblSDT,lblCM,lblText;
    JTextField txtMa,txtKhachHang,txtNamSinh,txtdiaChi,txtEmail,txtSDT,txtCM, txtTim;
    JButton btnThem,btnXoa,btnSua,btnLuu,btnThoat,btnSuaAnh, btnTim;
    ImageIcon icon;
    JComboBox cbcGT;
    JDateChooser namSinh;
    NhaCungCap_DAO nccDao;
    NhaCungCap ncc;
    List<NhaCungCap> lncc;
    private NCC_TableModel tableModel;

    public NhaCungCap_Form(){
        doshow();
    }

    private void doshow() {
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
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
        lbImage.setPreferredSize(new Dimension(130,120));
        //lbImage.setIcon(new ImageIcon(getClass().getResource("/images/user11.png")));
        lbImage.setIcon(icon);
        pnWest1.add(lbImage,BorderLayout.NORTH);
        pnWest1.add(btnSuaAnh);
        //pnCenter
        pnCenter1 = new JPanel();
        Box b,b1,b2,b3,b4;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840,100));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Nhà Cung Cấp: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblKhachHang = new JLabel("Tên Nhà Cung Cấp:    "));
        b1.add(txtKhachHang = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblEmail = new JLabel("Email:      "));
        b3.add(txtEmail = new JTextField(30));
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblSDT = new JLabel("Điện Thoại:     "));
        b3.add(txtSDT = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lbldiaChi = new JLabel("Địa Chỉ:    "));
        b4.add(txtdiaChi = new JTextField());
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblKhachHang.getPreferredSize());
        lblEmail.setPreferredSize(lblKhachHang.getPreferredSize());
        lblSDT.setPreferredSize(lblKhachHang.getPreferredSize());
        lbldiaChi.setPreferredSize(lblKhachHang.getPreferredSize());


        pnCenter1.add(b);
        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);

        //Center
        TitledBorder tileDanhSach = new TitledBorder("Danh sách nhà cung cấp");

        //pnSouth.setPreferredSize(new Dimension(1030,60));
        pnCenter = new JPanel();
        pnCenter.setBorder(tileDanhSach);
        //NhaCungCap_DAO nccDao = new NhaCungCap_DAO();
//        tableModel = new NCC_TableModel(nccDao.getLS());
//        JTable table = new JTable(tableModel);
        List<NhaCungCap> ls = new ArrayList<>();
        NCC_TableModel model = new NCC_TableModel(ls);
        JTable table = new JTable();
        table.setModel(model);

        //Sự kiện Table
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {
                    txtMa.setText(table.getValueAt(r,0).toString());
                    txtKhachHang.setText(table.getValueAt(r,1).toString());
                    txtSDT.setText(table.getValueAt(r,2).toString());
                    txtEmail.setText(table.getValueAt(r,3).toString());
                    txtdiaChi.setText(table.getValueAt(r,4).toString());
                    txtMa.setEditable(false);
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
        btnTim = new JButton("Tìm Nhà Cung Cấp");
        //Su kien tim
        btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nccDao = new NhaCungCap_DAO();
				lncc = nccDao.TimKiemSDT1(txtTim.getText());
				System.out.println(lncc);
				if (lncc.size() == 0) {
					JOptionPane.showMessageDialog(null, "Nhà cung cấp không tồn tại!");
				}
				else {
					table.setModel(new NCC_TableModel(lncc));
				}
			}
		});
        btnThem = new JButton("Thêm Nhà Cung Cấp");
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
                txtEmail.setText("");
                txtSDT.setText("");
                txtMa.setEditable(false);
                txtKhachHang.requestFocus();
            }
        });
//        btnXoa = new JButton("Xóa Nhà Cung Cấp");
//        btnXoa.setIcon(new ImageIcon(getClass().getResource("/images_menu/xoa.png")));
        btnSua = new JButton("Sửa TT Nhà Cung Cấp");
        btnSua.setIcon(new ImageIcon(getClass().getResource("/images_menu/sua.png")));

        //Su Kien Sua
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                NhaCungCap nccSua = new NhaCungCap(txtMa.getText(),txtKhachHang.getText(),txtSDT.getText(),txtEmail.getText(),txtdiaChi.getText(),icon.getDescription());
                if(r != -1){
                    int lc = JOptionPane.showConfirmDialog(null,"Bạn chắc chắn muốn sử thông tin?","Update",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION){
                        if(nccDao.updateNhaCC(table.getValueAt(r,0).toString(),nccSua)){
                            table.setModel(new NCC_TableModel(nccDao.getLS()));
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần sửa");
                }
            }
        });

        btnLuu = new JButton("Lưu Thông Tin");
        btnLuu.setIcon(new ImageIcon(getClass().getResource("/images_menu/luu.png")));

        //Su kien luu
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean rs = true;
                for (NhaCungCap ncc1: nccDao.getLS()) {
                    if (ncc1.getTenNhaCC().equalsIgnoreCase(txtKhachHang.getText().trim())){
                        rs = false;
                    }
                }
                System.out.println(rs);
                if(rs == true) {
                    NhaCungCap ncc = new NhaCungCap(txtMa.getText(), txtKhachHang.getText(), txtSDT.getText(), txtEmail.getText(), txtdiaChi.getText(), icon.getDescription());
                    if (nccDao.addNhaCungCap(ncc)) {
                        table.setModel(new NCC_TableModel(nccDao.getLS()));
                    }
                }else
                {
                    JOptionPane.showMessageDialog(null,"Tên nhà cung cấp đã tồn tại!");
                }
            }
        });
        //Sự kiện xóa
//        btnXoa.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int r = table.getSelectedRow();
//                if(r != -1) {
//                    int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn xóa dòng này không","Delete",JOptionPane.YES_NO_OPTION);
//                    if(lc == JOptionPane.YES_OPTION){
//                        if(nccDao.deleteNCC(table.getValueAt(r,0).toString())){
//                            table.setModel(new NCC_TableModel(nccDao.getLS()));
//                        }
//                    }
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

        this.add(pnNorth,BorderLayout.NORTH);

        this.add(pnCenter,BorderLayout.CENTER);

        this.add(pnSouth,BorderLayout.SOUTH);

    }
}

