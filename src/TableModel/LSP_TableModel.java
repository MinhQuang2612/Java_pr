package TableModel;

import entity.LoaiSanPham;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LSP_TableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7438128988031924793L;
	private List<LoaiSanPham> ds;
    String [] headers = {"STT","Mã Loại Sản Phẩm", "Tên Loại Sản Phẩm"};

    public LSP_TableModel(List<LoaiSanPham> ds){
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
        LoaiSanPham lsp = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return rowIndex+1;
            case 1:
                return lsp.getMaLoai();
            case 2:
                return lsp.getTenLoai();
            default:
                return lsp;
        }


    }
}

