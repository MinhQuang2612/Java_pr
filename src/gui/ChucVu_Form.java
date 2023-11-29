package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import TableModel.CV_TableModel;
import TableModel.KH_TableModel;
import TableModel.NV_TableModel;
import dao.ChucVu_DAO;
import dao.KhachHang_DAO;
import entity.ChucVu;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhanVien;

public class ChucVu_Form extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8244055517512121667L;
	JPanel pnNorth,pnCenter1,pnWest1,pnCenter,pnSouth;
    JLabel lbImage,lblMa,lblTen,lbldiaChi,lblText;
    JTextField txtMa,txtTen,txtdiaChi, txtTim;
    JButton btnThem,btnXoa,btnSua,btnLuu,btnThoat,btnSuaAnh, btnTim;
    ImageIcon icon;
    ChucVu_DAO cvDao;
    ChucVu cv;
    List<ChucVu> lcv;
    private CV_TableModel tableModel;
    public ChucVu_Form(){
        doshow();
    }

    private void doshow() {
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("QUẢN LÝ CHỨC VỤ");
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
        icon = new ImageIcon("C:\\Users\\vanhu\\Documents\\Nhom01_QuanLyMuaBanLinhKien_PTUD\\src\\images\\user11.png");
        lbImage.setPreferredSize(new Dimension(130,120));
        //lbImage.setIcon(new ImageIcon(getClass().getResource("/images/user11.png")));
        lbImage.setIcon(icon);
        pnWest1.add(lbImage,BorderLayout.NORTH);
//        pnWest1.add(btnSuaAnh);
        //pnCenter
        pnCenter1 = new JPanel();
        Box b,b1;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840,70));

        b.add(Box.createVerticalStrut(30));
        b.add(b1 = Box.createHorizontalBox());

        b1.add(lblMa = new JLabel("Mã Chức Vụ: "));
        b1.add(txtMa = new JTextField());
        txtMa.setEditable(false);
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên Chức Vụ:    "));
        b1.add(txtTen = new JTextField());
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblTen.getPreferredSize());

        pnCenter1.add(b);
        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);

        //Center
        TitledBorder tileDanhSach = new TitledBorder("Danh sách chức vụ");

        //pnSouth.setPreferredSize(new Dimension(1030,60));
        pnCenter = new JPanel();
        pnCenter.setBorder(tileDanhSach);
        
//        tableModel = new CV_TableModel(cvDao.getLS());
//        JTable table = new JTable(tableModel);
        List<ChucVu> ls = new ArrayList<>();
        CV_TableModel model = new CV_TableModel(ls);
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
        btnTim = new JButton("Tìm Chức Vụ");
        //Su kien tim
        btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cvDao = new ChucVu_DAO();
				lcv = cvDao.TimKiemMa1(txtTim.getText());
				System.out.println(lcv);
				if (lcv.size() == 0) {
					JOptionPane.showMessageDialog(null, "Chức vụ không tồn tại!");
				}
				else {
					table.setModel(new CV_TableModel(lcv));
				}
			}
		});
        btnThem = new JButton("Thêm Chức Vụ");
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
//        btnXoa = new JButton("Xóa Chức Vụ");
//        btnXoa.setIcon(new ImageIcon(getClass().getResource("/images_menu/xoa.png")));
        btnSua = new JButton("Sửa TT Chức Vụ");
        btnSua.setIcon(new ImageIcon(getClass().getResource("/images_menu/sua.png")));

     // Su Kien Sua
     		btnSua.addActionListener(new ActionListener() {
     			@Override
     			public void actionPerformed(ActionEvent e) {
     				// int r = table.getSelectedRow();
     				ChucVu cv = new ChucVu(txtMa.getText(), txtTen.getText(), 0.0);
     				if (cvDao.updateChucVu(txtMa.getText(), cv)) {
     					table.setModel(new CV_TableModel(cvDao.getLS()));
     					JOptionPane.showMessageDialog(null, "Chức vụ đã được cập nhật!");
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
                for (ChucVu cv : cvDao.getLS()) {
                    if (cv.getTenChucVu().equalsIgnoreCase(txtTen.getText().trim())) {
                        rs = false;
                    }
                }
                System.out.println(rs);
                if (rs == true) {
                    ChucVu cv = new ChucVu(txtMa.getText(), txtTen.getText(),0.0);
                    if (cvDao.addChucVu(cv)) {
                        table.setModel(new CV_TableModel(cvDao.getLS()));
                    }
                }else
                {
                    JOptionPane.showMessageDialog(null,"Tên chức vụ đã tồn tại!");
                }
            }
        });
        //Sự kiện xóa
//        btnXoa.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int r = table.getSelectedRow();
//                if(r != -1) {
//                    int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn xóa chức vụ này không","Delete",JOptionPane.YES_NO_OPTION);
//                    if(lc == JOptionPane.YES_OPTION){
//                        if(cvDao.deleteCV(table.getValueAt(r,1).toString())){
//                            table.setModel(new CV_TableModel(cvDao.getLS()));
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
