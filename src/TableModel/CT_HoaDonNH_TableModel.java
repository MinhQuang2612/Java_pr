package TableModel;

import entity.CT_HoaDonNhapHang;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CT_HoaDonNH_TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3643059123220537124L;
	private List<CT_HoaDonNhapHang> ds;
    String[] headers = {"STT", "Tên Sản Phẩm", "Loại Sản Phẩm","Hãng SX","Số Lượng","Đơn Giá",
            "Bảo Hành","Thành Tiền"};

    public CT_HoaDonNH_TableModel(List<CT_HoaDonNhapHang> ds) {
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
        CT_HoaDonNhapHang cthdnh = ds.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return cthdnh.getSanPham().getTenSP();
            case 2:
                return cthdnh.getSanPham().getLoaiSanPham().getTenLoai();
            case 3:
                return cthdnh.getSanPham().getNhaSanXuat().getTenNhaSX();
            case 4:
                return cthdnh.getSoLuong();
            case 5:
                return cthdnh.getSanPham().getDonGia();
            case 6:
                return cthdnh.getSanPham().getBaoHanh();
            case 7:
                return cthdnh.getThanhTien();
            default:
                return cthdnh;
        }
    }
}

