package TableModel;

import entity.NhanVien;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class NV_TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4477699205569192872L;
	private List<NhanVien> ds;
    String[] headers = {"Mã NV", "Tên NV", "Giới Tính", "Năm Sinh", "Điện Thoại", "Email", "Số CCCD", "Địa Chỉ",
            "Chức Vụ", "Ngày Vào Làm", "Images"};

    public NV_TableModel(List<NhanVien> ds) {
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
        NhanVien nv = ds.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return nv.getMaNV();
            case 1:
                return nv.getTenNV();
            case 2:
                return nv.getGioiTinh();
            case 3:
                return nv.getNgaySinh();
            case 4:
                return nv.getDienThoai();
            case 5:
                return nv.getEmail();
            case 6:
                return nv.getCCCD();
            case 7:
                return nv.getDiaChi();
            case 8:
                return nv.getChucVu().getTenChucVu();
            case 9:
                return nv.getNgayVaoLam();
            case 10:
                return nv.getiMages();
            default:
                return nv;
        }
    }
}