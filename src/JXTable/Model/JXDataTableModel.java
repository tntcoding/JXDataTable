package JXTable.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class JXDataTableModel extends javax.swing.table.DefaultTableModel{

    Class columns[];
    int numcols=0;
    int numrows=0;

    public JXDataTableModel(Object[][] data, Object[] columnNames) {
        super(data,columnNames);
    }

    public JXDataTableModel(Object[] columnNames, int rowCount) {
        super(columnNames,rowCount);
    }

    public JXDataTableModel(int rowCount, int columnCount) {
        super(rowCount,columnCount);
    }

    public JXDataTableModel(ResultSet rs, Class columns[]) {
        this(rs);
        this.columns=columns;
    }

    public JXDataTableModel(ResultSet rs) {
        this.setData(rs);
    }

    public JXDataTableModel() {
        super();
    }

    public Class[] getColumns() {
        return columns;
    }

    public void setColumns(Class[] columns) {
        this.columns = columns;
    }

    public void setClassforColumn(int col,Class colclass){
        this.columns[col]=colclass;
    }

    public int getNumRows(){
        return this.numrows;
    }

    @Override
    public int getColumnCount() {
        return this.numcols;
    }

    @Override
    public String getColumnName(int column) {
        return super.getColumnName(column);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return this.columns == null ? Object.class:this.columns[columnIndex]==null ? Object.class:this.columns[columnIndex];
    }

    private void setData(ResultSet rs){
        try {
            Object[] data = new Object[rs.getMetaData().getColumnCount()];
            columns = new Class[data.length];
            this.numcols=data.length;
            for (int i = 0; i < data.length; i++) {
                columns[i]=Class.forName(rs.getMetaData().getColumnClassName(i+1));
            }
            while(rs.next()){
                this.numrows+=1;
                for (int i = 1; i <= data.length; i++) {
                    data[i-1]=rs.getObject(i);
                }
                this.addRow(data);
            }
            data=null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JXDataTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JXDataTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeRow(int row) {
        super.removeRow(row);
    }

    @Override
    public Object getValueAt(int row, int column) {
        return super.getValueAt(row, column);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
    }

}