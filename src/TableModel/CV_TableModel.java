package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.ChucVu;

public class CV_TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7438128988031924793L;
	private List<ChucVu> ds;
    String [] headers = {"STT","Mã Chức Vụ", "Tên Chức Vụ"};

    public CV_TableModel(List<ChucVu> ds){
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
        ChucVu cv = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return rowIndex+1;
            case 1:
                return cv.getMaChucVu();
            case 2:
                return cv.getTenChucVu();
            default:
                return cv;
        }


    }
}

