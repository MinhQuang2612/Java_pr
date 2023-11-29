package TableModel;
import entity.NhaSanXuat;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class NSX_TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6209274031315758383L;
	private List<NhaSanXuat> ds;
    String [] headers = {"STT","Mã Nhà Sảm Xuất", "Tên Nhà Sản Xuất","Địa Chỉ"};

    public NSX_TableModel(List<NhaSanXuat> ds){
        super();
        this.ds = ds;
    }
    public String getColumnName(int column){
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
        NhaSanXuat nsx = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return rowIndex+1;
            case 1:
                return nsx.getMaNhaSX();
            case 2:
                return nsx.getTenNhaSX();
            case 3:
                return nsx.getDiaChi();
            default:
                return nsx;
        }


    }
}
