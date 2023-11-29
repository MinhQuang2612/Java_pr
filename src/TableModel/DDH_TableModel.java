package TableModel;

import dao.CT_DonDatHang_DAO;
import entity.CT_DonDatHang;
import entity.DonDatHang;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DDH_TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1619624066909696145L;
	private List<DonDatHang> ds;
    String[] headers = {"Mã Đơn Hàng", "Mã Khách Hàng","Tên Khách Hàng", "Ngày Đặt Hàng","Phương Thức Thanh Toán","Tình Trạng",
            "Tổng Tiền"};

    public DDH_TableModel(List<DonDatHang> ds) {
        super();
        this.ds = ds;
    }

    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public int getRowCount() {
        return ds.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DonDatHang ddh = ds.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ddh.getMaDDH();
            case 1:
                return ddh.getKhachHang().getMaKH();
            case 2:
                return ddh.getKhachHang().getTenKH();
            case 3:
                return ddh.getNgayDatHang();
            case 4:
                return ddh.getPTTT();
            case 5:
                return ddh.getTinhTrang();
            case 6:
            {
                double tong = 0;
                CT_DonDatHang_DAO ctdhDao = new CT_DonDatHang_DAO();
                for (CT_DonDatHang ctdh: ctdhDao.getLS(ddh.getMaDDH())) {
                    tong += ctdh.getThanhTien();
                }
                return tong;
            }
            default:
                return ddh;
        }
    }
}
