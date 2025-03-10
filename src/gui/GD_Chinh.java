package gui;

import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.KhachHang;
import entity.NhaSanXuat;
import entity.NhanVien;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

//
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JRDesignQuery;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;


import connection.MyConnection;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class GD_Chinh extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    JMenu Account;
	public JLabel lb,lbCenter,lbCenter1,lbCenter2,lbCenter3,lbCenter4;
    private JPanel pn_Center,pn_west;
    static TrangChu_Form tc;
    Border bd_cen;
    ImageIcon s[],b[];
    JTree tree;
    KhachHang kh;
    NhanVien nv;

    public GD_Chinh(){
        doShow();
    }
    public void doShow(){
        setSize(1300,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        
        setTitle("Quản lý bán hàng quần áo thời trang");
        Container cp = getContentPane();
        
        tc = new TrangChu_Form();

        //pn_North
        JPanel pn_North = new JPanel();
        JPanel pn_Banner = new JPanel();
        pn_North.setLayout(new GridLayout(2,1));
        lb = new JLabel();
        Border bd = BorderFactory.createLineBorder(Color.red);
        b = new ImageIcon[2];
        b[0] = new ImageIcon(getClass().getResource("/images/banner.png"));
        b[1] = new ImageIcon(getClass().getResource("/images/banner1.png"));
        //pn1.setBorder(bd);
        lb.setIcon(new ImageIcon(getClass().getResource("/images/banner1.png")));
        pn_Banner.add(lb);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuTrangChu = new JMenu("TRANG CHỦ");
        menuTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/trangchu11.png")));
        menuTrangChu.setBackground(Color.PINK);
        menuTrangChu.setFont(new Font("Arial",Font.BOLD,16));


        JMenu menuHeThong = new JMenu("HỆ THỐNG");
        menuHeThong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/hethong11.png")));

        menuHeThong.setForeground(Color.RED);
        Font fontMenu = new Font("Arial",Font.BOLD,16);
        menuHeThong.setFont(fontMenu);
        JMenuItem t;
        
        menuHeThong.add(t = new JMenuItem("Thoát"));
        t.setPreferredSize(new Dimension(200,40));
        t.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/dangxuat.png")));

        JMenu menuDanhMuc = new JMenu("DANH MỤC");
        menuDanhMuc.setForeground(Color.RED);
        menuDanhMuc.setFont(fontMenu);
        menuDanhMuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/danhmuc11.png")));

        JMenu linhKien,nVien;
        JMenuItem kHang,nCungCap,mnLoaiLK,mnNhaSX,mnChucVu;
        menuDanhMuc.add(nVien = new JMenu("Nhân Viên"));
        nVien.add(mnChucVu = new JMenuItem("Chức Vụ"));
        mnChucVu.setPreferredSize(new Dimension(150,40));
        nVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhanvien11.png")));
        nVien.setPreferredSize(new Dimension(200,40));
        menuDanhMuc.add(kHang = new JMenuItem("Khách Hàng"));
        kHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/khachHang11.png")));
        kHang.setPreferredSize(new Dimension(200,40));
        menuDanhMuc.add(nCungCap = new JMenuItem("Nhà Cung Cấp"));
        nCungCap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhaCC11.png")));
        nCungCap.setPreferredSize(new Dimension(200,40));
        menuDanhMuc.add(linhKien = new JMenu("Sản Phẩm"));
        linhKien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/linhKien11.png")));
        linhKien.setPreferredSize(new Dimension(200,40));
        linhKien.add(mnLoaiLK = new JMenuItem("Loại Sản Phẩm"));
        linhKien.add(mnNhaSX = new JMenuItem("Nhà Sản Xuất"));
        mnLoaiLK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/loailk.png")));
        mnNhaSX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhasx.png")));
        mnLoaiLK.setPreferredSize(new Dimension(150,40));
        mnNhaSX.setPreferredSize(new Dimension(150,40));


        JMenu menuQuanLy = new JMenu("XỬ LÝ");
        menuQuanLy.setForeground(Color.RED);
        menuQuanLy.setFont(fontMenu);
        JMenuItem nhapHang,banHang,datHang;
        menuQuanLy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/xuly11.png")));
        menuQuanLy.add(nhapHang = new JMenuItem("Nhập Hàng"));
        nhapHang.setPreferredSize(new Dimension(180,40));
        nhapHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/import.png")));
        menuQuanLy.add(banHang = new JMenuItem("Bán Hàng"));
        banHang.setPreferredSize(new Dimension(180,40));
        banHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/export.png")));

        JMenu menuBaoCao = new JMenu("BÁO CÁO");
        JMenu hdx,hdn;
        menuBaoCao.setForeground(Color.RED);
        menuBaoCao.setFont(fontMenu);
        JMenuItem htk,dt,xtn,xtt,xtq,xtnam,ntn,ntt,ntnam;
        menuBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/report11.png")));
        menuBaoCao.add(hdx = new JMenu("Hàng Đã Bán"));
        hdx.add(xtn = new JMenuItem("Trong Ngày"));
        hdx.add(xtt = new JMenuItem("Trong Tháng"));
        hdx.add(xtnam = new JMenuItem("Trong Năm"));
        xtn.setPreferredSize(new Dimension(150,40));
        xtt.setPreferredSize(new Dimension(150,40));
        xtnam.setPreferredSize(new Dimension(150,40));

        hdx.setPreferredSize(new Dimension(200,40));
        hdx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/bcHangBan.png")));
        menuBaoCao.add(hdn = new JMenu("Hàng Đã Nhập"));
//        hdn.add(ntn = new JMenuItem("Trong Ngày"));
        hdn.add(ntt = new JMenuItem("Trong Tháng"));
        hdn.add(ntnam = new JMenuItem("Trong Năm"));
//        ntn.setPreferredSize(new Dimension(150,40));
        ntt.setPreferredSize(new Dimension(150,40));
        ntnam.setPreferredSize(new Dimension(150,40));

        hdn.setPreferredSize(new Dimension(200,40));
        hdn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/bcHangNhap.png")));
        menuBaoCao.add(htk = new JMenuItem("Hàng Tồn Kho"));
        htk.setPreferredSize(new Dimension(200,40));
        htk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/bcHangTon.png")));
        menuBaoCao.add(dt = new JMenuItem("Doanh Thu"));
        dt.setPreferredSize(new Dimension(200,40));
        dt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/bcDoanhThu.png")));

        JMenuItem mnTimNV,mnTimKH,mnTimNCC,mnTimLK;
        JMenu menuTimKiem = new JMenu("TÌM KIẾM");
        menuTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/search11.png")));
        menuTimKiem.setForeground(Color.RED);
        menuTimKiem.setFont(fontMenu);
        menuTimKiem.add(mnTimNV = new JMenuItem("Nhân Viên"));
        mnTimNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhanvien11.png")));
        mnTimNV.setPreferredSize(new Dimension(200,40));
        menuTimKiem.add(mnTimKH = new JMenuItem("Khách Hàng"));
        mnTimKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/khachHang11.png")));
        mnTimKH.setPreferredSize(new Dimension(200,40));
        menuTimKiem.add(mnTimNCC = new JMenuItem("Nhà Cung Cấp"));
        mnTimNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/nhaCC11.png")));
        mnTimNCC.setPreferredSize(new Dimension(200,40));
        menuTimKiem.add(mnTimLK = new JMenuItem("Sản Phẩm"));
        mnTimLK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/linhKien11.png")));
        mnTimLK.setPreferredSize(new Dimension(200,40));
        
        JMenu huongDanSD = new JMenu("HƯỚNG DẪN");
        huongDanSD.setForeground(Color.RED);
        huongDanSD.setFont(fontMenu);
    
        Account = new JMenu("Tài Khoản");
        Account.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/acount11.png")));
        Account.setFont(new Font("Arial",Font.BOLD,16));
        JMenuItem thongTin,donHang,lichSu,doiMatKhau,dangXuat,gioHang;
        Account.add(thongTin = new JMenuItem("Thông Tin Tài Khoản"));
        thongTin.setPreferredSize(new Dimension(170,40));
        thongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/thongtin.png")));
        
        
        Account.add(doiMatKhau = new JMenuItem("Đổi Mật Khẩu"));
        doiMatKhau.setPreferredSize(new Dimension(170,40));
        doiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/doimatkhau.png")));
        Account.add(dangXuat = new JMenuItem("Đăng Xuất"));
        dangXuat.setPreferredSize(new Dimension(170,40));
        dangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/log-out.png")));

        JMenu space = new JMenu("            ");
        space.setEnabled(false);
        menuTrangChu.setSelected(true);

        menuBar.add(menuTrangChu);
        menuBar.add(menuHeThong);
        menuBar.add(menuDanhMuc);
        menuBar.add(menuQuanLy);
        menuBar.add(menuBaoCao);
        menuBar.add(menuTimKiem);
        menuBar.add(huongDanSD);
        menuBar.add(space);
        menuBar.add(Account);

        bd_cen = BorderFactory.createLineBorder(Color.red);

        menuTrangChu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	pn_west.setVisible(true);
                trangchu();
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

        //Sự kiện
        kHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TT_KhachHang_Form kh = new TT_KhachHang_Form();
                pn_Center.removeAll();
                pn_Center.add(kh);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Khách hàng"));
                pn_Center.validate();

            }
        });
        nVien.addMouseListener(new MouseListener() {
			
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
				TT_NhanVien_Form nv = new TT_NhanVien_Form();
                pn_Center.removeAll();
                pn_Center.add(nv);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Nhân Viên"));
                pn_Center.validate();
			}
		});
        linhKien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SanPham_Form lk = new SanPham_Form();
                pn_Center.removeAll();
                pn_Center.add(lk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Sản Phẩm"));
                pn_Center.validate();
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
        
        huongDanSD.addMouseListener(new MouseListener() {
			
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
				//System.out.println("Working Directory = " + System.getProperty("user.dir"));
				if (Desktop.isDesktopSupported()) {
		            // File in user working directory, System.getProperty("user.dir");
		            File file = new File("UserMaual.pdf");
		            if (!file.exists()) {
		                // In JAR
		                InputStream inputStream = ClassLoader.getSystemClassLoader()
		                                    .getResourceAsStream("UserManual.pdf");
		                // Copy file
		                OutputStream outputStream = null;
						try {
							outputStream = new FileOutputStream(file);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		                byte[] buffer = new byte[1024];
		                int length;
		                try {
							while ((length = inputStream.read(buffer)) > 0) {
							    outputStream.write(buffer, 0, length);
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		                try {
							outputStream.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		                try {
							inputStream.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            }
		            // Open file
		            try {
						Desktop.getDesktop().open(file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
				
			}
		});
        
        nCungCap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhaCungCap_Form ncc = new NhaCungCap_Form();
                pn_Center.removeAll();
                pn_Center.add(ncc);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Nhà Cung Cấp"));
                pn_Center.validate();
            }
        });
        
        
        banHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nv != null){
                    BanHang1_Form bh = new BanHang1_Form();
                    bh.nv = nv;
                    bh.bh = bh;
                    bh.doShow();
                    pn_west.setVisible(false);
                    pn_Center.removeAll();
                    pn_Center.add(bh);
                    pn_Center.setBorder(new TitledBorder(bd_cen,"Hóa Đơn Bán Hàng"));
                    pn_Center.validate();
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn không có quyền sử dụng chức năng này !");
                }

            }
        });
        
        nhapHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhapHang1_Form nh = new NhapHang1_Form();
                nh.nv = nv;
                nh.nh = nh;
                nh.doShow();
                pn_west.setVisible(false);
                pn_Center.removeAll();
                pn_Center.add(nh);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Hóa Đơn Nhập Hàng"));
                pn_Center.validate();
            }
        });
        thongTin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThongTinTaiKhoan_Form tt = new ThongTinTaiKhoan_Form();
                tt.kh = kh;
                tt.nv = nv;
                System.out.println(kh);
                tt.doshow();
                pn_Center.removeAll();
                pn_Center.add(tt);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Thông tin tài khoản"));
                pn_Center.validate();
            }
        });
        doiMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DoiMatKhau_Form dmk = new DoiMatKhau_Form();
                dmk.nv = nv;
                System.out.println(nv);
                dmk.ran = dmk.getSaltString();
                dmk.doshow();
                pn_Center.removeAll();
                pn_Center.add(dmk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Thay đổi mật khẩu"));
                pn_Center.validate();
            }
        });

        //Sự kiện thoát
        t.setMnemonic(KeyEvent.VK_T);
        t.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.ALT_MASK));
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lc = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn thoát chương trình không?","Xác nhận",JOptionPane.YES_NO_OPTION);
                if(lc == JOptionPane.YES_OPTION)
                    setVisible(false);
            }
        });

        mnNhaSX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhaSanXuat_Form nsx = new NhaSanXuat_Form();
                pn_Center.removeAll();
                pn_Center.add(nsx);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Nhà sản xuất"));
                pn_Center.validate();
            }
        });

        mnLoaiLK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoaiSanPham_Form llk = new LoaiSanPham_Form();
                pn_Center.removeAll();
                pn_Center.add(llk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Loại Sản Phẩm"));
                pn_Center.validate();
            }
        });
        mnChucVu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChucVu_Form llk = new ChucVu_Form();
                pn_Center.removeAll();
                pn_Center.add(llk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Chức Vụ"));
                pn_Center.validate();
            }
        });

        dt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TKDoanhThu_Form tk = new TKDoanhThu_Form();
                pn_Center.removeAll();
                pn_Center.add(tk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Thống Kê Doanh Thu"));
                pn_Center.validate();
            }
        });
        
        htk.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyBanHangQuanAo;instance=SQLEXPRESS;user=sa;password=12345678");
					String sql = "SELECT * FROM SANPHAM";
					JasperDesign jdesign = JRXmlLoader.load("D:\\\\Nhom13_QuanLyBanHangQuanAo\\\\src\\\\BaoCao_Jasper\\ThongKeHangKho.jrxml");
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
        xtn.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyBanHangQuanAo;instance=SQLEXPRESS;user=sa;password=12345678");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT sp.MASP,sp.TENSP,lsp.TENLOAI,nsx.TENNHASX,sp.BAOHANH,sp.DONVITINH,sp.DONGIA,ct.SOLUONG\r\n"
							+ "FROM [dbo].[HoaDonBanHang] hd JOIN [dbo].[CT_HoaDonBanHang] ct ON hd.MaHDBH = ct.MaHDBH\r\n"
							+ "JOIN [dbo].[SanPham] sp ON sp.MASP = ct.MASP\r\n"
							+ "JOIN [dbo].[LoaiSanPham] lsp ON lsp.MALOAI = sp.MALOAI\r\n"
							+ "JOIN [dbo].[NhaSanXuat] nsx ON nsx.MANHASX = sp.MANHASX\r\n"
							+ "WHERE DAY(hd.NGAYLAPHD) = DAY(GETDATE())";
					JasperDesign jdesign = JRXmlLoader.load("D:\\\\Nhom13_QuanLyBanHangQuanAo\\\\src\\\\BaoCao_Jasper\\ThongKeHangBan.jrxml");
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
        xtt.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyBanHangQuanAo;instance=SQLEXPRESS;user=sa;password=12345678");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT sp.MASP,sp.TENSP,lsp.TENLOAI,nsx.TENNHASX,sp.BAOHANH,sp.DONVITINH,sp.DONGIA,ct.SOLUONG\r\n"
							+ "FROM [dbo].[HoaDonBanHang] hd JOIN [dbo].[CT_HoaDonBanHang] ct ON hd.MaHDBH = ct.MaHDBH\r\n"
							+ "JOIN [dbo].[SanPham] sp ON sp.MASP = ct.MASP\r\n"
							+ "JOIN [dbo].[LoaiSanPham] lsp ON lsp.MALOAI = sp.MALOAI\r\n"
							+ "JOIN [dbo].[NhaSanXuat] nsx ON nsx.MANHASX = sp.MANHASX\r\n"
							+ "WHERE MONTH(hd.NGAYLAPHD) = MONTH(GETDATE())";
					JasperDesign jdesign = JRXmlLoader.load("D:\\\\Nhom13_QuanLyBanHangQuanAo\\\\src\\\\BaoCao_Jasper\\ThongKeHangBan1.jrxml");
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
        xtnam.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyBanHangQuanAo;instance=SQLEXPRESS;user=sa;password=12345678");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT sp.MASP,sp.TENSP,lsp.TENLOAI,nsx.TENNHASX,sp.BAOHANH,sp.DONVITINH,sp.DONGIA,ct.SOLUONG\r\n"
							+ "FROM [dbo].[HoaDonBanHang] hd JOIN [dbo].[CT_HoaDonBanHang] ct ON hd.MaHDBH = ct.MaHDBH\r\n"
							+ "JOIN [dbo].[SanPham] sp ON sp.MASP = ct.MASP\r\n"
							+ "JOIN [dbo].[LoaiSanPham] lsp ON lsp.MALOAI = sp.MALOAI\r\n"
							+ "JOIN [dbo].[NhaSanXuat] nsx ON nsx.MANHASX = sp.MANHASX\r\n"
							+ "WHERE YEAR(hd.NGAYLAPHD) = YEAR(GETDATE())";
					System.out.println(sql);
					JasperDesign jdesign = JRXmlLoader.load("D:\\\\Nhom13_QuanLyBanHangQuanAo\\\\src\\\\BaoCao_Jasper\\ThongKeHangBan2.jrxml");
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
        ntt.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyBanHangQuanAo;instance=SQLEXPRESS;user=sa;password=12345678");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT sp.MASP,sp.TENSP,lsp.TENLOAI,nsx.TENNHASX,sp.BAOHANH,sp.DONVITINH,sp.DONGIA,ct.SOLUONG\r\n"
							+ "FROM [dbo].HoaDonNhapHang hd JOIN [dbo].CT_HoaDonNhapHang ct ON hd.MaHDNH = ct.MaHDNH\r\n"
							+ "JOIN [dbo].[SanPham] sp ON sp.MASP = ct.MASP\r\n"
							+ "JOIN [dbo].[LoaiSanPham] lsp ON lsp.MALOAI = sp.MALOAI\r\n"
							+ "JOIN [dbo].[NhaSanXuat] nsx ON nsx.MANHASX = sp.MANHASX\r\n"
							+ "WHERE MONTH(hd.NGAYLAPHD) = MONTH(GETDATE())";
					JasperDesign jdesign = JRXmlLoader.load("D:\\\\Nhom13_QuanLyBanHangQuanAo\\\\src\\\\BaoCao_Jasper\\ThongKeHangNhap1.jrxml");
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
        ntnam.addActionListener(new ActionListener() {
        	private JasperPrint jprint;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("net.sourceforge.jtds.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/QuanLyBanHangQuanAo;instance=SQLEXPRESS;user=sa;password=12345678");
//					Connection con = MyConnection.getInstance().getConnection();
					String sql = "SELECT sp.MASP,sp.TENSP,lsp.TENLOAI,nsx.TENNHASX,sp.BAOHANH,sp.DONVITINH,sp.DONGIA,ct.SOLUONG\r\n"
							+ "FROM [dbo].HoaDonNhapHang hd JOIN [dbo].CT_HoaDonNhapHang ct ON hd.MaHDNH = ct.MaHDNH\r\n"
							+ "JOIN [dbo].[SanPham] sp ON sp.MASP = ct.MASP\r\n"
							+ "JOIN [dbo].[LoaiSanPham] lsp ON lsp.MALOAI = sp.MALOAI\r\n"
							+ "JOIN [dbo].[NhaSanXuat] nsx ON nsx.MANHASX = sp.MANHASX\r\n"
							+ "WHERE YEAR(hd.NGAYLAPHD) = YEAR(GETDATE())";
					JasperDesign jdesign = JRXmlLoader.load("D:\\\\Nhom13_QuanLyBanHangQuanAo\\\\src\\\\BaoCao_Jasper\\ThongKeHangNhap2.jrxml");
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

        mnTimKH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemKH_Form tkkh = new TimKiemKH_Form();
                pn_Center.removeAll();
                pn_Center.add(tkkh);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Tìm kiếm khách hàng"));
                pn_Center.validate();
            }
        });

        mnTimNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemNV_Form tknv = new TimKiemNV_Form();
                pn_Center.removeAll();
                pn_Center.add(tknv);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Tìm kiếm nhân viên"));
                pn_Center.validate();
            }
        });

        mnTimNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemNCC_Form tkncc = new TimKiemNCC_Form();
                pn_Center.removeAll();
                pn_Center.add(tkncc);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Tìm kiếm nhà cung cấp"));
                pn_Center.validate();
            }
        });

        mnTimLK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimKiemSP_Form tklk = new TimKiemSP_Form();
                pn_Center.removeAll();
                pn_Center.add(tklk);
                pn_Center.setBorder(new TitledBorder(bd_cen,"Tìm kiếm sản phẩm"));
                pn_Center.validate();
            }
        });


        dangXuat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                ActionEvent.CTRL_MASK));
        dangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lc = JOptionPane.showConfirmDialog(null,"Bạn muốn đăng xuất phải không?","Xác nhận",JOptionPane.YES_NO_OPTION);
                if(lc == JOptionPane.YES_OPTION){
                    setVisible(false);
                    DangNhap_Form dn = new DangNhap_Form();
                    dn.setVisible(true);
                }

            }
        });
        
       
        JPanel pn_Menu = new JPanel();
        pn_Menu.add(menuBar);
        //pn2.setBorder(bd);
        pn_North.add(pn_Banner);
        pn_North.add(pn_Menu);
        cp.add(pn_North,BorderLayout.NORTH);

        //pn_west
        pn_west = new JPanel();
        pn_west.setLayout(new BorderLayout());
        JButton btnz = new JButton("Danh Mục Sản Phẩm");
        btnz.setIcon(new ImageIcon(getClass().getResource("/images/menu111.png")));
        btnz.setSelected(true);
        btnz.setForeground(Color.WHITE);
        btnz.setBackground(Color.GRAY);
        pn_west.add(btnz,BorderLayout.NORTH);
        Border bd_west = BorderFactory.createLineBorder(Color.red);
        TitledBorder title_west = new TitledBorder(bd_west,"DANH MỤC SẢN PHẨM");
        //pn_west.setBorder(bd_west);


        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Sản Phẩm");
        //create the child nodes
        DefaultMutableTreeNode quanAoNode = new DefaultMutableTreeNode("Quần Áo");
        
        DefaultMutableTreeNode giayDepNode = new DefaultMutableTreeNode("Giày Dép");
        
        DefaultMutableTreeNode phuKienNode = new DefaultMutableTreeNode("Phụ kiện");
        
        root.add(quanAoNode);
        root.add(giayDepNode);
        root.add(phuKienNode);

        //create the tree by passing in the root node
        tree = new JTree(root);
        
//        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
//
//        // Đặt kích thước chữ cho các nút gốc con
//        Font font = new Font("Arial", Font.PLAIN, 13); // Thay đổi Font và kích thước tùy ý
//        renderer.setFont(font);
        
        tree.getSelectionModel().addTreeSelectionListener(new Selector());
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        JScrollPane sp_tree = new JScrollPane(tree,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                ,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp_tree.setPreferredSize(new Dimension(200,50));
        pn_west.add(sp_tree,BorderLayout.CENTER);
        cp.add(pn_west,BorderLayout.WEST);

        //pn_Center
        pn_Center = new JPanel();
        JPanel pn_C1 = new JPanel();
        JPanel pn_C2 = new JPanel();
        TitledBorder title_center = new TitledBorder(bd_west,"Trang chủ");
        pn_Center.setBorder(title_center);
        lbCenter = new JLabel();lbCenter1 = new JLabel();lbCenter2 = new JLabel();
        lbCenter3 = new JLabel();lbCenter4 = new JLabel();

        s = new ImageIcon[7];
        s[0] = new ImageIcon(getClass().getResource("/images/pn111.png"));
        s[1] = new ImageIcon(getClass().getResource("/images/pn1112.png"));
        s[2] = new ImageIcon(getClass().getResource("/images/pn1113.png"));
        s[3] = new ImageIcon(getClass().getResource("/images/pn1114.png"));
        s[4] = new ImageIcon(getClass().getResource("/images/pn1115.png"));
        s[5] = new ImageIcon(getClass().getResource("/images/pn1116.png"));
        s[6] = new ImageIcon(getClass().getResource("/images/pn1118.png"));

        lbCenter.setIcon(new ImageIcon(getClass().getResource("/images/pn1116.png")));


        lbCenter1.setIcon(new ImageIcon(getClass().getResource("/images/pn1121.png")));
        lbCenter2.setIcon(new ImageIcon(getClass().getResource("/images/pn1131.png")));
        lbCenter3.setIcon(new ImageIcon(getClass().getResource("/images/pn1132.png")));
        lbCenter4.setIcon(new ImageIcon(getClass().getResource("/images/pn1151.png")));
        pn_C1.add(lbCenter);
        pn_C1.add(lbCenter1);
        pn_C2.add(lbCenter2);
        pn_C2.add(lbCenter3);
        pn_C2.add(lbCenter4);

        pn_Center.add(pn_C1,BorderLayout.WEST);
        pn_Center.add(pn_C2,BorderLayout.WEST);
        cp.add(pn_Center,BorderLayout.CENTER);

        //pn_South
        JPanel pn_south = new JPanel();
        pn_south.setLayout(new GridLayout(2,1));
        JPanel pnt = new JPanel();
        JPanel pnd = new JPanel();
        JPanel pnd1 = new JPanel();
        Font ft_south = new Font("Arial",Font.BOLD,13);
        JLabel lb_south = new JLabel("Trần Minh Quang - Nguyễn Thanh Quyền - Nguyễn Thị Như Ý - Trương Hữu Trí");
        JLabel lb_nhom = new JLabel("Chương trình quản lý bán hàng quần áo thời trang");
        lb_nhom.setFont(ft_south);
        lb_south.setFont(ft_south);
        pnt.add(lb_south);
        pnd.add(lb_nhom);
        pnd1.add(new JLabel("    "));
        pn_south.add(pnt);
        pn_south.add(pnd);
        pn_south.add(pnd1);
        pn_south.setPreferredSize(new Dimension(0,50));
        cp.add(pn_south,BorderLayout.SOUTH);
        pn_south.setBorder(bd);
        
        if(kh != null) {
        	menuBaoCao.setEnabled(false);
        	menuQuanLy.setEnabled(false);
        	
        	menuHeThong.setEnabled(false);
        }
    }

    public void trangchu() {
        pn_Center.removeAll();
        tc = new TrangChu_Form();
        pn_Center.add(tc);
        pn_Center.setBorder(new TitledBorder(bd_cen,"Trang chủ"));
        pn_Center.validate();
        System.out.println("ok");
    }

    public void iterator() {
            do {
                for (int i = 0; i <= 6; i += 1) {
                    lbCenter.setIcon(s[i]);
                    tc.lbCenter.setIcon(s[i]);
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } while (true);
    }
    public void iterator1() {
        do {
            for (int i = 0; i <= 6; i += 1) {
                lbCenter.setIcon(s[i]);
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
            }
        } while (true);
    }
    private class Selector implements TreeSelectionListener {
        public void valueChanged(TreeSelectionEvent event) {
            Object obj = event.getNewLeadSelectionPath().getLastPathComponent();
            System.out.println("" + obj.toString());
            if(obj.toString().equalsIgnoreCase("Sản Phẩm")){
//                TT_SanPham_Form lk = new TT_SanPham_Form();
//                lk.kh = kh;
//                lk.doShow();
//                pn_Center.removeAll();
//                pn_Center.add(lk);
//                pn_Center.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red),"Sản Phẩm"));
//                pn_Center.validate();
            }else if(obj.toString().equalsIgnoreCase("Quần Áo")){
                QuanAo_Form mh = new QuanAo_Form();
                mh.doShow();
                mh.kh = kh;
                pn_Center.removeAll();
                pn_Center.add(mh);
                pn_Center.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red),"Quần Áo"));
                pn_Center.validate();
            } else if (obj.toString().equalsIgnoreCase("Giày Dép")){
                GiayDep_Form mh = new GiayDep_Form();
                mh.doShow();
                mh.kh = kh;
                pn_Center.removeAll();
                pn_Center.add(mh);
                pn_Center.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red),"Giày Dép"));
                pn_Center.validate();
            } else if (obj.toString().equalsIgnoreCase("Phụ Kiện")){
                PhuKien_Form mh = new PhuKien_Form();
                mh.doShow();
                mh.kh = kh;
                pn_Center.removeAll();
                pn_Center.add(mh);
                pn_Center.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.red),"Phụ Kiện"));
                pn_Center.validate();
            }
            else {
                System.out.println("" + obj.toString().length());
            }

        }
    }
    public static void main(String[] args) {
        GD_Chinh gd = new GD_Chinh();
        gd.setVisible(true);
        //System.out.println("heloo121");
        gd.iterator();
        //System.out.println("heloo11");

    }
}
