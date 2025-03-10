package gui;

import com.toedter.calendar.JDateChooser;

import TableModel.CT_HoaDonNH_TableModel;
import TableModel.SP_TableModel;
import dao.CT_GioHang_DAO;
import dao.CT_HoaDonBH_DAO;
import dao.CT_HoaDonNH_DAO;
import dao.GioHang_DAO;
import dao.HoaDonBanHang_DAO;
import dao.HoaDonNhapHang_DAO;
import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import dao.LoaiSanPham_DAO;
import dao.NhaCungCap_DAO;
import dao.NhaSanXuat_DAO;
import entity.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NhapHang1_Form extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1527081736862254029L;
	JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblTienThuoc,lblTienNhan,lblTienThue,lblTienThoi,lblTongTien,
            lblMaHD,lblST,lblNgayLap,lblNhanVien,lblDiaChi,lblNhaCC,lblEmail;
           
    JTextField txttienThuoc,txtTienNhan,txtThue,txtTienThoi,txtTong
            ,txtSDT,txtMaHD,txtNhanVien,txtDiaChi,txtNhaCC,txtMail;
    JLabel lblMa,lblTen,lblNgaySX,lblBaoHanh,lblTinhTrang,lblDonViTinh,lblGia,lblLoai,lblMoTa,lblNhaSX,lbImage,lblSoLuong;
    JTextField txtMa,txtTen,txtNhaSX,txtDonViTinh,txtGia,txtMoTa,txtSoLuong,txtTinhTrang;
    JButton btnThem,btnXoa,btnSua,btnLuu, btnTim;
    ImageIcon icon;
    ImageIcon lk [];
    JComboBox<String> cbcGT,cbcChucVu,cbcBaoHanh,cbcNhaSX;
    JDateChooser ngaySanXuat;
    SP_TableModel tableModel;
    String maHDNH = "";        
    JButton btnHoaDonMoi,btnIn,btnThoat,btnNhaCC,btnNhapthuoc, btnLapHD;
    JComboBox<String> cbcLoai,cbcDonVi;
    JDateChooser NgayLap,NgayHetHan;
    String mahd = "";
    NhanVien nv;
    String maNCC = "";
    NhaCungCap ncc;
    NhaCungCap_DAO ncc_dao;
    NhapHang1_Form nh;
    double tongTien = 0;
    public NhapHang1_Form(){

    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("LẬP HÓA ĐƠN NHẬP HÀNG");
        lblTieuDe.setFont(new Font("arial", Font.BOLD,18));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe,BorderLayout.NORTH);

        JPanel pnThongTin = new JPanel();
        pnThongTin.setBorder(new TitledBorder("Thông Tin Hóa Đơn"));
        Box b,b1,b2,b3,b4, b5;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(500,210));
        b.add(Box.createVerticalStrut(30));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMaHD = new JLabel("Mã HD:"));
        b1.add(txtMaHD = new JTextField());
        txtMaHD.setEditable(false);
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblNgayLap = new JLabel("Ngày Lập HD:"));
        NgayLap = new JDateChooser();
//        lblNgayVao.setSize(new Dimension(30,20));
//        lblNgayVao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        NgayLap.setDateFormatString("yyyy-MM-dd");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            NgayLap.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b1.add(NgayLap);
        b.add(Box.createVerticalStrut(10));

        Box b11;
        b.add(b11 = Box.createHorizontalBox());
        b11.add(lblNhanVien = new JLabel("Nhân Viên Lập HD: "));
        b11.add(txtNhanVien = new JTextField());
        b.add(Box.createVerticalStrut(10));
//        b.add(b2 = Box.createHorizontalBox());
//        b2.add(btnNhaCC = new JButton("Nhà Cung Cấp"));
//        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblNhaCC = new JLabel("Nhà Cung Cấp: "));
        b3.add(txtNhaCC = new JTextField());
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblEmail = new JLabel("Email: "));
        b3.add(txtMail = new JTextField());
        

        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblST = new JLabel("Số Điện Thoại: "));
        b4.add(txtSDT = new JTextField());
        b4.add(btnTim = new JButton("Tìm"));
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblDiaChi = new JLabel("Địa Chỉ: "));
        b4.add(txtDiaChi = new JTextField());
        b.add(Box.createVerticalStrut(10));
        
        b.add(b5 = Box.createHorizontalBox());
        b5.add(btnLapHD = new JButton("Chọn Nhà Cung Cấp"));

        lblMaHD.setPreferredSize(lblNhanVien.getPreferredSize());
        lblNgayLap.setPreferredSize(lblNhanVien.getPreferredSize());
        lblNhaCC.setPreferredSize(lblNhanVien.getPreferredSize());
        lblST.setPreferredSize(lblNhanVien.getPreferredSize());

        JPanel pnThuoc = new JPanel();
        pnThuoc.setBorder(new TitledBorder("Nhập Sản Phẩm"));
        pnThuoc.setLayout(new BorderLayout());
        Box bb,bb1,bb2,bb3,bb4,bb5,bb6;
        bb = Box.createVerticalBox();
        bb.setPreferredSize(new Dimension(650,170));

        bb.add(bb1 = Box.createHorizontalBox());
        bb1.add(lblMa = new JLabel("Mã Sản Phẩm: "));
        bb1.add(txtMa = new JTextField(20));
        bb1.add(Box.createHorizontalStrut(20));
        bb1.add(lblTen = new JLabel("Tên Sản Phẩm: "));
        bb1.add(txtTen = new JTextField(20));
        bb.add(Box.createVerticalStrut(10));

        bb.add(bb2 = Box.createHorizontalBox());
        bb2.add(lblBaoHanh = new JLabel("Bảo Hành: "));
        cbcBaoHanh = new JComboBox<>();
        cbcBaoHanh.addItem("2 tháng");
        cbcBaoHanh.addItem("3 tháng");
        cbcBaoHanh.addItem("6 tháng");
        cbcBaoHanh.addItem("12 tháng");
        cbcBaoHanh.setPreferredSize(new Dimension(225,20));
        bb2.add(cbcBaoHanh);
        bb2.add(Box.createHorizontalStrut(20));
        bb2.add(lblNgaySX = new JLabel("Ngày Sản Xuất: "));
        ngaySanXuat = new JDateChooser();
        //ngaySanXuat.setSize(new Dimension(30,20));
        ngaySanXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ngaySanXuat.setDateFormatString("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            ngaySanXuat.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bb2.add(ngaySanXuat);
        bb.add(Box.createVerticalStrut(10));

        bb.add(bb3 = Box.createHorizontalBox());
        bb3.add(lblTinhTrang = new JLabel("Tình Trạng: "));
        bb3.add(txtTinhTrang = new JTextField(20));
        txtTinhTrang.setText("Mới");
        txtTinhTrang.setEditable(false);
        bb3.add(Box.createHorizontalStrut(20));
        bb3.add(lblLoai = new JLabel("Loại Sản Phẩm: "));
        cbcLoai = new JComboBox<>();
        LoaiSanPham_DAO lspDao = new LoaiSanPham_DAO();
        for (LoaiSanPham lsp : lspDao.getLS()) {
			cbcLoai.addItem(lsp.getTenLoai());
		}
        cbcLoai.setPreferredSize(new Dimension(225,20));
        bb3.add(cbcLoai);
        bb.add(Box.createVerticalStrut(10));

        bb.add(bb4 = Box.createHorizontalBox());
        bb4.add(lblDonViTinh = new JLabel("Đơn Vị Tính: "));
        bb4.add(txtDonViTinh = new JTextField(20));
        bb4.add(Box.createHorizontalStrut(20));
        bb4.add(lblGia = new JLabel("Đơn Giá:    "));
        bb4.add(txtGia = new JTextField(20));
        bb.add(Box.createVerticalStrut(10));

        bb.add(bb5 = Box.createHorizontalBox());
        bb5.add(lblSoLuong = new JLabel("Số Lượng: "));
        bb5.add(txtSoLuong = new JTextField(20));
        bb5.add(Box.createHorizontalStrut(20));
        bb5.add(lblNhaSX = new JLabel("Nhà Sản Xuất: "));
        cbcNhaSX = new JComboBox<>();
        NhaSanXuat_DAO nsxDao = new NhaSanXuat_DAO();
        for (NhaSanXuat nsx : nsxDao.getLS()) {
			cbcNhaSX.addItem(nsx.getTenNhaSX());
		}
        cbcNhaSX.setPreferredSize(new Dimension(225,20));
        bb5.add(cbcNhaSX);
        bb.add(Box.createVerticalStrut(10));
        bb.add(bb6 = Box.createHorizontalBox());
        bb6.add(btnNhapthuoc = new JButton("Nhập Sản Phẩm"));
        btnNhapthuoc.setIcon(new ImageIcon(getClass().getResource("/images_menu/them.png")));
        txtMa.setEditable(false);
        
        lblBaoHanh.setPreferredSize(lblNgaySX.getPreferredSize());
        lblDonViTinh.setPreferredSize(lblNgaySX.getPreferredSize());
        lblGia.setPreferredSize(lblNgaySX.getPreferredSize());
        lblMa.setPreferredSize(lblNgaySX.getPreferredSize());
        lblTen.setPreferredSize(lblNgaySX.getPreferredSize());
        lblLoai.setPreferredSize(lblNgaySX.getPreferredSize());
        lblNhaSX.setPreferredSize(lblNgaySX.getPreferredSize());
        lblSoLuong.setPreferredSize(lblNgaySX.getPreferredSize());
        lblTinhTrang.setPreferredSize(lblNgaySX.getPreferredSize());


        pnThuoc.add(bb);
        pnThongTin.add(b);

        pnNorth.add(pnThuoc,BorderLayout.EAST);
        pnNorth.add(pnThongTin,BorderLayout.CENTER);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setBorder(new TitledBorder("Danh Sách Sản Phẩm Đã Nhập"));
        List<CT_HoaDonNhapHang> ls1 = new ArrayList<>();
        CT_HoaDonNH_TableModel model1 = new CT_HoaDonNH_TableModel(ls1);
        JTable table1 = new JTable();
        table1.setModel(model1);
        JScrollPane sc1 = new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc1.setPreferredSize(new Dimension(850,120));

        pnCenter.add(sc1);

        //pnSouth
        pnSouth = new JPanel();
        pnSouth.setLayout(new BorderLayout());
        Box s,s1,s2,s3;
        s = Box.createVerticalBox();
        s.setPreferredSize(new Dimension(1000,100));
        s.add(s1 = Box.createHorizontalBox());
        s1.add(lblTienThuoc = new JLabel("Tổng Tiền Sản Phẩm: "));
        s1.add(txttienThuoc = new JTextField());
        txttienThuoc.setEditable(false);
        s1.add(Box.createHorizontalStrut(50));
        s1.add(lblTienNhan = new JLabel("          "));
        s1.add(txtTienNhan = new JTextField());
        txtTienNhan.setEditable(false);
        s.add(Box.createVerticalStrut(7));

        s.add(s2 = Box.createHorizontalBox());
        s2.add(lblTienThue = new JLabel("Thuế GTGT: "));
        s2.add(txtThue = new JTextField());
        txtThue.setEditable(false);
        s2.add(Box.createHorizontalStrut(50));
        s2.add(lblTienThoi = new JLabel("           "));
        s2.add(txtTienThoi = new JTextField());
        txtTienThoi.setEditable(false);
        s.add(Box.createVerticalStrut(10));

        s.add(s3 = Box.createHorizontalBox());
        s3.add(lblTongTien = new JLabel("Tổng Tiền HD: "));
        s3.add(txtTong = new JTextField());
        txtTong.setEditable(false);
        s3.add(Box.createHorizontalStrut(50));
        s3.add(btnHoaDonMoi = new JButton("Tạo Hóa Đơn Mới"));
        s.add(Box.createVerticalStrut(7));

        Box d;
        d = Box.createVerticalBox();
        d.setPreferredSize(new Dimension(150,30));
        d.add(btnIn = new JButton("In Hóa Đơn"));
        d.add(Box.createVerticalStrut(15));
        d.add(btnThoat = new JButton("Quay Lại     "));
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/images_menu/quaylai.png")));
        btnIn.setIcon(new ImageIcon(getClass().getResource("/images_menu/printer.png")));

        pnSouth.add(s,BorderLayout.WEST);
        pnSouth.add(d,BorderLayout.EAST);
        pnSouth.setBorder(new TitledBorder("Chi tiết hóa đơn"));

        lblTienNhan.setPreferredSize(lblTienThuoc.getPreferredSize());
        lblTienThoi.setPreferredSize(lblTienThuoc.getPreferredSize());
        lblTienThue.setPreferredSize(lblTienThuoc.getPreferredSize());
        lblTongTien.setPreferredSize(lblTienThuoc.getPreferredSize());

       

        if (nv != null){
            txtNhanVien.setEditable(false);
            txtNhanVien.setText(nv.getTenNV());
        }
//        btnNhaCC.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DS_NhaCungCap_Form ds = new DS_NhaCungCap_Form();
//                ds.setVisible(true);
//                ds.nhapHang = nh;
//            }
//        });
//       
        btnLapHD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (maNCC == "") {
                	JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhà cung cấp!");
                	return;
                }
            	int result = JOptionPane.showConfirmDialog(null, "Bạn muốn nhập hàng từ NCC này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    ncc = ncc_dao.TimKiemMa(maNCC);

                    //System.out.println(ncc);
                    //System.out.println(nv);
                    
                    HoaDonNhapHang hd = new HoaDonNhapHang("", Date.valueOf(LocalDate.now()));
                    hd.setNhaCungCap(ncc);
                    hd.setNhanVien(nv);
                    HoaDonNhapHang_DAO hdDao = new HoaDonNhapHang_DAO();
                    hdDao.addHoaDonNH(hd);
                    
                }
            
        });
        
        btnTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ncc_dao = new NhaCungCap_DAO();
				ncc = ncc_dao.TimKiemSDT(txtSDT.getText());
				System.out.println(ncc);
				if (ncc == null) {
					JOptionPane.showMessageDialog(null, "Nhà cung cấp không tồn tại!");
				}
				else {
					txtNhaCC.setText(ncc.getTenNhaCC());
					txtDiaChi.setText(ncc.getDiaChi());
					txtMail.setText(ncc.geteMail());
					maNCC = ncc.getMaNhaCC();
				}
			}
			
			
		});

        btnHoaDonMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txttienThuoc.setText("");
                txtTong.setText("");
                txtNhaCC.setText("");
                txtDiaChi.setText("");
                txtSDT.setText("");
            }
        });
        
        //Su kien luu
        DecimalFormat df = new DecimalFormat("#,###.00 VND");
        btnNhapthuoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	HoaDonNhapHang_DAO hdDao = new HoaDonNhapHang_DAO();
            	SanPham_DAO lkDao = new SanPham_DAO();
                try {
					maHDNH = hdDao.getMa();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                if(!txtTen.getText().trim().equals("")) {
	                String dateTime = (String) formatter.format(ngaySanXuat.getDate());
	                SanPham sp = new SanPham(
	                		txtMa.getText().trim(), 
	                		txtTen.getText().trim(),
	                        cbcBaoHanh.getSelectedItem().toString(),
	                        java.sql.Date.valueOf(dateTime),
	                        txtTinhTrang.getText().trim(),
	                        txtDonViTinh.getText().trim(),
	                        Double.valueOf(txtGia.getText()),
	                        Integer.valueOf(txtSoLuong.getText()),
	                        "rrrs");
	                LoaiSanPham_DAO lspDao = new LoaiSanPham_DAO();
	                LoaiSanPham lsp;
	                NhaSanXuat_DAO nsxDao = new NhaSanXuat_DAO();
	                NhaSanXuat nsx;
	                if (lspDao.TimKiemTen(cbcLoai.getSelectedItem().toString()) != null) {
	                    lsp = lspDao.TimKiemTen(cbcLoai.getSelectedItem().toString());
	                    sp.setLoaiSanPham(lsp);
	                    nsx = nsxDao.TimKiemTen(cbcNhaSX.getSelectedItem().toString());
	                    sp.setNhaSanXuat(nsx);
	                    
	                    SanPham_DAO spDao = new SanPham_DAO();
	                    if(spDao.addSanPham(sp)) {
	                        try {
	//                        	List<CT_HoaDonNhapHang> ls = new ArrayList<CT_HoaDonNhapHang>();
	//                            table1.setModel(new CT_HoaDonNH_TableModel(ls));
	                            CT_HoaDonNH_DAO ctnhDao = new CT_HoaDonNH_DAO();
	                            HoaDonNhapHang_DAO nhDao = new HoaDonNhapHang_DAO();
	                            HoaDonNhapHang hdnh = nhDao.TimKiemMa(maHDNH);
	                            CT_HoaDonNhapHang ctnh = new CT_HoaDonNhapHang(sp.getSoLuong(),sp.getDonGia());
	                            ctnh.setSanPham(spDao.TimKiemTen(sp.getTenSP()));
	                            ctnh.setDonNhapHang(hdnh);
	                            ctnhDao.addCTHoaDonNH(ctnh);
	                            tongTien+= ctnh.getThanhTien();
	                            txttienThuoc.setText(String.valueOf(df.format(tongTien)));
	                            txtThue.setText("4%");
	                            txtTong.setText(String.valueOf(df.format(tongTien + tongTien*0.04)));
	                            table1.setModel(new CT_HoaDonNH_TableModel(ctnhDao.getLS(maHDNH)));
	                        } catch (Exception ex) {
	                            ex.printStackTrace();
	                        }
	                    } else
	                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
	
	                    System.out.println(spDao.TimKiemMa(sp.getTenSP()));
	                } else {
	                    JOptionPane.showMessageDialog(null, "Lỗi!");
	                }
	                clearText();
                }else {
                	JOptionPane.showMessageDialog(null, "Chưa nhập thông tin!");
                }

            }
        });
        btnIn.addActionListener(new ActionListener() {
        	private JasperPrint jprint;
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {					
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyBanHangQuanAo;instance=SQLEXPRESS;user=sa;password=12345678");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT hd.MAHDNH,hd.NGAYLAPHD,nv.TENNV,ncc.TENNHACC,ncc.SODT,ncc.DIACHI ,sp.TenSP,lsp.TENLOAI,nsx.TENNHASX,\r\n" + 
							"cthd.SOLUONG,sp.DonGia,sp.BAOHANH,cthd.SOLUONG * sp.DONGIA AS ThanhTien\r\n" + 
							"FROM [dbo].[CT_HoaDonNhapHang] cthd JOIN [dbo].[HoaDonNhapHang] hd ON cthd.MAHDNH = hd.MAHDNH\r\n" + 
							"JOIN [dbo].[NhanVien] NV ON HD.MANV = NV.MANV\r\n" + 
							"JOIN [dbo].[NhaCungCap] ncc ON ncc.MANHACC = hd.MANHACC\r\n" + 
							"JOIN [dbo].[SanPham] sp ON cthd.MaSP = sp.MASP\r\n" + 
							"JOIN [dbo].[LoaiSanPham] lsp ON lsp.MALOAI = sp.MALOAI\r\n" + 
							"JOIN [dbo].[NhaSanXuat] nsx ON nsx.MANHASX = sp.MANHASX\r\n" + 
							"WHERE hd.MAHDNH = "+"'"+maHDNH+"'";
					JasperDesign jdesign = JRXmlLoader.load("D:\\Nhom13_QuanLyBanHangQuanAo\\src\\BaoCao_Jasper\\BaoCaoNhapHang.jrxml");
					JRDesignQuery updateQuery = new JRDesignQuery();
					updateQuery.setText(sql);
					jdesign.setQuery(updateQuery);

					Map<String, Object> parameters = new HashMap<String, Object>();
					JasperReport jreport = JasperCompileManager.compileReport(jdesign);
					JasperPrint jpasperPrint = JasperFillManager.fillReport(jreport, parameters,con);
					
					
					JasperViewer.viewReport(jpasperPrint,false);
				}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);
    }
    public void clearText(){
        txtMa.setText("");
        txtTen.setText("");
        txtGia.setText("");
        txtSoLuong.setText("");
        txtDonViTinh.setText("");
        txtTen.requestFocus();
    }
}
