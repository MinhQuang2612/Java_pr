package TableModel;

import entity.SanPham;


import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SP_TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7564752013607774627L;
	private List<SanPham> ds;
    String[] headers = {"Mã SP", "Tên SP", "Bảo Hành","Ngày SX","Tình Trạng",
            "Số Lượng","Đơn Vị Tính","Đơn Giá","Loại LK","Nhà SX","Images"};

    public SP_TableModel(List<SanPham> ds) {
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
        SanPham sp = ds.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sp.getMaSP();
            case 1:
                return sp.getTenSP();
            case 2:
                return sp.getBaoHanh();
            case 3:
                return sp.getNgaySX();
            case 4:
                return sp.getTinhTrang();
            case 5:
                return sp.getSoLuong();
            case 6:
                return sp.getDonViTinh();
            case 7:
                return sp.getDonGia();
            case 8:
                return sp.getLoaiSanPham().getTenLoai();
            case 9:
                return sp.getNhaSanXuat().getTenNhaSX();
            case 10:
                return sp.getiMages();
            default:
                return sp;
        }
    }
}
