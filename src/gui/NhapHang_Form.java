package gui;

import TableModel.CT_HoaDonNH_TableModel;
import TableModel.SP_TableModel;
import com.toedter.calendar.JDateChooser;
import dao.*;
import entity.CT_HoaDonNhapHang;
import entity.HoaDonNhapHang;
import entity.NhaCungCap;
import entity.NhanVien;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class NhapHang_Form  extends JPanel {
    JPanel pnNorth,pnCenter,pnNorth2,pnSouth;
    JLabel lblMaHD,lblNgayXuat,lblMaNV,lblTenNV,lblMaNCC,lblTenNCC,lblSDT,lblMail,lblDiaChi,lblPT;
    JDateChooser NgayXuatHD;
    JTextField txtMahD,txtMaNV,txtTenNV,txtMaNCC,txtTenNCC,txtSDT,txtMail,txtDiaChi;
    JButton btnCapNhatSL,btnXoa,btnDatHang,btnQuayLai,btnChonHang,btnNhap;
    JRadioButton rbTT,rbOL;
    NhanVien nv;
    JTable table1;
    String ma = "";
    double tongTien = 0;
    public NhapHang_Form(){

    }
    public void doShow(){
        this.setLayout(new BorderLayout());
        //pn_North
        pnNorth = new JPanel();
        //pnNorth1 - Tiêu Đề
        JPanel pnNorth1 = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("HÓA ĐƠN NHẬP HÀNG");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 18));
        lblTieuDe.setForeground(Color.BLUE);
        pnNorth1.add(lblTieuDe);
        pnNorth.add(pnNorth1,BorderLayout.NORTH);
        //pnNorth2 - Thông Tin
        pnNorth2 = new JPanel();
        Box bx,bxHoaDon,bxNhanVien,bxKhachHang,b1,b2,b3;
        bx = Box.createVerticalBox();
        bx.setPreferredSize(new Dimension(900,180));
        bx.add(bxHoaDon = Box.createHorizontalBox());
        bxHoaDon.add(lblMaHD = new JLabel("Mã Hóa Đơn: "));
        bxHoaDon.add(txtMahD = new JTextField(30));
        txtMahD.setPreferredSize(new Dimension(90,20));
        bxHoaDon.add(Box.createHorizontalStrut(30));
        bxHoaDon.add(lblNgayXuat = new JLabel("Ngày Xuất Hóa Đơn:  "));
        NgayXuatHD = new JDateChooser();
        NgayXuatHD.setSize(new Dimension(30,20));
        NgayXuatHD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        NgayXuatHD.setDateFormatString("yyyy-MM-dd");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = java.sql.Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            NgayXuatHD.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bxHoaDon.add(NgayXuatHD);
        System.out.println("Ngày mới:" +NgayXuatHD.getDate());
        bx.add(Box.createVerticalStrut(10));
        bx.add(bxNhanVien = Box.createHorizontalBox());
        bxNhanVien.add(lblMaNV = new JLabel("Mã Nhân Viên: "));
        bxNhanVien.add(txtMaNV = new JTextField(30));
        bxNhanVien.add(Box.createHorizontalStrut(30));
        bxNhanVien.add(lblTenNV = new JLabel("Tên Nhân Viên: "));
        bxNhanVien.add(txtTenNV = new JTextField(30));
        bx.add(Box.createVerticalStrut(10));
        bx.add(bxKhachHang = Box.createVerticalBox());
        bxKhachHang.setPreferredSize(new Dimension(900,100));
        bxKhachHang.add(b1 = Box.createHorizontalBox());
        b1.add(lblMaNCC = new JLabel("Mã Nhà Cung Cấp: "));
        b1.add(txtMaNCC = new JTextField(30));
        b1.add(Box.createHorizontalStrut(30));
        b1.add(lblTenNCC = new JLabel("Tên Nhà Cung Cấp: "));
        b1.add(txtTenNCC = new JTextField(30));
        txtTenNCC.addMouseListener(new MouseListener() {
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
                NhaCungCap_DAO nccDao = new NhaCungCap_DAO();
                if(nccDao.TimKiemTen(txtTenNCC.getText().trim()) != null){
                    NhaCungCap ncc = nccDao.TimKiemTen(txtTenNCC.getText().trim());
                    txtMaNCC.setText(ncc.getMaNhaCC());
                    txtSDT.setText(ncc.getSoDT());
                    txtDiaChi.setText(ncc.getDiaChi());
                }else{
                    JOptionPane.showMessageDialog(null,"Chưa có thông tin nhà cung cấp");
                    txtMaNCC.setText("");
                    txtDiaChi.setText("");
                    txtSDT.setText("");
                    txtTenNCC.requestFocus();
                }
            }
        });
        bxKhachHang.add(Box.createVerticalStrut(10));
        bxKhachHang.add(b2 = Box.createHorizontalBox());
        b2.add(lblSDT = new JLabel("Số Điện Thoại: "));
        b2.add(txtSDT = new JTextField(30));
        b2.add(Box.createHorizontalStrut(30));
        b2.add(lblDiaChi = new JLabel("Địa Chỉ: "));
        b2.add(txtDiaChi = new JTextField(30));
        bxKhachHang.add(Box.createVerticalStrut(10));
        bxKhachHang.add(b3 = Box.createHorizontalBox());
        b3.add(btnNhap = new JButton("Nhập Hàng"));
        btnNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_menu/import.png")));

//        bxKhachHang.setBorder(new TitledBorder("Thông Tin Khách Hàng"));
        pnNorth2.add(bx);
        pnNorth.add(pnNorth2,BorderLayout.CENTER);

        lblMaHD.setPreferredSize(lblNgayXuat.getPreferredSize());
        lblMaNV.setPreferredSize(lblNgayXuat.getPreferredSize());
        lblMaNCC.setPreferredSize(lblNgayXuat.getPreferredSize());
        lblDiaChi.setPreferredSize(lblNgayXuat.getPreferredSize());
        lblSDT.setPreferredSize(lblNgayXuat.getPreferredSize());
        lblTenNCC.setPreferredSize(lblNgayXuat.getPreferredSize());
        lblTenNV.setPreferredSize(lblNgayXuat.getPreferredSize());

        txtMaNV.setText(nv.getMaNV());
        txtMaNV.setEditable(false);
        txtTenNV.setText(nv.getTenNV());
        txtTenNV.setEditable(false);
        txtMahD.setEditable(false);
        //pnCenter
        TitledBorder tileDanhSach = new TitledBorder("Danh sách sản phẩm");
        TitledBorder tileDanhSachSP = new TitledBorder("Danh sách sản phẩm nhập");

        //pnSouth.setPreferredSize(new Dimension(1030,60));
        pnCenter = new JPanel();
        JPanel pnCenter_C = new JPanel();
        pnCenter.setLayout(new BorderLayout());

        CT_HoaDonNH_DAO ctnhDao = new CT_HoaDonNH_DAO();
        List<CT_HoaDonNhapHang> ls = new ArrayList<>();
        CT_HoaDonNH_TableModel tableModel = new CT_HoaDonNH_TableModel(ls);
        table1 = new JTable(tableModel);
        //Sự kiện Table

        JScrollPane sc1 = new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc1.setPreferredSize(new Dimension(900,220));

        JPanel pnTable = new JPanel();

        btnCapNhatSL = new JButton("Lấy DS Sản Phẩm");
        btnCapNhatSL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(new CT_HoaDonNH_TableModel(ctnhDao.getLS(ma)));
            }
        });
        btnXoa = new JButton("Xóa Sản Phẩm");
        btnXoa.setIcon(new ImageIcon(getClass().getResource("/images_menu/xoa.png")));
        btnCapNhatSL.setIcon(new ImageIcon(getClass().getResource("/images_menu/sua.png")));


        pnCenter.setPreferredSize(new Dimension(900,250));
        pnCenter_C.setBorder(tileDanhSachSP);
        pnCenter_C.setLayout(new BorderLayout());
        pnCenter_C.add(pnTable,BorderLayout.CENTER);pnCenter.add(pnCenter_C,BorderLayout.CENTER);
        pnTable.add(sc1);

        //pnSouth

        pnSouth = new JPanel();
        btnDatHang = new JButton("Xuất Hóa Đơn");
        btnQuayLai = new JButton("Quay Lại");
        btnQuayLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                TrangChu_Form tc = new TrangChu_Form();
                add(tc);
                validate();
            }
        });
        btnDatHang.setIcon(new ImageIcon(getClass().getResource("/images_menu/thanhtoan.png")));
        btnQuayLai.setIcon(new ImageIcon(getClass().getResource("/images_menu/quaylai.png")));
        pnSouth.setBorder(new TitledBorder("Tác Vụ"));

        Box d = Box.createHorizontalBox();
        d.add(btnDatHang);
        d.add(Box.createHorizontalStrut(80));
        d.add(btnCapNhatSL);
//        d.add(Box.createHorizontalStrut(80));
//        btnCapNhatSL.setEnabled(false);
//        btnChonHang = new JButton("Chọn Mua");
//        btnChonHang.setIcon(new ImageIcon(getClass().getResource("/images_menu/datmua.png")));
//        btnChonHang.setEnabled(false);
//        d.add(btnChonHang);
//        btnXoa.setEnabled(false);
//        d.add(Box.createHorizontalStrut(80));
//        d.add(btnXoa);
        d.add(Box.createHorizontalStrut(80));
        d.add(btnQuayLai);


        pnSouth.add(d);


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = formatter.format(NgayXuatHD.getDate());
        btnNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HoaDonNhapHang_DAO hdnhDao = new HoaDonNhapHang_DAO();
                NhanVien_DAO nvDao = new NhanVien_DAO();
                NhaCungCap_DAO nccDao = new NhaCungCap_DAO();
                HoaDonNhapHang hdnh = new HoaDonNhapHang(txtMahD.getText().trim(), java.sql.Date.valueOf(dateTime));
                NhanVien nv = nvDao.TimKiemMa(txtMaNV.getText().trim());
                NhaCungCap ncc = nccDao.TimKiemMa(txtMaNCC.getText().trim());
                hdnh.setNhanVien(nv);
                hdnh.setNhaCungCap(ncc);
                if(hdnhDao.addHoaDonNH(hdnh)){
                    try {
                        ma = hdnhDao.getMa();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    NhapSanPham_Form nlk = new NhapSanPham_Form();
                    try {
                        nlk.maHDNH = hdnhDao.getMa();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    nlk.doshow();
                    nlk.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Lỗi!");
                }

            }
        });

        table1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                table1.setModel(new CT_HoaDonNH_TableModel(ctnhDao.getLS("HDNH007")));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                table1.setModel(new CT_HoaDonNH_TableModel(ctnhDao.getLS("HDNH007")));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                table1.setModel(new CT_HoaDonNH_TableModel(ctnhDao.getLS("HDNH007")));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                table1.setModel(new CT_HoaDonNH_TableModel(ctnhDao.getLS("HDNH007")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                table1.setModel(new CT_HoaDonNH_TableModel(ctnhDao.getLS("HDNH007")));
            }
        });

        btnDatHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CT_HoaDonNH_DAO ctDao = new CT_HoaDonNH_DAO();
                for (CT_HoaDonNhapHang ct: ctDao.getLS(ma)) {
                    tongTien += ct.getThanhTien();
                }
                int tongt = (int)tongTien;
                String tienC = String.valueOf(tongt);
                System.out.println(ma);
                HoaDonNhapHang_Form hd = new HoaDonNhapHang_Form();
                hd.maHD = ma;
                hd.tienChu = numberToString(new BigDecimal(tienC));
                hd.tong = tongTien;
                hd.doShow();
                setVisible(false);
                hd.setVisible(true);
            }
        });

        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);

    }
    public static String formatNumberForRead(double number) {
        NumberFormat nf = NumberFormat.getInstance();
        String temp = nf.format(number);
        String strReturn = "";
        int slen = temp.length();
        for (int i = 0; i < slen; i++) {
            if (String.valueOf(temp.charAt(i)).equals("."))
                break;
            else if (Character.isDigit(temp.charAt(i))) {
                strReturn += String.valueOf(temp.charAt(i));
            }
        }
        return strReturn;

    }

//    public static void main(String[] args) {
//        System.out.println("Read: 10.000.000 - " +numberToString(new BigDecimal( "10000000")));
//        System.out.println("Read: 5.123.894.500 - " +numberToString(new BigDecimal( "5123894500")));
//        System.out.println("Read: 1.235.123.894.500 - " +numberToString(new BigDecimal( "12355123894500")));
//    }

    public static String numberToString(BigDecimal number) {
        String sNumber = number.toString();
        // Tao mot bien tra ve
        String sReturn = "";
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Lat nguoc chuoi nay lai
        String sNumber1 = "";
        for (int i = iLen - 1; i >= 0; i--) {
            sNumber1 += sNumber.charAt(i);
        }
        // Tao mot vong lap de doc so
        // Tao mot bien nho vi tri cua sNumber
        int iRe = 0;
        do {
            // Tao mot bien cat tam
            String sCut = "";
            if (iLen > 3) {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + 3);
                sReturn = Read(sCut, iRe) + sReturn;
                iRe++;
                iLen -= 3;
            } else {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + iLen);
                sReturn = Read(sCut, iRe) + sReturn;
                break;
            }
        } while (true);
        if(sReturn.length() > 1){
            sReturn = sReturn.substring(0,1).toUpperCase() + sReturn.substring(1);
        }
        sReturn = sReturn + "đồng";

        // xu ly lan cuoi voi 220 000 tỷ 000 000 000 000 000 HUTTV ADDED 3 OCT
        if(sNumber.length()>12)
        {
            // tren gia tri can xu ly, kiem tra xem don vi TY co = 000 khong
            int begin = sNumber.length()-9;
            String donviTy = sNumber.substring(begin-3, (begin-3)+3);
            if(donviTy.equals("000"))
            {
                sReturn = sReturn.replaceFirst("ngàn", "ngàn tỷ");
            }
        }


        return sReturn;
    }

    // Khoi tao ham Read
    public static String Read(String sNumber, int iPo) {
        // Tao mot bien tra ve
        String sReturn = "";
        // Tao mot bien so
        String sPo[] = { "", "ngàn" + " ",
                "triệu" + " ", "tỷ" + " ",  "ngàn" + " "};
        String sSo[] = { "không" + " ", "một" + " ",
                "hai" + " ", "ba" + " ",
                "bốn" + " ", "năm" + " ",
                "sáu" + " ", "bảy" + " ",
                "tám" + " ", "chín" + " " };
        String sDonvi[] = { "", "mươi" + " ",
                "trăm" + " " };
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Tao mot bien nho vi tri doc
        int iRe = 0;
        // Tao mot vong lap de doc so
        for (int i = 0; i < iLen; i++) {
            String sTemp = "" + sNumber.charAt(i);
            int iTemp = Integer.parseInt(sTemp);
            // Tao mot bien ket qua
            String sRead = "";
            // Kiem tra xem so nhan vao co = 0 hay ko
            if (iTemp == 0) {
                switch (iRe) {
                    case 0:
                        break;// Khong lam gi ca
                    case 1: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0) {
                            sRead = "lẻ" + " ";
                        }
                        break;
                    }
                    case 2: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0
                                && Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "không trăm" + " ";
                        }
                        break;
                    }
                }
            } else if (iTemp == 1) {
                switch (iRe) {
                    case 1:
                        sRead = "mười" + " ";
                        break;
                    default:
                        sRead = "một" + " " + sDonvi[iRe];
                        break;
                }
            } else if (iTemp == 5) {
                switch (iRe) {
                    case 0: {
                        if(sNumber.length() <= 1){
                            sRead = "năm" + " ";
                        }
                        else if (Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "lăm" + " ";
                        } else
                            sRead = "năm" + " ";
                        break;
                    }
                    default:
                        sRead = sSo[iTemp] + sDonvi[iRe];
                }
            } else {
                sRead = sSo[iTemp] + sDonvi[iRe];
            }

            sReturn = sRead + sReturn;
            iRe++;
        }
        if (sReturn.length() > 0) {
            sReturn += sPo[iPo];
        }
        // xu ly lan cuoi voi 220 000 tỷ 000 000 000 000 000
        if(sNumber.length()>12)
        {
            // tren gia tri can xu ly, kiem tra xem don vi TY co = 000 khong
            System.out.println(sNumber.substring(11, 8));
        }
        return sReturn;
    }

    public static String getRomanNumerals(int Int) {
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        String res = "";
        for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
            int matches = Int/entry.getValue();
            res += repeat(entry.getKey(), matches);
            Int = Int % entry.getValue();
        }
        return res;
    }
    public static String repeat(String s, int n) {
        if(s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
