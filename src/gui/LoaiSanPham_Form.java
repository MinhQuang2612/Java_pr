package gui;

import TableModel.LSP_TableModel;
import TableModel.NV_TableModel;
import dao.LoaiSanPham_DAO;
import dao.NhanVien_DAO;
import entity.LoaiSanPham;
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

public class LoaiSanPham_Form extends JPanel {
    JPanel pnNorth,pnCenter1,pnWest1,pnCenter,pnSouth;
    JLabel lbImage,lblMa,lblTen,lbldiaChi,lblText;
    JTextField txtMa,txtTen,txtdiaChi, txtTim;
    JButton btnThem,btnXoa,btnSua,btnLuu,btnThoat,btnSuaAnh, btnTim;
    ImageIcon icon;
    LoaiSanPham_DAO lspDao;
    LoaiSanPham lsp;
    List<LoaiSanPham> llsp;
    private LSP_TableModel tableModel;

    public LoaiSanPham_Form(){
        doshow();
    }

    private void doshow() {
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ LOẠI SẢN PHẨM");
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
        Box b,b1;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840,70));

        b.add(Box.createVerticalStrut(30));
        b.add(b1 = Box.createHorizontalBox());

        b1.add(lblMa = new JLabel("Mã Loại Sản Phẩm: "));
        b1.add(txtMa = new JTextField());
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên Loại Sản Phẩm:    "));
        b1.add(txtTen = new JTextField());
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblTen.getPreferredSize());

        pnCenter1.add(b);
        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);

        //Center
        TitledBorder tileDanhSach = new TitledBorder("Danh sách loại sản phẩm");

        //pnSouth.setPreferredSize(new Dimension(1030,60));
        pnCenter = new JPanel();
        pnCenter.setBorder(tileDanhSach);
        //LoaiSanPham_DAO lspDao = new LoaiSanPham_DAO();
//        tableModel = new LSP_TableModel(lspDao.getLS());
//        JTable table = new JTable(tableModel);
        List<LoaiSanPham> ls = new ArrayList<>();
        LSP_TableModel model = new LSP_TableModel(ls);
        JTable table = new JTable();
        table.setModel(model);

        //Sự kiện Table
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {
                    txtMa.setText(table.getValueAt(r,1).toString());
                    txtTen.setText(table.getValueAt(r,2).toString());
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
        btnTim = new JButton("Tìm Loại Sản Phẩm");
        btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lspDao = new LoaiSanPham_DAO();
				llsp = lspDao.TimKiemMa1(txtTim.getText());
				System.out.println(llsp);
				if (llsp.size() == 0) {
					JOptionPane.showMessageDialog(null, "Loại sản phẩm không tồn tại!");
				}
				else {
					table.setModel(new LSP_TableModel(llsp));
				}
				
			}
		});

        btnThem = new JButton("Thêm Loại Sản Phẩm");
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
                txtMa.setEditable(false);
                txtTen.requestFocus();
            }
        });
//        btnXoa = new JButton("Xóa Loại Linh Kiện");
//        btnXoa.setIcon(new ImageIcon(getClass().getResource("/images_menu/xoa.png")));
        btnSua = new JButton("Sửa TT Loại Sản Phẩm");
        btnSua.setIcon(new ImageIcon(getClass().getResource("/images_menu/sua.png")));

        //Su Kien Sua
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                LoaiSanPham lspSua = new LoaiSanPham(txtMa.getText(),txtTen.getText());
                if(r != -1){
                    int lc = JOptionPane.showConfirmDialog(null,"Bạn chắc chắn muốn sử thông tin?","Update",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION){
                        if(lspDao.updateLoaiSP(table.getValueAt(r,1).toString(),lspSua)){
                            table.setModel(new LSP_TableModel(lspDao.getLS()));
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
                for (LoaiSanPham lsp1 : lspDao.getLS()) {
                    if (lsp1.getTenLoai().equalsIgnoreCase(txtTen.getText().trim())) {
                        rs = false;
                    }
                }
                System.out.println(rs);
                if (rs == true) {
                    LoaiSanPham lsp = new LoaiSanPham(txtMa.getText(), txtTen.getText());
                    if (lspDao.addLoaiSanPham(lsp)) {
                        table.setModel(new LSP_TableModel(lspDao.getLS()));
                    }
                }else
                {
                    JOptionPane.showMessageDialog(null,"Tên loại sản phẩm đã tồn tại!");
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
//                        if(lspDao.deleteLSP(table.getValueAt(r,1).toString())){
//                            table.setModel(new LSP_TableModel(lspDao.getLS()));
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