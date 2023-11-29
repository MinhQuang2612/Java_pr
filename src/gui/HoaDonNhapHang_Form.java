package gui;

import TableModel.CT_GioHang_TableModel;
import TableModel.CT_HoaDonBH_tableModel;
import TableModel.CT_HoaDonNH_TableModel;
import dao.*;
import entity.CT_GioHang;
import entity.CT_HoaDonNhapHang;
import entity.HoaDonBanHang;
import entity.HoaDonNhapHang;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HoaDonNhapHang_Form extends JFrame {
    JLabel lblgth,lblMaHD,lblNgayXuat,lblNguoiLap,lblKhachHang,lblSoDT,lblDiaChi,
            lblTongTien,lblKhachTra,lblTienThua,lblTienBangChu,lblGhiChu,lblBan,lblThuNgan,lblKhach;
    JTextField txtMaHD,txtNgayXuat,txtNguoilap,txtKhachHang,txtSoDT,txtDiaChi,
            txtTongTien,txtKhachTra,txtTienThua,txtTienBangChu,txtGhiChu;
    String maHD = "HDNH004";
    static double tong = 1000000;
    static int tong1 = (int)tong;
    static String tongT = String.valueOf(tong1);
    String tienChu = "";
//    static String [] p =tongT.split(".",0);

    public HoaDonNhapHang_Form(){
    }
    public void doShow(){
        setSize(750,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Hóa Đơn Bán Hàng");
        Container cp = getContentPane();

        //North
        JPanel pnNorth = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        Box b = Box.createHorizontalBox();
        JPanel pnWest1 = new JPanel();
        pnWest1.setLayout(new BorderLayout());
//        pnWest1.setPreferredSize(new Dimension(100,100));
        JPanel pnCenter1 = new JPanel();
        pnCenter1.setLayout(new BorderLayout());

        JLabel lblImages = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/logoCH.png"));
//        lblImages.setPreferredSize(new Dimension(120,120));
        lblImages.setIcon(icon);
        pnWest1.add(lblImages,BorderLayout.CENTER);
        b.setPreferredSize(new Dimension(300,110));
        b.add(Box.createHorizontalStrut(100));

        b.add(pnWest1);
//        b.add(Box.createHorizontalStrut(10));

        Box a,a1,a2,a3,a4;
        a = Box.createVerticalBox();
//        a.setPreferredSize(new Dimension());
        a.add(Box.createVerticalStrut(15));
        a.add(a1 = Box.createHorizontalBox());
        a1.add(lblgth = new JLabel("- TAH COMPUTER - CHUYÊN CUNG CẤP CÁC LOẠI LINH KIỆN MÁY TÍNH "));
        a.add(Box.createVerticalStrut(5));
        a.add(a2 = Box.createHorizontalBox());
        a2.add(lblgth = new JLabel("- Địa Chỉ: 799 Nguyễn Kiệm, Phường 3, Quận Gò Vấp, TP.Hồ Chí Minh"));
        a.add(Box.createVerticalStrut(5));
        a.add(a3 = Box.createHorizontalBox());
        a3.add(lblgth = new JLabel("- Website: TAHComputer.com.vn | Fanpage: fb.com/TAHCompouter"));
        a.add(Box.createVerticalStrut(5));
        a.add(a4 = Box.createHorizontalBox());
        a4.add(lblgth = new JLabel("- Hotline: 0985345999 - 0985567999 - 0985999999"));
        a.add(Box.createVerticalStrut(5));
        pnCenter1.add(a);
        b.add(pnCenter1);
        b.add(Box.createHorizontalStrut(100));


        //Center
        JPanel pnCenter = new JPanel();
        JPanel pnNorthCen = new JPanel();
        JPanel pnCenterCen = new JPanel();
        JPanel pnSouthCen = new JPanel();
        pnSouthCen.setLayout(new BorderLayout());
        pnCenter.setLayout(new BorderLayout());
        //Tiêu đề
        JLabel lblTieuDe = new JLabel("HÓA ĐƠN NHẬP HÀNG");
        lblTieuDe.setFont(new Font("Arial",Font.BOLD,18));
        pnNorthCen.add(lblTieuDe);

        //Thông Tin
        Box bCen,bCen1,bCen2,bCen3,bCen4,bCen5,bCen6;
        bCen = Box.createVerticalBox();
        bCen.setPreferredSize(new Dimension(600,140));
        bCen.add(bCen1 = Box.createHorizontalBox());
        bCen1.add(lblMaHD = new JLabel("Mã Hóa Đơn:"));
        bCen1.add(Box.createHorizontalStrut(30));
        bCen1.add(txtMaHD = new JTextField());
//        bCen.add(Box.createVerticalStrut(5));
        bCen.add(bCen2 = Box.createHorizontalBox());
        bCen2.add(lblNgayXuat = new JLabel("Ngày Xuất Hóa Đơn:"));
        bCen2.add(Box.createHorizontalStrut(30));
        bCen2.add(txtNgayXuat = new JTextField());
//        bCen.add(Box.createVerticalStrut(5));
        bCen.add(bCen3 = Box.createHorizontalBox());
        bCen3.add(lblNguoiLap = new JLabel("Người Lập Hóa Đơn:"));
        bCen3.add(Box.createHorizontalStrut(30));
        bCen3.add(txtNguoilap = new JTextField());
//        bCen.add(Box.createVerticalStrut(5));
        bCen.add(bCen4 = Box.createHorizontalBox());
        bCen4.add(lblKhachHang = new JLabel("Nhà Cung Cấp:"));
        bCen4.add(Box.createHorizontalStrut(30));
        bCen4.add(txtKhachHang = new JTextField());
//        bCen.add(Box.createVerticalStrut(5));
        bCen.add(bCen5 = Box.createHorizontalBox());
        bCen5.add(lblSoDT = new JLabel("Số Điện Thoại:"));
        bCen5.add(Box.createHorizontalStrut(30));
        bCen5.add(txtSoDT = new JTextField());
//        bCen.add(Box.createVerticalStrut(5));
        bCen.add(bCen6 = Box.createHorizontalBox());
        bCen6.add(lblDiaChi = new JLabel("Địa Chỉ"));
        bCen6.add(Box.createHorizontalStrut(30));
        bCen6.add(txtDiaChi = new JTextField());

        lblMaHD.setPreferredSize(lblNguoiLap.getPreferredSize());
        lblNgayXuat.setPreferredSize(lblNguoiLap.getPreferredSize());
        lblKhachHang.setPreferredSize(lblNguoiLap.getPreferredSize());
        lblSoDT.setPreferredSize(lblNguoiLap.getPreferredSize());
        lblDiaChi.setPreferredSize(lblNguoiLap.getPreferredSize());
        lblNguoiLap.setPreferredSize(lblNguoiLap.getPreferredSize());



        //Tính tiền
        JPanel pnTable = new JPanel();
        List<CT_HoaDonNhapHang> ds = new ArrayList<>();
        CT_HoaDonNH_TableModel tableModel1 = new CT_HoaDonNH_TableModel(ds);

        JTable table1 = new JTable(tableModel1);
        JScrollPane sc1 = new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc1.setPreferredSize(new Dimension(600,80));
        table1.setBackground(Color.WHITE);
        sc1.setBackground(Color.WHITE);

        JPanel pnTien = new JPanel();
        Box bc,bc1,bc2,bc3,bc4,bc5;
        bc = Box.createVerticalBox();
        bc.setPreferredSize(new Dimension(600,130));
        bc.add(bc1 = Box.createHorizontalBox());
        bc1.add(Box.createHorizontalStrut(250));
        bc1.add(lblTongTien = new JLabel("Tổng Tiền:"));
        bc1.add(Box.createHorizontalStrut(10));
        bc1.add(txtTongTien = new JTextField());
//        bCen.add(Box.createVerticalStrut(5));
//        bc.add(bc2 = Box.createHorizontalBox());
//        bc2.add(Box.createHorizontalStrut(250));
//        bc2.add(lblKhachTra = new JLabel("Tiền Khách Trả:"));
//        bc2.add(Box.createHorizontalStrut(10));
//        bc2.add(txtKhachTra = new JTextField());
////        bCen.add(Box.createVerticalStrut(5));
//        bc.add(bc3 = Box.createHorizontalBox());
//        bc3.add(Box.createHorizontalStrut(250));
//        bc3.add(lblTienThua = new JLabel("Tiền Trả Lại:"));
//        bc3.add(Box.createHorizontalStrut(10));
//        bc3.add(txtTienThua = new JTextField());
//        bCen.add(Box.createVerticalStrut(5));
        bc.add(bc4 = Box.createHorizontalBox());
        bc4.add(Box.createHorizontalStrut(250));
        bc4.add(lblTienBangChu = new JLabel("Tiền Bằng Chữ:"));
        bc4.add(Box.createHorizontalStrut(10));
        bc4.add(txtTienBangChu = new JTextField());
//        bCen.add(Box.createVerticalStrut(5));
        bc.add(bc5 = Box.createHorizontalBox());
        bc5.add(Box.createHorizontalStrut(250));
        bc5.add(lblGhiChu = new JLabel("Ghi Chú:"));
        bc5.add(Box.createHorizontalStrut(10));
        bc5.add(txtGhiChu = new JTextField());
        bc.add(Box.createVerticalStrut(30));

        pnTable.add(sc1);
        pnTien.add(bc);


        pnSouthCen.setLayout(new BorderLayout());
        pnSouthCen.add(pnTable,BorderLayout.NORTH);
        pnSouthCen.add(pnTien,BorderLayout.CENTER);

        lblTongTien.setPreferredSize(lblTienBangChu.getPreferredSize());
        lblGhiChu.setPreferredSize(lblTienBangChu.getPreferredSize());

        txtTongTien.setBorder(null);
        txtTongTien.setEditable(false);
        txtTongTien.setForeground(Color.BLACK);
        txtTienBangChu.setBorder(null);
        txtTienBangChu.setEditable(false);
        txtGhiChu.setBorder(null);
        txtGhiChu.setEditable(false);

        DecimalFormat df = new DecimalFormat("#,###.00 VND");
        txtTongTien.setText(String.valueOf(df.format(tong)));
        System.out.println(tienChu);
        txtTienBangChu.setText(tienChu);
        txtTienBangChu.setFont(new Font("Arial",Font.ITALIC,14));


        bCen.setBackground(Color.WHITE);
        bCen1.setBackground(Color.WHITE);
        pnCenterCen.setBackground(Color.WHITE);
        pnNorthCen.setBackground(Color.WHITE);
        pnSouthCen.setBackground(Color.WHITE);
        pnTable.setBackground(Color.WHITE);
        pnTien.setBackground(Color.WHITE);


        txtMaHD.setBorder(null);
        txtMaHD.setEditable(false);
        txtDiaChi.setBorder(null);
        txtDiaChi.setEditable(false);
        txtKhachHang.setBorder(null);
        txtKhachHang.setEditable(false);
        txtNgayXuat.setBorder(null);
        txtNgayXuat.setEditable(false);
        txtNguoilap.setBorder(null);
        txtNguoilap.setEditable(false);
        txtSoDT.setBorder(null);
        txtSoDT.setEditable(false);

        pnCenterCen.add(bCen);


        pnCenter.add(pnNorthCen,BorderLayout.NORTH);
        pnCenter.add(pnCenterCen,BorderLayout.CENTER);
        pnCenter.add(pnSouthCen,BorderLayout.SOUTH);
        //pnSouth
        JPanel pnSouth = new JPanel();

        Box bs = Box.createHorizontalBox();
        bs.add(lblBan = new JLabel("Người Nhận Hàng"));
        bs.add(Box.createHorizontalStrut(300));
//        bs.add(lblThuNgan = new JLabel("Thu Ngân"));
//        bs.add(Box.createHorizontalStrut(150));
        bs.add(lblKhach = new JLabel("Nhà Cung Cấp"));
        pnSouth.setPreferredSize(new Dimension(0,80));
        pnSouth.add(bs);
        pnSouth.setBackground(Color.WHITE);


        //Dữ liệu
        HoaDonNhapHang_DAO nhDao = new HoaDonNhapHang_DAO();
        HoaDonNhapHang hdnh = nhDao.TimKiemMa(maHD);
        txtMaHD.setText(maHD);
        txtNgayXuat.setText(hdnh.getNgayLapHD().toString());
        txtNguoilap.setText(hdnh.getNhanVien().getTenNV());
        txtKhachHang.setText(hdnh.getNhaCungCap().getTenNhaCC());
        txtSoDT.setText(hdnh.getNhaCungCap().getSoDT());
        txtDiaChi.setText(hdnh.getNhaCungCap().getDiaChi());
        CT_HoaDonNH_DAO ct_hoaDonNH_dao = new CT_HoaDonNH_DAO();
        table1.setModel(new CT_HoaDonNH_TableModel(ct_hoaDonNH_dao.getLS(maHD)));


        pnNorth.setBackground(Color.WHITE);
        pnWest1.setBackground(Color.WHITE);
        pnCenter1.setBackground(Color.WHITE);
        pnNorth.add(b);
        cp.add(pnNorth,BorderLayout.NORTH);
        cp.add(pnCenter,BorderLayout.CENTER);
        cp.add(pnSouth,BorderLayout.SOUTH);
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
    public static void main(String[] args) {
        HoaDonNhapHang_Form hd = new HoaDonNhapHang_Form();
        hd.tienChu = numberToString(new BigDecimal( tongT));
        hd.doShow();
        hd.setVisible(true);

        System.out.println("Read: 10.000.000 - " +numberToString(new BigDecimal( "10000000")));
        System.out.println("Read: 10.000.000 - " +numberToString(new BigDecimal( "5900000")));
    }
}
