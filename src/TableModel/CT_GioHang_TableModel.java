package TableModel;

import entity.CT_GioHang;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CT_GioHang_TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3960831260425895293L;
	private List<CT_GioHang> ds;
    String[] headers = {"STT", "Tên Sản Phẩm", "Loại Sản Phẩm","Hãng SX","Số Lượng","ĐƠn Giá",
            "Bảo Hành","Thành Tiền"};

    public CT_GioHang_TableModel(List<CT_GioHang> ds) {
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
        CT_GioHang ctgh = ds.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return ctgh.getSanPham().getTenSP();
            case 2:
                return ctgh.getSanPham().getLoaiSanPham().getTenLoai();
            case 3:
                return ctgh.getSanPham().getNhaSanXuat().getTenNhaSX();
            case 4:
                return ctgh.getSoLuong();
            case 5:
                return ctgh.getSanPham().getDonGia();
            case 6:
                return ctgh.getSanPham().getBaoHanh();
            case 7:
                return ctgh.getThanhTien();
            default:
                return ctgh;
        }
    }
}
